package com.kh.semi.errorbaord.service;

import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.commit;
import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.errorbaord.model.dao.ErrorBoardDao;
import com.kh.semi.errorbaord.model.vo.ErrorBoard;

public class ErrorBoardService {
	private ErrorBoardDao bDao = new ErrorBoardDao();
	

	public int getListCount() {
		Connection con = getConnection();

		int listCount = bDao.getListCount(con);

		close(con);
		return listCount;
	}


	public ArrayList<ErrorBoard> selectList(int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<ErrorBoard> list = bDao.selectList(con,currentPage,limit);

		close(con);
		return list;
	}


	public ErrorBoard selectOne(int bno) {
		Connection con = getConnection();

		ErrorBoard b = bDao.selectOne(con,bno);
		
		if(b != null) {
			int result = bDao.updateReadCount(con,bno);
			
			if(result > 0) commit(con);
			else rollback(con);
		}
		close(con);
		return b;
	}

	public int insertError(ErrorBoard cb) {
		int result = 0;
		Connection con = getConnection();
		
		result = bDao.insertError(cb,con);
		
		if(result>0) commit(con);
		else rollback(con);
		
		

		return result;
	}

	public ErrorBoard updateView(int bno) {
		Connection con = getConnection();
		
		ErrorBoard b = bDao.selectOne(con, bno);
		
		close(con);
		return b;

	}

	public int updateErrorboard(ErrorBoard b) {
		Connection con = getConnection();
		
		int result = bDao.updateErrorboard(con,b);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int deleteBoard(int bno) {
		Connection con = getConnection();
		int result = bDao.deleteBoard(con,bno);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		return result;
	}

	public ArrayList<ErrorBoard> searchBoard(int category, String keyword, String selectKeyword, int currentPage,int limit) {
		//키워드 - 검색창 category-공부팁  select-라디오버튼(작성자, 내용, 제목) 
		Connection con = getConnection();
		ArrayList<ErrorBoard> list = null;
		
		list = bDao.searchBoard(con,category,keyword,selectKeyword,currentPage,limit);
		
		close(con);
		
		return list;
	}


}
