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
import com.kh.semi.marketboardComment.model.service.MarketBoardCommentService;
import com.kh.semi.marketcomment.model.vo.MarketBoardComment;

/**
 * Servlet implementation class BoardSelectOneServlet
 */
@WebServlet("/buymarketselectOne.bo")
public class BuyMarketSelectOneServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3151176556428193838L;

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
		String sBno = request.getParameter("bno");
		String sGubun = request.getParameter("gubun");
		String page = "";
		
		if("".equals(sBno) || sBno == null || "".equals(sBno) || sBno == null) {
			page = "view/errorPage.jsp";
			request.setAttribute("msg", "잘못된 접근입니다!");
			
		} else {
			int bno = Integer.parseInt(sBno);
			int gubun = Integer.parseInt(sGubun);
			

			MarketBoard b = new BuyMarketService().selectOne(bno);
			ArrayList<MarketBoardComment> clist = new MarketBoardCommentService().selectList(bno);
			
			
			
			if(b != null) {
				if(gubun == 0) { // 상세보기
					page = "view/marketboard/semi_BuyMarket_BoardDetail.jsp";
				} else if(gubun == 1) { // 업데이트
					
					page = "view/marketboard/semi_BuyMarket_BoardUpdate.jsp";
				}
				request.setAttribute("Marketboard", b);
				request.setAttribute("clist", clist);
				
			}else {
				page = "view/common/errorPage.jsp";
				request.setAttribute("msg", "게시글 상세보기 실패!");
				
				
			}
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
