package com.kh.semi.mypage.model.service;

import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.commit;
import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.semi.member.dao.MemberDao;
import com.kh.semi.member.vo.Member;
import com.kh.semi.mypage.model.dao.MypageDao;
import com.kh.semi.mypage.model.vo.massage;

public class MypageService {
	private Connection conn;
	private MypageDao pDao = new MypageDao();

	public int mypageCheckId(String id) {
		conn = getConnection();
		MemberDao mDao = new MemberDao();
		int result = 0;
		result = mDao.chkId(conn,id);

		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int mypage_send(String id, String titlename, String writeNote, String sendId) {
		conn = getConnection();
		int result = 0;
		result = pDao.mypage_send(conn,id,titlename,writeNote,sendId);

		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int getListCount() {
		Connection con = getConnection();

		int listCount = pDao.getListCount(con);

		close(con);
		return listCount;
	}

	public ArrayList<massage> selectList(int currentPage, int limit, String cwriter) {
		Connection con = getConnection();
		ArrayList<massage> list = pDao.selectList(con,currentPage,limit,cwriter);

		close(con);
		return list;
	}

	public ArrayList<massage> searchMassage(String category, String keyword, int currentPage, int limit, String cwriter) {
		Connection con = getConnection();
		ArrayList<massage> list = null;

		//		if(category.length() >0) {
		//			list = nDao.searchNotice(con,category,keyword);
		//		}else {
		//			list = nDao.selectList(con);
		//		}

		list = (category.length() >0) ? pDao.searchNotice(con,category,keyword,currentPage,limit,cwriter) :  pDao.selectList(con,currentPage,limit,cwriter);
		return list;
	}

	public massage selectOne(int cno) {
		Connection con = getConnection();

		massage n = pDao.selectOne(con,cno);
		// 게시글의 상세보기를 통해 1회 조회할때
		//1. nno에 해당하는 게시글 내용을 가져오기(SELECT)
		//2. 게시글 내용이 성공적으로 불러와졌다면 (UPDATE) 조회수가 1 증가해야한다.
		if(n != null) {
			int result = pDao.updateRead(con,cno);

			if(result > 0) commit(con);
			else rollback(con);
		}
		close(con);
		return n;
	}

	public int updateMember1(Member m) {
		conn = getConnection();
		
		int result = pDao.updateMember(conn,m);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int updateMember2(Member m) {
		conn = getConnection();
		
		int result = pDao.updateMember2(conn,m);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int deleteMassage(String cno) {
		conn = getConnection();
		
		int result = pDao.deleteMassage(conn,cno);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		System.out.println(result);
		close(conn);
		
		return result;
	}
}

