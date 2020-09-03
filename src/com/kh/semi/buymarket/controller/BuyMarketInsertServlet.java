package com.kh.semi.buymarket.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.semi.buymarket.model.service.BuyMarketService;
import com.kh.semi.marketboard.model.vo.MarketBoard;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class MarketBoardInsertServlet
 */
@WebServlet("/buymarketInsert.bo")
public class BuyMarketInsertServlet extends HttpServlet {
	
       
    /**
	 * 
	 */
	private static final long serialVersionUID = -540537578435279371L;


	/**
     * @see HttpServlet#HttpServlet()
     */
    public BuyMarketInsertServlet() {
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
			request.getRequestDispatcher("view/common/errorPage.jsp").forward(request, response);
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
		String category = mrequest.getParameter("category");
		
	
		

		String fileName = mrequest.getFilesystemName("filename");
		System.out.println("fileName : "  + fileName);
		
		MarketBoard b = new MarketBoard();
		b.setBtitle(title);
		b.setBcontent(content);
		b.setBwriter(writer);
		b.setBoardfile(fileName);
		b.setBcondition(category);
		
	
		


		int result = new BuyMarketService().insertBoard(b);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/buymarketList.bo");
		}else {
			request.setAttribute("msg", "게시글 작성 실패");
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
