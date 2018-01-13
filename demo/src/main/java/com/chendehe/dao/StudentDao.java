package com.chendehe.dao;

import com.chendehe.entity.StudentEntity;
import java.util.List;

public interface StudentDao extends BaseDao {

  List<StudentEntity> findAll();

  //StudentEntity findOne(String id);

  void save(StudentEntity student);

  void saveBatch(List<StudentEntity> student);

  //void update(StudentEntity student);
  //void delete(String id);
  //int totalNum();
}
