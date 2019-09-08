package com.problem.entity;

public class Question {
	 private int id;
	 private String content;
	 private String type;
	 private String qimg;
	 private int uid;
	 private int status;
	 private String answer;
	 private String animg;
	 private String creationtime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQimg() {
		return qimg;
	}
	public void setQimg(String qimg) {
		this.qimg = qimg;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
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
	public String getCreationtime() {
		return creationtime;
	}
	public void setCreationtime(String creationtime) {
		this.creationtime = creationtime;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", content=" + content + ", type=" + type + ", qimg=" + qimg + ", uid=" + uid
				+ ", status=" + status + ", answer=" + answer + ", animg=" + animg + ", creationtime=" + creationtime
				+ "]";
	}
	 
	
}
