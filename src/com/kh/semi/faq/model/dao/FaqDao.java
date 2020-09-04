package com.kh.semi.faq.model.dao;

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
import com.kh.semi.faq.model.vo.Faq;
import com.kh.semi.notice.model.vo.Notice;

import static com.kh.semi.common.JDBCTemplate.*;


public class FaqDao {
	
private Properties prop;
	
	public FaqDao() {
		prop = new Properties();
		
		String filePath = FaqDao.class.getResource("/config/Faq-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filePath));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public ArrayList<Faq> selectList(Connection con, int currentPage, int limit) {
		System.out.println("dao 들어왓다");
		ArrayList<Faq> list = null;
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		String sql ="SELECT CO.*" + 
				"   FROM (" + 
				"          SELECT ROWNUM RNUM, C.* " + 
				"             FROM (" + 
				"                    SELECT * " + 
				"                      FROM FAQ " + 
				"                     WHERE FTITLE IS NOT NULL " + 
				"                     ORDER BY FNO DESC) C" + 
				"                     WHERE ROWNUM <=?) CO " + 
				"  WHERE RNUM >= ?";
		
		
		try {
			pstmt = con.prepareStatement(sql);
			
			int startRow = (currentPage -1) * limit +1; //1,11
			int endRow = startRow + limit -1; //10,20
			System.out.println("startRow : " + startRow );
			System.out.println("endRow : " + endRow );
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Faq>();
			
			while(rset.next()) {
				Faq f = new Faq();
				
				f.setFno(rset.getInt("FNO"));
				f.setFcategory(rset.getString("FCATEGORY"));
				f.setFtitle(rset.getString("FTITLE"));
				f.setFcontents(rset.getString("FCONTENTS"));
				
				list.add(f);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		System.out.println("dao : " + list);
		return list;
	}

	public int insertFaq(Connection con, Faq f) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertFaq");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, f.getFcategory());
			pstmt.setString(2, f.getFtitle());
			pstmt.setString(3, f.getFcontents());
			
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int deleteFaq(Connection con, int fno) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteFaq");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fno);
			
			
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}



	public int getListCount(Connection con) {
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
		
		System.out.println("카운트 뜨니 ?");
		System.out.println(listCount);
		return listCount;
	}



	public ArrayList<Faq> searchFaq(Connection con, String keyword, int currentPage, int limit) {
	
			ArrayList<Faq> list = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
		
			String sql = "SELECT CO.*" + 
					"   FROM (" + 
					"          SELECT ROWNUM RNUM, C.* " + 
					"             FROM (" + 
					"                    SELECT * " + 
					"                      FROM FAQ " + 
					"                     WHERE FTITLE IS NOT NULL " + 
					"                     ORDER BY FNO DESC) C" + 
					"                     WHERE ROWNUM <=?) CO " + 
					"  WHERE RNUM >= ?" +
		            " AND FTITLE LIKE CONCAT('%',CONCAT(?,'%')) ";
			
			
			
			try {
				pstmt = con.prepareStatement(sql);

				int startRow = (currentPage - 1) * limit +1;
				int endRow = startRow + limit - 1;
				System.out.println("다오 나옵네까4");

				pstmt.setInt(1, endRow);
				System.out.println("다오 나옵네까5");
				pstmt.setInt(2, startRow);
				System.out.println("다오 나옵네까6");
				pstmt.setString(3, keyword);
				System.out.println("다오 나옵네까7");
				
							
				rset = pstmt.executeQuery();

				list = new ArrayList<Faq>();

				while(rset.next()){
					System.out.println("다오 나옵네까8");
					Faq f = new Faq();

				
					f.setFno(rset.getInt("FNO"));
					f.setFcategory(rset.getString("FCATEGORY"));
					f.setFtitle(rset.getString("FTITLE"));
					f.setFcontents(rset.getString("FCONTENTS"));					            					
					list.add(f);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}

	
	
	public Faq selectOne(Connection con, int fno) {
		Faq f = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectOne");

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fno);

			rset = pstmt.executeQuery();

			if(rset.next()) {
				f = new Faq();

				f.setFno(rset.getInt("FNO"));
				f.setFcategory(rset.getString("FCATEGORY"));
				f.setFtitle(rset.getString("FTITLE"));
				f.setFcontents(rset.getString("FCONTENTS"));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return f;
	}



	public int updateReadCount(Connection con, int fno) {
		int result = 0;

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReadCount");

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fno);
			result = pstmt.executeUpdate();


		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertFaq(Faq f, Connection con) {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertFaq");

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,f.getFcategory());
			pstmt.setString(2,f.getFtitle());
			pstmt.setString(3,f.getFcontents());
			System.out.println("dao : " + f);
			result = pstmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}



	public int getSearchListCount(Connection con, String keyword) {
		int listCount = 0;
		PreparedStatement pstmt =null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchFaq");

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
