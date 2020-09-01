package com.kh.semi.comuboardComment.service2;

import java.sql.Connection;
import java.util.ArrayList;
import static com.kh.semi.common.JDBCTemplate.*;
import com.kh.semi.comuboardComment.model.vo.comuboardComment;

import com.kh.semi.coumuboardComment.dao2.BoardCommentDaoGS;

public class BoardCommentServiceGS {
	private BoardCommentDaoGS bcDao = new BoardCommentDaoGS();
	
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
