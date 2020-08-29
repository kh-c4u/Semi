package com.kh.semi.marketboard.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.semi.marketboard.model.vo.MarketBoard;

public class MarketBoardDao {
	private Properties prop;
	
	public MarketBoardDao(){
		
	prop = new Properties();
	
	String filePath = MarketBoardDao.class.getResource("/config/board-query.properties").getPath();
	
	try {
		prop.load(new FileReader(filePath));
	}catch(IOException e) {
		e.printStackTrace();
	}
	
}

	
public int getListCount(Connection conn) {
	// 총 게시글 수
	int listCount = 0;
	Statement stmt = null;
	ResultSet rset = null;
	
	String sql = prop.getProperty("listCount");
	
	try {
		stmt = conn.createStatement();
		rset = stmt.executeQuery(sql);
		
		if(rset.next()) {
			listCount = rset.getInt(1);
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		close(rset);
		close(stmt);
	}
	
	return listCount;
	
}



public ArrayList<MarketBoard> selectList(Connection conn, int currentPage, int limit) {
	ArrayList<MarketBoard> list =null;
	PreparedStatement pstmt=null;
	ResultSet rset = null;

	
	String sql = prop.getProperty("selectList");
	
	try {
		pstmt = conn.prepareStatement(sql);		
		
		int startRow = (currentPage - 1) * limit +1; // 1, 11
		int endRow = startRow + limit - 1; // 10,20
		System.out.println(startRow + ":" + endRow);
		pstmt.setInt(1, endRow);
		pstmt.setInt(2, startRow);
		
		rset = pstmt.executeQuery();
		
		list = new ArrayList<MarketBoard>();
		
		while(rset.next()){
			MarketBoard b = new MarketBoard();
			
			b.setBno(rset.getInt("BNO"));
			b.setBtype(rset.getInt("BTYPE"));
			b.setBtitle(rset.getString("BTITLE"));
			b.setBcontent(rset.getString("BCONTENT"));
			b.setBwriter(rset.getString("USER_NAME"));
			b.setBcount(rset.getInt("BCOUNT"));
			b.setBdate(rset.getDate("BDATE"));
			b.setBcondition(rset.getString("BCONDITION"));
			b.setBoardfile(rset.getString("BOARDFILE"));
		
			list.add(b);
			
		}
		System.out.println(5);
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		close(rset);
		close(pstmt);
	}


	return list;
}


public int insertBoard(Connection conn, MarketBoard b) {
	int result = 0;
	PreparedStatement pstmt = null;
	String sql = prop.getProperty("insertBoard");
	
	try {
		pstmt =  conn.prepareStatement(sql);
		pstmt.setString(1, b.getBtitle());
		pstmt.setString(2, b.getBcontent());
		pstmt.setString(3, b.getBwriter());
		pstmt.setString(4, b.getBoardfile());
		pstmt.setString(5, b.getBcondition());	
		
		result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
}

}


