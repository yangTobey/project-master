package com.spring.boot.service;

import com.spring.boot.bean.master.SysQualityManageFile;

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


}
