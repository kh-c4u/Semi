package com.kh.examQuestion.model.service;

import static com.kh.semi.common.JDBCTemplate.*;


import java.sql.Connection;
import java.util.ArrayList;

import com.kh.examQuestion.model.dao.ExamQuestionDao;
import com.kh.examQuestion.model.vo.ExamQuestion;
import com.kh.semi.examboard.model.dao.ExamBoardDao;
import com.kh.semi.examboard.model.vo.ExamBoard;

public class ExamQuestionService {
	ExamQuestionDao bDao = new ExamQuestionDao();

	public ArrayList<ExamQuestion> examQeustion(String tc) {
		ArrayList<ExamQuestion> list = new ArrayList<ExamQuestion>();
//			System.out.println("ㅅ서비스 들어왔니?");
			ExamQuestion eb =null;
			Connection con = getConnection();

			list = bDao.examQuestion(con, tc);

			close(con);
			return list;
		}

	public int calculScore(int qn, int an, String tc) {
		Connection con = getConnection();
		int score = 0;
		
		score = bDao.calculScore(con,qn,an,tc);
		
		close(con);
		
		return score;
	}

	public int scoreStore(String tc, int score,String id) {
		int result = 0;
		Connection con = getConnection();
		
		result = bDao.scoreStore(con,tc,score,id);
		
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}
	}