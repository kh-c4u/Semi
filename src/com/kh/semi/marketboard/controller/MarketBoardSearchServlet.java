package com.kh.semi.marketboard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.marketboard.model.service.MarketBoardService;
import com.kh.semi.marketboard.model.vo.MarketBoard;
import com.kh.semi.marketboard.model.vo.PageInfo;

/**
 * Servlet implementation class MarketBoardSearchServlet
 */
@WebServlet("/mbsearch.bo")
public class MarketBoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketBoardSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<MarketBoard> list = null;
		MarketBoardService cbs = new MarketBoardService();

		int startPage;
		int endPage;
		int maxPage;
		int currentPage;
		int limit;

		currentPage = 1;

		limit = 10;

		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		int listCount = cbs.getListCount();
		System.out.println("총 페이지 개수 : " + listCount);

		if (listCount <= 10) {
			maxPage = 1;
		} else {
			maxPage = (int) ((double) listCount / limit + 0.9);
		}

		startPage = ((int) ((double) currentPage / limit + 0.9) - 1) * limit + 1;

		endPage = startPage + limit - 1;

		// 만약 마지막 페이지보다 현재 게시글이 끝나는 페이지가 적다면
		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 검색 카테고리
		int category = Integer.parseInt(request.getParameter("conn"));

		

		// 검색 키워드 선택
		String selectKeyword = request.getParameter("skw");
		

		if (selectKeyword.equals("undefined")) {
			response.sendRedirect(request.getContextPath() + "/marketselectList.bo");
		} else {

			// 검색 키워드
			String keyword = request.getParameter("keyword");
			
			System.out.println("category : " + category);
			System.out.println("keyword : " + keyword);
			System.out.println("selectKeyword : " + selectKeyword);
			System.out.println("currentPage : " + currentPage);
			System.out.println("limit : " + limit);
			
			list = new MarketBoardService().searchBoard(category, keyword, selectKeyword, currentPage, limit);

			for (MarketBoard b : list) {
				System.out.println(b);
			}

			String page = "";
			if (list != null) {
				page = "view/marketboard/semi_Market_BoardList.jsp";
				request.setAttribute("list", list);
				PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
				request.setAttribute("pi", pi);

				request.getRequestDispatcher(page).forward(request, response);
			} else {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<html>");
				out.println("<body>");

				out.println("</body>");

				out.println("</html>");

				out.println("<script>");

				out.println("alert('검색 실패')");

				out.println("history.back();");

				out.println("</script>");

				page = "view/common/errorPage.jsp";
				request.setAttribute("msg", "게시글 목록 조회 실패!");
			}

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
