package com.kh.semi.mypage.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.kh.semi.member.vo.Member;
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

	public int getListCount(Connection con) {
		// 총 게시글 수
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT COUNT(*) FROM mypage_message";

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

	public ArrayList<massage> selectList(Connection con, int currentPage, int limit, String cwriter) {
		ArrayList<massage> list =null;
		PreparedStatement pstmt=null;
		ResultSet rset = null;

		String sql =	"SELECT * FROM (SELECT ROWNUM AS rownumber, b.* FROM MYPAGE_MESSAGE b "
						+ "WHERE "
				        + "CWRITER = ? "
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
			System.out.println("list!!!!!!! : "+list);
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
			System.out.println("메세지 cno : " + n);

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
}
