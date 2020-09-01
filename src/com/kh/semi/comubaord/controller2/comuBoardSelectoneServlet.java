package com.kh.semi.comubaord.controller2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.comubaord.model.service2.ComuBoardServiceGS;
import com.kh.semi.comubaord.model.vo.ComuBoard;
import com.kh.semi.comuboardComment.model.vo.comuboardComment;
import com.kh.semi.comuboardComment.service2.BoardCommentServiceGS;

/**
 * Servlet implementation class comuBoardSelectoneServlet
 */
@WebServlet("/GSCselectOne.bo")
public class comuBoardSelectoneServlet extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1615579536934385288L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public comuBoardSelectoneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		ComuBoard b = new ComuBoardServiceGS().selectOne(bno);
		ArrayList<comuboardComment> clist = new BoardCommentServiceGS().selectList(bno);
		
		String page = "";
		if(b != null) {
			page = "view/comuboard/comu_boardDetail_GS.jsp";
			request.setAttribute("board", b);
			request.setAttribute("clist", clist);
		}else {
			page = "view/common/errorPage.jsp";
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
