package com.kh.examQuestion.model.vo;

import java.io.Serializable;

public class ExamQuestion implements Serializable{

	private static final long serialVersionUID = 3768572286753798391L;
	
	private String tc; //Test_Question의 TC (ex C1
	private int qn; //Test_Question의 QN (ex 1
	private String qc; //Test_Question의 QC (ex 문제
	private int an; //TEST_ANSWER의 AN(ex 1
	private String ac; //TEST_ANSWER의 AC(ex 보기
	private int po; //TEST_ANSWER의 PO(ex 점수(0 or 5)
	
	public ExamQuestion() {}

	public ExamQuestion(String tc, int qn, String qc, int an, String ac, int po) {
		super();
		this.tc = tc;
		this.qn = qn;
		this.qc = qc;
		this.an = an;
		this.ac = ac;
		this.po = po;
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

	public int getAn() {
		return an;
	}

	public void setAn(int an) {
		this.an = an;
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public int getPo() {
		return po;
	}

	public void setPo(int po) {
		this.po = po;
	}

	@Override
	public String toString() {
		return "tc=" + tc + ", qn=" + qn + ", qc=" + qc + ", an=" + an + ", ac=" + ac + ", po=" + po;
	}

	
}
