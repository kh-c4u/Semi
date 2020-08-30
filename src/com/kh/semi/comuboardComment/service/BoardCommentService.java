package com.kh.semi.comuboardComment.service;

import java.sql.Connection;
import java.util.ArrayList;
import static com.kh.semi.common.JDBCTemplate.*;
import com.kh.semi.comuboardComment.model.vo.comuboardComment;
import com.kh.semi.coumuboardComment.dao.BoardCommentDao;

public class BoardCommentService {
	private BoardCommentDao bcDao = new BoardCommentDao();
	
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
}
