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
	
	String filePath = MarketBoardDao.class.getResource("/config/Salemarketboard-query.properties").getPath();
	
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
	System.out.println(sql);
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
		
			switch(rset.getInt("BCONDITION")){
			case 1:b.setBcondition("판매중");
			break;
			case 2:b.setBcondition("판매완료");
			break;
			case 3:b.setBcondition("--");
			break;
			}
			
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
//public ArrayList<MarketBoard> searchBoard(Connection conn, int category, String keyword, String selectKeyword,
//		int currentPage, int limit) {
//	ArrayList<MarketBoard> list = null;
//	PreparedStatement pstmt = null;
//	ResultSet rset = null;
//	String sql = null;
//	
//	switch(selectKeyword) {
//	case "content":
//		sql = prop.getProperty("searchContentBoard");
//		break;
//	case "title" :
//		sql = prop.getProperty("searchTitleBoard");
//		break;
//	case "writer" : 
//		sql = prop.getProperty("searchWriterBoard");
//		break;
//	}
//		try {
//			pstmt = conn.prepareStatement(sql);
//
//			int startRow = (currentPage - 1) * limit +1;
//			int endRow = startRow + limit - 1;
//
//			pstmt.setInt(1, endRow);
//			pstmt.setInt(2, startRow);
//			pstmt.setInt(3, category);
//			pstmt.setString(4, keyword);
//			
//		
//			rset = pstmt.executeQuery();
//
//			list = new ArrayList<MarketBoard>();
//
//			while(rset.next()){
//				MarketBoard b = new MarketBoard();
//
//				b.setBno(rset.getInt("BNO"));
//				b.setBtype(rset.getInt("BTYPE"));  //1 공부팁 2 합격수기 3수강후기 4무료인강추천
//				b.setBtitle(rset.getString("BTITLE"));
//				b.setBwriter(rset.getString("BWRITER"));
//				b.setBwriterId(rset.getString("USERNAME"));
//				b.setBcount(rset.getInt("BCOUNT"));
//				b.setBdate(rset.getDate("BDATE"));
//
//				switch(rset.getInt("BTYPE")){
//				
//				case 1:b.setBtypes("공부팁");
//				break;
//				case 2:b.setBtypestr("합격수기");
//				break;
//				case 3:b.setBtypestr("수강후기");
//				break;
//				case 4:b.setBtypestr("무료인강추천");
//				break;
//				case 5:b.setBtypestr("--");
//				break;
//				}
//				list.add(b);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			close(rset);
//			close(pstmt);
//		}
//		
//		return list;
//	}
//}








