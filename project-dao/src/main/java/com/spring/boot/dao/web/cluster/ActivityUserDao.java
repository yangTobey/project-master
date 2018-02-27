package com.spring.boot.dao.web.cluster;

import com.spring.boot.bean.cluster.ActivityUser;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface ActivityUserDao {

    ActivityUser findByUserId(Map<String, Object> map);


}
