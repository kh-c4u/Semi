package com.kh.semi.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.service.MemberService;
import com.kh.semi.member.vo.Member;

/**
 * Servlet implementation class MemberDeleteServlet
 */
/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/mDelete.me")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String userId = ((Member)session.getAttribute("member")).getUserId();
		String userPWd = ((Member)session.getAttribute("member")).getUserPwd();
		String username = ((Member)session.getAttribute("member")).getUserName();
		String useremail = ((Member)session.getAttribute("member")).getEmail();
		String userId2 = request.getParameter("Id");
		String userPWd2 = request.getParameter("PWd");
		String username2 = request.getParameter("name");
		String useremail2 = request.getParameter("email");
		
		MemberService ms = new MemberService();
		try {
			if(!(userId.equals(userId2)   &&
				userPWd.equals(userPWd2)  &&
				username.equals(username2)&&
				useremail.equals(useremail2))) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('회원정보가 일치하지 않습니다. 다시 입력해주세요.'); location.href='view/mypage/semi_mypage-withdraw.jsp';</script>");
				writer.close();
			}else {
				int result = ms.deleteMember(userId);
				System.out.println("회원 탈퇴  성공!");
				session.invalidate();
				response.sendRedirect("semi_main.jsp");
			}
		}catch(IOException e) {
			request.setAttribute("msg", "회원 탈퇴 수행 중 에러 발생");
			request.setAttribute("exception", e);

			System.out.println("MemberDeleteServlet에서 에러발생!!!!");

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
