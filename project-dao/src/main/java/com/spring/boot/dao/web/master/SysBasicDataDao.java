package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysBasicData;
import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;
import com.spring.boot.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysBasicDataDao extends BaseDao<SysBasicDataEntity> {
    /**
     * 获取公司基础数据分析统计分析信息
     *
     * @return
     */
    SysBasicDataEntity sysBasicDataAnalysisData(Map<String, Object> map);
    /**
     * 根据公司id、年份、月份查找系统记录
     * @param companyId
     * @param year
     * @param month
     * @return
     */
    SysBasicData sysBasicDataRecord(@Param("companyId")Long companyId, @Param("year")Integer year,@Param("month") Integer month);

    /**
     * 获取基础数据列表条数
     *
     * @param map
     * @return
     */
    int sysBasicDataAnalysisListTotal(Map<String, Object> map);
    /**
     * 获取公司基础数据列表
     *
     * @return
     */
    List<SysBasicDataEntity> sysBasicDataAnalysisList(Map<String, Object> map);
    /**
     * 新增
     *
     * @param map
     * @return
     */
    int addSysBasicData(Map<String, Object> map);

    /**
     * 更新信息
     *
     * @param map
     * @return
     */
    int updateSysBasicData(Map<String, Object> map);

    /**
     * 删除信息
     *
     * @param map
     * @return
     */
    int deleteSysBasicData(Map<String, Object> map);
    /**
     * 根据id查找基础数据信息
     *
     * @return
     */
    SysBasicDataEntity findSysBasicDataById(Map<String, Object> map);
}
