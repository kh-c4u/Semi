package com.kh.semi.examboard.model.dao;

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

import com.kh.semi.comubaord.model.dao.ComuBoardDao;
import com.kh.semi.comuboardComment.model.vo.comuboardComment;
import com.kh.semi.examboard.model.vo.ExamBoard;
import com.kh.semi.notice.model.vo.Notice;

public class ExamBoardDao {

	private Properties prop;

	public ExamBoardDao() {
		prop = new Properties();

		String filePath = ComuBoardDao.class.getResource("/config/Examboard-query.properties").getPath();

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
	public ArrayList<ExamBoard> selectList(Connection con, int currentPage, int limit) {
		ArrayList<ExamBoard> list =null;
		PreparedStatement pstmt=null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectList");
		System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);

			int startRow = (currentPage - 1) * limit +1;
			int endRow = startRow + limit - 1;

			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);

			rset = pstmt.executeQuery();

			list = new ArrayList<ExamBoard>();

			while(rset.next()){
				ExamBoard n = new ExamBoard();
				n.setBno(rset.getInt("BNO"));
				n.setBtitle(rset.getString("BTITLE"));
				n.setBdate(rset.getDate("BDATE"));
				n.setBtc(rset.getString("TC"));
				System.out.println(n);
				list.add(n);
			}

		}catch(SQLException e) {

		}finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}
	public ExamBoard examBoardDetail(Connection con, String tc) {
		ExamBoard eb = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("examBoardDetail");

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tc);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				eb = new ExamBoard();
				eb.setBdate(rset.getDate("BDATE"));
				eb.setBno(rset.getInt("BNO"));
				eb.setBoardfile(rset.getString("TFILE"));
				eb.setBtitle(rset.getString("BTITLE"));
				eb.setBtc(rset.getString("TC"));

			}
			System.out.println(eb);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);


		}

		return eb;
	}
	public ArrayList<ExamBoard> searchExam(Connection con, String keyword, int currentPage, int limit) {
		ArrayList<ExamBoard> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchExam");

		try {
			pstmt = con.prepareStatement(sql);

			int startRow = (currentPage - 1) * limit +1;
			int endRow = startRow + limit - 1;

			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			pstmt.setString(3, keyword);


			rset = pstmt.executeQuery();

			list = new ArrayList<ExamBoard>();

			while(rset.next()){
				ExamBoard n = new ExamBoard();
				n.setBno(rset.getInt("BNO"));
				n.setBtitle(rset.getString("BTITLE"));
				n.setBdate(rset.getDate("BDATE"));
				n.setBtc(rset.getString("TC"));
				list.add(n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}


		return list;
	}
	public int getSearchListCount(Connection con, String keyword) {
		int listCount = 0;
		PreparedStatement pstmt =null;
		ResultSet rset = null;

		String sql = prop.getProperty("countSearchExam");

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}
	public ArrayList<ExamBoard> selectTop7(Connection con) {
		ArrayList<ExamBoard> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectTop7");
		
		try {
			
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			list = new ArrayList<ExamBoard>();
			
			while(rset.next()) {
				
				ExamBoard b = new ExamBoard();
				
				b.setBno(rset.getInt("BNO"));
				b.setBtitle(rset.getString("BTITLE"));
				b.setBdate(rset.getDate("BDATE"));
				b.setBtc(rset.getString("TC"));
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
