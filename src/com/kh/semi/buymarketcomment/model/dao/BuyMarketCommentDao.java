package com.kh.semi.buymarketcomment.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.semi.marketcomment.model.vo.MarketBoardComment;

public class BuyMarketCommentDao {
	
		private Properties prop = new Properties();
		
		public BuyMarketCommentDao() {
			String filePath = BuyMarketCommentDao.class
					.getResource("/config/marketboardcomment-query.properties").getPath();
			try {
				prop.load(new FileReader(filePath));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		public ArrayList<MarketBoardComment> selectList(Connection conn, int bno) {
			ArrayList<MarketBoardComment> clist = null;
			
			PreparedStatement pstmt = null;
			
			ResultSet rset = null;
			
			
			String sql = prop.getProperty("selectList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bno);
				
				rset = pstmt.executeQuery();
				
				clist = new ArrayList<MarketBoardComment>();
				while(rset.next()) {
					MarketBoardComment bco = new MarketBoardComment();
					bco.setCno(rset.getInt("CNO"));
					bco.setBno(bno);
					bco.setCcontent(rset.getString("CCONTENT"));
					bco.setCwriter(rset.getString("USERNAME"));
					bco.setCwriterId(rset.getString("CWRITER"));
					bco.setCdate(rset.getDate("CDATE"));
					bco.setRefcno(rset.getInt("REF_CNO"));
					bco.setClevel(rset.getInt("CLEVEL"));
					clist.add(bco);
				}
				
				
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
				
			}
			return clist;
		}

		public int insertComment(Connection conn, MarketBoardComment bco) {
			int result = 0;
			
			

			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertComment");
			
			try {

				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, bco.getBno());
				pstmt.setString(2,  bco.getCcontent());
				pstmt.setString(3,  bco.getCwriter());
				
				if( bco.getRefcno() > 0) {

					pstmt.setInt(4,  bco.getRefcno());
					
				} else {

					pstmt.setNull(4, java.sql.Types.NULL);
					
				}
				
				pstmt.setInt(5,  bco.getClevel());

				result = pstmt.executeUpdate();			
				
			} catch (SQLException e) {

				e.printStackTrace();
				

			} finally {
				close(pstmt);			
			}
			
			return result;
		}

		public int deleteComment(Connection conn, MarketBoardComment bc) {
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("deleteComment");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bc.getCno());
				pstmt.setInt(2, bc.getBno());
				result = pstmt.executeUpdate();
				
		
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
		
			return result;
			
			
		}

		public int updateComment(Connection conn, MarketBoardComment bc) {
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("updateComment");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bc.getCcontent());
				pstmt.setInt(2, bc.getCno());
				pstmt.setInt(3, bc.getBno());
				
				result = pstmt.executeUpdate();

			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			return result;
		}

	}
















