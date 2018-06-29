package com.spring.boot.controller;

import com.spring.boot.util.DbTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2018/2/1.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "views/statistical/index";
    }
    @RequestMapping(value = "/DbTest", method = RequestMethod.GET)
    @ResponseBody
    public void DBTest() throws SQLException {
        long sys1=System.currentTimeMillis();
        Connection cc=DbTest.getConnection();
        long sys2=System.currentTimeMillis();
        System.out.println(sys2-sys1);
        if(!cc.isClosed()){
            System.out.println("Succeeded connecting to the Database!");
        }

        Statement statement = cc.createStatement();
        String sql = "select * from sys_user";
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()) {
            System.out.println(rs.getString("user_id")+"");
        }
    }
}
