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
import com.kh.semi.member.vo.Member;

/**
 * Servlet implementation class MyScoreServlet
 */
@WebServlet("/myscore.ex")
public class MyScoreServlet extends HttpServlet {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -5848706751225156052L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public MyScoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String answerString = request.getParameter("answerString");
		System.out.println("answerString : " + answerString);
		String tc = request.getParameter("tc");
		System.out.println("tc : " + tc);
		String[] answer = answerString.split(",");
		ExamQuestionService eqs = new ExamQuestionService();
		System.out.println("answer length : " + answer.length);
		int[] answernum = new int[answer.length];
		int score=0;
		int i = 0;
		for(String a : answer) {
			answernum[i] = Integer.parseInt(a);
			i++;
		}
		
		for(int qn =0 ; qn<answernum.length;qn++) {
			score += eqs.calculScore(qn,answernum[qn],tc);
			
			
		}
		
		System.out.println("score : " + score);
		HttpSession session = request.getSession();
		String id = ((Member)session.getAttribute("member")).getUserId();
		
		int result = eqs.scoreStore(tc,score,id);
		
		if(result>0) {
			
			response.sendRedirect("myscorelist.my");
			
		}else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter(); 

			out.println("<html>");
			out.println("<body>");

			out.println("</body>");

			out.println("</html>");

			out.println("<script>");

			out.println("alert('점수 추가 실패')");

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
