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
        long sys3=System.currentTimeMillis();
        //String sql = "select * from sys_user";
        String sql = "SELECT company.cityName,company.longitude,company.latitude,re.community_id as communityId,re.`status`," +
                "REPLACE (re.user_name,SUBSTR(re.user_name, 2),'**') as userName,company.id as cityId,co.`name` as communityName, " +
                "CASE re.level " +
                "WHEN 0 THEN '报修'" +
                "WHEN 1 THEN '投诉' " +
                "END AS levelName " +
                "FROM  `repair` AS re " +
                "LEFT JOIN community AS co ON re.community_id = co.community_id " +
                "LEFT JOIN worker_branchCommunity AS wocom ON wocom.community = co.community_id " +
                "LEFT JOIN worker_branchCompany AS company ON company.id = wocom.branchCompanyId " +
                "WHERE re.`status`!=-1 and re.`status`!=3 and wocom.type=1 and company.`status`!=2 " +
                "ORDER BY re.commit_time DESC LIMIT 0, 30";
        ResultSet rs = statement.executeQuery(sql);

        long sys4=System.currentTimeMillis();

        while(rs.next()) {
            System.out.println("AA:"+(sys4-sys3));
        }
    }
}
