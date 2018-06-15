package com.spring.boot.service;

import com.spring.boot.bean.master.SysContractFile;
import com.spring.boot.bean.master.SysProjectEnergyFile;
import com.spring.boot.bean.master.SysQualityManageFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by xiaoyang on 2018/5/6.
 *
 */
public interface SysFileService {

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

    /**
     * 根据文件id和操作类型删除文件信息
     * @param fileIds
     * @param type
     * @return
     */
    Map<String,Object> deleteFileByFileId(HttpServletRequest request,String fileIds, String type);


}
