package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysQualityManageFile;
import com.spring.boot.service.SysFileService;
import com.spring.boot.service.web.SysFileBusinessService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaoyan on 2018/5/6.
 */
@Service
public class SysFileServiceImpl implements SysFileService {
    private static final Logger logger = Logger.getLogger(SysFileServiceImpl.class);
    @Autowired
    private SysFileBusinessService sysFileBusinessService;

    @Override
    public SysQualityManageFile fileSysQualityManageFileById(Long fileId) {
        return sysFileBusinessService.fileSysQualityManageFileById(fileId);
    }
}
