package com.spring.boot.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/4.
 */
public class SysUtil {
    /**
     * 获取登录用户权限下可操作的公司id的组合
     *
     * @return
     */
    public static List<Long> getSysUserCompany() {
        Session session = null;
        //公司id集合
        List<Long> sysUserCompanyIds = null;
        if (null == SecurityUtils.getSubject()) {
            sysUserCompanyIds = new ArrayList<>();
            sysUserCompanyIds.add(0L);
        } else {
            session = SecurityUtils.getSubject().getSession();
            //公司id集合
            sysUserCompanyIds = (List<Long>) session.getAttribute("sysUserCompany");
        }
        if (null == sysUserCompanyIds) {
            sysUserCompanyIds = new ArrayList<>();
            sysUserCompanyIds.add(0L);
        }
        return sysUserCompanyIds;
    }

    /**
     * 获取查询数据需要用到的年和月（每月10号到下个月10号显示上一个月的数据，即5月10号到6月10号显示4月份数据。）
     * @param day
     * @return
     */
    public static Map<String,Integer> getYearAndMonth(int day){
        Map<String,Integer> map=new HashMap<String,Integer>();
        int year=0;
        int month=0;
        //获取当前月的天数
        int dayOfMonth=UtilHelper.getDayOfMonth();
        //每月10号到下个月10号显示上一个月的数据，即5月10号到6月10号显示4月份数据。
        if(dayOfMonth<day){
            if(UtilHelper.getMonth()<=2){
                year=UtilHelper.getYear()-1;
                month=12-2+UtilHelper.getMonth();
            }else{
                year=UtilHelper.getYear();
                month=UtilHelper.getMonth()-2;
            }
        }else{
            if(UtilHelper.getMonth()<=1){
                year=UtilHelper.getYear()-1;
                month=12;
            }else{
                year=UtilHelper.getYear();
                month=UtilHelper.getMonth()-1;
            }
        }
        map.put("year",year);
        map.put("month",month);
        return  map;
    }

    /**
     * 判断是否更新到redis缓存
     * @param day 当天所在当月的天数
     * @param year 修改数据所在的年份
     * @param month 修改数据所在的月份
     * @return
     */
    public static boolean updateToRedis(int day,int year,int month){
        //获取当前月的天数
        int dayOfMonth=UtilHelper.getDayOfMonth();
        //每月10号到下个月10号显示上一个月的数据，即5月10号到6月10号显示4月份数据。
        if(dayOfMonth<=day){
            if(UtilHelper.getMonth()<=2){
                if(12-2+UtilHelper.getMonth()==month&&UtilHelper.getYear()-1==year){
                    return true;
                }
            }else{
                if(UtilHelper.getMonth()-2==month&&UtilHelper.getYear()==year){
                    return true;
                }
            }
        }else{
            if(UtilHelper.getMonth()<=1){
                if(12==month&&UtilHelper.getYear()-1==year){
                    return true;
                }
            }else{
                if(UtilHelper.getMonth()-1==month&&UtilHelper.getYear()==year){
                    return true;
                }
            }
        }
        return  false;
    }
}
