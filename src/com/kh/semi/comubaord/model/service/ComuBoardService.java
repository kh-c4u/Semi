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




}
