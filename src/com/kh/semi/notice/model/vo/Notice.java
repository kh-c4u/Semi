package com.kh.semi.notice.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Notice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2953168986785534161L;

	private int nno;			// 게시글 번호
	private String ntitle;		// 제목
	private String ncontent;	// 게시글 내용
	private String nwriter;		// 게시글 작성자
	private String nwriterId;	// DB로부터 가져올 게시글 작성자 아이디
	private int ncount;			// 게시글 조회수
	private String boardfile;	// 게시글 첨부파일
	private Date ndate;			// 작성일
	
	
	
	public Notice() {
		super();
	}
	
	
	
	
	public Notice(String ntitle, String ncontent, String nwriterId, String boardfile) {
		super();
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.nwriterId = nwriterId;
		this.boardfile = boardfile;
	}




	public Notice(int nno, String ntitle, String ncontent, String nwriter, String nwriterId, int ncount,
			String boardfile, Date ndate) {
		super();
		this.nno = nno;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.nwriter = nwriter;
		this.nwriterId = nwriterId;
		this.ncount = ncount;
		this.boardfile = boardfile;
		this.ndate = ndate;
	}
	public int getNno() {
		return nno;
	}
	public void setNno(int nno) {
		this.nno = nno;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNwriter() {
		return nwriter;
	}
	public void setNwriter(String nwriter) {
		this.nwriter = nwriter;
	}
	public String getNwriterId() {
		return nwriterId;
	}
	public void setNwriterId(String nwriterId) {
		this.nwriterId = nwriterId;
	}
	public int getNcount() {
		return ncount;
	}
	public void setNcount(int ncount) {
		this.ncount = ncount;
	}
	public String getBoardfile() {
		return boardfile;
	}
	public void setBoardfile(String boardfile) {
		this.boardfile = boardfile;
	}
	public Date getNdate() {
		return ndate;
	}
	public void setNdate(Date ndate) {
		this.ndate = ndate;
	}
	@Override
	public String toString() {
		return "Notice [nno=" + nno + ", ntitle=" + ntitle + ", ncontent=" + ncontent + ", nwriter=" + nwriter
				+ ", nwriterId=" + nwriterId + ", ncount=" + ncount + ", boardfile=" + boardfile + ", ndate=" + ndate
				+ "]";
	}
	
	
	
}
