package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysContractFile;
import com.spring.boot.bean.master.SysProjectEnergyFile;
import com.spring.boot.bean.master.SysQualityManageFile;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysFileBusinessService {
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
