package com.spring.boot.service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface SysMenuService {
    /**
     * 获取公司列表数据
     * @return
     */
    Map<String,Object> getSysMenu();
    /**
     * 获取系统菜单和功能按钮
     * @return
     */
    Map<String,Object> getSysModule(String type,Long roleId);
    /**
     * 获取公司列表数据
     * @return
     */
    Map<String,Object> getSysCompanyList(int limit, int offset);

    /**
     * 新增公司信息
     * @param companyName 公司名称
     * @param companyPhone 公司联系电话
     * @param companyAddress 公司地址
     * @return
     */
    int addSysCompany(String companyName, String companyPhone, String companyAddress);

    /**
     * 更新公司信息
     * @param companyId 公司id
     * @param companyName 公司名称
     * @param companyPhone 公司联系电话
     * @param companyAddress 公司地址
     * @return
     */
    int updateSysCompanyInfo(String companyId, String companyName, String companyPhone, String companyAddress);
    /**
     * 删除公司信息
     * @param companyId 公司id
     * @return
     */
    int deleteSysCompanyById(String companyId);
    /**
     * 根据公司id获取公司数据
     * @return
     */
    Map<String,Object> findSysCompanyByCompanyId(long companyId);
}
