package com.kh.semi.examboard.model.service;

import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.comubaord.model.vo.ComuBoard;
import com.kh.semi.comuboardComment.model.vo.comuboardComment;
import com.kh.semi.examboard.model.dao.ExamBoardDao;
import com.kh.semi.examboard.model.vo.ExamBoard;
import com.kh.semi.notice.model.vo.Notice;

public class ExamBoardService {
	
	ExamBoardDao bDao = new ExamBoardDao();
	public int getListCount() {
		Connection con = getConnection();

		int listCount = bDao.getListCount(con);

		close(con);
		return listCount;
		
	}
	public ArrayList<ExamBoard> selectList(int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<ExamBoard> list = bDao.selectList(con,currentPage,limit);

		close(con);
		return list;
	}
	public ExamBoard examBoardDetail(String tc) {
		ExamBoard eb =null;
		Connection con = getConnection();
		
		eb = bDao.examBoardDetail(con, tc);

		close(con);
		return eb;
	}
	public ArrayList<ExamBoard> searchExam(String keyword, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<ExamBoard> list = null;
		
		list = bDao.searchExam(con,keyword,currentPage,limit);
		
		close(con);
		
		return list;
	}
	public int getSearchListCount(String keyword) {
		Connection con = getConnection();

		int listCount = bDao.getSearchListCount(con , keyword);

		close(con);
		return listCount;
	}
	public ArrayList<ExamBoard> selectTop7() {
		Connection con = getConnection();
		
		ArrayList<ExamBoard> list = bDao.selectTop7(con);
		
		close(con);
		
		return list;
	}

}
