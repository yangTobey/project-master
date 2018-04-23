package com.spring.boot.dao.web.cluster;

import com.spring.boot.bean.cluster.ActivityUser;
import com.spring.boot.bean.cluster.RepairOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaoyang on 2018/4/23.
 */
public interface RepairOrderDao {

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
    RepairOrder getRepairOrderForLastMonth(@Param("year")Integer year,@Param("month") Integer month);
    /**
     * 查找当年已完成的工单的数据信息
     * @param map
     * @return
     */
    RepairOrder getRepairOrderFinish(Map<String, Object> map);

}
