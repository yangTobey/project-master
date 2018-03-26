package com.spring.boot.bean.master;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/3/20.
 */
public class SysQualityManageFile {
    private Long fileId;
    private Long qualityId;
    private Long companyId;
    private String fileName;
    private String fileUrl;
    private Double fileSize;
    private Integer statusCode;
    private Timestamp uploadTime;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getQualityId() {
        return qualityId;
    }

    public void setQualityId(Long qualityId) {
        this.qualityId = qualityId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }
}
