package com.kh.semi.faq.model.vo;

import java.io.Serializable;


public class Faq implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4698448415975328578L;
	
	
	private int fno;			// 게시글번호
	private String fcategory;	// 분류
	private String ftitle;		//게시글 제목
	private String fcontents;	//게시글 내용
	
	
	public Faq() {

	}


	
	
	public Faq(String fcategory) {
		super();
		this.fcategory = fcategory;
	}




	public Faq(String ftitle, String fcontents) {
		super();
		
		this.ftitle = ftitle;
		this.fcontents = fcontents;
	}




	public Faq(int fno, String fcategory, String ftitle, String fcontents) {
		super();
		this.fno = fno;
		this.fcategory = fcategory;
		this.ftitle = ftitle;
		this.fcontents = fcontents;
	}


	




	public int getFno() {
		return fno;
	}


	public void setFno(int fno) {
		this.fno = fno;
	}


	public String getFcategory() {
		return fcategory;
	}


	public void setFcategory(String fcategory) {
		this.fcategory = fcategory;
	}


	public String getFtitle() {
		return ftitle;
	}


	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}


	public String getFcontents() {
		return fcontents;
	}


	public void setFcontents(String fcontents) {
		this.fcontents = fcontents;
	}


	@Override
	public String toString() {
		return "Faq [fno=" + fno + ", fcategory=" + fcategory + ", ftitle=" + ftitle + ", fcontents=" + fcontents + "]";
	}
	
}
	