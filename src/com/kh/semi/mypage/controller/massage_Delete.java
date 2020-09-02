package com.kh.semi.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.vo.Member;
import com.kh.semi.mypage.model.service.MypageService;
import com.kh.semi.mypage.model.vo.massage;

/**
 * Servlet implementation class massage_Delete
 */
@WebServlet("/massageDelet.bo")
public class massage_Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public massage_Delete() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cno = request.getParameter("cno");
		MypageService ms = new MypageService();
		try {		
			int result = ms.deleteMassage(cno);
			System.out.println("메세지 삭제 성공!");
			response.sendRedirect(request.getContextPath() + "/massageList.bo");
		}catch(IOException e) {
			request.setAttribute("msg", "메세지 삭제 수행 중 에러 발생");
			request.setAttribute("exception", e);

			System.out.println("MemberDeleteServlet에서 에러발생!!!!");

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