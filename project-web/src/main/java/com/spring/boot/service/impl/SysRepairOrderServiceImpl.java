package com.spring.boot.service.impl;

import com.spring.boot.bean.cluster.RepairOrder;
import com.spring.boot.service.SysRepairOrderService;
import com.spring.boot.service.web.SysRepairOrderBusinessService;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysRepairOrderServiceImpl implements SysRepairOrderService {
    private static final Logger logger = Logger.getLogger(SysRepairOrderServiceImpl.class);
    @Autowired
    private SysRepairOrderBusinessService sysRepairOrderBusinessService;


    @Override
    public Map<String, Object> getRepairOrder() {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer year = UtilHelper.getYear();
        Integer month = UtilHelper.getMonth();
        map.put("year", UtilHelper.getYear());
        map.put("month", UtilHelper.getMonth());
        map.put("startTime", UtilHelper.getYear() + "-01-01 00:00:00");
        //下一年
        map.put("endTime", UtilHelper.getYear() + 1 + "-01-01 00:00:00");
        //工单数量(月)
        Map<Integer, Integer> monthRepairOrderMap = new HashMap<Integer, Integer>();
        //工单环比增长率(月)
        Map<Integer, Double> monthToMonthRepairOrderMap = new HashMap<Integer, Double>();
        try {
            List<RepairOrder> list = sysRepairOrderBusinessService.getRepairOrderForMonth(map);
            RepairOrder repairOrderInfo = sysRepairOrderBusinessService.getRepairOrderFinish(map);
            //查询结果得到的月份
            Integer monthInfo = 0;
            Integer repairTotal = 0;
            RepairOrder repairOrderLastMonth = null;
            for (RepairOrder repairOrder : list) {
                monthInfo = repairOrder.getMonth();
                if (monthInfo == 1) {
                    repairOrderLastMonth = sysRepairOrderBusinessService.getRepairOrderForLastMonth(year - 1, 12);
                } else {
                    repairOrderLastMonth = sysRepairOrderBusinessService.getRepairOrderForLastMonth(year, month - 1);
                }
                monthRepairOrderMap.put(monthInfo, repairOrder.getMonthRepairTotal());
                if (null != repairOrderLastMonth) {
                    //工单环比增长率(月)
                    monthToMonthRepairOrderMap.put(monthInfo, UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(repairOrder.getMonthRepairTotal(), repairOrderLastMonth.getMonthRepairTotal())));
                }
                //将每个月的工单数量相加为全年的工单数量，避免数据量大时，单独查询
                repairTotal += repairOrder.getMonthRepairTotal();
            }
            if (null != repairOrderInfo) {
                //年工单总数
                repairOrderInfo.setRepairTotal(repairTotal);
                //工单完成率（年）
                repairOrderInfo.setRepairOrderFinishScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(repairOrderInfo.getRepairTotal(), repairTotal)));
                //当年每个月的工单数量
                repairOrderInfo.setMonthRepairOrderMap(monthRepairOrderMap);
                repairOrderInfo.setMonthToMonthRepairOrderMap(monthToMonthRepairOrderMap);
                return R.ok().putData(200, repairOrderInfo, "获取数据成功！");
            } else {
                return R.error(500, "获取工单信息失败，服务器异常，请联系系统管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取工单统计信息失败：" + e.getMessage());
            return R.error(500, "获取工单信息失败，服务器异常，请联系系统管理员！");
        }
    }
}
