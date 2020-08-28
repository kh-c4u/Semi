package com.kh.semi.member.service;

import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.commit;
import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.semi.member.dao.MemberDao;
import com.kh.semi.member.vo.Member;


public class MemberService {
	private Connection conn;
	private MemberDao mDao = new MemberDao();
	public int MemberSignUp(Member m){
		conn = getConnection();
		
		int result = 0;
		result = mDao.MemberSignUp(conn,m);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int chkId(String id) {
		int result = 0;
		
		conn = getConnection();
		
		result = mDao.chkId(conn,id);
		
		close(conn);
		
		return result;
	}
	public Member memberLogin(Member m) {
		Member result = null;
		conn = getConnection();
		
		result = mDao.memberLogin(conn,m);
		
		close(conn);
		
		return result;
	}
	
	
	
	
	
}
