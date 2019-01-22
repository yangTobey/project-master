package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysBudgetDetails;
import com.spring.boot.bean.master.SysChargeDetails;
import com.spring.boot.bean.master.SysProject;
import com.spring.boot.bean.master.entity.*;
import com.spring.boot.dao.web.cluster.ActivityUserDao;
import com.spring.boot.service.SysDataAnalysisService;
import com.spring.boot.util.JsonUtils;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import com.spring.boot.websocket.WebSocket;
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
                if(sysBasicDataEntity==null){
                    sysBasicDataEntity=new SysBasicDataEntity();
                }
                sysPropertyDataAnalysisEntity.setConstructionArea(sysBasicDataEntity.getConstructionArea());
                sysPropertyDataAnalysisEntity.setChargeArea(sysBasicDataEntity.getChargeArea());
                sysPropertyDataAnalysisEntity.setSubsidiaryCount(sysBasicDataEntity.getSubsidiaryCount());
                sysPropertyDataAnalysisEntity.setCityNumber(sysBasicDataEntity.getCityNumber());
                sysPropertyDataAnalysisEntity.setProjectNumber(sysBasicDataEntity.getProjectNumber());
                sysPropertyDataAnalysisEntity.setSalesDistribution(sysBasicDataEntity.getSalesDistribution());
                sysPropertyDataAnalysisEntity.setHouseNumber(sysBasicDataEntity.getHouseNumber());
                sysPropertyDataAnalysisEntity.setAcceptHouseNumber(sysBasicDataEntity.getAcceptHouseNumber());
                sysPropertyDataAnalysisEntity.setDecorateHouseNumber(sysBasicDataEntity.getDecorateHouseNumber());
                sysPropertyDataAnalysisEntity.setForSaleHouseNumber(sysBasicDataEntity.getForSaleHouseNumber());
                sysPropertyDataAnalysisEntity.setParkingSpace(sysBasicDataEntity.getParkingSpace());
                sysPropertyDataAnalysisEntity.setForSaleParkingSpace(sysBasicDataEntity.getForSaleParkingSpace());
                sysPropertyDataAnalysisEntity.setForSaleHouseScale(sysBasicDataEntity.getForSaleHouseScale());
                sysPropertyDataAnalysisEntity.setAcceptHouseNumberScale(sysBasicDataEntity.getAcceptHouseNumberScale());
                sysPropertyDataAnalysisEntity.setDecorateHouseScale(sysBasicDataEntity.getDecorateHouseScale());
                sysPropertyDataAnalysisEntity.setForSaleParkingSpaceScale(sysBasicDataEntity.getForSaleParkingSpaceScale());
            }
            /***********************************品质检查数据缓存******************************/
            // 缓存key是否存在
            //品质检查统计信息
            boolean qualityManageYearKey = redisTemplate.hasKey("qualityManageYear");
            boolean qualityManageMonthKey = redisTemplate.hasKey("qualityManageMonth");
            if (qualityManageYearKey) {
                SysQualityManageEntity sysQualityManageEntityForYear =(SysQualityManageEntity) redisTemplate.opsForValue().get("qualityManageYear");
                if(sysQualityManageEntityForYear==null){
                    sysQualityManageEntityForYear=new SysQualityManageEntity();
                }
                sysPropertyDataAnalysisEntity.setYearQualityCheck(sysQualityManageEntityForYear.getQualityCheck());
                sysPropertyDataAnalysisEntity.setYearQualityCheckPass(sysQualityManageEntityForYear.getQualityCheckPass());
                sysPropertyDataAnalysisEntity.setYearQualityCheckFail(sysQualityManageEntityForYear.getQualityCheckFail());
                sysPropertyDataAnalysisEntity.setYearQualityCheckUnmodified(sysQualityManageEntityForYear.getQualityCheckUnmodified());
                sysPropertyDataAnalysisEntity.setYearQualityCheckPassPercent(sysQualityManageEntityForYear.getQualityCheckPassScale());
                sysPropertyDataAnalysisEntity.setYearModifiedPassPercent(sysQualityManageEntityForYear.getModifiedPassScale());

            }
            if (qualityManageMonthKey) {
                SysQualityManageEntity sysQualityManageEntityForMonth = (SysQualityManageEntity) redisTemplate.opsForValue().get("qualityManageMonth");
                if(sysQualityManageEntityForMonth==null){
                    sysQualityManageEntityForMonth=new SysQualityManageEntity();
                }
                sysPropertyDataAnalysisEntity.setQualityCheck(sysQualityManageEntityForMonth.getQualityCheck());
                sysPropertyDataAnalysisEntity.setQualityCheckPass(sysQualityManageEntityForMonth.getQualityCheckPass());
                sysPropertyDataAnalysisEntity.setQualityCheckFail(sysQualityManageEntityForMonth.getQualityCheckFail());
                sysPropertyDataAnalysisEntity.setQualityCheckUnmodified(sysQualityManageEntityForMonth.getQualityCheckUnmodified());
                sysPropertyDataAnalysisEntity.setQualityCheckPassPercent(sysQualityManageEntityForMonth.getQualityCheckPassScale());
                sysPropertyDataAnalysisEntity.setModifiedPassPercent(sysQualityManageEntityForMonth.getModifiedPassScale());
                sysPropertyDataAnalysisEntity.setCheckPassScaleMap(sysQualityManageEntityForMonth.getCheckPassScaleMap());
                sysPropertyDataAnalysisEntity.setModifiedPassScaleMap(sysQualityManageEntityForMonth.getModifiedPassScaleMap());
                sysPropertyDataAnalysisEntity.setSecurityEvent(sysQualityManageEntityForMonth.getSecurityEvent());

            }
            /***********************************工程能耗管理数据缓存******************************/
            // 缓存key是否存在
            //工程能耗管理统计信息
            boolean sysProjectForYearKey = redisTemplate.hasKey("sysProjectForYear");
            boolean sysProjectForMonthKey = redisTemplate.hasKey("sysProjectForMonth");
            if (sysProjectForYearKey) {
                SysProject sysProject = (SysProject) redisTemplate.opsForValue().get("sysProjectForYear");
                if(sysProject==null){
                    sysProject=new SysProject();
                }
                sysPropertyDataAnalysisEntity.setProjectUnfinishedTotal(sysProject.getYearProjectUnfinishedTotal());
                sysPropertyDataAnalysisEntity.setProjectFinishedTotal(sysProject.getYearProjectFinishedTotal());
                //年系统问题处理率
                sysPropertyDataAnalysisEntity.setMonthProjectUnfinishedScale(UtilHelper.DecimalFormatDouble(UtilHelper.DecimalFormatNumber(sysProject.getYearProjectFinishedTotal(),sysProject.getYearProjectUnfinishedTotal())));
                sysPropertyDataAnalysisEntity.setYearConsumptionElectricity(sysProject.getYearConsumptionElectricity());
                sysPropertyDataAnalysisEntity.setYearConsumptionWater(sysProject.getYearConsumptionWater());
                sysPropertyDataAnalysisEntity.setMonthConsumptionElectricity(sysProject.getMonthConsumptionElectricity());
                sysPropertyDataAnalysisEntity.setMonthConsumptionWater(sysProject.getMonthConsumptionWater());

                sysPropertyDataAnalysisEntity.setYoYElectricityScale(sysProject.getYoYConsumptionElectricityScale());
                sysPropertyDataAnalysisEntity.setMtoMtElectricityScale(sysProject.getMtoMtConsumptionElectricityScale());
                sysPropertyDataAnalysisEntity.setYoYWaterScale(sysProject.getYoYConsumptionWaterScale());
                sysPropertyDataAnalysisEntity.setMtoMtWaterScale(sysProject.getMtoMtConsumptionWaterScale());
            }
            if (sysProjectForMonthKey) {
                SysProjectEnergyEntity sysProjectEnergyEntity = (SysProjectEnergyEntity) redisTemplate.opsForValue().get("sysProjectForMonth");
                if(sysProjectEnergyEntity==null){
                    sysProjectEnergyEntity=new SysProjectEnergyEntity();
                }
                sysPropertyDataAnalysisEntity.setMtoMtCsElectricityScaleMap(sysProjectEnergyEntity.getMtoMtCsElectricityScaleMap());
                sysPropertyDataAnalysisEntity.setMtoMtCsWaterScaleMap(sysProjectEnergyEntity.getMtoMtCsWaterScaleMap());
                sysPropertyDataAnalysisEntity.setMonthCsElectricityMap(sysProjectEnergyEntity.getMonthCsElectricityMap());
                sysPropertyDataAnalysisEntity.setMonthCsWaterMap(sysProjectEnergyEntity.getMonthCsWaterMap());
            }

            /***********************************人员成本管理数据缓存******************************/
            // 缓存key是否存在
            //年度统计信息
            boolean sysLaborCostDetailsKey = redisTemplate.hasKey("sysLaborCostDetails");
            if (sysLaborCostDetailsKey) {
                SysLaborCostDetailsEntity SysLaborCostDetailsEntity = (SysLaborCostDetailsEntity) redisTemplate.opsForValue().get("sysLaborCostDetails");
                if(SysLaborCostDetailsEntity==null){
                    SysLaborCostDetailsEntity=new SysLaborCostDetailsEntity();
                }
                sysPropertyDataAnalysisEntity.setLaborCostTotal(SysLaborCostDetailsEntity.getLaborCostTotal());
                sysPropertyDataAnalysisEntity.setAverageLaborCost(SysLaborCostDetailsEntity.getAverageLaborCost());
                sysPropertyDataAnalysisEntity.setHeadcountTotal(SysLaborCostDetailsEntity.getHeadcountTotal());
                sysPropertyDataAnalysisEntity.setEmployeeTotal(SysLaborCostDetailsEntity.getEmployeeTotal());
                sysPropertyDataAnalysisEntity.setEntryTotal(SysLaborCostDetailsEntity.getEntryTotal());
                sysPropertyDataAnalysisEntity.setDemissionTotal(SysLaborCostDetailsEntity.getDemissionTotal());
                sysPropertyDataAnalysisEntity.setSysEmployeeScale(SysLaborCostDetailsEntity.getSysEmployeeScale());
                sysPropertyDataAnalysisEntity.setSysLaborCostScale(SysLaborCostDetailsEntity.getSysLaborCostScale());
                sysPropertyDataAnalysisEntity.setSysLaborCostLastMonthScale(SysLaborCostDetailsEntity.getSysLaborCostLastMonthScale());
                sysPropertyDataAnalysisEntity.setSysDemissionScale(SysLaborCostDetailsEntity.getSysDemissionScale());
                sysPropertyDataAnalysisEntity.setPropertyLaborCostScale(SysLaborCostDetailsEntity.getPropertyLaborCostScale());
                sysPropertyDataAnalysisEntity.seteBusinessScale(SysLaborCostDetailsEntity.geteBusinessScale());
                sysPropertyDataAnalysisEntity.setSaleLaborCostScale(SysLaborCostDetailsEntity.getSaleLaborCostScale());
                
            }
            map.put("property",sysPropertyDataAnalysisEntity);
            WebSocket.sendInfo(JsonUtils.obj2JsonString(R.ok().putData(200, map, "获取成功！！")));
            return R.ok().putData(200, sysPropertyDataAnalysisEntity, "获取成功！！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取物业大屏数据展示统计详细信息失败！" + e.getMessage());
            try {
                WebSocket.sendInfo(JsonUtils.obj2JsonString(R.error(500, "获取物业大屏数据展示统计详细信息失败，服务器异常，请联系管理员！")));
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
                if(sysChargeDetails==null){
                    sysChargeDetails=new SysChargeDetails();
                }
                sysFinancialDataAnalysisEntity.setChargeMoney(sysChargeDetails.getChargeMoney());
                sysFinancialDataAnalysisEntity.setChargeMoneyNow(sysChargeDetails.getChargeMoneyNow());
                sysFinancialDataAnalysisEntity.setChargeDebt(sysChargeDetails.getChargeDebt());
                sysFinancialDataAnalysisEntity.setChargeDebtReturn(sysChargeDetails.getChargeDebtReturn());
                sysFinancialDataAnalysisEntity.setChargeMoneyScale(sysChargeDetails.getChargeMoneyScale());
                sysFinancialDataAnalysisEntity.setChargeDebtScale(sysChargeDetails.getChargeDebtScale());
                sysFinancialDataAnalysisEntity.setYearChargeDebtScale(sysChargeDetails.getYearChargeDebtScale());
                sysFinancialDataAnalysisEntity.setYearChargeMoneyScale(sysChargeDetails.getYearChargeMoneyScale());
            }
            /***********************************应收账款数据缓存******************************/
            // 缓存key是否存在
            //应收账款统计信息
            boolean sysReceivableAccountKey = redisTemplate.hasKey("sysReceivableAccount");
            if (sysReceivableAccountKey) {
                Map<String, Object> resultMap = (Map<String, Object>) redisTemplate.opsForValue().get("sysReceivableAccount");
                SysAccountsReceivable sysAccountsReceivable=(SysAccountsReceivable)resultMap.get("sysAccountsReceivable");
                SysReceivableAccountsOwnerEntity sysReceivableAccountsOwnerEntity=(SysReceivableAccountsOwnerEntity)resultMap.get("sysAccountsReceivableMonth");
                if(sysAccountsReceivable==null){
                    sysAccountsReceivable=new SysAccountsReceivable();
                }
                if(sysReceivableAccountsOwnerEntity==null){
                    sysReceivableAccountsOwnerEntity=new SysReceivableAccountsOwnerEntity();
                }
                sysFinancialDataAnalysisEntity.setReceivableAccountsOwner(sysAccountsReceivable.getReceivableAccountsOwner());
                sysFinancialDataAnalysisEntity.setCompleteAccountsOwner(sysAccountsReceivable.getCompleteAccountsOwner());
                sysFinancialDataAnalysisEntity.setCompleteCoupon(sysAccountsReceivable.getCompleteCoupon());
                sysFinancialDataAnalysisEntity.setReceivableCoupon(sysAccountsReceivable.getReceivableCoupon());
                sysFinancialDataAnalysisEntity.setCompleteVacancy(sysAccountsReceivable.getCompleteVacancy());
                sysFinancialDataAnalysisEntity.setReceivableVacancy(sysAccountsReceivable.getReceivableVacancy());
                sysFinancialDataAnalysisEntity.setCompleteSubsidy(sysAccountsReceivable.getCompleteSubsidy());
                sysFinancialDataAnalysisEntity.setReceivableSubsidy(sysAccountsReceivable.getReceivableSubsidy());
                sysFinancialDataAnalysisEntity.setCompleteSales(sysAccountsReceivable.getCompleteSales());
                sysFinancialDataAnalysisEntity.setReceivableSales(sysAccountsReceivable.getReceivableSales());
                sysFinancialDataAnalysisEntity.setReceivableOpen(sysAccountsReceivable.getReceivableOpen());
                sysFinancialDataAnalysisEntity.setCompleteOpen(sysAccountsReceivable.getCompleteOpen());
                sysFinancialDataAnalysisEntity.setCompletePropertySubsidy(sysAccountsReceivable.getCompletePropertySubsidy());
                sysFinancialDataAnalysisEntity.setReceivablePropertySubsidy(sysAccountsReceivable.getReceivablePropertySubsidy());
                sysFinancialDataAnalysisEntity.setCompleteHouseOther(sysAccountsReceivable.getCompleteHouseOther());
                sysFinancialDataAnalysisEntity.setReceivableHouseOther(sysAccountsReceivable.getReceivableHouseOther());
                sysFinancialDataAnalysisEntity.setCompleteHouse(sysAccountsReceivable.getCompleteHouse());
                sysFinancialDataAnalysisEntity.setReceivableHouse(sysAccountsReceivable.getReceivableHouse());
                sysFinancialDataAnalysisEntity.setCouponScale(sysAccountsReceivable.getCouponScale());
                sysFinancialDataAnalysisEntity.setVacancyScale(sysAccountsReceivable.getVacancyScale());
                sysFinancialDataAnalysisEntity.setSubsidyScale(sysAccountsReceivable.getSubsidyScale());
                sysFinancialDataAnalysisEntity.setSalesScale(sysAccountsReceivable.getSalesScale());
                sysFinancialDataAnalysisEntity.setOpenScale(sysAccountsReceivable.getOpenScale());
                sysFinancialDataAnalysisEntity.setPropertySubsidyScale(sysAccountsReceivable.getPropertySubsidyScale());
                sysFinancialDataAnalysisEntity.setHouseOtherScale(sysAccountsReceivable.getHouseOtherScale());
                sysFinancialDataAnalysisEntity.setReceivableAccounts(sysReceivableAccountsOwnerEntity.getReceivableAccounts());
                sysFinancialDataAnalysisEntity.setCompleteAccounts(sysReceivableAccountsOwnerEntity.getCompleteAccounts());


            }
            /***********************************执行预算数据缓存******************************/
            // 缓存key是否存在
            //执行预算统计信息
            boolean sysBudgetDetailsKey = redisTemplate.hasKey("sysBudgetDetails");
            if (sysBudgetDetailsKey) {
                SysBudgetDetails sysBudgetDetails = (SysBudgetDetails) redisTemplate.opsForValue().get("sysBudgetDetails");
                if(sysBudgetDetails==null){
                    sysBudgetDetails=new SysBudgetDetails();
                }
                /*SysBudgetDetails sysBudgetDetailsForMonth=sysBudgetDetailsEntity.getSysBudgetDetailsForMonth();
                if(sysBudgetDetailsForMonth==null){
                    sysBudgetDetailsForMonth=new SysBudgetDetails();
                }
                SysBudgetDetails sysBudgetDetailsForYear=sysBudgetDetailsEntity.getSysBudgetDetailsForYear();
                if(sysBudgetDetailsForYear==null){
                    sysBudgetDetailsForYear=new SysBudgetDetails();
                }*/
                //注:原需求为月度信息，后改为年度信息
                sysFinancialDataAnalysisEntity.setBudgetIncome(sysBudgetDetails.getBudgetIncome());
                sysFinancialDataAnalysisEntity.setRealIncome(sysBudgetDetails.getRealIncome());
                sysFinancialDataAnalysisEntity.setBudgetProfits(sysBudgetDetails.getBudgetProfits());
                sysFinancialDataAnalysisEntity.setRealProfits(sysBudgetDetails.getRealProfits());
                //年度信息
                sysFinancialDataAnalysisEntity.setBudgetExpenses(sysBudgetDetails.getBudgetExpenses());
                sysFinancialDataAnalysisEntity.setPersonnelCost(sysBudgetDetails.getPersonnelCost());
                sysFinancialDataAnalysisEntity.setAdministrativeCost(sysBudgetDetails.getAdministrativeCost());
                sysFinancialDataAnalysisEntity.setMaterialCost(sysBudgetDetails.getMaterialCost());
                sysFinancialDataAnalysisEntity.setEnergyCost(sysBudgetDetails.getEnergyCost());
                sysFinancialDataAnalysisEntity.setEquipmentCost(sysBudgetDetails.getEquipmentCost());
                sysFinancialDataAnalysisEntity.setCleaningCost(sysBudgetDetails.getCleaningCost());
                sysFinancialDataAnalysisEntity.setAfforestCost(sysBudgetDetails.getAfforestCost());
                sysFinancialDataAnalysisEntity.setOrderMaintenanceCost(sysBudgetDetails.getOrderMaintenanceCost());
                sysFinancialDataAnalysisEntity.setCommunityActivitiesCost(sysBudgetDetails.getCommunityActivitiesCost());
                sysFinancialDataAnalysisEntity.setOtherCost(sysBudgetDetails.getOtherCost());
                sysFinancialDataAnalysisEntity.setRealExpensesTotal(sysBudgetDetails.getRealExpensesTotal());
                sysFinancialDataAnalysisEntity.setBudgetIncomeMap(sysBudgetDetails.getBudgetIncomeMap());
                sysFinancialDataAnalysisEntity.setRealIncomeMap(sysBudgetDetails.getRealIncomeMap());
                sysFinancialDataAnalysisEntity.setRealExpensesTotalMap(sysBudgetDetails.getRealExpensesTotalMap());
                sysFinancialDataAnalysisEntity.setBudgetExpensesMap(sysBudgetDetails.getBudgetExpensesMap());
                sysFinancialDataAnalysisEntity.setRealProfitsMap(sysBudgetDetails.getRealProfitsMap());
                sysFinancialDataAnalysisEntity.setRealProfitsScaleMap(sysBudgetDetails.getRealProfitsScaleMap());
            }
            map.put("financial",sysFinancialDataAnalysisEntity);
            WebSocket.sendInfo(JsonUtils.obj2JsonString(R.ok().putData(200, map, "获取成功！！")));
            return R.ok().putData(200, sysFinancialDataAnalysisEntity, "获取成功！！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取财务大屏数据展示统计详细信息失败！" + e.getMessage());
            try {
                WebSocket.sendInfo(JsonUtils.obj2JsonString(R.error(500, "获取财务大屏数据展示统计详细信息失败，服务器异常，请联系管理员！")));
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return R.error(500, "获取财务大屏数据展示统计详细信息失败，服务器异常，请联系管理员！");
        }
    }

    /**
     * 获取物业大屏界面工单统计信息
     * @return
     */
    @Override
    public Map<String, Object> sysRepairOrder() {
        return null;
    }
}
