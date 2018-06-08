package com.spring.boot.service.impl;

import com.spring.boot.bean.cluster.CityInfo;
import com.spring.boot.bean.cluster.RepairOrder;
import com.spring.boot.bean.cluster.RepairOrderLatest;
import com.spring.boot.service.SysRepairOrderService;
import com.spring.boot.service.web.SysRepairOrderBusinessService;
import com.spring.boot.util.JsonUtils;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import com.spring.boot.websocket.WebSocket;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * 定时任务，每30分钟执行一次
     * @return
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    @Override
    public void getRepairOrder() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
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
            //年工单完成数量
            RepairOrder repairOrderFinish = sysRepairOrderBusinessService.getRepairOrderFinish(map);
            RepairOrder repairOrderInfo = new RepairOrder();
            List<CityInfo> cityInfoList=sysRepairOrderBusinessService.getCityInfo();

            List<RepairOrderLatest> repairOrderLatestList=sysRepairOrderBusinessService.getRepairOrderLatest();

            Map<Long,List<String>> cityOrderMap=new HashMap<Long,List<String>>();
            for(RepairOrderLatest repairOrderLatest:repairOrderLatestList){
                String orderName="";
                //报修单状态（0：待处理，1：受理，2：完成，-1：取消，3：已删除）
                if(repairOrderLatest.getStatus()==0){
                    orderName=repairOrderLatest.getUserName()+"新发布了一条"+repairOrderLatest.getLevelName()+"工单";
                }else if(repairOrderLatest.getStatus()==1){
                    orderName=repairOrderLatest.getUserName()+"发布的工单已接单，马上进行处理";
                }else if(repairOrderLatest.getStatus()==2){
                    orderName=repairOrderLatest.getUserName()+"发布的工单已完成，待客服回访";
                }else if(repairOrderLatest.getStatus()==4){
                    orderName=repairOrderLatest.getUserName()+"发布的订单为疑难工单，物业管理正在努力解决";
                }
                //以小区id作为key存储数据，一个小区可能存在多条信息，如果key存在，直接追加数据
                if(cityOrderMap.containsKey(repairOrderLatest.getCityId())){
                    cityOrderMap.get(repairOrderLatest.getCityId()).add(repairOrderLatest.getCommunityName()+"--"+orderName);
                }else{
                    if(repairOrderLatest.getCityId()!=null){
                        List<String> listData=new ArrayList<String>();
                        listData.add(repairOrderLatest.getCommunityName()+"--"+orderName);
                        cityOrderMap.put(repairOrderLatest.getCityId(),listData);
                    }
                }
            }

            //查询结果得到的月份
            Integer monthInfo = 0;
            Integer repairTotal = 0;
            RepairOrder repairOrderLastMonth = null;
            if (null != list) {
                for (RepairOrder repairOrder : list) {
                    monthInfo = repairOrder.getMonth();
                    if (monthInfo == 1) {
                        repairOrderLastMonth = sysRepairOrderBusinessService.getRepairOrderForLastMonth(year - 1, 12);
                    } else {
                        repairOrderLastMonth = sysRepairOrderBusinessService.getRepairOrderForLastMonth(year, monthInfo - 1);
                    }
                    monthRepairOrderMap.put(monthInfo, repairOrder.getMonthRepairTotal());
                    if (null != repairOrderLastMonth) {
                        //工单环比增长率(月)
                        monthToMonthRepairOrderMap.put(monthInfo, UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(repairOrder.getMonthRepairTotal()-repairOrderLastMonth.getMonthRepairTotal(), repairOrderLastMonth.getMonthRepairTotal())));
                    }
                    //将每个月的工单数量相加为全年的工单数量，避免数据量大时，单独查询
                    repairTotal += repairOrder.getMonthRepairTotal();
                }

                //年工单总数
                repairOrderInfo.setRepairTotal(repairTotal);
                //工单完成率（年）
                repairOrderInfo.setRepairOrderFinishScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(repairOrderFinish.getRepairTotal(), repairTotal)));
                //当年每个月的工单数量
                repairOrderInfo.setMonthRepairOrderMap(monthRepairOrderMap);
                repairOrderInfo.setMonthToMonthRepairOrderMap(monthToMonthRepairOrderMap);
                //城市信息
                repairOrderInfo.setCityInfos(cityInfoList);
                repairOrderInfo.setCityOrderMap(cityOrderMap);
                //repairOrderInfo.setRepairOrderLatestList(repairOrderLatestList);
                resultMap.put("repairOrder",repairOrderInfo);
                WebSocket.sendInfo(JsonUtils.obj2JsonString(R.ok().putData(200, resultMap, "获取成功！！")));
                //return R.ok().putData(200, repairOrderInfo, "获取数据成功！");
            } else {
                WebSocket.sendInfo(JsonUtils.obj2JsonString(R.error(500, "获取工单统计信息失败，服务器异常，请联系管理员！")));
               // return R.error(500, "获取工单信息失败，服务器异常，请联系系统管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取工单统计信息失败：" + e.getMessage());
            try {
                WebSocket.sendInfo(JsonUtils.obj2JsonString(R.error(500, "获取工单统计信息失败，服务器异常，请联系管理员！")));
            }catch (Exception ex){
                ex.printStackTrace();
            }
            //return R.error(500, "获取工单信息失败，服务器异常，请联系系统管理员！");
        }
    }
}
