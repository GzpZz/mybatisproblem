package com.problem.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
import com.problem.dao.QuestionDao;
import com.problem.dao.UserDao;
import com.problem.dto.ResultData;
import com.problem.entity.Question;
import com.problem.entity.User;
import com.problem.service.QuestionService;
import com.problem.service.impl.QuestionServiceImpl;
import com.problem.vo.QuestionVO;
import com.problem.vo.UpdateQuestionVO;
import com.problem.vo.UpdateStatusVO;

@WebServlet("/question/*")
public class QuestionController extends DispatcherServlet {
	
	public QuestionDao model () throws IOException {
		String res="config.xml";
		InputStream	in = Resources.getResourceAsStream(res);
			SqlSessionFactory ssf=new SqlSessionFactoryBuilder().build(in);
			SqlSession ss=ssf.openSession(true);
			QuestionDao mapper2 = ss.getMapper(QuestionDao.class);
			return mapper2;	
	}
	
	@RequestMapping("insert.action")
	@ResponseBody
	public ResultData insert(@Param(isjavabean=true)Question q,HttpSession ss) throws ServletException, IOException {
		ResultData rs = new ResultData();
		User rsu = (User) ss.getAttribute("loginuser");
		if(rsu == null ) {
			rs.setMsg("您还未登陆，请先登陆！");
			rs.setCode(300);
		}else {
			
			//添加问题
				model().insert(q);
    		  rs.setMsg("插入成功！");
		}
		return rs;
	}
	
	@RequestMapping("getall.action")
	@ResponseBody
	public ResultData queryByPage(@Param(isjavabean=true) QuestionVO  queryVO) {//page=1&limit=3
		try {
			System.out.println("下面是获取全部数据");
			List<Question> byCondition = model().getByCondition(queryVO);
			/*for(Question q : byCondition) {
				System.out.println(q);
			}*/
			ResultData rs=new ResultData();
			rs.setCount(byCondition.size());
			rs.setData(byCondition);
			return rs;
			
		} catch (IOException e) {
			
		}
		return null;
	}

	
	@RequestMapping("update.action") //ok
	@ResponseBody
	public ResultData update(@Param(isjavabean=true) UpdateQuestionVO  updatevo) throws IOException {//page=1&limit=3
		ResultData rd = new ResultData();
		model().udpateAnswer(updatevo);
		return rd;
	}
	 
	@RequestMapping("delete.action")
	@ResponseBody
	public ResultData delete(@Param("deleteids")String deleteids) {//page=1&limit=3
		ResultData rd = new ResultData();
		if(deleteids != null && !"".equals( deleteids )) {
			//deleteids ="12,35,65," 由于可以多选（id）
			StringTokenizer stk = new StringTokenizer(deleteids,",");
			int []ids = new int[stk.countTokens()];
			int index = 0;
			while(stk.hasMoreTokens()) {
				ids [index++] =Integer.parseInt( stk.nextToken() );
			}
			QuestionService qs = new QuestionServiceImpl();
			qs.deleteQuestion(ids);
		}
		return rd;
	}
	
	@RequestMapping("updatestatus.action")  
	@ResponseBody
	public ResultData updateStatus(@Param(isjavabean=true)UpdateStatusVO vo) throws IOException {
		ResultData rd = new ResultData();
		model().updateStatus(vo);
		return rd;
	}
	
	
 
}
