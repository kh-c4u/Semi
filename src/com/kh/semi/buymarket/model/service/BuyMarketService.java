package com.kh.semi.buymarket.model.service;
import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.commit;
import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.buymarket.model.dao.BuyMarketDao;
import com.kh.semi.buymarket.model.vo.BuyMarketBoard;


public class BuyMarketService {

	private BuyMarketDao bDao = new BuyMarketDao();
	
	public int getListCount() {
		Connection conn = getConnection();
		
		int listCount = bDao.getListCount(conn);
		
		close(conn);
		return listCount;
	}
	
	
	
	public ArrayList<BuyMarketBoard> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<BuyMarketBoard> list = bDao.selectList(conn,currentPage,limit);
		
		close(conn);
		return list;
	}

	public int insertBoard(BuyMarketBoard b) {
		Connection conn = getConnection();
		
		int result = bDao.insertBoard(conn,b);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
		
	}

	public BuyMarketBoard selectOne(int bno) {
		Connection conn = getConnection();
		
		BuyMarketBoard b = bDao.selectOne(conn, bno);
	
		if(b != null) {
			int result = bDao.marketBoardCount(conn,bno);
			
			if(result > 0) commit(conn);
			else rollback(conn);
		}
		
		close(conn);
		return b;
	}

	public int deleteBoard(int bno) {
		Connection conn = getConnection();
		int result = bDao.deleteBoard(conn,bno);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	public int updateBoard(BuyMarketBoard b) {
		Connection conn = getConnection();
		
		int result = bDao.updateBoard(conn,b);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	
	}
	
}
