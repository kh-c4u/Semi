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
import com.kh.semi.comubaord.model.vo.PageInfo;

/**
 * Servlet implementation class comuboardlistServlet
 */
@WebServlet("/comuboardlist.bo")
public class comuboardlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public comuboardlistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				ArrayList<ComuBoard> list =null;
				ComuBoardService cbs = new ComuBoardService();
				
	
				int startPage;
				int endPage;
				int maxPage;
				int currentPage;
				int limit;
				
				currentPage = 1;
				
				limit = 10;
				
				if(request.getParameter("currentPage") != null) {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				
				int listCount = cbs.getListCount();
				System.out.println("총 페이지 개수 : " + listCount);
				
				if(listCount <=10) {
			         maxPage = 1;
			      }else {
			         maxPage = (int) ((double) listCount / limit + 0.9);
			      }
				
				startPage 
					=    ((int)((double)currentPage/limit+0.9)-1)*limit +1;
		               
				endPage = startPage + limit -1;
				
				// 만약 마지막 페이지보다 현재 게시글이 끝나는 페이지가 적다면
				if(endPage > maxPage) {
					endPage = maxPage;
				}
				
				list = cbs.selectList(currentPage,limit); // 현재페이지와 목록의수 --> 리스트출력
				
				String page = "";
				
				if(list != null) {
					page = "view/comuboard/comu_boardlist.jsp";
					request.setAttribute("list", list);
					
					PageInfo pi = new PageInfo(currentPage,listCount,limit,maxPage,startPage,endPage);
					request.setAttribute("pi", pi);
				}else {
					page = "view/common/errorPage.jsp";
					request.setAttribute("msg", "게시글 목록 조회 실패!");
				}
				request.getRequestDispatcher(page).forward(request, response);
			}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
