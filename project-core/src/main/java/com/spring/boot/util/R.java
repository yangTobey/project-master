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

	public static R ok(int code, String msg) {
		R r = new R();
		r.put("code", code);
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

	public R putValue(String key, Object value,String msg) {
		super.put(key, value);
		super.put("msg", msg);
		return this;
	}

	/**
	 * 接口正常返回数据（格式：返回码100，map数据集）
	 * @param code
	 * @param map
	 * @return
	 */
	public R putData(int code,Map<String, Object> map,String msg) {
		super.put("code", code);
		super.put("msg", msg);
		super.put("data", map);
		return this;
	}
	/**
	 * 接口正常返回数据（格式：返回码100，map数据集）
	 * @param code
	 * @param map
	 * @return
	 */
	public R put(int code,Map<String, Object> map,String msg) {
		R r = new R();
		map.put("code", code);
		map.put("msg", msg);
		r.putAll(map);
		return r;
	}
}
