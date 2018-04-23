package com.spring.boot.service.web;

import com.spring.boot.bean.cluster.RepairOrder;
import com.spring.boot.bean.master.SysCompany;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysRepairOrderBusinessService {

    /**
     * 查找当年每个月的数据信息
     * @param map
     * @return
     */
    List<RepairOrder> getRepairOrderForMonth(Map<String, Object> map);

    /**
     * 查找上个月的数据信息
     * @param year 年份
     * @param month 月份
     * @return
     */
    RepairOrder getRepairOrderForLastMonth(Integer year,Integer month);
    /**
     * 查找当年已完成的工单的数据信息
     * @param map
     * @return
     */
    RepairOrder getRepairOrderFinish(Map<String, Object> map);


}
