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
     * 转换为json字符串（WriteMapNullValue:保留null值以及字段,WriteNonStringKeyAsString:将map的key不为string类型的转换为string）
     *
     * @param obj 需要转化的对象
     * @return
     */
    public static String obj2JsonString(Object obj) {
        //SerializerFeature.WriteNonStringKeyAsString,//如果key不为String 则转换为String 比如Map的key为Integer
        //SerializerFeature.WriteMapNullValue, //输出空置的字段
        return JSON.toJSONString(obj, SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.WriteMapNullValue);
        // return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue).toString();
    }

}
