package com.spring.boot.service;

import com.spring.boot.bean.master.User;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface CompanyService {

    /**
     * 新增公司信息
     * @param companyName 公司名称
     * @param companyPhone 公司联系电话
     * @param companyAddress 公司地址
     * @return
     */
    int addCompany(String companyName,String companyPhone,String companyAddress);

    /**
     * 更新公司信息
     * @param companyId 公司id
     * @param companyName 公司名称
     * @param companyPhone 公司联系电话
     * @param companyAddress 公司地址
     * @return
     */
    int updateCompanyInfo(String companyId,String companyName,String companyPhone,String companyAddress);
}
