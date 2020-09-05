package com.kh.examQuestion.model.dao;

import static com.kh.semi.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public ExamQuestion examQuestion(Connection con, String tc, int qn) {
		ExamQuestion eb = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("examQuestion");

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tc);
			pstmt.setInt(2, qn);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				eb = new ExamQuestion();
				eb.setQc(rset.getString("QC"));
				eb.setQn(rset.getInt("QN"));
				eb.setTc(rset.getString("TC"));

			}
			System.out.println(eb);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);


		}

		return eb;
	}

}
