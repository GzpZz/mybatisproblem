package com.problem.dto;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**
 * 用于返回统一的数据格式。
 * @author Administrator
 *
 */
public class ResultData {
	private int code=200;//业务状态码。
	private String msg="OK";//消息。
	private Object data;
	private int count = 0;
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void writeToResponse(HttpServletResponse rp) {
		String jsonstr = JSONObject.toJSONString(this);
		byte[] bs;
		try {
			//实现跨域。
			rp.setHeader("Access-Control-Allow-Origin", "*");
			//将当前对象转成JSON字符串后，输出到响应体。
			bs = jsonstr.getBytes("UTF-8");
			rp.setContentType("application/json;charset=UTF-8");
			rp.getOutputStream().write(bs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		ResultData d = new ResultData();
		d.setData( new ArrayList<String>());
		String jsonstr = JSONObject.toJSONString(d);
		System.out.println(jsonstr);
	}
	
/*
 * {
 *   code:200,
 *   msg:"",
 *   data:{}
 * }
 * 
 * */
	
	/*
	 * {
	 *   code:200,
	 *   msg:"",
	 *   data:[]
	 * }
	 * 
	 * */
}
