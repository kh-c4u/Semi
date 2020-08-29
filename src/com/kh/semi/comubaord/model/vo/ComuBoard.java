package com.kh.semi.comubaord.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ComuBoard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6361368573203972465L;

	
	private int bno;			// 게시글 번호
	private int btype;			// 게시판 종류
	private String btitle;		// 제목
	private String bcontent;	// 게시글 내용
	private String bwriter;		// 게시글 작성자
	private String bwriterId;	// DB로부터 가져올 게시글 작성자 아이디
	private int bcount;			// 게시글 조회수
	private String boardfile;	// 게시글 첨부파일
	private Date bdate;			// 작성일

	public ComuBoard() {}

	public ComuBoard(String btitle, String bcontent, String bwriter, String boardfile) {
		super();
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.boardfile = boardfile;
	}

	public ComuBoard(int bno, int btype, String btitle, String bcontent, String bwriter, int bcount, String boardfile,
			Date bdate) {
		super();
		this.bno = bno;
		this.btype = btype;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.bcount = bcount;
		this.boardfile = boardfile;
		this.bdate = bdate;
		
	}
	
	
	// 전체 리스트용
	public ComuBoard(int bno, int btype, String btitle, String bwriter, String bwriterId, int bcount, Date bdate) {
		super();
		this.bno = bno;
		this.btype = btype;
		this.btitle = btitle;
		this.bwriter = bwriter;
		this.bwriterId = bwriterId;
		this.bcount = bcount;
		this.bdate = bdate;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getBtype() {
		return btype;
	}

	public void setBtype(int btype) {
		this.btype = btype;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public String getBwriterId() {
		return bwriterId;
	}

	public void setBwriterId(String bwriterId) {
		this.bwriterId = bwriterId;
	}

	public int getBcount() {
		return bcount;
	}

	public void setBcount(int bcount) {
		this.bcount = bcount;
	}

	public String getBoardfile() {
		return boardfile;
	}

	public void setBoardfile(String boardfile) {
		this.boardfile = boardfile;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	@Override
	public String toString() {
		return "bno=" + bno + ", btype=" + btype + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bwriter="
				+ bwriter + ", bwriterId=" + bwriterId + ", bcount=" + bcount + ", boardfile=" + boardfile + ", bdate="
				+ bdate;
	}
	
	
	
}
