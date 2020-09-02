package com.kh.semi.faq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.faq.model.service.FaqService;
import com.kh.semi.faq.model.vo.Faq;


import com.kh.semi.member.vo.Member;




/**
 * Servlet implementation class FaqSelectOneServlet
 */
@WebServlet("/fOne.fa")
public class FaqSelectOneServlet extends HttpServlet {
	
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 2442470957715604099L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public FaqSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fno = Integer.parseInt(request.getParameter("fno"));

		Faq f = new FaqService().selectOne(fno);
		
		HttpSession session = request.getSession(false);
		Member user = (Member)session.getAttribute("member");
		
		
		if(f != null && user!=null) {
		
			request.setAttribute("board", f);
		
			
		}else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter(); 

			out.println("<html>");
			out.println("<body>");

			out.println("</body>");

			out.println("</html>");

			out.println("<script>");

			out.println("alert('로그인이 필요합니다.')");

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
