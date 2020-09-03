package com.kh.semi.buymarket.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.buymarket.model.service.BuyMarketService;
import com.kh.semi.buymarketcomment.service.BuyMarketCommentService;
import com.kh.semi.marketboard.model.vo.MarketBoard;
import com.kh.semi.marketcomment.model.vo.MarketBoardComment;
import com.kh.semi.member.vo.Member;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sBno = request.getParameter("bno");
		String sGubun = request.getParameter("gubun");
		String page = "";
		System.out.println(" detail");
		if ("".equals(sBno) || sBno == null || "".equals(sBno) || sBno == null) {
			page = "view/errorPage.jsp";
			request.setAttribute("msg", "잘못된 접근입니다!");

		} else {
			int bno = Integer.parseInt(sBno);
			int gubun = Integer.parseInt(sGubun);

			MarketBoard b = new BuyMarketService().selectOne(bno);
			ArrayList<MarketBoardComment> clist = new BuyMarketCommentService().selectList(bno);
			HttpSession session = request.getSession(false);
			Member user = (Member) session.getAttribute("member");

			if (b != null && user != null) {
				page = "view/marketboard/semi_BuyMarket_BoardDetail.jsp";
				request.setAttribute("board", b);
				request.setAttribute("clist", clist);

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

				out.println("alert('로그인이 필요합니다.')");

				out.println("history.back();");

				out.println("</script>");

				clist.forEach(item -> {
					System.out.println("item : " + item);
				});

				if (b != null) {
					if (gubun == 0) { // 상세보기
						page = "view/marketboard/semi_BuyMarket_BoardDetail.jsp";
					} else if (gubun == 1) { // 업데이트

						page = "view/marketboard/semi_BuyMarket_BoardUpdate.jsp";
					}
					request.setAttribute("Marketboard", b);
					request.setAttribute("clist", clist);

				} else {
					page = "view/common/errorPage.jsp";
					request.setAttribute("msg", "게시글 상세보기 실패!");

				}
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
