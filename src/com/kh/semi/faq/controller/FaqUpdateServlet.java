package com.kh.semi.faq.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.semi.faq.model.service.FaqService;
import com.kh.semi.faq.model.vo.Faq;
import com.kh.semi.member.vo.Member;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
/**
 * Servlet implementation class FaqUpdateServlet
 */
@WebServlet("/fUpdate.fa")
public class FaqUpdateServlet extends HttpServlet {
	
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 5528838368761539661L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public FaqUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category = request.getParameter("fcategory");
		System.out.println("fcategory : " + category);

		String title = request.getParameter("ftitle");
		System.out.println("ftitle : " + title);
		
		String contents= request.getParameter("fcontents");
		System.out.println("fcontents : " + contents);
		
		HttpSession session = request.getSession();
		String writer = ((Member)session.getAttribute("member")).getUserId();
		System.out.println("writer : " +writer);
		
		int fno = Integer.parseInt(request.getParameter("fno"));
		System.out.println("fno : " +fno);
		
		Faq f = new Faq(category,title,contents);
		f.setFno(fno);
		int result = 0;
		FaqService fs = new FaqService();
		result = fs.updateFaq(f);

		if(result>0) {
			response.sendRedirect("fList.fa");

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
