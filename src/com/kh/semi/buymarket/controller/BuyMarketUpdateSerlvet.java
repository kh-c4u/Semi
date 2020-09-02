package com.kh.semi.buymarket.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.semi.buymarket.model.service.BuyMarketService;
import com.kh.semi.market.model.vo.MarketBoard;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BoardUpdateSerlvet
 */
@WebServlet("/bUpdate.bo")
public class BuyMarketUpdateSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyMarketUpdateSerlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 전송할 최대 크기 설정
				int maxSize = 1024* 1024* 10;
				
				System.out.println(1);
				// 2. multipart/form-data 형식으로 전송되었는지 확인
				if(!ServletFileUpload.isMultipartContent(request)) {
					request.setAttribute("msg", "multipart를 통한 전송이 아닙니다.");
					request.getRequestDispatcher("view/common/errorPage.jsp").forward(request, response);
				}
				System.out.println(2);
				// 3. 웹 상의 루트(최상위 경로) 경로를 활용하여
				//    저장할 폴더의 위치 지정하기
				
				String root = request.getServletContext().getRealPath("/");
				System.out.println("최상위 경로 : " + root);
				
				System.out.println(3);
				// 게시판의 첨부파일을 저장할 폴더 이름 지정하기
				// (폴더를 자동으로 만들어 주지 않기 때문에 미리 폴더를 준비해야 한다.)
				String savePath = root + "resources/boardUploadFiles";
				
				// 4. 실제 담아온 파일 기타 정보들을 활용하여
						//    MultipartRequest 생성하기
						// request --> MultipartRequest
						
				MultipartRequest mrequest = new MultipartRequest(
													request, // 변경하기 위한 원본 객체
													savePath, // 파일 저장 경로
													maxSize, // 저장할 파일의 최대 크기
													"UTF-8", // 저장할 문자셋 설정
													new DefaultFileRenamePolicy()
													         // 만약에 동일한 이름의 
															 // 파일을 저장했을 경우	
													 		 // 기존의 파일과 구분하기 위해 
													 		 // 새로운 파일명 뒤에 숫자를 붙이는 규칙
															 // OOO.txt --> OOO1.txt 
											);
				
				System.out.println(4);
				// 5-1. 기본 전송값 처리하기
				String title = mrequest.getParameter("title");
				String content = mrequest.getParameter("content");
				int bno = Integer.parseInt(mrequest.getParameter("bno"));
				
				// 6. 전송된 파일 VO에 담아 서비스로 보내기
				MarketBoard b = new MarketBoard();
				b.setBno(bno);
				b.setBtitle(title);
				b.setBcontent(content);
				b.setBoardfile(mrequest.getFilesystemName("file"));
				System.out.println(5);
				System.out.println("수정 데이터 : " + b);
				// 7. 서비스 결과 처리하기
				int result = new BuyMarketService().updateBoard(b);
				
				if( result > 0) {
					response.sendRedirect(request.getContextPath()+"/buymarketselectOne.bo?bno="+bno);
					System.out.println(6);
				} else {
					request.setAttribute("msg", "게시글 수정 실패!");
					request
					.getRequestDispatcher("view/common/errorPage.jsp")
					.forward(request, response);
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
