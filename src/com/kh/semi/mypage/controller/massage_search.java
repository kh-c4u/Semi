package com.kh.semi.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.mypage.model.service.MypageService;
import com.kh.semi.mypage.model.vo.PageInfo_massage;
import com.kh.semi.mypage.model.vo.massage;

/**
 * Servlet implementation class massage_search
 */
@WebServlet("/mypagesearchNotice.no")
public class massage_search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public massage_search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<massage> list =null;
		MypageService ms = new MypageService();
		// 페이징 처리에 필요한 변수들
				// 한번에 표시할 페이지들 중 가장 앞의 페이지
				// 1, 2, 3, 4, 5, --> 1 // 6, 7, 8, 9, 10 --> 6
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
				limit = 5;
				// 만약에 사용자가 현재 페이지의 정보를 들고왔다면
				// 현재 페이지의 정보를 1에서 특정 페이지로 수정해주어야 한다.
				
				if(request.getParameter("currentPage") != null) {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}

				// 페이징 처리
				int listCount = ms.getListCount();

				// 총 253개
				maxPage = (int)((double)listCount / limit +0.9);
				//                         253.0 / 10+0.9 --> 26
				startPage 
				=    ((int)((double)currentPage/limit+0.9)-1)*limit +1;
				//    1/10+0.9 ->1 -1 * 10 + 1  
				//    7/ 0.7+0.9->1.6 -1 ->0.6 -> 0 +1 
				// 11/   1.1+0.9 -> 2 -1 -> 1 * 10 +1 ->11
				// 19/   1.9+0.9-> 2.8-1->1.8 -> 1 * 10 +1->11
				endPage = startPage + limit -1;

				// 만약 마지막 페이지보다 현재 게시글이 끝나는 페이지가 적다면
				if(endPage > maxPage) {
					endPage = maxPage;
				}
				
		// 검색 카테고리
				String category = request.getParameter("con");
				
				// 검색 키워드
				String keyword = request.getParameter("keyword");
				System.out.println("keyword : " + keyword);
				System.out.println("category : " + category);
				list = new ArrayList<massage>();
				
				list = new MypageService().searchMassage(category,keyword,currentPage,limit);
				System.out.println("list : " + list);
				System.out.println("list size = " + list.size()/5);
				if(list.size()<5) {
					System.out.println("!!!");
					limit = list.size()/5;
					endPage = list.size()/5;
				}
				String page = "";
				if(list != null) {
					System.out.println("massage_search성공");
					page = "view/mypage/semi_mypage-massage_list.jsp";
					request.setAttribute("list", list);
					PageInfo_massage pi = new PageInfo_massage(currentPage,listCount,limit,maxPage,startPage,endPage);
					request.setAttribute("pi", pi);
				}else {
					System.out.println("massage_search에러");
					page = "view/common/errorPage.jsp";
					request.setAttribute("msg", "공지사항 검색 실패!");
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
