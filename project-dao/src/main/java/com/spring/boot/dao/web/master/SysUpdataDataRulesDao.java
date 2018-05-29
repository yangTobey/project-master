package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysUpdateDataRules;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaoyang on 2018/2/6.
 */
public interface SysUpdataDataRulesDao {
    SysUpdateDataRules findSysUpdateDataRules();
}
