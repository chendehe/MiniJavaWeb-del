package com.chendehe.controller;

import com.chendehe.service.SampleService;
import com.chendehe.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    private SampleService service;

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    /**
     * 查找列表
     */
    @GetMapping("/list")
    String findAll() {
        service.findAll();
        return "FindAll";
    }

    /**
     * 查找详情
     */
    @GetMapping("/{id}")
    String findOne(String id) {

        return "FindOne";
    }

    /**
     * 新建
     */
    @PostMapping("/")
    String save(UserVo userVo) {

        return "Added";
    }

    /**
     * 更新
     */
    @PutMapping("/{id}")
    String update(UserVo userVo, String id) {

        return "Updated";
    }

    /**
     * 删除
     * @param id
     */
    @DeleteMapping("/{id}")
    String delete(String id){

        return "Deleted";
    }

}