package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysContract;
import com.spring.boot.bean.master.SysContractFile;
import com.spring.boot.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysContractDao extends BaseDao<SysContract>{

    /**
     * 新增附件文档信息
     * @param sysContractFile
     * @return
     */
    int addSysContractFile(SysContractFile sysContractFile);
    /**
     * 更新合同
     * @param map
     * @return
     */
    int updateSysContract(Map<String, Object> map);
    /**
     * 更新合同
     * @param map
     * @return
     */
    int deleteSysContract(Map<String, Object> map);
    /**
     * 根据合同编号查找合同
     * @param contractCode
     * @return
     */
    SysContract findSysContractByContractCode(String contractCode);
    /**
     * 合同列表
     * @param map
     * @return
     */
    List<SysContract> sysContractDataList(Map<String, Object> map);
    /**
     * 合同列表总数
     * @param map
     * @return
     */
    int sysContractDataTotal(Map<String, Object> map);
    /**
     * 报表统计列表
     * @param map
     * 注：如果mybatis需要使用if:test判断参数，需要在dao层加上@Param
     * @return
     */
    List<SysContract> sysContractAnalysisData(Map<String, Object> map);
    /**
     * 根据id获取信息
     * @return
     */
    SysContract findSysContractById(Long contractId);
    /**
     * 根据id获取附件文档信息
     * @return
     */
    List<SysContractFile> findSysContractFileById(Long contractId);

    /**
     * 根据id删除文档附件信息（用于更新时操作）
     * @param contractId
     * @return
     */
    int deleteSysContractFileByContractId(Long contractId);
    /**
     * 更新快到期的合同状态
     */
    void updateSysContractExpire(@Param("type")Integer type,@Param("nowTime")String nowTime);
}
