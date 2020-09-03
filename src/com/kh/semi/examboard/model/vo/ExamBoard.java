package com.kh.semi.examboard.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ExamBoard implements Serializable{

	private static final long serialVersionUID = 5046490717757992087L;
	
	private int bno;
	private String btc;
	private String btitle;
	private Date bdate;
	private String boardfile;
	public ExamBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExamBoard(int bno, String btc, String btitle, Date bdate, String boardfile) {
		super();
		this.bno = bno;
		this.btc = btc;
		this.btitle = btitle;
		this.bdate = bdate;
		this.boardfile = boardfile;
	}
	public ExamBoard(int bno, String btc, String btitle, Date bdate) {
		super();
		this.bno = bno;
		this.btc = btc;
		this.btitle = btitle;
		this.bdate = bdate;
	}
	public ExamBoard(int bno, String btitle, Date bdate) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bdate = bdate;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBtc() {
		return btc;
	}
	public void setBtc(String btc) {
		this.btc = btc;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public String getBoardfile() {
		return boardfile;
	}
	public void setBoardfile(String boardfile) {
		this.boardfile = boardfile;
	}
	@Override
	public String toString() {
		return "bno=" + bno + ", btc=" + btc + ", btitle=" + btitle + ", bdate=" + bdate + ", boardfile="
				+ boardfile ;
	}
	
   

}
