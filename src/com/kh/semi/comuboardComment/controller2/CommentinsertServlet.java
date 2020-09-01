package com.kh.semi.comuboardComment.controller2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.comuboardComment.model.vo.comuboardComment;
import com.kh.semi.comuboardComment.service.BoardCommentService;

/**
 * Servlet implementation class CommentinsertServlet
 */
@WebServlet("/GSCinsertComment.bo")
public class CommentinsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentinsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writer = request.getParameter("writer");
		int bno = Integer.parseInt(request.getParameter("bno"));
		String content = request.getParameter("replyContent");
		int refcno = Integer.parseInt(request.getParameter("refcno"));
		int clevel= Integer.parseInt(request.getParameter("clevel"));
		
		comuboardComment bco = new comuboardComment(bno,content,writer,refcno,clevel);
		
		int result = new BoardCommentService().insertComment(bco);
		
		if(result > 0) {
			response.sendRedirect("CselectOne.bo?bno="+bno);
		}else {
			request.setAttribute("msg", "댓글 작성 실패!");
			request.getRequestDispatcher("view/common/errorPage.jsp").forward(request, response);
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
