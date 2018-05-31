package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysContract;
import com.spring.boot.bean.master.SysContractFile;
import com.spring.boot.bean.master.SysContractType;
import com.spring.boot.service.SysContractService;
import com.spring.boot.service.web.SysContractBusinessService;
import com.spring.boot.util.R;
import com.spring.boot.util.SysUtil;
import com.spring.boot.util.UtilHelper;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysContractServiceImpl implements SysContractService {
    private static final Logger logger = Logger.getLogger(SysContractServiceImpl.class);
    @Autowired
    private SysContractBusinessService sysContractBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Map<String, Object> sysContractTypeList(Integer limit, Integer offset) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map.put("limit", limit);
        map.put("offset", offset);
        try {
            int count = sysContractBusinessService.sysContractTypeDataTotal(map);
            List<SysContractType> list = sysContractBusinessService.sysContractTypeList(map);

            resultMap.put("total", count);
            resultMap.put("list", list);
            return R.ok().putData(200, resultMap, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取合同分类列表失败：" + e.getMessage());
            return R.error(500, "获取合同分类列表失败,服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> sysAllContractType() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map.put("limit", null);
        map.put("offset", null);
        try {
            List<SysContractType> list = sysContractBusinessService.sysContractTypeList(map);
            resultMap.put("list", list);
            return R.ok().putData(200, resultMap, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取合同分类列表失败：" + e.getMessage());
            return R.error(500, "获取合同分类列表失败,服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> addSysContractType(String contractTypeName) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //公司编码（服务识别号）
        String contractTypeCode = "D" + RandomUtils.nextInt(10) + RandomUtils.nextInt(10) + String.valueOf(System.currentTimeMillis()).substring(5, 12) + UtilHelper.chars.charAt((int) (Math.random() * 52));
        map.put("contractTypeName", contractTypeName);
        map.put("contractTypeCode", contractTypeCode);
        map.put("createTime", UtilHelper.getNowTimeStr());
        try {
            int count = sysContractBusinessService.addSysContractType(map);
            if (count > 0) {
                return R.ok(200, "新增成功！");
            } else {
                return R.error(500, "新增失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("新增合同分类失败：" + e.getMessage());
            return R.error(500, "新增合同分类失败,服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> updateSysContractType(String contractTypeId, String contractTypeName) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map.put("contractTypeId", contractTypeId);
        map.put("contractTypeName", contractTypeName);
        map.put("status", 2);
        int count = sysContractBusinessService.updateSysContractType(map);
        if (count > 0) {
            return R.ok(200, "更新成功！");
        } else {
            return R.error(500, "更新失败！");
        }
    }

    @Override
    public Map<String, Object> deleteSysContractType(String contractTypeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map.put("contractTypeId", contractTypeId);
        int count = sysContractBusinessService.deleteSysContractType(map);
        if (count > 0) {
            return R.ok(200, "删除成功！");
        } else {
            return R.error(500, "删除失败！");
        }
    }

    @Override
    public Map<String, Object> sysContractDataList(String contractName, String contractCode, Integer statusCode, String contractStartTime, String contractEndTime, String contractTypeId, String firstPartyCompany
            , String secondPartyCompany, Integer limit, Integer offset, Long companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Long> sysUserCompanyIds = null;
        try {
            if (companyId == 0) {
                //获取用户权限下可操作的小区信息
                sysUserCompanyIds = SysUtil.getSysUserCompany();
            } else {
                sysUserCompanyIds = new ArrayList<Long>();
                sysUserCompanyIds.add(companyId);
            }
            map.put("sysUserCompanyIds", sysUserCompanyIds);
            map.put("contractName", contractName);
            map.put("contractCode", contractCode);
            map.put("companyId", companyId);
            map.put("statusCode", statusCode);
            map.put("contractStartTime", contractStartTime);
            map.put("contractEndTime", contractEndTime);
            map.put("contractTypeId", contractTypeId);
            map.put("firstPartyCompany", firstPartyCompany);
            map.put("secondPartyCompany", secondPartyCompany);
            map.put("limit", limit);
            map.put("offset", offset);
            int count = sysContractBusinessService.sysContractDataTotal(map);
            List<SysContract> list = sysContractBusinessService.sysContractDataList(map);

            map = new HashMap<String, Object>();

            map.put("total", count);
            map.put("list", list);
            return R.ok().putData(200, map, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取失败：" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addSysContract(String contractName, String contractCode, String contractMoney, String contractStartTime, String contractEndTime, Integer contractTypeId
            , String firstPartyCompany, String secondPartyCompany, String personLiableName, String fileInfo,Long companyId) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        SysContract sysContract = sysContractBusinessService.findSysContractByContractCode(contractCode);
        if (sysContract != null) {
            return R.error(500, "添加合同失败，系统已存在相同编号的合同，请重新添加或者联系系统管理员！！");
        } else {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String now=format.format(new Date());
                Date startTime = format.parse(contractStartTime);
                Date endTime = format.parse(contractEndTime);
                Date nowTime=format.parse(now);

                sysContract = new SysContract();
                sysContract.setContractCode(contractCode);
                sysContract.setContractName(contractName);
                sysContract.setContractEndTime(Timestamp.valueOf(contractEndTime));
                sysContract.setContractStartTime(Timestamp.valueOf(contractStartTime));
                sysContract.setContractMoney(Double.valueOf(contractMoney));
                sysContract.setContractTypeId(contractTypeId);
                sysContract.setFirstPartyCompany(firstPartyCompany);
                sysContract.setSecondPartyCompany(secondPartyCompany);
                sysContract.setPersonLiableName(personLiableName);
                sysContract.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
                sysContract.setCompanyId(companyId);
                //算两个日期间隔多少天
                int day = (int) ((endTime.getTime() - startTime.getTime()) / (1000*3600*24));
                if(nowTime.getTime()-endTime.getTime()>0){//到期：指的是当前日期大于合同结束时间
                    sysContract.setStatusCode(4);
                }else if(startTime.getTime()-nowTime.getTime()>0){//未开始：当前日期小于开始时间
                    sysContract.setStatusCode(1);
                }else if(day<31){//即将到期：结束日期-当前日期<31天
                    sysContract.setStatusCode(3);
                }else if(nowTime.getTime()-startTime.getTime()>0&&endTime.getTime()-nowTime.getTime()>0){
                    sysContract.setStatusCode(2);
                }
                int count = sysContractBusinessService.addSysContract(sysContract);
                if (count > 0) {
                    if (!UtilHelper.isEmpty(fileInfo)) {
                        String[] fileInfoArray;
                        //去掉最后那个逗号，在进行获取数据
                        fileInfoArray = fileInfo.substring(0, fileInfo.length()).split(";");
                        SysContractFile sysContractFile = null;
                        String[] fileData;
                        for (String fileUrl : fileInfoArray) {
                            sysContractFile = new SysContractFile();
                            //根据，逗号分隔，获取文件的地址和文件大小（文件数据格式：文件地址，文件大小）
                            fileData = fileUrl.substring(0, fileUrl.length()).split(",");
                            sysContractFile.setContractId(sysContract.getContractId());
                            sysContractFile.setFileName(fileData[0].substring(fileData[0].lastIndexOf("/") + 1, fileData[0].lastIndexOf(".")));
                            sysContractFile.setFileSize(Double.valueOf(fileData[1]));
                            sysContractFile.setFileUrl(fileData[0]);
                            sysContractFile.setUploadTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
                            sysContractBusinessService.addSysContractFile(sysContractFile);
                        }
                    }
                    return R.ok(200, "添加合同成功！！");
                } else {
                    return R.error(500, "添加合同失败，请联系系统管理员！！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("添加合同失败：" + e.getMessage());
                throw new RuntimeException();
                //return R.error(500,"服务器异常，请联系系统管理员！！");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> updateSysContract(Long contractId, String contractName, String contractCode, String contractMoney, String contractStartTime, String contractEndTime
            , String contractTypeId, String firstPartyCompany, String secondPartyCompany, String personLiableName, String fileInfo,Long companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map.put("contractId", contractId);
        map.put("contractName", contractName);
        map.put("contractCode", contractCode);
        map.put("contractMoney", contractMoney);
        map.put("contractStartTime", contractStartTime);
        map.put("contractEndTime", contractEndTime);
        map.put("contractTypeId", contractTypeId);
        map.put("firstPartyCompany", firstPartyCompany);
        map.put("secondPartyCompany", secondPartyCompany);
        map.put("personLiableName", personLiableName);
        map.put("createTime", Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        map.put("companyId", companyId);
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String now=format.format(new Date());
            Date startTime = format.parse(contractStartTime);
            Date endTime = format.parse(contractEndTime);
            Date nowTime=format.parse(now);
            //算两个日期间隔多少天
            int day = (int) ((endTime.getTime() - startTime.getTime()) / (1000*3600*24));
            if(nowTime.getTime()-endTime.getTime()>0){//到期：指的是当前日期大于合同结束时间
                map.put("statusCode", 4);
            }else if(startTime.getTime()-nowTime.getTime()>0){//未开始：当前日期小于开始时间
                map.put("statusCode", 1);
            }else if(day<31){//即将到期：结束日期-当前日期<31天
                map.put("statusCode", 3);
            }else if(nowTime.getTime()-startTime.getTime()>0&&endTime.getTime()-nowTime.getTime()>0){
                map.put("statusCode", 2);
            }
            SysContract sysContract = sysContractBusinessService.findSysContractByContractCode(contractCode);
            if (sysContract != null) {
                //判断是否已存在该编号的合同
                if (!contractId.equals(sysContract.getContractId())) {
                    return R.error(500, "更新合同失败，系统已存在相同编号的合同，请重新添加或者联系系统管理员！！");
                }
            }
            int count = sysContractBusinessService.updateSysContract(map);
            if (count > 0) {
                if (!UtilHelper.isEmpty(fileInfo)) {
                    //先删除原有数据库记录
                    //sysContractBusinessService.deleteSysContractFileByContractId(contractId);
                    String[] fileInfoArray;
                    //去掉最后那个逗号，在进行获取数据
                    fileInfoArray = fileInfo.substring(0, fileInfo.length()).split(";");
                    SysContractFile sysContractFile = null;
                    String[] fileData;
                    for (String fileUrl : fileInfoArray) {
                        sysContractFile = new SysContractFile();
                        //根据，逗号分隔，获取文件的地址和文件大小（文件数据格式：文件地址，文件大小）
                        fileData = fileUrl.substring(0, fileUrl.length()).split(",");
                        sysContractFile.setContractId(contractId);
                        sysContractFile.setFileName(fileData[0].substring(fileData[0].lastIndexOf("/") + 1, fileData[0].lastIndexOf(".")));
                        sysContractFile.setFileSize(Double.valueOf(fileData[1]));
                        sysContractFile.setFileUrl(fileData[0]);
                        sysContractFile.setUploadTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
                        sysContractBusinessService.addSysContractFile(sysContractFile);
                    }
                }
                return R.ok(200, "更新合同信息成功！！！");
            } else {
                return R.error(500, "更新合同失败，请联系系统管理员！！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新合同失败：" + e.getMessage());
            throw new RuntimeException();
            //return R.error(500,"服务器异常，请联系系统管理员！！");
        }
    }

    @Override
    public Map<String, Object> deleteSysContract(Long contractId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map.put("contractId", contractId);
        try {
            int count = sysContractBusinessService.deleteSysContract(map);
            if (count > 0) {
                return R.ok(200, "删除合同成功！！");
            } else {
                return R.error(500, "删除合同失败，请联系系统管理员！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除合同失败：" + e.getMessage());
            return R.error(500, "服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> getSysContractExpireDataTotal(Long companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //获取用户权限下可操作的小区信息
        List<Long> sysUserCompanyIds = null;
        if (companyId == 0) {
            //获取用户权限下可操作的小区信息
            sysUserCompanyIds = SysUtil.getSysUserCompany();
        } else {
            sysUserCompanyIds = new ArrayList<Long>();
            sysUserCompanyIds.add(companyId);
        }
        /*组装请求参数数据*/
        //status_code为3时，表示即将过期
        map.put("statusCode", 3);
        map.put("sysUserCompanyIds", sysUserCompanyIds);
        /*组装结果数据*/
        try {
            resultMap.put("total", sysContractBusinessService.sysContractDataTotal(map));
            return R.ok().putData(200, resultMap, "获取即将过期合同信息总条数成功！！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取合即将过期同档案数据失败：" + e.getMessage());
            return R.error(500, "服务器异常！！");
        }
    }

    @Override
    public Map<String, Object> findSysContractById(Long contractId) {
        try {
            SysContract sysContract = sysContractBusinessService.findSysContractById(contractId);
            if(null!=sysContract){
                List<SysContractFile> sysContractFile=sysContractBusinessService.findSysContractFileById(contractId);
                sysContract.setFileList(sysContractFile);
                return R.ok().putData(200, sysContract, "根据id查找合同文档信息成功！");
            }else{
                return R.error(500, "根据id查找合同文档信息失败，服务器异常，请联系系统管理员！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("根据id查找合同文档信息失败：" + e.getMessage());
            return R.error(500, "根据id查找合同文档信息失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String, Object> findSysContractFileById(long contractId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            List<SysContractFile> fileList = sysContractBusinessService.findSysContractFileById(contractId);
            if (fileList != null) {
                resultMap.put("list", fileList);
                return R.ok().putData(200, resultMap, "根据id查找品质管理数据成功！");
            } else {
                return R.error(500, "不存在文件！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取品质管理文件失败：" + e.getMessage());
            return R.error(500, "获取品质管理文件失败，服务器异常，请联系系统管理员！");
        }
    }

    @Override
    public Map<String, Object> sysContractAnalysisData(Long companyId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        List<Long> sysUserCompanyIds = null;
        try {
            if (companyId == 0) {
                //获取用户权限下可操作的小区信息
                sysUserCompanyIds = SysUtil.getSysUserCompany();
            } else {
                sysUserCompanyIds = new ArrayList<Long>();
                sysUserCompanyIds.add(companyId);
            }
            map.put("sysUserCompanyIds", sysUserCompanyIds);
            List<SysContract> list = sysContractBusinessService.sysContractAnalysisData(map);
            int contractWorking = 0;
            int contractNumber = 0;
            int contractexpired = 0;
            for (SysContract sysContract : list) {
                if (sysContract.getStatusCode() == 2||sysContract.getStatusCode() == 3) {
                    contractWorking += sysContract.getTotal();
                } else if (sysContract.getStatusCode() == 4) {
                    contractexpired += sysContract.getTotal();
                }
                contractNumber += sysContract.getTotal();
            }
            /*组装结果数据*/
            resultMap.put("contractWorking", contractWorking);
            resultMap.put("contractNumber", contractNumber);
            resultMap.put("contractexpired", contractexpired);
            return R.ok().putData(200, resultMap, "获取数据成功！！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取合同档案统计数据失败：" + e.getMessage());
            return R.error(500, "服务器异常！！");
        }
    }

    @Override
    public void updateSysContractExpire() {
        System.out.println("定时器执行！！");
        //（1：未执行，2：在执行，3：即将过期，4：已经过期，5：已经删除）
        //1：未执行
        sysContractBusinessService.updateSysContractExpire(1,UtilHelper.getNowTimeStr());
        //2：在执行
        sysContractBusinessService.updateSysContractExpire(2,UtilHelper.getNowTimeStr());
        //3：即将过期
        sysContractBusinessService.updateSysContractExpire(3,UtilHelper.getNowTimeStr());
        //4：已经过期
        sysContractBusinessService.updateSysContractExpire(4,UtilHelper.getNowTimeStr());
    }
}
