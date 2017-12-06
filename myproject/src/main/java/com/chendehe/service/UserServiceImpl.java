package com.chendehe.service;

import com.chendehe.dao.UserDao;
import com.chendehe.entity.UserEntity;
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
  public List<UserVo> findAll() {

    List<UserEntity> userList = userDao.findAll();
    List<UserVo> userVoList = Lists.newArrayList();

    for (UserEntity user : userList) {
      userVoList.add(convertEntityToVo(user));
    }

    return userVoList;
  }

  @Override
  public UserVo findOne(String id) {
    return convertEntityToVo(userDao.findOne(id));
  }

  @Override
  public void save(UserVo vo) {
    userDao.save(convertVoToEntity(vo));
  }

  @Override
  public void update(UserVo vo) {
    userDao.update(convertVoToEntity(vo));
  }

  @Override
  public void delete(String id) {
    userDao.delete(id);
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
    user.setId(vo.getId());
    user.setName(vo.getName());
    user.setGender(vo.getGender());
    user.setBirthday(vo.getBirthday());
    user.setAddress(vo.getAddress());
    user.setCreateTime(new Date());
    return user;
  }
}
