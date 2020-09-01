package com.kh.semi.notice.model.dao;

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

import com.kh.semi.comubaord.model.vo.ComuBoard;
import com.kh.semi.notice.model.vo.Notice;

public class NoticeDao {
	private Properties prop;

	public NoticeDao() {
		prop = new Properties();

		String filePath = NoticeDao.class.getResource("/config/notice-query.properties").getPath();

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

	public ArrayList<Notice> selectList(Connection con, int currentPage, int limit) {
		ArrayList<Notice> list =null;
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

			list = new ArrayList<Notice>();

			while(rset.next()){
				Notice n = new Notice();

				n.setNno(rset.getInt("BNO"));
				n.setNtitle(rset.getString("BTITLE"));
				n.setNwriter(rset.getString("BWRITER"));
				n.setNwriterId(rset.getString("USERNAME"));
				n.setNcount(rset.getInt("BCOUNT"));
				n.setNdate(rset.getDate("BDATE"));
				list.add(n);

			}

		}catch(SQLException e) {

		}finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public Notice selectOne(Connection con, int bno) {
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectOne");

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);

			rset = pstmt.executeQuery();

			if(rset.next()) {
				n = new Notice();

				n.setNno(rset.getInt("BNO"));
				n.setNtitle(rset.getString("BTITLE"));
				n.setNcontent(rset.getString("BCONTENT"));
				n.setNwriter(rset.getString("USERNAME"));
				n.setNwriterId(rset.getString("BWRITER"));
				n.setNcount(rset.getInt("BCOUNT"));
				n.setNdate(rset.getDate("BDATE"));
				n.setBoardfile(rset.getString("BOARDFILE"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return n;
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

	public int insertNotice(Notice n, Connection con) {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertNotice");

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,n.getNtitle());
			pstmt.setString(2,n.getNcontent());
			pstmt.setString(3, n.getNwriterId());
			pstmt.setString(4,n.getBoardfile());
			System.out.println("dao : " + n);
			result = pstmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public int updateNotice(Connection con, Notice n) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = null;
		if(n.getBoardfile() !=null){
			sql = prop.getProperty("updateNoticeChageFile");
		}else {
			sql = prop.getProperty("updateNotice");

		}

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, n.getNtitle());
			pstmt.setString(2, n.getNcontent());
			if(n.getBoardfile()!= null) {
				pstmt.setString(3, n.getBoardfile());
				pstmt.setInt(4, n.getNno());
			}else {
				pstmt.setInt(3, n.getNno());
			}

			result = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteNotice(Connection con, int bno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteNotice");

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

	public ArrayList<Notice> searchNotice(Connection con, String keyword, int currentPage, int limit) {
		ArrayList<Notice> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchNotice");
		
		try {
			pstmt = con.prepareStatement(sql);

			int startRow = (currentPage - 1) * limit +1;
			int endRow = startRow + limit - 1;

			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			pstmt.setString(3, keyword);
			
		
			rset = pstmt.executeQuery();

			list = new ArrayList<Notice>();

			while(rset.next()){
				Notice n = new Notice();
				n.setNno(rset.getInt("BNO"));
				n.setNtitle(rset.getString("BTITLE"));
				n.setNwriter(rset.getString("BWRITER"));
				n.setNwriterId(rset.getString("USERNAME"));
				n.setNcount(rset.getInt("BCOUNT"));
				n.setNdate(rset.getDate("BDATE"));
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

				String sql = prop.getProperty("countSearchNotice");

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

}
