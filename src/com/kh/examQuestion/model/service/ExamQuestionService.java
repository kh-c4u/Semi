package com.kh.examQuestion.model.service;

import static com.kh.semi.common.JDBCTemplate.close;
import static com.kh.semi.common.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.kh.examQuestion.model.dao.ExamQuestionDao;
import com.kh.examQuestion.model.vo.ExamQuestion;
import com.kh.semi.examboard.model.dao.ExamBoardDao;
import com.kh.semi.examboard.model.vo.ExamBoard;

public class ExamQuestionService {
	ExamQuestionDao bDao = new ExamQuestionDao();
	
	public ExamQuestion examQeustion(String tc) {
		ExamQuestion eb =null;
		Connection con = getConnection();
		
		eb = bDao.examQuestion(con, tc);

		close(con);
		return eb;
	}
}
