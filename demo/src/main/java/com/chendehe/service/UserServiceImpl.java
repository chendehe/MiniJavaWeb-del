package com.chendehe.service;

import com.chendehe.common.ErrorCode;
import com.chendehe.common.MyConstant;
import com.chendehe.common.enums.FileType;
import com.chendehe.common.enums.GenderEnum;
import com.chendehe.dao.StudentDao;
import com.chendehe.dao.UserDao;
import com.chendehe.entity.UserEntity;
import com.chendehe.exception.ValidationException;
import com.chendehe.service.sheet.ForkJoinExcel;
import com.chendehe.service.sheet.SheetFactory;
import com.chendehe.util.DataCheck;
import com.chendehe.util.DataConvert;
import com.chendehe.util.IdGenerator;
import com.chendehe.vo.Page;
import com.chendehe.vo.PageResult;
import com.chendehe.vo.UserVo;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  private UserDao userDao;
  private StudentDao studentDao;

  @Autowired
  public UserServiceImpl(UserDao userDao, StudentDao studentDao) {
    this.userDao = userDao;
    this.studentDao = studentDao;
  }

  @Override
  public PageResult<UserVo> findAll(Page page) {

    PageResult<UserVo> result = new PageResult<>();
    List<UserEntity> userList = userDao.findAll();
    List<UserVo> userVoList = Lists.newArrayList();

    for (UserEntity user : userList) {
      userVoList.add(convertEntityToVo(user));
    }
    result.setList(userVoList);
    result.setTotalNum(userDao.totalNum());
    result.setPageSize(page.getPageSize());
    result.setCurrentPage(page.getCurrentPage());
    return result;
  }

  @Override
  public UserVo findOne(String id) {
    DataCheck.checkTrimStrEmpty(id, ErrorCode.PARAM_EMPTY, "id");
    return convertEntityToVo(userDao.findOne(id));
  }

  @Transactional
  @Override
  public UserVo save(UserVo vo) {
    //data bind if need validator
    //DataBinder binder = new DataBinder(vo);
    //binder.setValidator(new UserValidator());
    //binder.validate();
    vo.setId(IdGenerator.get());

    checkInputData(vo);

    UserEntity entity = convertVoToEntitySave(vo);
    userDao.save(entity);

    return vo;
  }

  @Transactional
  @Override
  public UserVo update(UserVo vo) {

    checkInputData(vo);

    UserEntity user = convertVoToEntityUpdate(vo);

    userDao.update(user);
    return vo;
  }

  @Transactional
  @Override
  public void delete(String id) {
    DataCheck.checkTrimStrEmpty(id, ErrorCode.PARAM_EMPTY, "id");
    userDao.delete(id);
  }

  @Transactional
  @Override
  public void upload(MultipartFile file) {
    long start = System.currentTimeMillis();
    LOGGER.info("[UserServiceImpl] start:{}", start);

    //屏障，加上主线程(需要获取结果，可以去掉)
    final CyclicBarrier barrier = new CyclicBarrier(MyConstant.SHEET_NUMBER + 1);
    final ExecutorService exe = Executors.newFixedThreadPool(MyConstant.SHEET_NUMBER);

    validateFileType(file);

    try {
      //Excel文件工厂
      Workbook wb = WorkbookFactory.create(file.getInputStream());

      long wbTime = System.currentTimeMillis();
      LOGGER.info("[UserServiceImpl] Create wb:{}", wbTime - start);

      //解析每个sheet
      List<Future> futures = Lists.newArrayList();
      for (int i = 0; i < MyConstant.SHEET_NUMBER; i++) {
        futures.add(parseThread(exe, barrier, wb, i));
      }

      //主线程
      barrierWait(barrier);

      //循环结果，处理异常
      for (Future future : futures) {
        future.get();
      }

    } catch (IOException | InvalidFormatException | InterruptedException | ExecutionException e) {
      LOGGER.error("[UserServiceImpl] {}", e);
      throw new ValidationException(ErrorCode.EXCEL_PARSE_ERROR);
    } finally {
      //reset栅栏
      barrier.reset();
      //尝试关闭，但不一定关闭,isShutdown标志位变成true
      if (!exe.isShutdown()) {
        exe.shutdownNow();
      }
    }

    long end = System.currentTimeMillis();
    LOGGER.info("[UserServiceImpl] Total end:{}", end - start);
  }

  @Override
  public void downLoad(String id, String path) {

    long start = System.currentTimeMillis();
    LOGGER.info("[UserServiceImpl] start:{}", start);

    Future<Workbook> result = new ForkJoinPool().submit(
        new ForkJoinExcel(
            userDao.findAll(), studentDao.findAll()
        ));

    // 第六步，将文件存到指定位置
    Workbook wb;
    try {
      if (null == (wb = result.get())) {
        return;
      }
    } catch (InterruptedException | ExecutionException e) {
      LOGGER.error("[UserServiceImpl] {}", e);
      throw new ValidationException(ErrorCode.EXCEL_DOWNLOAD_FAILED);
    }

    try (OutputStream os = new FileOutputStream(path)) {
      wb.write(os);
    } catch (IOException e) {
      LOGGER.error("[UserServiceImpl] {}", e);
      throw new ValidationException(ErrorCode.EXCEL_DOWNLOAD_FAILED);
    }

    long end = System.currentTimeMillis();
    LOGGER.info("[UserServiceImpl] Total end:{}", end - start);
  }

  /**
   * entity 转为 vo.
   *
   * @param user entity
   * @return vo
   */
  private UserVo convertEntityToVo(UserEntity user) {
    UserVo userVo = new UserVo();
    userVo.setId(user.getId());
    userVo.setName(user.getName());
    userVo.setGender(user.getGender());
    userVo.setBirthday(user.getBirthday());
    userVo.setAddress(user.getAddress());
    return userVo;
  }

  /**
   * vo 转为 entity.
   *
   * @param vo UserVo
   * @return UserEntity
   */
  private UserEntity convertVoToEntitySave(UserVo vo) {
    UserEntity user = new UserEntity();

    convertVoToEntity(vo, user);

    user.setCreateTime(new Date());
    return user;
  }

  /**
   * vo 转为更新后的 entity.
   *
   * @param vo UserVo
   * @return UserEntity
   */
  private UserEntity convertVoToEntityUpdate(UserVo vo) {
    UserEntity user = new UserEntity();

    convertVoToEntity(vo, user);

    user.setUpdateTime(new Date());
    return user;
  }

  private void convertVoToEntity(UserVo vo, UserEntity user) {
    user.setId(vo.getId());
    user.setName(vo.getName());
    user.setGender(vo.getGender());
    user.setBirthday(vo.getBirthday());
    user.setAddress(vo.getAddress());
  }

  private void checkInputData(UserVo vo) {
    DataCheck.checkTrimStrEmpty(vo.getAddress(), ErrorCode.PARAM_EMPTY, "address");
    DataCheck.checkTrimStrEmpty(vo.getId(), ErrorCode.PARAM_EMPTY, "id");
    DataCheck.checkTrimStrEmpty(vo.getName(), ErrorCode.PARAM_EMPTY, "name");
    DataCheck.checkNull(vo.getBirthday(), ErrorCode.PARAM_EMPTY, "birthday");
    DataCheck.checkNull(vo.getGender(), ErrorCode.PARAM_EMPTY, "gender");
    DataCheck.checkEnum(GenderEnum.class, vo.getGender(), ErrorCode.PARAM_TYPE_ERROR, "gender");
  }

  /**
   * 校验文件类型.
   *
   * @param file 文件
   */
  private void validateFileType(MultipartFile file) {
    //文件格式校验
    byte[] b;
    try (InputStream is = file.getInputStream()) {
      b = new byte[30];
      if (is.read(b) <= 0) {
        LOGGER.error("[UserServiceImpl] file type error");
        throw new ValidationException(ErrorCode.EXCEL_PARSE_ERROR);
      }
    } catch (IOException e) {
      LOGGER.error("[UserServiceImpl] file type error");
      throw new ValidationException(ErrorCode.EXCEL_PARSE_ERROR);
    }
    String code = DataConvert.bytes2HexString(b);
    String orgFileName = file.getOriginalFilename();
    String fileType = orgFileName.substring(orgFileName.indexOf('.') + 1);
    LOGGER.info("[UserServiceImpl] code:{}, fileType:{}", code, fileType);
    String allowType = FileType.forCode(fileType.trim().toLowerCase());

    if (StringUtils.isEmpty(fileType)
        || StringUtils.isEmpty(allowType)
        || !code.startsWith(allowType)) {
      LOGGER.error("[UserServiceImpl] file type error");
      throw new ValidationException(ErrorCode.EXCEL_PARSE_ERROR);
    }
  }

  private void barrierWait(CyclicBarrier barrier) {
    try {
      LOGGER.info("[UserServiceImpl] waiting... {}", barrier.getNumberWaiting());
      barrier.await(MyConstant.BARRIER_TIMEOUT, TimeUnit.SECONDS);
    } catch (InterruptedException | BrokenBarrierException e) {
      LOGGER.error("[UserServiceImpl] {}", e);
      throw new ValidationException(ErrorCode.BARRIER_ERROR);
    } catch (TimeoutException e) {
      LOGGER.error("[UserServiceImpl] {}", e);
      throw new ValidationException(ErrorCode.BARRIER_TIMEOUT);
    }
  }

  private Future parseThread(ExecutorService exe, CyclicBarrier barrier, Workbook wb, int i) {
    return exe.submit(() -> {
      try {
        new SheetFactory().parseSheet(wb.getSheetAt(i));
      } finally {
        barrierWait(barrier);
      }
    });
  }

}
