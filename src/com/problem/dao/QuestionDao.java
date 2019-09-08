package com.problem.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.problem.dto.Page;
import com.problem.dto.ResultData;
import com.problem.entity.Question;
import com.problem.vo.QuestionVO;
import com.problem.vo.UpdateQuestionVO;
import com.problem.vo.UpdateStatusVO;

public interface QuestionDao {
	//1添加问题
	void insert(@Param("q")Question q);  //ok
	//2查询问题 3个查询。  page ,pagesize =>  limit page*pageSize,pageSize;
	//返回：{total:20,data:[{},{}]}
	/**
	 * 根据分类查找。带分页
	 * @param type
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Map<String,Object> getByType(String type,int page,int pageSize);
	
	/**
	 * 
	 * @param type
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Map<String,Object> getByKeyWord(String keyword,int page,int pageSize);
	
	
	
	//3删除问题。根据id。
	void deleteById(int id);
	
	Question findById(int id);
	
	// ids = 1,2,3,4
	void deleteByIdList(String []strs);
	
	
	//4更新状态。根据id更新status。
	void updateStatus(@Param("p")UpdateStatusVO vo);
	/**
	 * 根据问题id更新答案。
	 * @param id
	 * @param status
	 */
	void updateAnswerById(int id,String answer,String animg);
	
	ResultData getAllByPage(Page page);
	
	
	List<Question> getByCondition(QuestionVO qvo);
	
	/**
	 * 更新答案、状态。   ok
	 * @param updatevo
	 */
	void udpateAnswer(@Param("p")UpdateQuestionVO updatevo);
	
}
