package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysContract;
import com.spring.boot.bean.master.SysContractType;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysContractBusinessService {
    /**
     * 合同类型列表
     * @param map
     * @return
     */
    List<SysContractType> sysContractTypeList(Map<String, Object> map);
    /**
     * 合同类型列表总数
     * @param map
     * @return
     */
    int sysContractTypeDataTotal(Map<String, Object> map);

    /**
     * 新增合同分类
     * @param map
     * @return
     */
    int addSysContractType(Map<String, Object> map);
    /**
     * 更新合同分类
     * @param map
     * @return
     */
    int updateSysContractType(Map<String, Object> map);

    /**
     * 删除合同分类
     * @param map
     * @return
     */
    int deleteSysContractType(Map<String, Object> map);

    /**
     * 新增合同
     * @param map
     * @return
     */
    int addSysContract(Map<String, Object> map);
    /**
     * 更新合同
     * @param map
     * @return
     */
    int updateSysContract(Map<String, Object> map);
    /**
     * 删除合同
     * @param map
     * @return
     */
    int deleteSysContract(Map<String, Object> map);

    /**
     * 根据合同编号查找合同
     * @param contractCode
     * @return
     */
    SysContract findSysContractByContractCode(String contractCode);
    /**
     * 合同列表
     * @param map
     * @return
     */
   List<SysContract> sysContractDataList(Map<String, Object> map);
    /**
     * 合同列表总数
     * @param map
     * @return
     */
    int sysContractDataTotal(Map<String, Object> map);

    /**
     * 报表统计列表
     * @param companyId
     * @return
     */
    List<SysContract> sysContractAnalysisData(Long companyId);
}
