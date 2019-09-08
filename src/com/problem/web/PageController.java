package com.problem.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@WebServlet("/index.html")
public class PageController extends HttpServlet{
	
	public void doGet(HttpServletRequest rq,HttpServletResponse rp) throws IOException {
		HttpSession ss = rq.getSession();
		String status = null;
		if( ss .getAttribute("loginuser")!= null ) {
			status = "login";
		}else {
			status = "logout";
		}
		String path  = rq.getServletContext().getRealPath("/");
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new File( path ));
        cfg.setDefaultEncoding("UTF-8");
        
      
        Map root = new HashMap();
         
        root.put("loginstate", status);
        root.put("testinfo", "When you see this,you win!!!");
        root.put("testinfo", "»Æ´óÎÄ!");
      
        Template temp = cfg.getTemplate("index.html");

       
        rp.setContentType("text/html;charset=UTF-8");
        try {
			temp.process(root, rp.getWriter());
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void  doPost(HttpServletRequest rq,HttpServletResponse rp) throws IOException{
		rp.setContentType("text/html;charset=Unicode");
		String path  = rq.getServletContext().getRealPath("/");
		String gbkhtml = path +"/gbk.html";
		BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(gbkhtml),"GBK"));
		while(true) {
			String data = buf.readLine();
			if(data == null )break;
			rp.getWriter().write(data);
		}
		buf.close();
		
		 
	}
}
