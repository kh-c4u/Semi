package com.kh.semi.comubaord.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.comubaord.model.service.ComuBoardService;
import com.kh.semi.comubaord.model.vo.ComuBoard;
import com.kh.semi.comuboardComment.model.vo.comuboardComment;
import com.kh.semi.comuboardComment.service.BoardCommentService;
import com.kh.semi.member.vo.Member;

/**
 * Servlet implementation class comuBoardSelectoneServlet
 */
@WebServlet("/CselectOne.bo")
public class comuBoardSelectoneServlet extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1615579536934385288L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public comuBoardSelectoneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		ComuBoard b = new ComuBoardService().selectOne(bno);
		ArrayList<comuboardComment> clist = new BoardCommentService().selectList(bno);
		HttpSession session = request.getSession(false);
		Member user = (Member)session.getAttribute("member");
		
		
		String page = "";
		if(b != null && user != null) {
			page = "view/comuboard/comu_boardDetail.jsp";
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
