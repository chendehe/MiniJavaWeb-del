package com.chendehe.service;

import com.chendehe.common.ErrorCode;
import com.chendehe.common.enums.GenderEnum;
import com.chendehe.dao.UserDao;
import com.chendehe.entity.UserEntity;
import com.chendehe.util.DataCheck;
import com.chendehe.util.IdGenerator;
import com.chendehe.vo.Page;
import com.chendehe.vo.PageResult;
import com.chendehe.vo.UserVo;
import java.util.Date;
import java.util.List;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

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

    checkInputData(vo);

    vo.setId(IdGenerator.get());
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
}
