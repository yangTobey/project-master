package com.spring.boot.controller;

import com.spring.boot.bean.cluster.ActivityUser;
import com.spring.boot.service.ActivityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/1/25.
 */
@RestController
@RequestMapping("/activtyUser")
public class ActivityUserController {
    @Autowired
    private ActivityUserService activityUserService;



    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ActivityUser findByUserId(@RequestParam(value = "id", required = true) String id) {
        return activityUserService.findByUserId(id);
    }

}
