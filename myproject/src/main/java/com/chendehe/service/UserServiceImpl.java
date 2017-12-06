package com.chendehe.service;

import com.chendehe.dao.UserDao;
import com.chendehe.entity.UserEntity;
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
    result.setTotalNum(11);
    result.setPageSize(page.getPageSize());
    result.setCurrentPage(page.getCurrentPage());
    return result;
  }

  @Override
  public UserVo findOne(String id) {
    return convertEntityToVo(userDao.findOne(id));
  }

  @Override
  public UserVo save(UserVo vo) {
    UserEntity entity = convertVoToEntity(vo);
    userDao.save(entity);

    vo.setId(entity.getId());
    return vo;
  }

  @Override
  public UserVo update(UserVo vo) {
    UserEntity user = convertVoToEntityUpdate(vo);

    userDao.update(user);
    return vo;
  }

  @Override
  public void delete(String id) {
    userDao.delete(id);
  }

  /**
   * vo 转为更新后的 entity
   *
   * @param vo UserVo
   * @return UserEntity
   */
  private UserEntity convertVoToEntityUpdate(UserVo vo) {
    UserEntity user = new UserEntity();
    user.setId(vo.getId());
    user.setName(vo.getName());
    user.setGender(vo.getGender());
    user.setBirthday(vo.getBirthday());
    user.setAddress(vo.getAddress());
    user.setUpdateTime(new Date());
    return user;
  }

  /**
   * entity 转为 vo
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
   * vo 转为 entity
   *
   * @param vo UserVo
   * @return UserEntity
   */
  private UserEntity convertVoToEntity(UserVo vo) {
    UserEntity user = new UserEntity();
    user.setId(IdGenerator.get());
    user.setName(vo.getName());
    user.setGender(vo.getGender());
    user.setBirthday(vo.getBirthday());
    user.setAddress(vo.getAddress());
    user.setCreateTime(new Date());
    return user;
  }
}
