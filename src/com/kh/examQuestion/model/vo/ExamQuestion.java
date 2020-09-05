package com.kh.examQuestion.model.vo;

import java.io.Serializable;

public class ExamQuestion implements Serializable{

	private static final long serialVersionUID = 3768572286753798391L;
	
	private String tc;
	private int qn;
	private String qc;
	
	public ExamQuestion() {}

	public ExamQuestion(String tc, int qn, String qc) {
		super();
		this.tc = tc;
		this.qn = qn;
		this.qc = qc;
	}
	public ExamQuestion(String tc, int qn) {
		super();
		this.tc = tc;
		this.qn = qn;
		
	}

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	public int getQn() {
		return qn;
	}

	public void setQn(int qn) {
		this.qn = qn;
	}

	public String getQc() {
		return qc;
	}

	public void setQc(String qc) {
		this.qc = qc;
	}

	@Override
	public String toString() {
		return "tc=" + tc + ", qn=" + qn + ", qc=" + qc;
	}
	
	
}
