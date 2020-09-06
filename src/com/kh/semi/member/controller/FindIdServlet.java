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
 * Servlet implementation class FindIdServlet
 */
@WebServlet("/findid.me")
public class FindIdServlet extends HttpServlet {
	
       


	/**
	 * 
	 */
	private static final long serialVersionUID = 19196205946241673L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public FindIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		
		MemberService ms = new MemberService();
		
		String id = null;
		
		id= ms.findId(name,email);
		System.out.println(id);
		if(id!=null) {
			request.setAttribute("id", id);
			request.getRequestDispatcher("/view/member/semi_find_ID.jsp").forward(request, response);
			
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('아이디 찾기 실패 다시 입력해주세요.'); history.back();</script>");
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
