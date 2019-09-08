package com.problem.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class PostParameterEncodingFilter implements Filter {
	private String charset="UTF-8";
    
     
	public void destroy() {
	 
	}
	 
	public void init(FilterConfig fConfig) throws ServletException {
	    
	}
	 
	
	public void doFilter(ServletRequest rq, ServletResponse rp, FilterChain chain) throws IOException, ServletException {
		rq.setCharacterEncoding( charset );
		//rp.setCharacterEncoding(charset);
		chain.doFilter(rq, rp); 
		
	}

	

}
