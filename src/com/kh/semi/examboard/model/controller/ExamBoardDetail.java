package com.kh.semi.examboard.model.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.examboard.model.service.ExamBoardService;
import com.kh.semi.examboard.model.vo.ExamBoard;
import com.kh.semi.member.vo.Member;

/**
 * Servlet implementation class ExamBoardDetail
 */
@WebServlet("/examdetail.ex")
public class ExamBoardDetail extends HttpServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = -994402237916046421L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ExamBoardDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tc = request.getParameter("tc");
		ExamBoard eb = null;
		
		ExamBoardService ebs = new ExamBoardService();
		
		eb = ebs.examBoardDetail(tc);
		
		HttpSession session = request.getSession(false);
		Member user = (Member)session.getAttribute("member");
		
		
		String page = "";
		if(eb != null && user != null) {
			page = "view/exam/semi_exam_Detail.jsp";
			request.setAttribute("board", eb);

			request.getRequestDispatcher(page).forward(request, response);
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
