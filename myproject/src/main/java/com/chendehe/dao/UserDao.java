package com.chendehe.dao;

import com.chendehe.entity.UserEntity;
import java.util.List;

public interface UserDao {

  List<UserEntity> findAll();

  UserEntity findOne(String id);

  void save(UserEntity user);

  void update(UserEntity user);

  void delete(Long id);
}
