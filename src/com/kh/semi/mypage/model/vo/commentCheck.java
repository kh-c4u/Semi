package com.kh.semi.mypage.model.vo;

import java.io.Serializable;
import java.sql.Date;

import javafx.scene.control.Alert;

public class commentCheck implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 939521485114141956L;
	
	private String typecontent;
	private String typename;
	private int bno;
	private int btype;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private int bcount;
	private String boardfile;
	private Date bdate;
	private int bcommentnum;
	
	public commentCheck() {
		super();
	}
	
	

	public commentCheck(String typecontent, String typename, int bno, int btype, String btitle, String bcontent,
			String bwriter, int bcount, String boardfile, Date bdate, int bcommentnum) {
		super();
		this.typecontent = typecontent;
		this.typename = typename;
		this.bno = bno;
		this.btype = btype;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.bcount = bcount;
		this.boardfile = boardfile;
		this.bdate = bdate;
		this.bcommentnum = bcommentnum;
	}



	public String getTypecontent() {
		return typecontent;
	}
	public void setTypecontent(String typecontent) {
		this.typecontent = typecontent;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
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
	
	public int getBtype() {
		return btype;
	}

	public void setBtype(int btype) {
		this.btype = btype;
	}
	
	public int getBcommentnum() {
		return bcommentnum;
	}



	public void setBcommentnum(int bcommentnum) {
		this.bcommentnum = bcommentnum;
	}



	@Override
	public String toString() {
		return "commentCheck [typecontent=" + typecontent + ", typename=" + typename + ", bno=" + bno + ", btype="
				+ btype + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bwriter=" + bwriter + ", bcount="
				+ bcount + ", boardfile=" + boardfile + ", bdate=" + bdate + ", bcommentnum=" + bcommentnum + "]";
	}



	
}
