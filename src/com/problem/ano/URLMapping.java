package com.problem.ano;

import java.lang.reflect.Method;

public class URLMapping {
	private String value;
	private String method;
	private Method invokeMethod;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Method getInvokeMethod() {
		return invokeMethod;
	}
	public void setInvokeMethod(Method invokeMethod) {
		this.invokeMethod = invokeMethod;
	}
	@Override
	public String toString() {
		return "URLMapping [value=" + value + ", method=" + method + ", invokeMethod=" + invokeMethod + "]";
	}
	 
	
}
