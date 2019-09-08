package com.problem.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

 
//@WebFilter("/*")
public class CosRqFilter implements Filter {
 
	public void destroy() {
		 
	}

	 
	public void doFilter(ServletRequest request, ServletResponse rp, FilterChain chain) throws IOException, ServletException {
		
		if(rp instanceof HttpServletResponse) {
			HttpServletResponse rpp = (HttpServletResponse) rp;
			rpp.setHeader("Access-Control-Allow-Origin", "*");
		}
		chain.doFilter(request, rp);
	}

	 
	public void init(FilterConfig fConfig) throws ServletException {
		 
	}

}
