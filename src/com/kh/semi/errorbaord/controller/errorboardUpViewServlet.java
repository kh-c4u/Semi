package com.kh.semi.errorbaord.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.errorbaord.model.vo.ErrorBoard;
import com.kh.semi.errorbaord.service.ErrorBoardService;

/**
 * Servlet implementation class comuboardUpViewServlet
 */
@WebServlet("/erUpView.bo")
public class errorboardUpViewServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4558529078575827991L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public errorboardUpViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		ErrorBoard b = new ErrorBoardService().updateView(bno);
		
		String page = "";
		if(b != null) {
			page = "view/errorboard/semi_error_baordUpdate.jsp";
			request.setAttribute("board", b);
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
