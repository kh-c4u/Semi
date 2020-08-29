package com.kh.semi.comubaord.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.comubaord.model.service.ComuBoardService;
import com.kh.semi.comubaord.model.vo.ComuBoard;

/**
 * Servlet implementation class SearchComuBoardServlet
 */
@WebServlet("/searchBoard1.bo")
public class SearchComuBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchComuBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 검색 카테고리
				String category = request.getParameter("con");
				
				// 검색 키워드
				String keyword = request.getParameter("keyword");
				
				ArrayList<ComuBoard> list = new ArrayList<ComuBoard>();
				
				list = new ComuBoardService().searchBoard(category,keyword);
				
				String page = "";
				if(list != null) {
					page = "view/comuboard/comu_boardlist1.jsp";
					request.setAttribute("list", list);
				}else {
					page = "view/common/errorPage.jsp";
					request.setAttribute("msg", "게시글 검색 실패!");
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
