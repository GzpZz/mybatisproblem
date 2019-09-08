package com.problem.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.problem.entity.User;
public class test {
	/*@Test
	public void testuser() throws IOException {
		String res="config.xml";
		InputStream	in = Resources.getResourceAsStream(res);
			SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(in);
			SqlSession ss=ssf.openSession(true);
			UserDao mapper2 = ss.getMapper(UserDao.class);
			User u = mapper2.findByUserNameAndPassword(new User(123,"ÕÅÈý","123"));
			System.out.println(u);
		}*/
	
	}

