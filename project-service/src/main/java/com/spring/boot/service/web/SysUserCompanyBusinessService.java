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

}
