package com.problem.service.impl;

 
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.problem.conf.CommonConfig;
import com.problem.dao.QuestionDao;
import com.problem.entity.Question;
import com.problem.service.QuestionService;
 

public class QuestionServiceImpl implements  QuestionService{

	public QuestionDao model() throws IOException {
		String res="config.xml";
		InputStream	in = Resources.getResourceAsStream(res);
			SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(in);
			SqlSession ss=ssf.openSession(true);
			QuestionDao mapper2 = ss.getMapper(QuestionDao.class);
			return mapper2;	
	}
	@Override
	public void deleteQuestion(int [] ids) {   
		StringBuilder idlist_str = new StringBuilder();
		for(int id : ids) {
			idlist_str.append(id).append(",");
			Question q;
			try {
				q = model().findById(id);
				if(q != null ) {
					deleteFileFromDisk(q.getQimg());
					deleteFileFromDisk(q.getAnimg());
				}
			} catch (IOException e) {
				System.out.println("É¾³ýÊ§°Ü");
			}
			
		}
		 idlist_str.deleteCharAt( idlist_str.length() - 1 );
		 String []str=idlist_str.toString().split(",");
		 try {
			model().deleteByIdList(str);
		} catch (IOException e) {
			
		}		
	}

	 private static void deleteFileFromDisk(String imgslist) {
		 if( imgslist != null && !"".equals( imgslist )) {
			 StringTokenizer stk = new StringTokenizer(imgslist, "#");
			 while(stk.hasMoreTokens()) {
				 String dir = stk.nextToken();
				 String real_disk_path = CommonConfig.BASE_PATH +  dir.replaceFirst(CommonConfig.PRE_FIX, "");
				 File f = new File(real_disk_path);
				 if(f.exists()) {
					 f.delete();
				 }
			 }
		 }
	 }
	 
}
