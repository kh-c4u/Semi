package com.kh.semi.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.member.service.MemberService;
import com.kh.semi.member.vo.Member;

/**
 * Servlet implementation class signUpServlet
 */
@WebServlet("/signup.do")
public class signUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("password");
		String userName = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address1") + request.getParameter("address2");
		
		Member m = new Member(userId,userPwd,userName,email,address);
		
		MemberService ms = new MemberService();
		int result = ms.MemberSignUp(m);
		
		if(result>0) {
			response.sendRedirect("view/semi_SignUpConfirm.jsp");
			
			
		}else {
			PrintWriter out=response.getWriter(); 

			

			out.println("<html>");
			out.println("<body>");

			out.println("</body>");

			out.println("</html>");

			out.println("<script>");

			out.println("alert('회원가입 실패');");

			out.println("history.back();");

			out.println("</script>");

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
