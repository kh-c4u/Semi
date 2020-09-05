package com.kh.eaxmAnswer.model.vo;

import java.io.Serializable;

public class ExamAnswer implements Serializable{

	private static final long serialVersionUID = -151334797478967608L;
	
	private String tc; //시험코드  A1:기사  B1:기능사  C1:산업기사
	private int qn; //문제 번호
	private int an; //문항번호(1,2,3,4반복)
	private String ac; //문항 
	private int po; //점수
	
	public ExamAnswer() {}

	public ExamAnswer(String tc, int qn, int an, String ac, int po) {
		super();
		this.tc = tc;
		this.qn = qn;
		this.an = an;
		this.ac = ac;
		this.po = po;
	}

	public ExamAnswer(String tc, int qn, int an, String ac) {
		super();
		this.tc = tc;
		this.qn = qn;
		this.an = an;
		this.ac = ac;
	}

	public ExamAnswer(int qn, int an, String ac) {
		super();
		this.qn = qn;
		this.an = an;
		this.ac = ac;
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
		return "tc=" + tc + ", qn=" + qn + ", an=" + an + ", ac=" + ac + ", po=" + po;
	}
	
	
	
	
}
