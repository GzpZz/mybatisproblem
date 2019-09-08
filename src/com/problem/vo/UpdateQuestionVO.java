package com.problem.vo;

public class UpdateQuestionVO {
	private int status;
	private String answer;
	private String animg;
	private int id;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnimg() {
		return animg;
	}
	public void setAnimg(String animg) {
		this.animg = animg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "UpdateQuestionVO [status=" + status + ", answer=" + answer + ", animg=" + animg + ", id=" + id + "]";
	}
	
}
