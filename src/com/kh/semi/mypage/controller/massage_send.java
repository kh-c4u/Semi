package com.kh.semi.mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.vo.Member;
import com.kh.semi.mypage.model.service.MypageService;

/**
 * Servlet implementation class massage_send
 */
@WebServlet("/semi_mypage-massage_send.do")
public class massage_send extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public massage_send() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String id = request.getParameter("id");
		String titlename = request.getParameter("titlename");
		String writeNote = request.getParameter("writeNote");
		String sendId = ((Member)session.getAttribute("member")).getUserId();
		MypageService ps = new MypageService();
		try {
			int result = ps.mypageCheckId(id);
			if(result == 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('없는 ID입니다. 다시 입력해주세요.'); history.back(); </script>");
				writer.close();
			}else {
				System.out.println("아이디 있음");
				result = ps.mypage_send(id,titlename,writeNote,sendId);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('쪽지를 전송하였습니다.'); location.href='view/mypage/semi_mypage-massage_send.jsp'; </script>");
				writer.close();
			}
//			response.sendRedirect("../semi_main.jsp");
		}catch(Exception e) {
			request.setAttribute("msg", "massage_send 수행 중 에러 발생");
			request.setAttribute("exception", e);
			System.out.println("massage_send에서 에러발생!!!!");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
