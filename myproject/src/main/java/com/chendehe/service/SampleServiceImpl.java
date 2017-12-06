package com.chendehe.service;

import com.chendehe.dao.UserDao;
import com.chendehe.entity.UserEntity;
import com.chendehe.vo.UserVo;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SampleServiceImpl.class);

  private UserDao userDao;

  @Autowired
  public SampleServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public List<UserVo> findAll() {
    List<UserEntity> userList = userDao.findAll();
    List<UserVo> userVoList = new ArrayList<>();
    for (UserEntity user : userList) {
      UserVo userVo = new UserVo();
      userVo.setId(user.getId());
      userVo.setName(user.getName());
      userVo.setSex(user.getSex());
      userVo.setBirthday(user.getBirthday());
      userVo.setAddress(user.getAddress());
      userVoList.add(userVo);
    }

    LOGGER.info("======>{}", userVoList.toString());
    System.out.println(userVoList);
    return userVoList;
  }

  @Override
  public UserVo findOne(String id) {
    userDao.findOne(id);
    return new UserVo();
  }

  @Override
  public void save(UserVo userVo) {

  }

  @Override
  public void update(UserVo userVo) {

  }

  @Override
  public void delete(String id) {

  }


}
