package com.kh.semi.mypage.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.semi.member.vo.Member;
import com.kh.semi.mypage.model.vo.MyScore;
import com.kh.semi.mypage.model.vo.commentCheck;
import com.kh.semi.mypage.model.vo.massage;
public class MypageDao {

	public int mypage_send(Connection conn, String id, String titlename, String writeNote, String sendId) {
		int result = 0;

		PreparedStatement pstmt = null;
		String sql = "INSERT INTO mypage_message VALUES(SEQ_MASSAGE.NEXTVAL,?,?,?,?,DEFAULT,0)";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, titlename);
			pstmt.setString(2, writeNote);
			pstmt.setString(3, sendId);
			pstmt.setString(4, id);

			result=pstmt.executeUpdate();


		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int getListCount(Connection con, String cwriter) {
		// 총 게시글 수
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT COUNT(*) FROM mypage_message"
				+ " WHERE "
		        + "CWRITER = ? ";
//		System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cwriter);
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

	public ArrayList<massage> selectList(Connection con, int currentPage, int limit, String cwriter) {
		ArrayList<massage> list =null;
		PreparedStatement pstmt=null;
		ResultSet rset = null;

		String sql =	"SELECT * FROM (SELECT ROWNUM AS rownumber, b.* FROM MYPAGE_MESSAGE b "
						+ "WHERE "
				        + "CTOWRITER = ? "
						+ ") a WHERE a.rownumber  >= ? AND a.rownumber  <= ?"
						+ " ORDER BY CNO ASC";
		
		try {
			pstmt = con.prepareStatement(sql);

			int startRow = (currentPage - 1) * limit +1; // 1, 11
			int endRow = startRow + limit - 1; // 10,20
			
			pstmt.setString(1, cwriter);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();

			list = new ArrayList<massage>();

			while(rset.next()){
				massage b = new massage();
				b.setCno(rset.getInt("CNO"));
				b.setCtitle(rset.getString("CTITLE"));
				b.setCcontent(rset.getString("CCONTENT"));
				b.setCwriter(rset.getString("CWRITER"));
				b.setCchecked(Integer.parseInt(rset.getString("CCHECKED")));
				b.setCtowriter(rset.getString("CTOWRITER"));
				b.setCdate(rset.getString("CDATETIME"));
				list.add(b);
			}
//			System.out.println("list!!!!!!! : "+list);
		}catch(SQLException e) {

		}finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public ArrayList<massage> searchNotice(Connection con, String category, String keyword, int currentPage, int limit, String cwriter) {
		ArrayList<massage> list =null;
		PreparedStatement pstmt=null;
		ResultSet rset = null;

		String sql = null;

		switch(category) {
		case "ctowriter":
			sql = "SELECT * FROM (SELECT ROWNUM AS rownumber, b.* FROM MYPAGE_MESSAGE b WHERE CTOWRITER LIKE '%'||?||'%'"
					+ " AND"
	                + " CWRITER = ? "
					+ ") a WHERE a.rownumber  >= ? AND a.rownumber  <= ? "
					+ " ORDER BY CNO ASC";
			break;
		case "ctitle":
			sql = "SELECT * FROM (SELECT ROWNUM AS rownumber, b.* FROM MYPAGE_MESSAGE b WHERE CTITLE LIKE CONCAT(CONCAT('%',?),'%') "
					+ " AND "
	                + "CWRITER = ? "
					+ ") a WHERE a.rownumber  >= ? AND a.rownumber  <= ? "
					+ " ORDER BY CNO ASC";
			break;
		case "ccontent":
			sql = "SELECT * FROM (SELECT ROWNUM AS rownumber, b.* FROM MYPAGE_MESSAGE b WHERE CCONTENT LIKE CONCAT(CONCAT('%',?),'%') "
					+ " AND "
	                + "CWRITER = ? "
					+ ") a WHERE a.rownumber  >= ? AND a.rownumber  <= ? "
					+ " ORDER BY CNO ASC";
			break;
		}

		try {
			int startRow = (currentPage - 1) * limit +1; // 1, 11
			int endRow = startRow + limit - 1; // 10,20

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, cwriter);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);

			rset = pstmt.executeQuery();
			list = new ArrayList<massage>();

			while(rset.next()) {
				massage n = new massage();
				n.setCno(rset.getInt("CNO"));
				n.setCtitle(rset.getString("CTITLE"));
				n.setCcontent(rset.getString("CCONTENT"));
				n.setCwriter(rset.getString("CWRITER"));
				n.setCchecked(Integer.parseInt(rset.getString("CCHECKED")));
				n.setCtowriter(rset.getString("CTOWRITER"));
				n.setCdate(rset.getString("CDATETIME"));
				list.add(n);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public massage selectOne(Connection con, int nno) {
		massage n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MYPAGE_MESSAGE WHERE CNO=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, nno);

			// INSERT,UPDATE,DELETE : executeUpdate()
			// SELECT : executeQuery()
			rset = pstmt.executeQuery();

			if(rset.next()) {
				n = new massage();

				n.setCno(nno);
				n.setCtitle(rset.getString("CTITLE"));
				n.setCcontent(rset.getString("CCONTENT"));
				n.setCwriter(rset.getString("CWRITER"));
				n.setCchecked(Integer.parseInt(rset.getString("CCHECKED")));
				n.setCtowriter(rset.getString("CTOWRITER"));
				n.setCdate(rset.getString("CDATETIME"));
			}
//			System.out.println("메세지 cno : " + n);

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return n;
	}

	public int updateRead(Connection con, int cno) {
		int result = 0;

		PreparedStatement pstmt = null;
		String sql = "UPDATE MYPAGE_MESSAGE SET CCHECKED=1 WHERE CNO=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cno);
			result = pstmt.executeUpdate();
			// INSERT,UPDATE,DELTE : executeUpdate()
			// SELECT : executeQuery()

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateMember(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE USRINFO SET USR_PWD=?, USR_EMAIL=? WHERE USR_ID= ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getUserId());
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMember2(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE USRINFO SET USR_PWD=?, USR_EMAIL=?, USR_ADR=? WHERE USR_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getAddress());
			pstmt.setString(4, m.getUserId());
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteMassage(Connection conn, String cno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM MYPAGE_MESSAGE WHERE CNO=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cno);
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<commentCheck> commentCheck(Connection con, String bwriter, int currentPage, int limit) {
		ArrayList<commentCheck> list =null;
		PreparedStatement pstmt=null;
		ResultSet rset = null;

		String sql = /*
						 * "SELECT '커뮤니티' AS \"TYPECONTENT\",'기사' AS \"TYPENAME\",A.* FROM COMU_BOARD A "
						 * +"WHERE BWRITER = ? " +"UNION ALL "
						 * +"SELECT '커뮤니티' AS \"TYPECONTENT\",'기능사' AS \"TYPENAME\",A.* FROM COMU_GBOARD A "
						 * +"WHERE BWRITER = ? " +"UNION ALL "
						 * +"SELECT '커뮤니티' AS \"TYPECONTENT\",'산업기사' AS \"TYPENAME\",A.* FROM COMU_SGBOARD A "
						 * +"WHERE BWRITER = ? " +"UNION ALL "
						 * +"SELECT '장터' AS \"TYPECONTENT\",'팝니다' AS \"TYPENAME\",A.BNO,A.BTYPE,A.BTITLE,A.BCONTENT,A.BWRITER,A.BCOUNT,A.BOARDFILE,A.BDATE FROM MARKETBOARD A "
						 * +"WHERE BWRITER = ? " +"UNION ALL "
						 * +"SELECT '장터' AS \"TYPECONTENT\",'삽니다' AS \"TYPENAME\",A.BNO,A.BTYPE,A.BTITLE,A.BCONTENT,A.BWRITER,A.BCOUNT,A.BOARDFILE,A.BDATE FROM SALE_MARKETBOARD A "
						 * +"WHERE BWRITER = ?  ";
						 */
		"SELECT * FROM (SELECT ROWNUM AS rownumber,b.* FROM("
               +" SELECT '커뮤니티' AS \"TYPECONTENT\",'기사' AS \"TYPENAME\",A.*,(SELECT COUNT(*) FROM COMU_COMMENT WHERE BNO=A.BNO) AS COMMENTNUM FROM COMU_BOARD A "
               +"WHERE BWRITER = ? "
				+"UNION ALL "
				+"SELECT '커뮤니티' AS \"TYPECONTENT\",'기능사' AS \"TYPENAME\",A.*,(SELECT COUNT(*) FROM GCOMU_COMMENT WHERE BNO=A.BNO) AS COMMENTNUM FROM COMU_GBOARD A "
				+"WHERE BWRITER = ? "
				+"UNION ALL "
				+"SELECT '커뮤니티' AS \"TYPECONTENT\",'산업기사' AS \"TYPENAME\",A.*,(SELECT COUNT(*) FROM SGCOMU_COMMENT WHERE BNO=A.BNO) AS COMMENTNUM FROM COMU_SGBOARD A "
				+"WHERE BWRITER = ? "
				+"UNION ALL "
				+"SELECT '장터' AS \"TYPECONTENT\",'팝니다' AS \"TYPENAME\",A.BNO,A.BTYPE,A.BTITLE,A.BCONTENT,A.BWRITER,A.BCOUNT,A.BOARDFILE,A.BDATE,(SELECT COUNT(*) FROM marketboard_comment WHERE BNO=A.BNO) AS COMMENTNUM FROM MARKETBOARD A "
				+"WHERE BWRITER = ? "
				+"UNION ALL "
				+"SELECT '장터' AS \"TYPECONTENT\",'삽니다' AS \"TYPENAME\",A.BNO,A.BTYPE,A.BTITLE,A.BCONTENT,A.BWRITER,A.BCOUNT,A.BOARDFILE,A.BDATE,(SELECT COUNT(*) FROM sale_marketboard_comment WHERE BNO=A.BNO) AS COMMENTNUM FROM SALE_MARKETBOARD A "
				+"WHERE BWRITER = ?)b "
            +")a WHERE a.rownumber  >= ? AND a.rownumber  <= ? "
            +"ORDER BY BNO ASC";
		int startRow = (currentPage - 1) * limit +1; // 1, 11
		int endRow = startRow + limit - 1; // 10,20
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, bwriter);
			pstmt.setString(2, bwriter);
			pstmt.setString(3, bwriter);
			pstmt.setString(4, bwriter);
			pstmt.setString(5, bwriter);
			pstmt.setInt(6, startRow);
			pstmt.setInt(7, endRow);
			
			rset = pstmt.executeQuery();

			list = new ArrayList<commentCheck>();

			while(rset.next()){
				commentCheck b = new commentCheck();
				b.setTypecontent(rset.getString("TYPECONTENT"));
				b.setTypename(rset.getString("TYPENAME"));
				b.setBno(Integer.parseInt(rset.getString("BNO")));
				b.setBtype(Integer.parseInt(rset.getString("BTYPE")));
				b.setBtitle(rset.getString("BTITLE"));
				b.setBcontent(rset.getString("BCONTENT"));
				b.setBwriter(rset.getString("BWRITER"));
				b.setBcount(Integer.parseInt(rset.getString("BCOUNT")));
				b.setBoardfile(rset.getString("BOARDFILE"));
				b.setBdate(rset.getDate("BDATE"));
				b.setBcommentnum(Integer.parseInt(rset.getString("COMMENTNUM")));
				list.add(b);
			}
//			System.out.println("list!!!!!!! : "+list);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int getListCountComment(Connection con, String bwriter) {
		// 총 게시글 수
				int listCount = 0;
				PreparedStatement pstmt = null;
				ResultSet rset = null;

				String sql = "SELECT COUNT(*) FROM (SELECT '커뮤니티' AS \"TYPECONTENT\",'기사' AS \"TYPENAME\",A.* FROM COMU_BOARD A " 
						+"WHERE BWRITER = ? "
						+"UNION ALL "
						+"SELECT '커뮤니티' AS \"TYPECONTENT\",'기능사' AS \"TYPENAME\",A.* FROM COMU_GBOARD A "
						+"WHERE BWRITER = ? "
						+"UNION ALL "
						+"SELECT '커뮤니티' AS \"TYPECONTENT\",'산업기사' AS \"TYPENAME\",A.* FROM COMU_SGBOARD A "
						+"WHERE BWRITER = ? "
						+"UNION ALL "
						+"SELECT '장터' AS \"TYPECONTENT\",'팝니다' AS \"TYPENAME\",A.BNO,A.BTYPE,A.BTITLE,A.BCONTENT,A.BWRITER,A.BCOUNT,A.BOARDFILE,A.BDATE FROM MARKETBOARD A "
						+"WHERE BWRITER = ? "
						+"UNION ALL "
						+"SELECT '장터' AS \"TYPECONTENT\",'삽니다' AS \"TYPENAME\",A.BNO,A.BTYPE,A.BTITLE,A.BCONTENT,A.BWRITER,A.BCOUNT,A.BOARDFILE,A.BDATE FROM SALE_MARKETBOARD A "
						+"WHERE BWRITER = ? )";
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, bwriter);
					pstmt.setString(2, bwriter);
					pstmt.setString(3, bwriter);
					pstmt.setString(4, bwriter);
					pstmt.setString(5, bwriter);
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

	public ArrayList<MyScore> myscoreList(Connection con, String id) {
		ArrayList<MyScore> sco = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM USR_SCORE JOIN TEST_INFO USING(TC) WHERE USR_ID=? ORDER BY TC,SOLVEDATE";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			sco = new ArrayList<MyScore>();
			while(rset.next()) {
				MyScore ms = new MyScore();
				ms.setId(rset.getString("USR_ID"));
				ms.setTc(rset.getString("TC"));
				ms.setScore(rset.getInt("SCORE"));
				ms.setTc_name(rset.getString("TN"));
				
				sco.add(ms);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return sco;
	}
}
