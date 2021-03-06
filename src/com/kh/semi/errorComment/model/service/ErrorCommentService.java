package com.kh.semi.errorComment.model.service;

import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.commit;
import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.comuboardComment.model.vo.comuboardComment;
import com.kh.semi.coumuboardComment.dao.BoardCommentDao;
import com.kh.semi.errorComment.model.dao.ErrorCommentDao;

public class ErrorCommentService {
	private ErrorCommentDao bcDao = new ErrorCommentDao();
	
	public ArrayList<comuboardComment> selectList(int bno) {
		Connection con = getConnection();
		
		ArrayList<comuboardComment> clist = bcDao.selectList(con,bno);
		close(con);
		return clist;
	}

	public int insertComment(comuboardComment bco) {
		Connection con = getConnection();
		int result = bcDao.insertComment(con,bco);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		return result;
	}

	public int deleteComment(comuboardComment com) {
		Connection con = getConnection();
		int result = bcDao.deleteComment(con,com);
		
		if(result > 0) commit(con);
		else rollback(con);
		close(con);
		
		return result;
	}

	public int updateComment(comuboardComment com) {
		int result = 0;
		Connection con = getConnection();
		result= bcDao.updateComment(con,com);
		
		
		if(result > 0) commit(con);
		else rollback(con);
		close(con);
		
		return result;
	}
}	