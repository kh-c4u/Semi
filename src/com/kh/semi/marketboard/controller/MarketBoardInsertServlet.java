package com.kh.semi.marketboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.semi.marketboard.model.service.MarketBoardService;
import com.kh.semi.marketboard.model.vo.MarketBoard;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class MarketBoardInsertServlet
 */
@WebServlet("/marketboardInsert.bo")
public class MarketBoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketBoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int maxSize = 1024 * 1024 * 10;

		
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "multipart를 통한 전송이 아닙니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}


		String root = request.getServletContext().getRealPath("/");
		System.out.println("최상위 경로 : " + root);
		
		String savePath = root + "resources/boardUploadFiles";
		


		MultipartRequest mrequest = new MultipartRequest(
									request, 
									savePath,
									maxSize,
									"UTF-8",
									new DefaultFileRenamePolicy()
									);
		
		String title = mrequest.getParameter("title");
		// content에 개행문자는 \n 으로 저장되어 있을것이다.
		// 개행문자는 다음과 같이 바꿔준다. : replaceAll("\n","<br>");
		String content = mrequest.getParameter("content");
		content  = content.replaceAll("\n", "<br>");
		String writer = mrequest.getParameter("userId");
		String sale_status = mrequest.getParameter("sale_status");
		


		String fileName = mrequest.getFilesystemName("filename");
		
		MarketBoard b = new MarketBoard();
		b.setBtitle(title);
		b.setBcontent(content);
		b.setBwriter(writer);
		b.setBoardfile(fileName);
		b.setBcondition(sale_status);
		


		int result = new MarketBoardService().insertBoard(b);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/marketselectList.bo");
		}else {
			request.setAttribute("msg", "게시글 작성 실패");
			request.getRequestDispatcher("view/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
