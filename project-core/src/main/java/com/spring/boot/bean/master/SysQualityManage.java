package com.spring.boot.bean.master;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
public class SysQualityManage {
    /**
     * 主键ID
     */
    private Long qualityId;
    /**
     * 公司id
     */
    @NotNull(message = "公司id不能为空！")
    @Range(min = 1,max = Integer.MAX_VALUE,message = "公司id格式不正确，或者不符合常理！！")
    private Long companyId;
    /**
     * 年份
     */
    @NotNull(message = "年份不能为空！")
    @Range(message = "年份格式不正确，或者不符合常理！！")
    private Integer year;
    /**
     * 月份
     */
    @NotNull(message = "月份不能为空！")
    @Range(min=1, max=12, message = "月份格式错误")
    private Integer month;
    /**
     * 月品质检查项
     */
    @NotNull(message = "月品质检查项不能为空！")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "月品质检查项格式不正确，或者不符合常理！")
    private Integer qualityCheck;
    /**
     * 月品质检查合格项
     */
    @NotNull(message = "月品质检查合格项不能为空！")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "月品质检查合格项格式不正确，或者不符合常理！")
    private Integer qualityCheckPass;

    private Integer qualityCheckFail;
    /**
     * 月安全事故数量项
     */
    @NotNull(message = "月安全事故数量项不能为空！")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "月安全事故数量项格式不正确，或者不符合常理！")
    private Integer securityEvent;
    /**
     * 品质检查未整改项
     */
    @NotNull(message = "品质检查未整改项不能为空！")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "品质检查未整改项格式不正确，或者不符合常理！")
    private Integer qualityCheckUnmodified;
    /**
     * 上个月品质检查未整改项
     */
    @NotNull(message = "上个月品质检查未整改项不能为空！")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "上个月品质检查未整改项格式不正确，或者不符合常理！")
    private Integer lastQualityCheckUnmodified;
    private Integer statusCode;
    private Date createTime;

    private String companyName;
    /**
     * 新增或者更新的文件信息
     */
    private String fileInfo;

    private Integer fileNum;

    List<SysQualityManageFile> fileList;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getQualityCheck() {
        return qualityCheck;
    }

    public void setQualityCheck(Integer qualityCheck) {
        this.qualityCheck = qualityCheck;
    }

    public Integer getQualityCheckPass() {
        return qualityCheckPass;
    }

    public void setQualityCheckPass(Integer qualityCheckPass) {
        this.qualityCheckPass = qualityCheckPass;
    }

    public Integer getQualityCheckFail() {
        return qualityCheckFail;
    }

    public void setQualityCheckFail(Integer qualityCheckFail) {
        this.qualityCheckFail = qualityCheckFail;
    }

    public Integer getSecurityEvent() {
        return securityEvent;
    }

    public void setSecurityEvent(Integer securityEvent) {
        this.securityEvent = securityEvent;
    }

    public Integer getQualityCheckUnmodified() {
        return qualityCheckUnmodified;
    }

    public void setQualityCheckUnmodified(Integer qualityCheckUnmodified) {
        this.qualityCheckUnmodified = qualityCheckUnmodified;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getFileNum() {
        return fileNum;
    }

    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
    }

    public List<SysQualityManageFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<SysQualityManageFile> fileList) {
        this.fileList = fileList;
    }

    public Integer getLastQualityCheckUnmodified() {
        return lastQualityCheckUnmodified;
    }

    public void setLastQualityCheckUnmodified(Integer lastQualityCheckUnmodified) {
        this.lastQualityCheckUnmodified = lastQualityCheckUnmodified;
    }

    public String getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(String fileInfo) {
        this.fileInfo = fileInfo;
    }
}
