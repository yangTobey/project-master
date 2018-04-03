package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysChargeDetails;
import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysChargeDao extends BaseDao<SysChargeDetails> {

    /**
     * 新增系统收费详细信息
     */
    int addSysCharge(SysChargeDetails sysChargeDetails);
}
