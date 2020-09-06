package com.kh.semi.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.member.service.MemberService;

/**
 * Servlet implementation class FindPwdServlet
 */
@WebServlet("/findpwd.me")
public class FindPwdServlet extends HttpServlet {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -2390174517812477748L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public FindPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		
		MemberService ms = new MemberService();
		
		String pwd = null;
		
		pwd= ms.findPwd(id,email);
		
		if(pwd!=null) {
			request.setAttribute("pwd", pwd);
			request.getRequestDispatcher("/view/member/semi_find_PW.jsp").forward(request, response);
			
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('비밀번호 찾기 실패 다시 입력해주세요.'); history.back();</script>");
			writer.close();
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
