package com.kh.semi.comuboardComment.controller3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.comuboardComment.model.vo.comuboardComment;
import com.kh.semi.comuboardComment.service.BoardCommentService;

/**
 * Servlet implementation class CommentDeleteServlet
 */
@WebServlet("/comentDelete.co")
public class CommentDeleteServlet extends HttpServlet {
	
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1700344466406057039L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public CommentDeleteServlet() {
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
		
		BoardCommentService bcs = new BoardCommentService();
		
		int result = bcs.deleteComment(com);
		
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/CselectOne.bo?bno=" + bno);
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
