package com.kh.semi.buymarket.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.buymarket.model.service.BuyMarketService;
import com.kh.semi.buymarketcomment.service.BuyMarketCommentService;
import com.kh.semi.market.model.vo.MarketBoard;
import com.kh.semi.marketcomment.model.vo.MarketBoardComment;

/**
 * Servlet implementation class BoardSelectOneServlet
 */
@WebServlet("/buymarketselectOne.bo")
public class BuyMarketSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyMarketSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int bno = Integer.parseInt(request.getParameter("bno"));
			
			
			MarketBoard b = new BuyMarketService().selectOne(bno);
			ArrayList<MarketBoardComment> clist = new BuyMarketCommentService().selectList(bno);
			
			
			String page = "";
			if(b != null) {
				page = "view/marketboard/semi_BuyMarket_BoardDetail.jsp";
				request.setAttribute("Marketboard", b);
				request.setAttribute("clist", clist);
				
			}else {
				page = "view/errorPage.jsp";
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
