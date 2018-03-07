package com.spring.boot.service;

import com.spring.boot.bean.cluster.ActivityUser;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface ActivityUserService {
    /**
     * 根据用户id查找用户信息
     *
     * @param userId
     * @return
     */
    ActivityUser findByUserId(String userId);

}
