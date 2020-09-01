package com.kh.semi.buymarket.model.dao;

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

import com.kh.semi.market.model.vo.MarketBoard;

public class BuyMarketDao {
	private Properties prop;
	
	public BuyMarketDao(){
		
	prop = new Properties();
	
	String filePath = BuyMarketDao.class.getResource("/config/marketboard-query.properties").getPath();
	
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

	System.out.println("SQL");
	
	String sql = prop.getProperty("selectList");
	System.out.println(sql);
	try {
		pstmt = conn.prepareStatement(sql);		
		
		int startRow = (currentPage - 1) * limit +1; // 1, 11
		int endRow = startRow + limit - 1; // 10,20
		System.out.println(startRow + ":" + endRow);
		pstmt.setInt(1, endRow);
		pstmt.setInt(2, startRow);
		
		System.out.println("123123");

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
			System.out.println("1212");
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
	System.out.println(sql);
	System.out.println(b);
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


public MarketBoard selectOne(Connection conn, int bno) {
	MarketBoard b = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	String sql = prop.getProperty("selectOne");
	
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bno);
		
		rset = pstmt.executeQuery();
		
		if(rset.next()) {
			b = new MarketBoard();
			
			b.setBno(rset.getInt("BNO"));
			b.setBtype(rset.getInt("BTYPE"));
			b.setBtitle(rset.getString("BTITLE"));
			b.setBcontent(rset.getString("BCONTENT"));
			b.setBwriter(rset.getString("USERNAME"));
			b.setBwriterId(rset.getString("BWRITER"));
			b.setBcount(rset.getInt("BCOUNT"));
			b.setBdate(rset.getDate("BDATE"));
			b.setBcondition(rset.getString("BCONDITION"));
			b.setBoardfile(rset.getString("BOARDFILE"));
			
		}
	}catch(SQLException e) {
		e.printStackTrace();		
	}finally {
		close(rset);
		close(pstmt);
	}
	return b;
}


public int deleteBoard(Connection conn, int bno) {
	int result = 0;
	PreparedStatement pstmt = null;
	String sql = prop.getProperty("deleteBoard");
	
	try {
		pstmt =  conn.prepareStatement(sql);
		pstmt.setInt(1, bno);
		
		result = pstmt.executeUpdate();
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		close(pstmt);
	}
	
	return result;
}


public int updateBoard(Connection conn, MarketBoard b) {
	int result = 0;
	PreparedStatement pstmt = null;
	System.out.println("안녕하세요");
	
	String sql = null;
	if(b.getBoardfile() != null) {
		sql = prop.getProperty("updateBoardChangeFile");
	}else {
		sql = prop.getProperty("updateBoard");
	}
	
	System.out.println("저는");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBtitle());
			pstmt.setString(2, b.getBcontent());
			if(b.getBoardfile()!= null) {
				pstmt.setString(3, b.getBoardfile());
				pstmt.setInt(4, b.getBno());
			}else {
				pstmt.setInt(3, b.getBno());
			}
			
			System.out.println("한정원");

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("입니다.");

		} finally {
			close(pstmt);
		}
		System.out.println("안녕히계세요");

		return result;
	}


public int marketBoardCount(Connection conn, int bno) {
	int result = 0;

	PreparedStatement pstmt = null;
	String sql = prop.getProperty("boardCount");

	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bno);
		result = pstmt.executeUpdate();


	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		close(pstmt);
	}
	return result;
}
}








