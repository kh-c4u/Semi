package com.kh.Qna.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kh.Qna.vo.Qna;

public class QnaDao {

	public int Qnawrite(Connection conn, Qna qna) {
		int result = 0;

		PreparedStatement pstmt = null;
		String sql = "INSERT INTO QNA (SEQ, TITLE, CATEGORY, USER_ID, CONTENT, FILENAME) " + " VALUES(?,?,?,?,?,?) ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna.getSeq());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getCategoryName());
			pstmt.setString(4, qna.getUser_id());
			pstmt.setString(5, qna.getContent());
			pstmt.setString(6, qna.getFileName());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int qnaSeq(Connection conn) {
		int result = 0;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT MAX(SEQ) SEQ FROM QNA";
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt("SEQ");
			}

		} catch (SQLException e) {
			close(rset);
			close(pstmt);
		}

		return result;
	}

	public Qna QnaList(Connection conn, int seq) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Qna qnaVo = new Qna();

		String sql = "SELECT SEQ, CATEGORY, USER_ID, CONTENT, TITLE, REG_DATE, FILENAME, STATE FROM QNA "
				+ " WHERE SEQ = ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				qnaVo.setSeq(rset.getInt("SEQ"));
				qnaVo.setUser_id(rset.getString("USER_ID"));
				qnaVo.setContent(rset.getString("CONTENT"));
				qnaVo.setCategoryName(rset.getString("CATEGORY"));
				qnaVo.setTitle(rset.getString("TITLE"));
				qnaVo.setReg_date(rset.getString("REG_DATE"));
				qnaVo.setFileName(rset.getString("FILENAME"));
				qnaVo.setState(rset.getInt("STATE"));
			}

		} catch (SQLException e) {
			close(rset);
			close(pstmt);
		}

		return qnaVo;
	}

	public List<Qna> QnaBoardList(Connection conn) {

		List<Qna> qnalist = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT SEQ, CATEGORY, TITLE, REG_DATE, STATE FROM QNA";
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Qna qna = new Qna();
				qna.setSeq(rset.getInt("SEQ"));
				qna.setCategoryName(rset.getString("CATEGORY"));
				qna.setTitle(rset.getString("TITLE"));
				qna.setReg_date(rset.getString("REG_DATE"));
				qna.setState(rset.getInt("STATE"));
				qnalist.add(qna);

			}
			for (Qna qna2 : qnalist) {
				System.out.println("seq 2: " + qna2.getTitle());
			}

		} catch (SQLException e) {
			close(rset);
			close(pstmt);
		}

		return qnalist;
	}

	public List<Qna> QnaReplyList(Connection conn, int seq) {
		List<Qna> qnalist = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT SEQ, CATEGORY, TITLE, REG_DATE, CONTENT, STATE FROM QNA " + " WHERE MAINSEQ = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Qna qna = new Qna();
				qna.setSeq(rset.getInt("SEQ"));
				qna.setCategoryName(rset.getString("CATEGORY"));
				qna.setTitle(rset.getString("TITLE"));
				qna.setContent(rset.getString("CONTENT"));
				qna.setReg_date(rset.getString("REG_DATE"));
				qna.setState(rset.getInt("STATE"));
				qnalist.add(qna);

			}
			for (Qna qna2 : qnalist) {
				System.out.println("seq 2: " + qna2.getTitle());
			}

		} catch (SQLException e) {
			close(rset);
			close(pstmt);
		}

		return qnalist;
	}

	public int QnaReplyWrite(Connection conn, Qna qnaVo) {
		int result = 0;

		PreparedStatement pstmt = null;
		String sql = "INSERT INTO QNA (SEQ, TITLE, CATEGORY, USER_ID, CONTENT, FILENAME, MAINSEQ) "
				+ " VALUES(?,?,?,?,?,?,?) ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaVo.getSeq());
			pstmt.setString(2, qnaVo.getTitle());
			pstmt.setString(3, qnaVo.getCategoryName());
			pstmt.setString(4, qnaVo.getUser_id());
			pstmt.setString(5, qnaVo.getContent());
			pstmt.setString(6, qnaVo.getFileName());
			pstmt.setInt(7, qnaVo.getMainseq());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int QnaReplyDelete(Connection conn, int seq) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "DELETE FROM QNA " + " WHERE SEQ = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, seq);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public List<Qna> QnaBoardDateList(Connection conn, HashMap<String, String> map) {

		List<Qna> qnalist = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT SEQ, CATEGORY, TITLE, REG_DATE, STATE"
				+ " FROM QNA "
				+ " WHERE REG_DATE BETWEEN  TO_DATE(?, 'YY/MM/DD')   AND TO_DATE(?, 'YY/MM/DD') + 0.99999";
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("fromDt2 : " + map.get("fromDt"));
			System.out.println("toDt 2: " + map.get("toDt"));
			pstmt.setString(1, map.get("fromDt"));
			pstmt.setString(2, map.get("toDt"));
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Qna qna = new Qna();
				System.out.println("seq : "+ rset.getInt("SEQ"));
				qna.setSeq(rset.getInt("SEQ"));
				qna.setCategoryName(rset.getString("CATEGORY"));
				qna.setTitle(rset.getString("TITLE"));
				qna.setReg_date(rset.getString("REG_DATE"));
				qna.setState(rset.getInt("STATE"));
				qnalist.add(qna);

			}
			for (Qna qna2 : qnalist) {
				System.out.println("seq 2: " + qna2.getTitle());
			}

		} catch (SQLException e) {
			close(rset);
			close(pstmt);
		}

		return qnalist;
	}
}
