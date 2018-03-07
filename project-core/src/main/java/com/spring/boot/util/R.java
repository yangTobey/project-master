package com.spring.boot.util;

import java.util.HashMap;
import java.util.Map;

/**
 * controller返回数据类型封装
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("code", 0);
	}
	
	public static R error() {
		return error(500, "未知异常，请联系管理员！！");
	}
	
	public static R error(String msg) {
		return error(500, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	/**
	 * 接口正常返回数据（格式：返回码100，map数据集）
	 * @param code
	 * @param map
	 * @return
	 */
	public R put(String code,Map<String, Object> map) {
		super.put(code, map);
		return this;
	}
}
