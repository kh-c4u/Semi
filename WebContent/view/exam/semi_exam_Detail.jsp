<%@page import="com.kh.semi.examboard.model.vo.ExamBoard"%>
<%@page import="com.kh.semi.comubaord.model.vo.PageInfo"%>
<%@page import="com.kh.semi.comuboardComment.model.vo.comuboardComment"%>
<%@page import="com.kh.semi.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
   Member m = (Member)session.getAttribute("member");
   ExamBoard b = (ExamBoard)request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>C4U 너만의 기사</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/semi_menu_frame.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/semi_posting.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/semi_user.css">
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
    #Ebtn{
        width: 150px;
        height: 50px;
    }
</style>
<title>C4U 너만의 기사</title>
</head>
<body>
	<div id="main-header">
		<div class="main-header-logo">
			<a href="<%= request.getContextPath()%>/semi_main.jsp"><img
				src="<%= request.getContextPath()%>/resources/images/semiLogosize.png" /></a>
		</div>
		<ul id='BeforeLogin' class="main-header-login">
			<%if(m == null) {%>
			<li><a href="view/member/semi_Login.jsp">로그인</a></li>
			<li><span>|</span><a href="view/member/semi_SignupForm.jsp">회원가입</a></li>
			<li><span>|</span><a href="/#">고객센터</a></li>
			<%}else{ %>
			<li><a><%=m.getUserName()%>님</a></li>
			<li><span>|</span><a href="logOut.do">로그아웃</a></li>
			<li><span>|</span><a href="view/mypage/semi_changepersonalinfo.jsp">마이페이지</a></li>
			<%} %>
		</ul>

		<ul class="main-header-navi">
			<li><a href="#">기사</a></li>
			<li><a href="#">산업기사</a></li>
			<li><a href="#">기능사</a></li>
		</ul>

	</div>

	<div id="main-box1"></div>


	<div class="left-menu">
		<ul id="left-menu-share" class="left-menu1">

			<li>
				<dl>
					<dt>
						<div class="left-menu-name">게시판</div>
					</dt>
				</dl>
			</li>
			<div class="menu-line"></div>
			<li>
				<dl>
					<dt>
						<div>커뮤니티</div>
					</dt>
					<dd>
						<a href="<%=request.getContextPath()%>/comuboardlist.bo">- 기사</a>
					</dd>
					<dd>
						<a href="<%=request.getContextPath()%>/SGScomuboardlist.bo">- 산업기사</a>
					</dd>
					<dd>
						<a href="<%=request.getContextPath()%>/GScomuboardlist.bo">- 기능사</a>
					</dd>
				</dl>
			</li>
			<div class="menu-line"></div>
			<li>
				<dl>
					<dt>
						<div>장터</div>
					</dt>
				<dd><a href="<%=request.getContextPath()%>/buymarketList.bo">- 삽니다</a></dd>
              	<dd><a href="<%=request.getContextPath()%>/marketselectList.bo">- 팝니다</a></dd>
				</dl>
			</li>
			<div class="menu-line"></div>
			<li>
				<dl>
					<dt>
						<div>고객센터</div>
					</dt>
					<dd>
						<a href="#">- 자주 묻는 질문</a>
					</dd>
					<dd>
						<a href="#">- 1:1문의</a>
					</dd>
					<dd>
						<a href="#">- 신고</a>
					</dd>
				</dl>
			</li>
		</ul>
	</div>


	<% if(m != null ) { %>
	<div class="sub-right-content">
		<div class="page-title Nanum2"></div>
		<div class="left pb5 mr5">
			<a href="<%= request.getContextPath()%>/ExamBoardlist.ex"
				class="btn3">목록</a>

		</div>

		<div class="table-wrap">
			<table class="tstyle2" style="word-break: break-all">
				<colgroup>
					<col width="6%" />
					<col width="23%" />
					<col width="6%" />
					<col width="13%" />
					<col width="10%" />
					<col width="25%" />
					
				</colgroup>
				<tbody>
					<tr>
						<th class="tcenter vm">제목</th>

						<td class="pl20"
							style="border-right: 1.3px solid grey;"><%= b.getBtitle() %>
						</td>
                        <th class="tcenter vm">작성일</th>
						<td class="pl20 r-noline"><%= b.getBdate() %></td>
						
						<th class="tcenter vm">첨부파일</th>
						<td class="pl20">
						<% if(b.getBoardfile() != null && b.getBoardfile().length() > 0) { %>
						<a
							href="<%= request.getContextPath()%>/resources/examFiles/<%=b.getBoardfile() %>"
							download="<%= b.getBoardfile() %>"> <%=b.getBoardfile() %>
						</a>
						<% } %>
                        </td>
                    </tr>
                            <tr >
                                <td colspan="6" rowspan="5" style="text-align: center; border-left: 1px solid  #bbbbbb; ">
                                    <br><br><br><br><br>
                                    ↓ 바로 문제 풀러 가기 ↓<br><br><br>

                                    <button onclick="location.href='<%=request.getContextPath()%>/QuestionList.qo'"
                                     id="Ebtn">문제 풀기</button>
                                    <br><br><br><br>
                                </td>
                            </tr>
				</tbody>
			</table>

		</div>
       
	</div>


	<% } else {
      request.setAttribute("msg", "회원만 가능한 서비스 입니다.");
      request.getRequestDispatcher("/view/common/errorPage.jsp").forward(request, response);
   }
   %>


	<div id="main-footer">
		<div class="main-footer-wrap">
			<div>
				<img
					src="<%= request.getContextPath()%>/resources/images/semiLogosize.png" />
			</div>
			<div>
				<ul>
					<li>고객센터 1544 – 1004 (평일 9:00 ~ 12:30, 13:30 ~ 18:00)
						help@c4you.com</li>
					<li><span>㈜합격하조</span> <span>대표이사 유승제</span> <span>사업자
							등록번호 1004-1004</span></li>
					<li>서울시 강남구 역삼대로</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>