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
	private static final long serialVersionUID = 1L;
       
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
		int maxSize = 1024*1024*10;

		if(!ServletFileUpload.isMultipartContent(request)) {
			// 만약 올바른 multipart / form-data로 전송되지 않았을 경우
			// 에러 페이지로 즉시 이동시킨다.
			request.setAttribute("msg", "multipart를 통한 전송이 아닙니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
		}

		String root = request.getServletContext().getRealPath("/");
		System.out.println("최상위 경로 : " + root);

		// 게시판의 첨부파일을 저장할 폴더 이름 지정하기
		String savePath = root + "resources/boardUploadFiles";

		// 4. 실제 담아온 파일 기타 정보들을 활용하여
		// (request --> MultipartRequest)
		// MultipartRequest 생성하기
		MultipartRequest mrequest = new MultipartRequest(request, savePath,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		

		String category =mrequest.getParameter("fcategory");
		System.out.println("fcategory : " + category);

		String title = mrequest.getParameter("ftitle");
		System.out.println("ftitle : " + title);
		
		String contents= mrequest.getParameter("fcontents");
		System.out.println("fcontents : " + contents);

		int fno = Integer.parseInt(mrequest.getParameter("fno"));
		System.out.println("bno : " +fno);
		
		Faq f = new Faq(category,title,contents);
		f.setFno(fno);
		int result = 0;
		FaqService fs = new FaqService();
		result = fs.updateFaq(f);

		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/fOne.fa?fno=" + fno);

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
