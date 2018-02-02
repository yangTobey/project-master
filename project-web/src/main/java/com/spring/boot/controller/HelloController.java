package com.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/2/1.
 */
@Controller
@RequestMapping("/index")
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "views/main/index";
    }
}
