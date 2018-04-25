package com.spring.boot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Map;

/**
 * json数据转换工具类
 * Created by xiaoyang on 2018/4/24.
 */
public class JsonUtils {
    /**
     * 换换为map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> json2Map(String json) {
        return JSON.parseObject(json, Map.class);
    }

    /**
     * 转换为json字符串（保留null值以及字段）
     *
     * @param obj 需要转化的对象
     * @return
     */
    public static String obj2JsonString(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }

}
