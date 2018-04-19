package com.spring.boot.service;

import com.spring.boot.bean.cluster.ActivityUser;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface SysPropertyDataAnalysisService {
    /**
     * 根据用户id查找用户信息
     *
     * @return
     */
    Map<String,Object> sysPropertyDataAnalysis();

}
