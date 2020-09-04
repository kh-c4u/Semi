/**
 * 
 */
package com.kh.semi.comubaord.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.semi.comubaord.model.vo.ComuBoard;

import static com.kh.semi.common.JDBCTemplate.*;

/**
 * @author haha2
 *
 */
public class ComuBoardDao {

	private Properties prop;

	public ComuBoardDao() {
		prop = new Properties();

		String filePath = ComuBoardDao.class.getResource("/config/Comuboard-query.properties").getPath();

		try {
			prop.load(new FileReader(filePath));
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

	public int getListCount(Connection con) {

		// 총 게시글 수
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("listCount");

		try {
			stmt = con.createStatement();
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

	public ArrayList<ComuBoard> selectList(Connection con, int currentPage, int limit) {
		ArrayList<ComuBoard> list =null;
		PreparedStatement pstmt=null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectList");

		try {
			pstmt = con.prepareStatement(sql);

			int startRow = (currentPage - 1) * limit +1;
			int endRow = startRow + limit - 1;

			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);

			rset = pstmt.executeQuery();

			list = new ArrayList<ComuBoard>();

			while(rset.next()){
				ComuBoard b = new ComuBoard();

				b.setBno(rset.getInt("BNO"));
				b.setBtype(rset.getInt("BTYPE"));  //1 공부팁 2 합격수기 3수강후기 4무료인강추천
				b.setBtitle(rset.getString("BTITLE"));
				b.setBwriter(rset.getString("BWRITER"));
				b.setBwriterId(rset.getString("USERNAME"));
				b.setBcount(rset.getInt("BCOUNT"));
				b.setBdate(rset.getDate("BDATE"));

				switch(rset.getInt("BTYPE")){
				case 1:b.setBtypestr("공부팁");
				break;
				case 2:b.setBtypestr("합격수기");
				break;
				case 3:b.setBtypestr("수강후기");
				break;
				case 4:b.setBtypestr("무료인강추천");
				break;
				case 5:b.setBtypestr("--");
				break;
				}
				list.add(b);

			}

		}catch(SQLException e) {

		}finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public ComuBoard selectOne(Connection con, int bno) {
		ComuBoard b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectOne");

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);

			rset = pstmt.executeQuery();

			if(rset.next()) {
				b = new ComuBoard();

				b.setBno(rset.getInt("BNO"));
				b.setBtype(rset.getInt("BTYPE"));
				b.setBtitle(rset.getString("BTITLE"));
				b.setBcontent(rset.getString("BCONTENT"));
				b.setBwriter(rset.getString("USERNAME"));
				b.setBwriterId(rset.getString("BWRITER"));
				b.setBcount(rset.getInt("BCOUNT"));
				b.setBdate(rset.getDate("BDATE"));
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

	public int insertComu(ComuBoard cb, Connection con) {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertComu");

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cb.getBtype());
			pstmt.setString(2,cb.getBtitle());
			pstmt.setString(3,cb.getBcontent());
			pstmt.setString(4, cb.getBwriter());
			pstmt.setString(5,cb.getBoardfile());
			System.out.println("dao : " + cb);
			result = pstmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public int updateReadCount(Connection con, int bno) {
		int result = 0;

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReadCount");

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			result = pstmt.executeUpdate();


		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateComuboard(Connection con, ComuBoard b) {

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = null;
		if(b.getBoardfile() !=null){
			sql = prop.getProperty("updateBoardChageFile");
		}else {
			sql = prop.getProperty("updateBoard");

		}

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, b.getBtitle());
			pstmt.setString(2, b.getBcontent());
			if(b.getBoardfile()!= null) {
				pstmt.setString(3, b.getBoardfile());
				pstmt.setInt(4, b.getBno());
			}else {
				pstmt.setInt(3, b.getBno());
			}

			result = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteBoard(Connection con, int bno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
				
		return result;
	}

	public ArrayList<ComuBoard> searchBoard(Connection con, int category, String keyword, String selectKeyword,
			int currentPage, int limit) {
		ArrayList<ComuBoard> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = null;
		
		switch(selectKeyword) {
		case "content":
			sql = prop.getProperty("searchContentBoard");
			break;
		case "title" :
			sql = prop.getProperty("searchTitleBoard");
			break;
		case "writer" : 
			sql = prop.getProperty("searchWriterBoard");
			break;
		}

		
		
		
		try {
			pstmt = con.prepareStatement(sql);

			int startRow = (currentPage - 1) * limit +1;
			int endRow = startRow + limit - 1;

			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, category);
			pstmt.setString(4, keyword);
			
		
			rset = pstmt.executeQuery();

			list = new ArrayList<ComuBoard>();

			while(rset.next()){
				ComuBoard b = new ComuBoard();

				b.setBno(rset.getInt("BNO"));
				b.setBtype(rset.getInt("BTYPE"));  //1 공부팁 2 합격수기 3수강후기 4무료인강추천
				b.setBtitle(rset.getString("BTITLE"));
				b.setBwriter(rset.getString("BWRITER"));
				b.setBwriterId(rset.getString("USERNAME"));
				b.setBcount(rset.getInt("BCOUNT"));
				b.setBdate(rset.getDate("BDATE"));

				switch(rset.getInt("BTYPE")){
				
				case 1:b.setBtypestr("공부팁");
				break;
				case 2:b.setBtypestr("합격수기");
				break;
				case 3:b.setBtypestr("수강후기");
				break;
				case 4:b.setBtypestr("무료인강추천");
				break;
				case 5:b.setBtypestr("--");
				break;
				}
				list.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<ComuBoard> selectTop7(Connection con) {
		ArrayList<ComuBoard> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectTop7");
		
		try {
			
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			list = new ArrayList<ComuBoard>();
			
			while(rset.next()) {
				
				ComuBoard b = new ComuBoard();
				
				b.setBno(rset.getInt("BNO"));
				b.setBtype(rset.getInt("BTYPE"));
				
				b.setBtitle(rset.getString("BTITLE"));
				b.setBcontent(rset.getString("BCONTENT"));
				b.setBwriter(rset.getString("USERNAME"));
				b.setBcount(rset.getInt("BCOUNT"));
				b.setBdate(rset.getDate("BDATE"));
				b.setBoardfile(rset.getString("BOARDFILE"));
				
				list.add(b);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}


	
}