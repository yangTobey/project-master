package com.spring.boot.service.web;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysContractBusinessService {

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
