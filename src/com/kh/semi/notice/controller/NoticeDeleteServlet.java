package com.kh.semi.notice.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.notice.model.service.NoticeService;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet("/noticedelete.no")
public class NoticeDeleteServlet extends HttpServlet {
	
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1756827922964030745L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest mrequest = new MultipartRequest(request,"/");
		int bno = Integer.parseInt(mrequest.getParameter("bno"));
		System.out.println("delte bno : " + bno);
		// 서비스 결과 처리
		int result = new NoticeService().deleteNotice(bno);
		
		if(result > 0) {
			response.sendRedirect("noticeList.no");
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
