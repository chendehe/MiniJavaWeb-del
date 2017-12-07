package com.chendehe.service;

import com.chendehe.vo.Page;
import com.chendehe.vo.PageResult;
import com.chendehe.vo.UserVo;

public interface UserService {

  /**
   * 查找列表.
   */
  PageResult<UserVo> findAll(Page page);

  /**
   * 查找详情.
   */
  UserVo findOne(String id);

  /**
   * 新建.
   */
  UserVo save(UserVo vo);

  /**
   * 更新.
   */
  UserVo update(UserVo vo);

  /**
   * 删除.
   */
  void delete(String id);

}
