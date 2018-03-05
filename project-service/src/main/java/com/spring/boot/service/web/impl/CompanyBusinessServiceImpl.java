package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.User;
import com.spring.boot.dao.web.master.CompanyDao;
import com.spring.boot.dao.web.master.UserDao;
import com.spring.boot.service.web.CompanyBusinessService;
import com.spring.boot.service.web.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class CompanyBusinessServiceImpl implements CompanyBusinessService {
    @Autowired
    private CompanyDao companyDao;

    @Override
    public int addCompany(Map<String, Object> map) {
        return companyDao.addCompany(map);
    }

    @Override
    public int updateCompanyInfo(Map<String, Object> map) {
        return companyDao.updateCompanyInfo(map);
    }

    @Override
    public int deleteCompanyInfo(Map<String, Object> map) {
        return companyDao.deleteCompanyInfo(map);
    }
}
