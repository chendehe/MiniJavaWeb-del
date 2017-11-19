package com.chendehe.service;
import java.util.ArrayList;

import com.chendehe.dao.UserDao;
import com.chendehe.entity.UserEntity;
import com.chendehe.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    UserDao userDao;

    @Override
    public List<UserVo> findAll() {
        List<UserEntity> userList = userDao.getAll();
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
        System.out.println(userVoList);
        return userVoList;
    }

    @Override
    public UserVo findOne(String id) {
        return null;
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
