package com.spring.boot.service.web;

import com.spring.boot.bean.master.User;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface CompanyBusinessService {

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
}
