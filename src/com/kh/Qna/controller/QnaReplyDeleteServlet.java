package com.kh.Qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.vo.Member;
import com.kh.semi.qna.service.QnaService;

/**
 * Servlet implementation class QnaReplyDeleteServlet
 */
@WebServlet("/QnaReplyDServlet.do")
public class QnaReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()	
	 */
	public QnaReplyDeleteServlet() {
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
		// TODO Auto-generated method stub
		Member result = new Member();
		HttpSession session = request.getSession();
		session.setAttribute("member", result);
		int seq = Integer.parseInt(request.getParameter("seq"));

		QnaService qs = new QnaService();

//				if("admin".equals(result.getUserId())) {
		if ("admin".equals("admin")) {
			qs.QnaReplyDelete(seq);
		}
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
