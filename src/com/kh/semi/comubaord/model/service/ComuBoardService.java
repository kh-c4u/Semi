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



}
