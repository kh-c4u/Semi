package com.kh.semi.comubaord.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.semi.common.JDBCTemplate.*;
import com.kh.semi.comubaord.model.dao.ComuBoardDao;
import com.kh.semi.comubaord.model.vo.ComuBoard;

public class ComuBoardService {
	private ComuBoardDao bDao = new ComuBoardDao();

	public int getListCount() {
		Connection con = getConnection();

		int listCount = bDao.getListCount(con);

		close(con);
		return listCount;
	}

	public ArrayList<ComuBoard> selectList(int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<ComuBoard> list = bDao.selectList(con,currentPage,limit);

		close(con);
		return list;
	}

	public ComuBoard selectOne(int bno) {
		Connection con = getConnection();

		ComuBoard b = bDao.selectOne(con,bno);
		
		if(b != null) {
			int result = bDao.updateReadCount(con,bno);
			
			if(result > 0) commit(con);
			else rollback(con);
		}
		close(con);
		return b;
	}

	public int insertComu(ComuBoard cb) {
		int result = 0;
		Connection con = getConnection();
		
		result = bDao.insertComu(cb,con);
		
		if(result>0) commit(con);
		else rollback(con);
		
		

		return result;
	}

	public ComuBoard updateView(int bno) {
		Connection con = getConnection();
		
		ComuBoard b = bDao.selectOne(con, bno);
		
		close(con);
		return b;

	}

	public int updateComuboard(ComuBoard b) {
		Connection con = getConnection();
		
		int result = bDao.updateComuboard(con,b);
		
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

	public ArrayList<ComuBoard> searchBoard(int category, String keyword, String selectKeyword, int currentPage,int limit) {
		//키워드 - 검색창 category-공부팁  select-라디오버튼(작성자, 내용, 제목) 
		Connection con = getConnection();
		ArrayList<ComuBoard> list = null;
		
		list = bDao.searchBoard(con,category,keyword,selectKeyword,currentPage,limit);
		
		close(con);
		
		return list;
	}

	public ArrayList<ComuBoard> selectTop7() {
		Connection con = getConnection();
		
		ArrayList<ComuBoard> list = bDao.selectTop7(con);
		
		close(con);
		
		return list;
	}

}
