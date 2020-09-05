package com.kh.Qna.controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class QnadetailServlet
 */
@WebServlet("/QnadetailServlet.bo")
public class QnadetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QnadetailServlet() {
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
		HttpSession session = request.getSession();
		Member result = new Member();
		result = (Member) session.getAttribute("member");
//		request.setAttribute("user_id", result.getUserId());
		request.setAttribute("user_id", "admin");
		int seq = Integer.parseInt(request.getParameter("seq"));
		QnaService qs = new QnaService();
		Qna qnaVo = new Qna();
		qnaVo = qs.QnaList(seq);

		request.setAttribute("qnaVo", qnaVo);

		List<Qna> qnaReplylist = qs.QnaReplyList(seq);
		System.out.println("siz : " + qnaReplylist.size());
		request.setAttribute("qnaReplylist", qnaReplylist);
		request.setAttribute("replyCnt", qnaReplylist.size());
		request.setAttribute("seq", seq);

		request.getRequestDispatcher("view/qna/qnadetail.jsp").forward(request, response);
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
