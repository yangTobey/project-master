package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysChargeDetails;
import com.spring.boot.bean.master.SysUser;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysChargeBusinessService {

    /**
     * 新增系统收费详细信息
     */
    int addSysCharge(SysChargeDetails sysChargeDetails);
    /**
     * 更新系统收费详细信息
     */
    int updateSysCharge(Map<String, Object> map);


}
