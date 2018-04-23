package com.spring.boot.service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface SysRepairOrderService {
    /**
     * 获取瑞加+系统的工单统计信息
     * @return
     */
    Map<String,Object> getRepairOrder();

}
