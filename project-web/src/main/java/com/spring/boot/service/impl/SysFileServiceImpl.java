package com.spring.boot.service.impl;

import com.spring.boot.bean.master.*;
import com.spring.boot.service.SysFileService;
import com.spring.boot.service.web.SysFileBusinessService;
import com.spring.boot.util.R;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

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

    @Override
    public SysProjectEnergyFile fileSysProjectEnergyFileById(Long fileId) {
        return sysFileBusinessService.fileSysProjectEnergyFileById(fileId);
    }

    @Override
    public SysContractFile fileSysContractFileById(Long fileId) {
        return sysFileBusinessService.fileSysContractFileById(fileId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> deleteFileByFileId(HttpServletRequest request, String fileIds, String type, String fileUrl) {

        String filePath = "";
        boolean success = false;
        //删除在新增数据时，还没保存对应实体信息的文件，避免操作太多，造成服务器垃圾文件太多
        if (StringUtils.isEmpty(fileIds)) {
            filePath = request.getSession().getServletContext().getRealPath("/") + fileUrl;
            File file = new File(filePath);
            if (file.exists()) {
                success = file.delete();
            }
        } else {
            //去掉最后那个逗号，在进行获取数据
            String[] fileInfoArray = fileIds.substring(0, fileIds.length()).split(";");
            for (String fileId : fileInfoArray) {
                //每次循环，将success设置为false；
                success = false;
                Long id = Long.valueOf(fileId);
                int count = 0;
                if ("quality".equals(type)) {
                    SysQualityManageFile sysQualityManageFile = sysFileBusinessService.fileSysQualityManageFileById(id);
                    if (null != sysQualityManageFile) {
                        filePath = request.getSession().getServletContext().getRealPath("/") + sysQualityManageFile.getFileUrl();
                        count = sysFileBusinessService.deleteSysQualityManageFileById(id);

                    }
                } else if ("projectEnergy".equals(type)) {
                    SysProjectEnergyFile sysProjectEnergyFile = sysFileBusinessService.fileSysProjectEnergyFileById(id);
                    if (null != sysProjectEnergyFile) {
                        filePath = request.getSession().getServletContext().getRealPath("/") + sysProjectEnergyFile.getFileUrl();
                        count = sysFileBusinessService.deleteSysProjectEnergyFileById(id);
                    }
                } else if ("contract".equals(type)) {
                    SysContractFile sysContractFile = sysFileBusinessService.fileSysContractFileById(id);
                    if (null != sysContractFile) {
                        filePath = request.getSession().getServletContext().getRealPath("/") + sysContractFile.getFileUrl();
                        count = sysFileBusinessService.deleteSysContractFileById(id);
                    }
                } else if ("basic".equals(type)) {
                    SysBasicDataFile sysBasicDataFile = sysFileBusinessService.fileSysBasicDataFileById(id);
                    if (null != sysBasicDataFile) {
                        filePath = request.getSession().getServletContext().getRealPath("/") + sysBasicDataFile.getFileUrl();
                        count = sysFileBusinessService.deleteSysBasicFileById(id);
                    }
                }else if("company".equals(type)){
                    SysCompanyFile sysCompanyFile=sysFileBusinessService.fileSysCompanyFileById(id);
                    if(null!=sysCompanyFile){
                        filePath=request.getSession().getServletContext().getRealPath("/") + sysCompanyFile.getFileUrl();
                        count=sysFileBusinessService.deleteSysCompanyFileById(id);
                    }
                }/*else if("budget".equals(type)){
                    SysContractFile sysContractFile=sysFileBusinessService.fileSysContractFileById(id);
                    if(null!=sysContractFile){
                        filePath=request.getSession().getServletContext().getRealPath("/") + sysContractFile.getFileUrl();
                        count=sysFileBusinessService.deleteSysBudgetFileById(id);
                    }
                }*/
                File file = new File(filePath);
                if (count > 0 && file.exists()) {
                    success = file.delete();
                }
                if (!success) {
                    break;
                }
            }
        }
        if (success) {
            return R.ok(200, "删除成功！");
        } else {
            return R.ok(500, "删除失败！");
        }


    }
}
