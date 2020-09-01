package com.kh.semi.faq.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kh.semi.faq.model.service.FaqService;
import com.kh.semi.faq.model.vo.Faq;

/**
 * Servlet implementation class FaqInsertServlet
 */
@WebServlet("/fInsert.fa")
public class FaqInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fcategory = request.getParameter("category");
		String ftitle = request.getParameter("title");
		String fcontents = request.getParameter("contents");
		System.out.println("서블렛나와와??");
	
	
		// 공지사항 등록을 위한 VO객체 만들기
		Faq f = new Faq();
		
		f.setFcategory(fcategory);
		f.setFtitle(ftitle);
		f.setFcontents(fcontents);
		
		FaqService ns = new FaqService();
		int result = ns.insertFaq(f);
		
		if(result > 0) {
			// 공지사랑 추가 성공
			response.sendRedirect("fList.fa");
		}else {
			//공지사항 추가 실패
			request.setAttribute("msg", "공지사항 들록 실패");
			request.getRequestDispatcher("views/common/errorPAge.jsp").forward(request, response);
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
