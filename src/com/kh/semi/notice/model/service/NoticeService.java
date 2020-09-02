package com.kh.semi.notice.model.service;

import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.commit;
import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.rollback;

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


	public Notice selectOne(int bno) {

		Connection con = getConnection();

		Notice n = nDao.selectOne(con,bno);

		if(n != null) {
			int result = nDao.updateReadCount(con,bno);

			if(result > 0) commit(con);
			else rollback(con);
		}
		close(con);
		return n;
	}


	public int insertNotice(Notice n) {
		int result = 0;
		Connection con = getConnection();

		result = nDao.insertNotice(n,con);

		if(result>0) commit(con);
		else rollback(con);



		return result;
	}


	public Notice updateView(int bno) {
		Connection con = getConnection();

		Notice n = nDao.selectOne(con, bno);

		close(con);
		return n;
	}


	public int updateNotice(Notice n) {
		Connection con = getConnection();

		int result = nDao.updateNotice(con,n);

		if(result > 0) commit(con);
		else rollback(con);

		close(con);

		return result;
	}


	public int deleteNotice(int bno) {
		Connection con = getConnection();
		int result = nDao.deleteNotice(con,bno);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		return result;
	}


	public int getSearchListCount(String keyword) {
		Connection con = getConnection();

		int listCount = nDao.getSearchListCount(con , keyword);

		close(con);
		return listCount;
	}


	public ArrayList<Notice> searchNotice(String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Notice> list = null;
		
		list = nDao.searchNotice(con,keyword,currentPage,limit);
		
		close(con);
		
		return list;
	}


	public ArrayList<Notice> selectTop7() {
		Connection con = getConnection();
		
		ArrayList<Notice> list = nDao.selectTop7(con);
		
		close(con);
		
		return list;
	}


}
