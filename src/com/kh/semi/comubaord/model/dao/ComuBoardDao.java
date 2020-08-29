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
				list.add(b);
				
			}
			
		}catch(SQLException e) {
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
}