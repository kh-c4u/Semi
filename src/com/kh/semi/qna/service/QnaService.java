package com.kh.semi.qna.service;

import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.commit;
import static com.kh.semi.common.JDBCTemplate.getConnection;
import static com.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kh.Qna.dao.QnaDao;
import com.kh.Qna.vo.Qna;
import com.kh.semi.member.dao.MemberDao;
import com.kh.semi.member.vo.Member;

public class QnaService {
	private Connection conn;
	private QnaDao qDao = new QnaDao();

	public int Qnawrite(Qna qnaVo) {
		int seq = 0;
		conn = getConnection();

		int result = 0;
		seq = qDao.qnaSeq(conn);
		qnaVo.setSeq(seq + 1);
		result = qDao.Qnawrite(conn, qnaVo);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Qna QnaList(int seq) {
		Qna qnaVo = new Qna();
		conn = getConnection();

		qnaVo = qDao.QnaList(conn, seq);
		System.out.println("sever : " + qnaVo.getCategoryName());
		close(conn);
		return qnaVo;
	}

	public List<Qna> QnaBoardList() {
		conn = getConnection();
		List<Qna> qnalist = new ArrayList();
		qnalist = qDao.QnaBoardList(conn);

		close(conn);
		return qnalist;
	}

	public List<Qna> QnaReplyList(int seq) {
		conn = getConnection();
		List<Qna> qnalist = new ArrayList();
		qnalist = qDao.QnaReplyList(conn, seq);

		close(conn);
		return qnalist;
	}

	public int QnaReplyWrite(Qna qnaVo) {
		int seq = 0;
		conn = getConnection();

		int result = 0;
		seq = qDao.qnaSeq(conn);
		qnaVo.setSeq(seq + 1);
		result = qDao.QnaReplyWrite(conn, qnaVo);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int QnaReplyDelete(int seq) {
		conn = getConnection();

		int result = qDao.QnaReplyDelete(conn, seq);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);

		return result;
	}

	public List<Qna> QnaBoardDateList(HashMap<String, String> map) {
		conn = getConnection();
		List<Qna> qnalist = new ArrayList();
		qnalist = qDao.QnaBoardDateList(conn, map);

		close(conn);
		return qnalist;
	}

	public <Board> ArrayList<Board> selectList(int currentPage, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getListCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
