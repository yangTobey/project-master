package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysUser;
import com.spring.boot.bean.master.SysUserCompany;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysUserCompanyBusinessService {

    /**
     * 获取用户权限下可以操作的公司
     *
     * @return
     */
    List<SysUserCompany>  sysUserCompany(Long userId);

    /**
     * 新增信息
     * @param sysUserCompany
     * @return
     */
    int saveSysUserCompany(SysUserCompany sysUserCompany);
    /**
     * 根据id删除信息
     *
     * @param userId
     * @return
     */
    int deleteSysUserCompany(Long userId);

}
