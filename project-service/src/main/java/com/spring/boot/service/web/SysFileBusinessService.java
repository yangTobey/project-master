package com.spring.boot.service.web;

import com.spring.boot.bean.master.*;

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
    /**
     * 根据文件id查找公司附件文档信息
     * @param fileId
     * @return
     */
    SysCompanyFile fileSysCompanyFileById(Long fileId);

    /**
     * 根据文件id基础数据附件文档信息
     * @param fileId
     * @return
     */
    SysBasicDataFile fileSysBasicDataFileById(Long fileId);



    /**
     * 根据文件id删除品质管理文件
     * @param fileId
     * @return
     */
    int deleteSysQualityManageFileById(Long fileId);

    /**
     * 根据文件id删除功能能耗文件
     * @param fileId
     * @return
     */
    int deleteSysProjectEnergyFileById(Long fileId);

    /**
     * 根据文件id删除合同管理文件
     * @param fileId
     * @return
     */
    int deleteSysContractFileById(Long fileId);

    /**
     * 根据文件id删除基础数据文件
     * @param fileId
     * @return
     */
    int deleteSysBasicFileById(Long fileId);

    /**
     * 根据文件id删除应收账款文件
     * @param fileId
     * @return
     */
    int deleteSysAccountsReceivableFileById(Long fileId);

    /**
     * 根据文件id删除预算执行文件
     * @param fileId
     * @return
     */
    int deleteSysBudgetFileById(Long fileId);
    /**
     * 根据文件id删除公司执行文件
     * @param fileId
     * @return
     */
    int deleteSysCompanyFileById(Long fileId);


}
