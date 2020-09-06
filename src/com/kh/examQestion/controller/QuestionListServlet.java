package com.kh.examQestion.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.eaxmAnswer.model.vo.PageInfo;
import com.kh.examQuestion.model.service.ExamQuestionService;
import com.kh.examQuestion.model.vo.ExamQuestion;
import com.kh.semi.member.vo.Member;

import javafx.scene.control.Alert;

/**
 * Servlet implementation class QuestionListServlet
 */
@WebServlet("/QuestionList.qo")
public class QuestionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tc = request.getParameter("tc");
//		int qn = Integer.parseInt(request.getParameter("qn"));
//		System.out.println("qn : " + qn);
//		ExamQuestion eb = null;
		ArrayList<ExamQuestion> list = new ArrayList<ExamQuestion>();


//		int qn = Integer.parseInt(request.getParameter("qn"));
//		System.out.println("서블렛qn :" + qn);

		ExamQuestion eb = null;
		int maxPage; //마지막페이지
		int currentPage = 0; //현제 패이지
		ExamQuestionService ebs = new ExamQuestionService();

		list = ebs.examQeustion(tc);
	
		HttpSession session = request.getSession(false);
		Member user = (Member)session.getAttribute("member");

		String page = "";
		if(list != null && user != null) {
			page = "view/exam/semi_exam_page.jsp";
			request.setAttribute("list", list);
			System.out.println("list : " + list );
			maxPage = list.size()/4;
			PageInfo pi = new PageInfo(maxPage,currentPage);
			
			
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
		}else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter(); 

			out.println("<html>");
			out.println("<body>");

			out.println("</body>");

			out.println("</html>");

			out.println("<script>");

			out.println("alert('문제 페이지 오류 발생')");

			out.println("history.back();");

			out.println("</script>");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

	}
