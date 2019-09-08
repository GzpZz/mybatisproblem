package com.problem.vo;

public class UpdateStatusVO {
	private int id;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "UpdateStatusVO [id=" + id + ", status=" + status + "]";
	}
	
}
