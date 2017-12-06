package com.chendehe.service;

import com.chendehe.vo.UserVo;
import java.util.List;

public interface SampleService {

  /**
   * 查找列表
   */
  List<UserVo> findAll();

  /**
   * 查找详情
   */
  UserVo findOne(String id);

  /**
   * 新建
   */
  void save(UserVo userVo);

  /**
   * 更新
   */
  void update(UserVo userVo);

  /**
   * 删除
   */
  void delete(String id);

}
