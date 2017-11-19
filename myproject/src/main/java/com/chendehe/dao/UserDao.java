package com.chendehe.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.chendehe.entity.UserEntity;

@Repository
@Mapper
public interface UserDao {

    @Select("SELECT * FROM T_USER")
    List<UserEntity> getAll();

    UserEntity getOne(Long id);

    void save(UserEntity user);

    void update(UserEntity user);

    void delete(Long id);
}
