package com.kh.semi.comubaord.controller2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.comubaord.model.service.ComuBoardService;
import com.kh.semi.comubaord.model.vo.ComuBoard;

/**
 * Servlet implementation class comuboardUpViewServlet
 */
@WebServlet("/cbUpView.bo")
public class comuboardUpViewServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4558529078575827991L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public comuboardUpViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		ComuBoard b = new ComuBoardService().updateView(bno);
		
		String page = "";
		if(b != null) {
			page = "view/comuboard/comu_baordUpdate.jsp";
			request.setAttribute("board", b);
		}else {
			page = "view/common/errorPage.jsp";
			request.setAttribute("msg", "공지 글 수정 페이지 연결 실패!");
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
