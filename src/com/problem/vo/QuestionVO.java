package com.problem.vo;

import com.problem.dto.Page;

public class QuestionVO {
	private String searchtype;
	private String seachcontent;
	private String searchdate;
	private String searchdate2;
	private int status;
	
	private Page page;
	
	public String getSearchtype() {
		return searchtype;
	}
	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}
	public String getSeachcontent() {
		return seachcontent;
	}
	public void setSeachcontent(String seachcontent) {
		this.seachcontent = seachcontent;
	}
	public String getSearchdate() {
		return searchdate;
	}
	public void setSearchdate(String searchdate) {
		this.searchdate = searchdate;
	}
	
	 
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSearchdate2() {
		return searchdate2;
	}
	public void setSearchdate2(String searchdate2) {
		this.searchdate2 = searchdate2;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "QuestionVO [searchtype=" + searchtype + ", seachcontent=" + seachcontent + ", searchdate=" + searchdate
				+ ", searchdate2=" + searchdate2 + ", status=" + status + ", page=" + page + "]";
	}
	
 
	
	
}
