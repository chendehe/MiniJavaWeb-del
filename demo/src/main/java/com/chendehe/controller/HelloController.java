package com.chendehe.controller;

import com.chendehe.exception.BaseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/json")
    public String json() throws BaseException {
        throw new BaseException("发生错误2");
    }

}
