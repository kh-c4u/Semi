package com.kh.semi.member.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.semi.member.vo.Member;
public class MemberDao {

	public int MemberSignUp(Connection conn, Member m) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO USRINFO VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getAddress());
			
			result=pstmt.executeUpdate();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int chkId(Connection conn, String id) {
		int result = 0;

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM USRINFO WHERE USR_ID=?";
		System.out.println("DAO ID : " + id);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = 1;
			}
			
		} catch (SQLException e) {
			close(rset);
			close(pstmt);
		}
		
		
		return result;
	}

	public Member memberLogin(Connection conn, Member m) {
		Member result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM USRINFO WHERE USR_ID=? AND USR_PWD=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			rset = pstmt.executeQuery();
			
			
			if(rset.next()) {
				result = new Member();
				result.setAddress(rset.getString("USR_ADR"));
				result.setEmail(rset.getString("USR_EMAIL"));
				result.setUserId(rset.getString("USR_ID"));
				result.setUserName(rset.getString("USR_NAME"));
				result.setUserPwd(rset.getString("USR_PWD"));
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		return result;
		
	}

	public int deleteMember(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM USRINFO WHERE USR_ID=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public String findId(Connection conn, String name, String email) {
		String id = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM USRINFO WHERE USR_NAME=? AND USR_EMAIL=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				id = rset.getString("USR_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return id;
	}

	public String findPwd(Connection conn, String id, String email) {
		String pwd = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM USRINFO WHERE USR_ID=? AND USR_EMAIL=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				pwd = rset.getString("USR_PWD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return pwd;
	}

}
