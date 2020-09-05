package com.kh.Qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.Qna.vo.Qna;
import com.kh.semi.member.vo.Member;
import com.kh.semi.qna.service.QnaService;

/**
 * Servlet implementation class QnaReplyWriteServlet
 */
@WebServlet("/QnaReplyWriteServlet.do")
public class QnaReplyWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QnaReplyWriteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title2 = (String) request.getAttribute("title");
		Member result = new Member();
		HttpSession session = request.getSession();
		session.setAttribute("member", result);
		int seq = Integer.parseInt(request.getParameter("seq"));
		String categoryName = (String) request.getParameter("categoryNameTmp");
		String title = (String) request.getParameter("title");
		String content = (String) request.getParameter("content");

		Qna qnaVo = new Qna();
		qnaVo.setCategoryName("reply");
		qnaVo.setContent(content);
		qnaVo.setTitle("reply");
		qnaVo.setUser_id("admin");
		qnaVo.setMainseq(seq);
//				qnaVo.setUser_id(result.getUserId());

		QnaService qs = new QnaService();
		qs.QnaReplyWrite(qnaVo);

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
