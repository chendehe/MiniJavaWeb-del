package com.chendehe.dao;

import com.chendehe.entity.UserEntity;
import java.util.List;

public interface UserDao {

  List<UserEntity> getAll();

  UserEntity getOne(Long id);

  void save(UserEntity user);

  void update(UserEntity user);

  void delete(Long id);
}
