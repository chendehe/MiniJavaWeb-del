package com.chendehe.service;

import com.chendehe.common.ErrorCode;
import com.chendehe.common.MyConstant;
import com.chendehe.common.enums.GenderEnum;
import com.chendehe.dao.UserDao;
import com.chendehe.entity.StudentEntity;
import com.chendehe.entity.UserEntity;
import com.chendehe.exception.ValidationException;
import com.chendehe.util.DataCheck;
import com.chendehe.util.IdGenerator;
import com.chendehe.vo.Page;
import com.chendehe.vo.PageResult;
import com.chendehe.vo.UserVo;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  private UserDao userDao;

  @Autowired
  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
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

  @Override
  public UserVo update(UserVo vo) {

    checkInputData(vo);

    UserEntity user = convertVoToEntityUpdate(vo);

    userDao.update(user);
    return vo;
  }

  @Override
  public void delete(String id) {
    DataCheck.checkTrimStrEmpty(id, ErrorCode.PARAM_EMPTY, "id");
    userDao.delete(id);
  }

  @Override
  public void upload(MultipartFile file) {
    long start = System.currentTimeMillis();
    LOGGER.info("[UserServiceImpl] start:{}", start);

    //屏障，加上主线程
    final CyclicBarrier barrier = new CyclicBarrier(MyConstant.SHEET_NUMBER + 1);
    try {
      //Excel文件工厂
      Workbook wb = WorkbookFactory.create(file.getInputStream());

      long wbTime = System.currentTimeMillis();
      LOGGER.info("[UserServiceImpl] Create wb:{}", wbTime - start);

      //解析每个sheet
      for (int i = 0; i < MyConstant.SHEET_NUMBER; i++) {
        String sheetName = wb.getSheetAt(i).getSheetName();
        int index = i;
        new Thread(() -> {
          if (MyConstant.SHEET_USER.equals(sheetName.trim())) {
            parseUser(wb.getSheetAt(index));

            barrierWait(barrier);
          } else if (MyConstant.SHEET_STUDENT.equals(sheetName.trim())) {
            parseStudent(wb.getSheetAt(index));

            barrierWait(barrier);
          }
        }, "parse " + sheetName).start();
      }

      barrierWait(barrier);
    } catch (IOException | InvalidFormatException e) {
      LOGGER.error("[UserServiceImpl] {}", e);
      throw new ValidationException(ErrorCode.BARRIER_ERROR);
    } finally {
      barrier.reset();
    }

    long end = System.currentTimeMillis();
    LOGGER.info("[UserServiceImpl] Total end:{}", end - start);
  }

  @Override
  public void downLoad(String id) {

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

  private static void barrierWait(CyclicBarrier barrier) {
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

  private static void parseUser(Sheet sh) {
    List<UserEntity> users = Lists.newArrayList();

    boolean firstIn = true;
    for (Row row : sh) {
      if (firstIn) {
        //去掉标题
        firstIn = false;
        continue;
      }
      if (null == row.getCell(0)) {
        break;
      }

      UserEntity user = new UserEntity();
      user.setId(IdGenerator.get());
      user.setName(row.getCell(0).getStringCellValue());
      user.setGender((int) row.getCell(1).getNumericCellValue());
      user.setBirthday(row.getCell(2).getDateCellValue());
      user.setAddress(row.getCell(3).getStringCellValue());
      user.setCreateTime(new Date());
      users.add(user);
    }
    LOGGER.info("[UserServiceImpl] save user in db... :{}", users.size());
  }

  private static void parseStudent(Sheet sh) {
    List<StudentEntity> students = Lists.newArrayList();

    boolean firstIn = true;
    for (Row row : sh) {
      if (firstIn) {
        //去掉标题
        firstIn = false;
        continue;
      }
      if (null == row.getCell(0)) {
        break;
      }

      StudentEntity student = new StudentEntity();
      student.setId(IdGenerator.get());
      student.setSchool(row.getCell(0).getStringCellValue());
      student.setAcademy(row.getCell(1).getStringCellValue());
      student.setMajor(row.getCell(2).getStringCellValue());
      student.setClasses(row.getCell(3).getStringCellValue());
      student.setCreateTime(new Date());
      students.add(student);
    }
    LOGGER.info("[UserServiceImpl] save student in db... :{}", students.size());
  }
}
