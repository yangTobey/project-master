package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysEnergy;
import com.spring.boot.bean.master.SysProject;
import com.spring.boot.bean.master.SysProjectEnergy;
import com.spring.boot.bean.master.SysProjectEnergyFile;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;
import com.spring.boot.dao.BaseDao;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysProjectDao extends BaseDao<SysProject> {

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
    int updateSysProject(Map<String, Object> map);

}
