package com.kh.semi.notice.model.service;

import static com.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.comubaord.model.vo.ComuBoard;
import com.kh.semi.notice.model.dao.NoticeDao;
import com.kh.semi.notice.model.vo.Notice;

public class NoticeService {
	NoticeDao nDao = new NoticeDao();
	
	
	public int getListCount() {
		Connection con = getConnection();

		int listCount = nDao.getListCount(con);

		close(con);
		return listCount;
	}


	public ArrayList<Notice> selectList(int currentPage, int limit) {
		
			Connection con = getConnection();
			ArrayList<Notice> list = nDao.selectList(con,currentPage,limit);

			close(con);
			return list;
		
	}

}
