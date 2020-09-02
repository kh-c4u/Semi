package com.kh.semi.mypage.model.vo;

import java.io.Serializable;

public class massage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7418790688868389251L;
	
	private int cno; //번호
	private String ctitle;		// 제목
	private String ccontent;	// 게시글 내용
	private String cwriter;		// 게시글 작성자
	private int cchecked;			// 게시글 조회여부('0','1')
	private String ctowriter;	// DB로부터 가져올 게시글 작성자 아이디
	private String cdate;			// 작성일
	
	public massage() {
	}

	public massage(String ctitle, String ccontent, String cwriter, int cchecked, String ctowriter, String cdate) {
		super();
		this.ctitle = ctitle;
		this.ccontent = ccontent;
		this.cwriter = cwriter;
		this.cchecked = cchecked;
		this.ctowriter = ctowriter;
		this.cdate = cdate;
	}

	public massage(int cno, String ctitle, String ccontent, String cwriter, int cchecked, String ctowriter,
			String cdate) {
		super();
		this.cno = cno;
		this.ctitle = ctitle;
		this.ccontent = ccontent;
		this.cwriter = cwriter;
		this.cchecked = cchecked;
		this.ctowriter = ctowriter;
		this.cdate = cdate;
	}

	public String getCtitle() {
		return ctitle;
	}

	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public String getCwriter() {
		return cwriter;
	}

	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}

	public int getCchecked() {
		return cchecked;
	}

	public void setCchecked(int cchecked) {
		this.cchecked = cchecked;
	}

	public String getCtowriter() {
		return ctowriter;
	}

	public void setCtowriter(String ctowriter) {
		this.ctowriter = ctowriter;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	@Override
	public String toString() {
		return "cno=" + cno + ", ctitle=" + ctitle + ", ccontent=" + ccontent + ", cwriter=" + cwriter
				+ ", cchecked=" + cchecked + ", ctowriter=" + ctowriter + ", cdate=" + cdate;
	}

	
	
	
}
