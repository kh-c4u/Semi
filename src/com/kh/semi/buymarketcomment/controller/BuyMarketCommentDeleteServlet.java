package com.kh.semi.buymarketcomment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.buymarketcomment.service.BuyMarketCommentService;
import com.kh.semi.marketcomment.model.vo.MarketBoardComment;

/**
 * Servlet implementation class BuyMarketCommentDeleteServlet
 */
@WebServlet("/bmcdelete.bo")
public class BuyMarketCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyMarketCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cno = Integer.parseInt(request.getParameter("cno"));
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		MarketBoardComment bc = new MarketBoardComment();
		bc.setBno(bno);
		bc.setCno(cno);
	
		
		BuyMarketCommentService mcs = new BuyMarketCommentService();
		
		int result = mcs.deleteComment(bc);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/buymarketselectOne.bo?bno=" + bno +"&gubun=0");		
		}else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			
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