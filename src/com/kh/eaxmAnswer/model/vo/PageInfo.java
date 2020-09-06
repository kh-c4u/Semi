package com.kh.eaxmAnswer.model.vo;

import java.io.Serializable;

public class PageInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1865298794134253306L;
	
	private int maxPage;
	private int currentPage;
	public PageInfo() {
		super();
	}
	public PageInfo(int maxPage, int currentPage) {
		super();
		this.maxPage = maxPage;
		this.currentPage = currentPage;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	@Override
	public String toString() {
		return "PageInfo [maxPage=" + maxPage + ", currentPage=" + currentPage + "]";
	}
	
	
	
	
}
