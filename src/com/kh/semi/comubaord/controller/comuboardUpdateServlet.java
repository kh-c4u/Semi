package com.kh.semi.comubaord.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.comubaord.model.service.ComuBoardService;
import com.kh.semi.comubaord.model.vo.ComuBoard;

/**
 * Servlet implementation class comuboardUpdateServlet
 */
@WebServlet("/comuboardUpdate.bo")
public class comuboardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public comuboardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
	    String content= request.getParameter("content");
	    int bno = Integer.parseInt(request.getParameter("bno"));
	
	    ComuBoard b = new ComuBoard();
	    b.setBno(bno);
	    b.setBcontent(content);
	    b.setBtitle(title);
	    
	    int result = new ComuBoardService().updateComuboard(b);
	    
	    if(result > 0) {
	    	// 수정 성공
	    	response.sendRedirect("CselectOne.bo?nno="+bno);
	    }else {
	    	// 수정 실패
	    	request.setAttribute("msg", "공지사항 수정 실패!");
	    	request.getRequestDispatcher("view/common/errorPage.jsp").forward(request, response);
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
