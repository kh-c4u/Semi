package com.kh.semi.notice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.comuboardComment.model.vo.comuboardComment;
import com.kh.semi.member.vo.Member;
import com.kh.semi.notice.model.service.NoticeService;
import com.kh.semi.notice.model.vo.Notice;
import com.kh.semi.noticeComment.service.BoardCommentService;

/**
 * Servlet implementation class NoticeSelectOneServlet
 */
@WebServlet("/noticeOne.no")
public class NoticeSelectOneServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5223866049167107695L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeSelectOneServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));

		Notice b = new NoticeService().selectOne(bno);
		ArrayList<comuboardComment> clist = new BoardCommentService().selectList(bno);

		String page = "";
		HttpSession session = request.getSession(false);
		Member user = (Member)session.getAttribute("member");
		
		
		if(b != null && user!=null) {
			page = "view/notice/notice_boardDetail.jsp";
			request.setAttribute("board", b);
			request.setAttribute("clist", clist);
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
