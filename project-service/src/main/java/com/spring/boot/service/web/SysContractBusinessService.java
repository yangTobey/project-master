package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysContract;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysContractBusinessService {

    /**
     * 新增合同分类
     * @param map
     * @return
     */
    int addContractType(Map<String, Object> map);
    /**
     * 更新合同分类
     * @param map
     * @return
     */
    int updateContractType(Map<String, Object> map);

    /**
     * 删除合同分类
     * @param map
     * @return
     */
    int deleteContractType(Map<String, Object> map);

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
}
