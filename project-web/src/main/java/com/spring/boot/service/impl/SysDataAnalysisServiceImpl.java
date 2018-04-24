package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysBudgetDetails;
import com.spring.boot.bean.master.SysChargeDetails;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;
import com.spring.boot.bean.master.entity.SysFinancialDataAnalysisEntity;
import com.spring.boot.bean.master.entity.SysLaborCostDetailsEntity;
import com.spring.boot.bean.master.entity.SysPropertyDataAnalysisEntity;
import com.spring.boot.dao.web.cluster.ActivityUserDao;
import com.spring.boot.service.SysDataAnalysisService;
import com.spring.boot.util.JsonUtils;
import com.spring.boot.util.R;
import com.spring.boot.websocket.PropertyWebSocket;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysDataAnalysisServiceImpl implements SysDataAnalysisService {
    private static final Logger logger = Logger.getLogger(SysDataAnalysisServiceImpl.class);
    @Autowired
    private ActivityUserDao activityUserDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 物业大屏数据展示redis缓存获取
     *
     * @return
     */
    @Override
    public Map<String, Object> sysPropertyDataAnalysis() {
        Map<String, Object> map = new HashMap<String, Object>();
        SysPropertyDataAnalysisEntity sysPropertyDataAnalysisEntity = new SysPropertyDataAnalysisEntity();
        try {
            /***********************************基础数据缓存******************************/
            // 缓存key是否存在
            boolean sysBasicDataKey = redisTemplate.hasKey("sysBasicData");
            if (sysBasicDataKey) {
                SysBasicDataEntity sysBasicDataEntity = (SysBasicDataEntity) redisTemplate.opsForValue().get("sysBasicData");
                sysPropertyDataAnalysisEntity.setSysBasicData(sysBasicDataEntity);
            }
            /***********************************品质检查数据缓存******************************/
            // 缓存key是否存在
            //品质检查统计信息
            boolean qualityManageKey = redisTemplate.hasKey("qualityManage");
            if (qualityManageKey) {
                Map<String, Object> resultMap = (Map<String, Object>) redisTemplate.opsForValue().get("qualityManageYear");
                sysPropertyDataAnalysisEntity.setQualityManageMap(resultMap);
            }
            /***********************************工程能耗管理数据缓存******************************/
            // 缓存key是否存在
            //工程能耗管理统计信息
            boolean sysProjectEnergyKey = redisTemplate.hasKey("sysProjectEnergy");
            if (sysProjectEnergyKey) {
                Map<String, Object> resultMap = (Map<String, Object>) redisTemplate.opsForValue().get("sysProjectForYear");
                sysPropertyDataAnalysisEntity.setSysProjectEnergyMap(resultMap);
            }

            /***********************************人员成本管理数据缓存******************************/
            // 缓存key是否存在
            //年度统计信息
            boolean sysLaborCostDetailsKey = redisTemplate.hasKey("sysLaborCostDetails");
            if (sysLaborCostDetailsKey) {
                SysLaborCostDetailsEntity SysLaborCostDetailsEntity = (SysLaborCostDetailsEntity) redisTemplate.opsForValue().get("sysLaborCostDetails");
                sysPropertyDataAnalysisEntity.setSysLaborCostDetails(SysLaborCostDetailsEntity);
            }
            map.put("property",sysPropertyDataAnalysisEntity);
            PropertyWebSocket.sendInfo(JsonUtils.obj2JsonString(R.ok().putData(200, map, "获取成功！！")));
            return R.ok().putData(200, sysPropertyDataAnalysisEntity, "获取成功！！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取物业大屏数据展示统计详细信息失败！" + e.getMessage());
            try {
                PropertyWebSocket.sendInfo(JsonUtils.obj2JsonString(R.error(500, "获取物业大屏数据展示统计详细信息失败，服务器异常，请联系管理员！")));
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return R.error(500, "获取物业大屏数据展示统计详细信息失败，服务器异常，请联系管理员！");
        }
    }

    /**
     * 财务大屏数据redis缓存获取
     *
     * @return
     */
    @Override
    public Map<String, Object> sysFinancialDataAnalysis() {
        Map<String, Object> map = new HashMap<String, Object>();
        SysFinancialDataAnalysisEntity sysFinancialDataAnalysisEntity = new SysFinancialDataAnalysisEntity();
        try {
            /***********************************收费情况数据缓存******************************/
            // 缓存key是否存在
            //收费情况统计信息
            boolean sysChargeDetailsKey = redisTemplate.hasKey("sysChargeDetails");
            if (sysChargeDetailsKey) {
                SysChargeDetails sysChargeDetails = (SysChargeDetails) redisTemplate.opsForValue().get("sysChargeDetails");
                Map<String, Object> sysChargeMap = new HashMap<String, Object>();
                sysChargeMap.put("sysChargeMap", sysChargeDetails);
                sysFinancialDataAnalysisEntity.setSysChargeDetailsMap(sysChargeMap);
            }
            /***********************************应收账款数据缓存******************************/
            // 缓存key是否存在
            //应收账款统计信息
            boolean sysReceivableAccountKey = redisTemplate.hasKey("sysReceivableAccount");
            if (sysReceivableAccountKey) {
                Map<String, Object> resultMap = (Map<String, Object>) redisTemplate.opsForValue().get("sysReceivableAccount");
                Map<String, Object> sysAccountMap = new HashMap<String, Object>();
                sysAccountMap.put("sysAccountMap", resultMap);
                sysFinancialDataAnalysisEntity.setSysAccountsReceivableMap(sysAccountMap);
            }
            /***********************************执行预算数据缓存******************************/
            // 缓存key是否存在
            //执行预算统计信息
            boolean sysBudgetDetailsKey = redisTemplate.hasKey("sysBudgetDetails");
            if (sysBudgetDetailsKey) {
                SysBudgetDetails sysBudgetDetails = (SysBudgetDetails) redisTemplate.opsForValue().get("sysBudgetDetails");
                Map<String, Object> sysBudgetMap = new HashMap<String, Object>();
                sysBudgetMap.put("sysAccountMap", sysBudgetDetails);
                sysFinancialDataAnalysisEntity.setSysBudgetDetailsMap(sysBudgetMap);
            }
            map.put("financial",sysFinancialDataAnalysisEntity);
            PropertyWebSocket.sendInfo(JsonUtils.obj2JsonString(R.ok().putData(200, map, "获取成功！！")));
            return R.ok().putData(200, sysFinancialDataAnalysisEntity, "获取成功！！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取财务大屏数据展示统计详细信息失败！" + e.getMessage());
            try {
                PropertyWebSocket.sendInfo(JsonUtils.obj2JsonString(R.error(500, "获取财务大屏数据展示统计详细信息失败，服务器异常，请联系管理员！")));
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return R.error(500, "获取财务大屏数据展示统计详细信息失败，服务器异常，请联系管理员！");
        }
    }
}
