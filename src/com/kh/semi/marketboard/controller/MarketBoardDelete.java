package com.kh.semi.marketboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.marketboard.model.service.MarketBoardService;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class MarketBoardDelete
 */
@WebServlet("/mdelete.bo")
public class MarketBoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketBoardDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest mrequest = new MultipartRequest(request,"/");
		int bno = Integer.parseInt(mrequest.getParameter("bno"));
		System.out.println("delete bno : " + bno);
		// 서비스 결과 처리
		int result = new MarketBoardService().deleteBoard(bno);
		
		if(result > 0) {
			response.sendRedirect("marketselectList.bo");
		}else {
			request.setAttribute("msg", "게시글 삭제 실패!");
			request.getRequestDispatcher("view/errorPage.jsp").forward(request, response);
			
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
