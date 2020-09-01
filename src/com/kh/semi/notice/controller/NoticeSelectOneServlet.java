package com.kh.semi.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.comuboardComment.model.vo.comuboardComment;
import com.kh.semi.notice.model.service.NoticeService;
import com.kh.semi.notice.model.vo.Notice;
import com.kh.semi.noticeComment.service.BoardCommentService;

/**
 * Servlet implementation class NoticeSelectOneServlet
 */
@WebServlet("/noticeOne.no")
public class NoticeSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeSelectOneServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));

		Notice b = new NoticeService().selectOne(bno);
		ArrayList<comuboardComment> clist = new BoardCommentService().selectList(bno);

		String page = "";
		if(b != null) {
			page = "view/notice/notice_boardDetail.jsp";
			request.setAttribute("board", b);
			request.setAttribute("clist", clist);
		}else {
			page = "view/common/errorPage.jsp";
			request.setAttribute("msg", "게시글 상세보기 실패!");
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
