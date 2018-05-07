package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysBasicData;
import com.spring.boot.bean.master.SysContractFile;
import com.spring.boot.bean.master.SysProjectEnergyFile;
import com.spring.boot.bean.master.SysQualityManageFile;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;
import com.spring.boot.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysFileDao extends BaseDao<SysQualityManageFile> {
    /**
     * 根据文件id查找品质管理附件文档信息
     * @param fileId
     * @return
     */
    SysQualityManageFile fileSysQualityManageFileById(Long fileId);
    /**
     * 根据文件id查找工程能耗附件文档信息
     * @param fileId
     * @return
     */
    SysProjectEnergyFile fileSysProjectEnergyFileById(Long fileId);
    /**
     * 根据文件id查找合同档案附件文档信息
     * @param fileId
     * @return
     */
    SysContractFile fileSysContractFileById(Long fileId);
}
