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
public interface SysEnergyDao extends BaseDao<SysEnergy> {
    /**
     * 添加能耗信息
     * @return
     */
    int addSysEnergy(SysEnergy sysEnergy);

    /**
     * 添加能耗信息
     * @return
     */
    int updateSysEnergy(Map<String, Object> map);

}
