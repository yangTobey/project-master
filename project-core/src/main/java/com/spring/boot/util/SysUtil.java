package com.spring.boot.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/4.
 */
public class SysUtil {
    /**
     * 获取登录用户权限下可操作的公司id的组合
     *
     * @return
     */
    public static List<Long> getSysUserCompany() {
        Session session = null;
        //公司id集合
        List<Long> sysUserCompanyIds = null;
        if (null == SecurityUtils.getSubject()) {
            sysUserCompanyIds = new ArrayList<>();
            sysUserCompanyIds.add(0L);
        } else {
            session = SecurityUtils.getSubject().getSession();
            //公司id集合
            sysUserCompanyIds = (List<Long>) session.getAttribute("sysUserCompany");
        }
        if (null == sysUserCompanyIds) {
            sysUserCompanyIds = new ArrayList<>();
            sysUserCompanyIds.add(0L);
        }
        return sysUserCompanyIds;
    }
}
