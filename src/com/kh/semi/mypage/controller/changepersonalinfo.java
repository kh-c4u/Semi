package com.kh.semi.mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.member.vo.Member;
import com.kh.semi.mypage.model.service.MypageService;

/**
 * Servlet implementation class changepersonalinfo
 */
@WebServlet("/changeup.do")
public class changepersonalinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changepersonalinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 정보 수정용 데이터 꺼내오기
		        String id = request.getParameter("id");
				String password= request.getParameter("password");
				String email = request.getParameter("email");
				String address = request.getParameter("address")+" "
						       + request.getParameter("address2");
				
				
				// 해당 회원을 구분짓는 ID받아오기
				HttpSession session = request.getSession(false);
				Member m = (Member)session.getAttribute("member");
				
				// 기존의 회원정보를 새로운 값으로 변경하기
				m.setUserPwd(password);
				m.setEmail(email);
				m.setAddress(address);
				
				System.out.println(id);
				System.out.println(password);
				System.out.println(email);
				System.out.println(address);
				
				
				
				System.out.println("변경한 회원 정보 확인 : " + m);
				
				MypageService ms = new MypageService();
				
				try {
					if(address.equals(" ")) {
						System.out.println("어드레스 없음");
						int result = ms.updateMember1(m);
					}else {
						System.out.println("어드레스 있음");
						int result = ms.updateMember2(m);
					}
					System.out.println("회원 정보 수정 완료!");
					session.setAttribute("member", m);
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('회원 정보 수정 완료!'); location.href='view/mypage/semi_changepersonalinfo.jsp'; </script>");
					writer.close();
				}catch(IOException e) {
					request.setAttribute("msg", "회원 정보 수정 중 에러 발생");
					request.setAttribute("exception", e);
					
					request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
