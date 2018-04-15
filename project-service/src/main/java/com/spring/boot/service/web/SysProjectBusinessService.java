package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysMenu;
import com.spring.boot.bean.master.SysProject;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface SysProjectBusinessService {
    /**
     * 添加工程信息
     * @param sysProject
     * @return
     */
    int addSysProject(SysProject sysProject);
    /**
     * 添加工程信息
     * @param map
     * @return
     */
    int updateSysProject(Map<String,Object> map);
}
