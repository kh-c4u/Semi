package com.kh.semi.errorbaord.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.errorbaord.service.ErrorBoardService;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class comuboardDeleteServlet
 */
@WebServlet("/erDelete.bo")
public class errorboardDeleteServlet extends HttpServlet {
	
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 2246450383476469697L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public errorboardDeleteServlet() {
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
		int result = new ErrorBoardService().deleteBoard(bno);
		
		if(result > 0) {
			response.sendRedirect("errorList.bo");
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
