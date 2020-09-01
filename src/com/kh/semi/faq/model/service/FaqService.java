package com.kh.semi.faq.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.faq.model.dao.FaqDao;
import com.kh.semi.faq.model.vo.Faq;

import static com.kh.semi.common.JDBCTemplate.*;

public class FaqService {

private FaqDao fDao = new FaqDao();
	
	

	public ArrayList<Faq> selectList(int currentPage, int limit) {
		System.out.println("서비스다" );
		Connection con = getConnection();
		ArrayList<Faq> list = fDao.selectList(con,currentPage,limit);
		
		System.out.println("ser : " + list);
		
		close(con);
		return list;
	}
	
	public int getListCount() {
		Connection con = getConnection();
		
		int listCount = fDao.getListCount(con);
		
		close(con);
		
		return listCount;
	}


	public int insertFaq(Faq f) {
		Connection con = getConnection();
		
		int result = fDao.insertFaq(con,f);
		
		// insert이니까
		// 성공여부에 따라서 commit 또는 rollback 한다.
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		return result;
	}

	public int deleteFaq(int fno) {
		Connection con = getConnection();
		
		int result = fDao.deleteFaq(con,fno);
		
		// delete이니까
		// 성공여부에 따라서 commit 또는 rollback 한다.
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		return result;
	}
	

}
