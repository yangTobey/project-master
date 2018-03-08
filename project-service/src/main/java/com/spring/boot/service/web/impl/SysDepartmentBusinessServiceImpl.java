package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysDepartment;
import com.spring.boot.dao.web.master.SysCompanyDao;
import com.spring.boot.dao.web.master.SysDepartmentDao;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysDepartmentBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysDepartmentBusinessServiceImpl implements SysDepartmentBusinessService {
    @Autowired
    private SysDepartmentDao sysDepartmentDao;

    @Override
    public List<SysDepartment> getSysDepartmentInfo() {
        return sysDepartmentDao.queryList();
    }

    @Override
    public int addSysDepartment(Map<String, Object> map) {
        return sysDepartmentDao.save(map);
    }

    @Override
    public int updateSysDepartmentInfo(Map<String, Object> map) {
        return sysDepartmentDao.update(map);
    }

    @Override
    public int deleteSysDepartment(Map<String, Object> map) {
        return sysDepartmentDao.delete(map);
    }
}
