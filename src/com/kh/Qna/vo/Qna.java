package com.kh.Qna.vo;

public class Qna {

	private int seq;
	private String categoryName;
	private String title;
	private String content;
	private String fileName;
	private int state;
	private String user_id;
	private String reg_date;
	private int mainseq;

	public Qna() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Qna(int seq, String categoryName, String title, String content, String fileName, int state, String user_id,
			String reg_date, int mainseq) {
		super();
		this.seq = seq;
		this.categoryName = categoryName;
		this.title = title;
		this.content = content;
		this.fileName = fileName;
		this.state = state;
		this.user_id = user_id;
		this.reg_date = reg_date;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getMainseq() {
		return mainseq;
	}

	public void setMainseq(int mainseq) {
		this.mainseq = mainseq;
	}

	@Override
	public String toString() {
		return "Qna [seq=" + seq + ", categoryName=" + categoryName + ", title=" + title + ", content=" + content
				+ ", fileName=" + fileName + ", state=" + state + ", user_id=" + user_id + ", reg_date=" + reg_date
				+ "]";
	}

}
