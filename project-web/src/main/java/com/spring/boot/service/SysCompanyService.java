package com.spring.boot.service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface SysCompanyService {
    /**
     * 获取公司列表数据
     * @return
     */
    Map<String,Object> getSysCompanyList(Integer limit,Integer offset);

    /**
     * 新增公司信息
     * @param companyName 公司名称
     * @param companyPhone 公司联系电话
     * @param companyAddress 公司地址
     * @return
     */
    Map<String,Object> addSysCompany(String companyName,String companyPhone,String companyAddress);

    /**
     * 更新公司信息
     * @param companyId 公司id
     * @param companyName 公司名称
     * @param companyPhone 公司联系电话
     * @param companyAddress 公司地址
     * @return
     */
    Map<String,Object> updateSysCompanyInfo(String companyId,String companyName,String companyPhone,String companyAddress);
    /**
     * 删除公司信息
     * @param companyId 公司id
     * @return
     */
    Map<String,Object> deleteSysCompanyById(Long companyId);
    /**
     * 根据公司id获取公司数据
     * @return
     */
    Map<String,Object> findSysCompanyByCompanyId(long companyId);
    /**
     * 获取全部公司列表数据
     * @return
     */
    Map<String,Object> getAllSysCompany();
}
