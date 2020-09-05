package com.kh.Qna.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
 * Servlet implementation class MemberIdCheckServlet
 */
@WebServlet("/QnaBoardServlet.do")
public class QnaBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QnaBoardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
//			session.setAttribute("member", result);
		Member member = ((Member)session.getAttribute("member"));
		String user_name = member.getUserName();
//		request.setAttribute("user_name", user_name);
		request.setAttribute("user_name", "관리자");
		Qna qnaVo = new Qna();

		QnaService qs = new QnaService();
		List<Qna> qnalist = new ArrayList();

		System.out.println("es : " + qnaVo.getTitle());
		request.setAttribute("categoryname", qnaVo.getCategoryName());
		request.setAttribute("title", qnaVo.getTitle());
		request.setAttribute("reg_date", qnaVo.getReg_date());
		request.setAttribute("state", Integer.toString(qnaVo.getState()));
//			System.out.println(result);
//			request.getRequestDispatcher("view/IdCheckForm.jsp").forward(request, response);
//			response.setContentType("text/html;charset=euc-kr");
//			PrintWriter out = response.getWriter();
//			System.out.println(result);
//			if(result != 0)	out.println("0"); 
//			else		out.println("1");
//			
//			out.close();
//			response.sendRedirect("view/qna/qna.jsp");

		String fromDt = request.getParameter("fromDt");
		String toDt = request.getParameter("toDt");
		System.out.println("fromDt : " + fromDt);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("fromDt", fromDt);
		map.put("toDt", toDt);
		if (!(("".equals(fromDt) || fromDt == null) && ("".equals(toDt)|| toDt == null))) {
			qnalist = qs.QnaBoardDateList(map);
		}
		else {
			qnalist = qs.QnaBoardList();
		}
		request.setAttribute("qnalist", qnalist);
		request.setAttribute("fromDt", fromDt);
		request.setAttribute("toDt", toDt);
		
		
		request.getRequestDispatcher("view/qna/qna.jsp").forward(request, response);
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
