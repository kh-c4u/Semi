package com.kh.semi.marketboard.model.service;
import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.marketboard.model.dao.MarketBoardDao;
import com.kh.semi.marketboard.model.vo.MarketBoard;


public class MarketBoardService {

	private MarketBoardDao bDao = new MarketBoardDao();
	
	public int getListCount() {
		Connection conn = getConnection();
		
		int listCount = bDao.getListCount(conn);
		
		close(conn);
		return listCount;
	}
	
	public ArrayList<MarketBoard> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<MarketBoard> list = bDao.selectList(conn,currentPage,limit);
		
		close(conn);
		return list;
	}

	public int insertBoard(MarketBoard b) {
		Connection conn = getConnection();
		
		int result = bDao.insertBoard(conn,b);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
		
	}
	
}
