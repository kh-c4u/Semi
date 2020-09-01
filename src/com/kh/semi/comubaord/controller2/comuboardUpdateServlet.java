package com.kh.semi.comubaord.controller2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.semi.comubaord.model.service2.ComuBoardServiceGS;
import com.kh.semi.comubaord.model.vo.ComuBoard;
import com.kh.semi.member.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class comuboardUpdateServlet
 */
@WebServlet("/GScomuboardUpdate.bo")
public class comuboardUpdateServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -309402496948173448L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public comuboardUpdateServlet() {
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
		String title = mrequest.getParameter("title");
		System.out.println("title : " + title);
		
		int category = Integer.parseInt(mrequest.getParameter("category"));
		System.out.println("category : " + category);
		
		
		String content = mrequest.getParameter("content");
		System.out.println("content : " + content);
		String fileName = mrequest.getFilesystemName("filename");
		System.out.println("fileName : " + fileName);
		
		HttpSession session = request.getSession();
		String writer = ((Member)session.getAttribute("member")).getUserId();
		System.out.println("writer : " +writer);
		int bno = Integer.parseInt(mrequest.getParameter("bno"));
		System.out.println("bno : " +bno);
		
		
		ComuBoard cb = new ComuBoard(title,content,writer,fileName);
		cb.setBtype(category);
		cb.setBno(bno);
		int result = 0;
		ComuBoardServiceGS cs = new ComuBoardServiceGS();
		result = cs.updateComuboard(cb);
		
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/GSCselectOne.bo?bno=" + bno);
			
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
