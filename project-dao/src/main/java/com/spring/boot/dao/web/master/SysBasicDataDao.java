package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysBasicData;
import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.dao.BaseDao;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysBasicDataDao extends BaseDao<SysBasicData> {

    /**
     * 获取公司列表条数
     *
     * @param map
     * @return
     */
    int getSysCompanyListTotal(Map<String, Object> map);
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
}
