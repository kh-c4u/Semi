package com.kh.examQestion.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.examQuestion.model.service.ExamQuestionService;
import com.kh.examQuestion.model.vo.ExamQuestion;
import com.kh.semi.examboard.model.service.ExamBoardService;
import com.kh.semi.examboard.model.vo.ExamBoard;
import com.kh.semi.member.vo.Member;

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

		System.out.println("서블릿 :" + tc);

		int qn = Integer.parseInt(request.getParameter("qn"));

		ExamQuestion eb = null;

		ExamQuestionService ebs = new ExamQuestionService();


		eb = ebs.examQeustion(tc);
		System.out.println("db");

		eb = ebs.examQeustion(tc,qn);


		HttpSession session = request.getSession(false);
		Member user = (Member)session.getAttribute("member");

		String page = "";
		if(eb != null && user != null) {
			page = "view/exam/semi_exam_page.jsp";
			request.setAttribute("Question", eb);

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

			out.println("alert('문제 페이지 오류 발생')");

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
