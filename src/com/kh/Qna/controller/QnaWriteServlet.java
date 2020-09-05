package com.kh.Qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.Qna.vo.Qna;
import com.kh.semi.member.vo.Member;
import com.kh.semi.qna.service.QnaService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class QnaWriteServlet
 */
@WebServlet("/QnaWriteServlet.do")
public class QnaWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QnaWriteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int maxSize = 1024 * 1024 * 10;
		String root = request.getServletContext().getRealPath("/");
		String savePath = root + "resources/boardUploadFiles";
		MultipartRequest mrequest = new MultipartRequest(
				request,
				savePath, 
				maxSize, 
				"UTF-8", 
				new DefaultFileRenamePolicy()
	
		);
		String title2 = (String) request.getAttribute("title");
		
		Member result = new Member();
		HttpSession session = request.getSession();
		session.setAttribute("member", result);
		
		String categoryName = (String) mrequest.getParameter("categoryNameTmp");
		
		String title = (String) mrequest.getParameter("title");
		String content = mrequest.getParameter("content");
		

	
		System.out.println("경로 : " + root); 

	

		String fileName = mrequest.getFilesystemName("file");
		Qna qnaVo = new Qna();
		qnaVo.setCategoryName(categoryName);
		qnaVo.setContent(content);
		qnaVo.setFileName(fileName);
		qnaVo.setTitle(title);
		qnaVo.setUser_id("admin");
//		qnaVo.setUser_id(result.getUserId());

		int result1 = new QnaService().Qnawrite(qnaVo);
		
		if(result1>0) {
		request.getRequestDispatcher(request.getContextPath()+"/QnaBoardServlet.do");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}else {
		request.setAttribute("msg", "실패");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}

}
