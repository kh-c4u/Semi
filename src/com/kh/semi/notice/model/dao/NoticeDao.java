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

}
