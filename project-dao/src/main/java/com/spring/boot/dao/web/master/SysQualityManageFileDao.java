package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysQualityManage;
import com.spring.boot.bean.master.SysQualityManageFile;
import com.spring.boot.bean.master.entity.SysQualityManageEntity;
import com.spring.boot.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysQualityManageFileDao extends BaseDao<SysQualityManageFile> {

    /**
     * 根据品质id查找文件
     * @return
     */
    List<SysQualityManageFile> findSysQualityManageFileById(Long qualityId);


}
