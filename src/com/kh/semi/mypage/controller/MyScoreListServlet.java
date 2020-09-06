package com.kh.semi.mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.vo.Member;
import com.kh.semi.mypage.model.service.MypageService;
import com.kh.semi.mypage.model.vo.MyScore;

/**
 * Servlet implementation class MyScoreListServlet
 */
@WebServlet("/myscorelist.my")
public class MyScoreListServlet extends HttpServlet {
       

	/**
	 * 
	 */
	private static final long serialVersionUID = -6163436531193584574L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public MyScoreListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = ((Member)session.getAttribute("member")).getUserId();
		
		MypageService ms = new MypageService();
		
		ArrayList<MyScore> sco = null;
		
		sco = ms.myscoreList(id);
		
		if(sco!=null) {
			request.setAttribute("scoreList", sco);
			request.getRequestDispatcher("/view/mypage/semi_mypage_myscore.jsp").forward(request,response);
			
		}else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter(); 

			out.println("<html>");
			out.println("<body>");

			out.println("</body>");

			out.println("</html>");

			out.println("<script>");

			out.println("alert('점수 가져오기 실패')");

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
