package com.chendehe.dao;

import com.chendehe.entity.UserEntity;
import java.util.List;

public interface UserDao extends BaseDao {

  List<UserEntity> findAll();

  UserEntity findOne(String id);

  void save(UserEntity user);

  void saveBatch(List<UserEntity> user);

  void update(UserEntity user);

  void delete(String id);

  int totalNum();
}
