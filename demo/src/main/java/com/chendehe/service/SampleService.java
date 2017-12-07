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
     * @param id
     */
    UserVo findOne(String id);

    /**
     * 新建
     * @param userVo
     */
    void save(UserVo userVo);

    /**
     * 更新
     * @param userVo
     */
    void update(UserVo userVo);

    /**
     * 删除
     * @param id
     */
    void delete(String id);

}
