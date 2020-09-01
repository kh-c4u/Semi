package com.kh.semi.buymarket.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.buymarket.model.service.BuyMarketService;
import com.kh.semi.market.model.vo.MarketBoard;
import com.kh.semi.market.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/buymarketList.bo")
public class BuyMarketListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyMarketListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<MarketBoard> list =null;
				BuyMarketService bs = new BuyMarketService();
				int startPage;
				
				// 한 번에 표시할 페이지들 중 가장 뒤의 페이지
				int endPage;
				
				// 전체 페이지의 가장 마지막 페이지
				int maxPage;
				
				// 사용자가 위치한 현재 페이지
				int currentPage;
				
				// 총 페이지 수(한 페이지 당 보여줄 게시글 수)
				int limit;
				
				// 처음 접속 시 페이지는 1페이지 부터 시작한다.
				currentPage = 1;
				
				// 글 개수 및 페이지 수 10개로 제한
				limit = 10;
				
				// 만약에 사용자가 현재 페이지의 정보를 들고왔다면
				// 현재 페이지의 정보를 1에서 특정 페이지로 수정해주어야 한다.
				if(request.getParameter("currentPage") != null) {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				
				// 페이징 처리
				int listCount = bs.getListCount();
				System.out.println("총 페이지 개수 : " + listCount);
				
		
				maxPage = (int)((double)listCount / limit +0.9);
				//                         253.0 / 10+0.9 --> 26
				startPage 
					=    ((int)((double)currentPage/limit+0.9)-1)*limit +1;
		            
				
				endPage = startPage + limit -1;
			
				// 만약 마지막 페이지보다 현재 게시글이 끝나는 페이지가 적다면
				if(endPage > maxPage) {
					endPage = maxPage;
				}
				
			
				list = bs.selectList(currentPage,limit); // 현재페이지와 목록의수 --> 리스트출력
				
				String page = "";
				
				
				if(list != null) {
					page = "view/marketboard/semi_BuyMarket_BoardList.jsp";
					request.setAttribute("list", list);
					
					PageInfo pi = new PageInfo(currentPage,listCount,limit,maxPage,startPage,endPage);
					request.setAttribute("pi", pi);
				}else {
					page = "view/common/errorPage.jsp";
					request.setAttribute("msg", "게시글 목록 조회 실패!");
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
