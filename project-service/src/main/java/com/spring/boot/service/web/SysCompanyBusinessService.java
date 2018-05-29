package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysUser;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysCompanyBusinessService {

    /**
     * 获取公司列表数据
     *
     * @return
     */
    List<SysCompany> getSysCompanyList(Map<String, Object> map);

    /**
     * 获取公司列表数据条数
     *
     * @return
     */
   int getSysCompanyListTotal(Map<String, Object> map);

    /**
     * 根据公司id查找子公司总数
     *
     * @return
     */
    int querySubsidiaryCount(Map<String, Object> map);
    /**
     * 新增公司
     *
     * @param map
     * @return
     */
    int addSysCompany(Map<String, Object> map);

    /**
     * 更新公司信息
     *
     * @param map
     * @return
     */
    int updateSysCompanyInfo(Map<String, Object> map);

    /**
     * 删除公司信息
     *
     * @param map
     * @return
     */
    int deleteSysCompanyById(Map<String, Object> map);
    /**
     * 根据公司id获取公司数据
     * @return
     */
    SysCompany findSysCompanyByCompanyId(Map<String, Object> map);
    /**
     * 获取全部公司列表数据
     * @return
     */
    List<SysCompany> getAllSysCompany();

    /**
     * 获取全部公司的主键id
     * @return
     */
    List<Long> getAllSysCompanyId();
}
