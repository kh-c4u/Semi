package com.kh.semi.mypage.model.vo;

import java.io.Serializable;

public class MyScore implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9018538983407725259L;
	
	
	private String id;
	private String tc;
	private String tc_name;
	private int score;
	public MyScore() {
		super();
	}
	
	
	
	public MyScore(String id, String tc, String tc_name, int score) {
		super();
		this.id = id;
		this.tc = tc;
		this.tc_name = tc_name;
		this.score = score;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTc() {
		return tc;
	}
	public void setTc(String tc) {
		this.tc = tc;
	}
	public String getTc_name() {
		return tc_name;
	}
	public void setTc_name(String tc_name) {
		this.tc_name = tc_name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}



	@Override
	public String toString() {
		return "MyScore [id=" + id + ", tc=" + tc + ", tc_name=" + tc_name + ", score=" + score + "]";
	}
	
	
	
	
	
}
