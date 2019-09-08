package com.problem.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.problem.ano.Param;
import com.problem.ano.RequestMapping;
import com.problem.ano.ResponseBody;
import com.problem.dao.UserDao;
import com.problem.dto.ResultData;
import com.problem.entity.User;

@WebServlet("/user/*")
public class UserController extends  DispatcherServlet {
	
	public UserDao queryuser() throws IOException {
		String res="config.xml";
		InputStream	in = Resources.getResourceAsStream(res);
			SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(in);
			SqlSession ss=ssf.openSession(true);
			UserDao mapper2 = ss.getMapper(UserDao.class);
			return mapper2;	
	}
	
	@RequestMapping("login.action")
	public String login(HttpSession ss,@Param(isjavabean=true)User user) throws ServletException, IOException {
		System.out.println(user);//从前端获取的值
		User rsu = (User) ss.getAttribute("loginuser");
		if(rsu != null ) {
			 return "redirect:/";
		}else {
			System.out.println("coming");
			  rsu =queryuser().findByUserNameAndPassword(user);
			  if(rsu != null ) {
				  //登陆成功。
				  ss.setAttribute("loginuser", rsu );
				  return "redirect:/";
			  }else {
				   return "/login.html";
			  }
		}
	}
	
	@RequestMapping("logout.action")
	public String logout(HttpSession ss) {
		ss.invalidate();
		return "redirect:/";
	}
 
}
