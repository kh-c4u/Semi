package com.kh.semi.faq.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kh.semi.faq.model.service.FaqService;
import com.kh.semi.notice.model.service.NoticeService;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class FaqDeleteServlet
 */
@WebServlet("/fDelete.fa")
public class FaqDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int fno = Integer.parseInt(request.getParameter("fno"));
		System.out.println("delte fno : " + fno);
		// 서비스 결과 처리
		int result = new FaqService().deleteFaq(fno);
		System.out.println("serv: " + fno);
		if(result > 0) {
			response.sendRedirect("fList.fa");
		}else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter(); 

			out.println("<html>");
			out.println("<body>");

			out.println("</body>");

			out.println("</html>");

			out.println("<script>");

			out.println("alert('삭제 실패')");

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
