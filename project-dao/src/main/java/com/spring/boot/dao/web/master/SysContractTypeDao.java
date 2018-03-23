package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysContractType;
import com.spring.boot.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysContractTypeDao extends BaseDao<SysContractType>{
    /**
     * 合同类型列表
     * @param map
     * @return
     */
    List<SysContractType> sysContractTypeList(Map<String, Object> map);
    /**
     * 合同类型列表总数
     * @param map
     * @return
     */
    int sysContractTypeDataTotal(Map<String, Object> map);

    /**
     * 新增合同分类
     * @param map
     * @return
     */
    int addContractType(Map<String, Object> map);
    /**
     * 更新合同分类
     * @param map
     * @return
     */
    int updateContractType(Map<String, Object> map);
    /**
     * 删除合同分类
     * @param map
     * @return
     */
    int deleteContractType(Map<String, Object> map);


}
