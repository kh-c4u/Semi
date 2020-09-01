package com.kh.semi.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.notice.model.service.NoticeService;
import com.kh.semi.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpviewServlet
 */
@WebServlet("/noticeupview.no")
public class NoticeUpviewServlet extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7941607200519051666L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeUpviewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));

		Notice n = new NoticeService().updateView(bno);

		String page = "";
		if(n != null) {
			page = "view/notice/notice_Update.jsp";
			request.setAttribute("board", n);
		}else {
			page = "view/common/errorPage.jsp";
			request.setAttribute("msg", "공지 글 수정 페이지 연결 실패!");
		}

		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
