package com.kh.examQuestion.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.examQuestion.model.vo.ExamQuestion;
import com.kh.semi.comubaord.model.dao.ComuBoardDao;
import com.kh.semi.examboard.model.vo.ExamBoard;

public class ExamQuestionDao {
	
	private Properties prop;

	public ExamQuestionDao() {
		prop = new Properties();

		String filePath = ComuBoardDao.class.getResource("/config/Exam-query.properties").getPath();

		try {
			prop.load(new FileReader(filePath));
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<ExamQuestion> examQuestion(Connection con, String tc) {

		ExamQuestion eb = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("examQuestion");
		ArrayList<ExamQuestion> list = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tc);
			rset = pstmt.executeQuery();
			list = new ArrayList<ExamQuestion>();

			while(rset.next()) {
				eb = new ExamQuestion();
				eb.setTc(rset.getString("TC"));
				eb.setQn(Integer.parseInt(rset.getString("QN")));
				eb.setQc(rset.getString("QC"));
				eb.setAn(Integer.parseInt(rset.getString("AN")));
				eb.setAc(rset.getString("AC"));
				eb.setPo(Integer.parseInt(rset.getString("PO")));
				list.add(eb);
			}
			System.out.println(eb);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;

	}

	public int calculScore(Connection con, int qn, int an, String tc) {
		int score = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT PO FROM TEST_ANSWER WHERE TC=? AND QN=? AND AN=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tc);
			pstmt.setInt(2, qn+1);
			pstmt.setInt(3, an);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				score = rset.getInt("PO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		return score;
	}

	public int scoreStore(Connection con, String tc, int score, String id) {
		int result = 0 ;
		
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO USR_SCORE VALUES(?,?,?,SYSDATE)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, tc);
			pstmt.setInt(3, score);
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		
		
		return result;
	}
}
