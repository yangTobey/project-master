package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysUser;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysCompanyDao {

    /**
     * 新增公司
     * @param map
     * @return
     */
    int addCompany(Map<String, Object> map);

    /**
     * 更新公司信息
     * @param map
     * @return
     */
    int updateCompanyInfo(Map<String, Object> map);
    /**
     * 删除公司信息
     * @param map
     * @return
     */
    int deleteCompanyInfo(Map<String, Object> map);
}
