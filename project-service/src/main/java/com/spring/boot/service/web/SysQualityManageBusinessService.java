package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysQualityManage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysQualityManageBusinessService {

    /**
     * 获取列表数据
     * @return
     */
    List<SysQualityManage> getSysQualityManageList(Map<String, Object> map);

    /**
     * 获取列表数据条数
     * @param map
     * @return
     */
    int getSysQualityManageListTotal(Map<String, Object> map);

    /**
     * 新增质量管理信息
     * @param map
     * @return
     */
    int addSysQualityManage(Map<String, Object> map);

    /**
     * 更新信息
     * @param map
     * @return
     */
    int updateSysQualityManage(Map<String, Object> map);
    /**
     * 删除信息
     * @param map
     * @return
     */
    int deleteSysQualityManageById(Map<String, Object> map);
    /**
     * 根据公司id获取公司数据
     * @return
     */
    SysQualityManage findSysQualityManageById(Map<String, Object> map);
}
