package com.kh.semi.errorComment.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.comuboardComment.model.vo.comuboardComment;
import com.kh.semi.errorComment.model.service.ErrorCommentService;

/**
 * Servlet implementation class CommentDeleteServlet
 */
@WebServlet("/ercomentDelete.co")
public class ErCommentDeleteServlet extends HttpServlet {
	
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1700344466406057039L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ErCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cno = Integer.parseInt(request.getParameter("cno"));
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		comuboardComment com = new comuboardComment();
		com.setBno(bno);
		com.setCno(cno);
		
		ErrorCommentService bcs = new ErrorCommentService();
		
		int result = bcs.deleteComment(com);
		
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/erselectOne.bo?bno=" + bno);
		}else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter(); 

			out.println("<html>");
			out.println("<body>");

			out.println("</body>");

			out.println("</html>");

			out.println("<script>");

			out.println("alert('게시 실패')");

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
