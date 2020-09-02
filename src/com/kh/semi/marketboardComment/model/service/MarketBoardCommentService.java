package com.kh.semi.marketboardComment.model.service;

import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.commit;
import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.marketboardComment.model.dao.MarketBoardCommentDao;
import com.kh.semi.marketcomment.model.vo.MarketBoardComment;

public class MarketBoardCommentService {

	private MarketBoardCommentDao bcDao = new MarketBoardCommentDao();
	
	public ArrayList<MarketBoardComment> selectList(int bno) {
		Connection conn = getConnection();
		
		ArrayList<MarketBoardComment> clist = bcDao.selectList(conn,bno);
		close(conn);
		return clist;
	}

	public int insertComment(MarketBoardComment bco) {
		Connection conn = getConnection();
		
		int result = bcDao.insertComment(conn, bco);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int deleteComment(MarketBoardComment bc) {
		Connection conn = getConnection();
		
		int result = bcDao.deleteComment(conn,bc);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	public int updateComment(MarketBoardComment bc) {
		Connection conn = getConnection();
		int result = bcDao.updateComment(conn, bc);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}

}
