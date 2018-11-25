package com.spring.boot.controller;

import com.spring.boot.aspect.NotNull;
import com.spring.boot.aspect.UserMessage;
import com.spring.boot.aspect.ValidParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yang on 2018/11/24.
 */
@RestController
@RequestMapping("/activtyUser")
public class TestContrller {
    @RequestMapping(value = "/userMesage", method = RequestMethod.GET)
    public String test(@ValidParam UserMessage param, @NotNull Integer limit){
        return null;
    }
}
