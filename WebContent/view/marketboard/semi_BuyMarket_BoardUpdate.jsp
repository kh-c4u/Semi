<%@page import="com.kh.semi.member.vo.Member"%>
<%@page import="com.kh.semi.marketboard.model.vo.MarketBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%  Member m = (Member)session.getAttribute("member"); 
     MarketBoard b = (MarketBoard)request.getAttribute("board");%> 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>C4U 너만의 기사</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/semi_menu_frame.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/semi_comu_write.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<div id="main-header">
		<div class="main-header-logo">
			<a href="<%= request.getContextPath()%>/semi_main.jsp"> <img
				src="<%=request.getContextPath()%>/resources/images/semiLogosize.png" /></a>
		</div>
		 <ul id='BeforeLogin' class="main-header-login">
         <%if(m == null) {%>
         <li><a href="view/member/semi_Login.jsp">로그인</a></li>
         <li><span>|</span><a href="view/member/semi_SignupForm.jsp">회원가입</a></li>
         <li><span>|</span><a href="<%= request.getContextPath()%>/fList.fa">고객센터</a></li>
         <%}else{ %>
         <li><a><%=m.getUserName()%>님</a></li>
         <li><span>|</span><a href="logOut.do">로그아웃</a></li>
         <li><span>|</span><a href="view/semi_mypage-withdraw.jsp">마이페이지</a></li>
         <%} %>
      </ul>

		<ul class="main-header-navi">
			<li><a href="<%= request.getContextPath()%>/ExamBoardlist.ex">기사</a></li>
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
				<dd><a href="<%=request.getContextPath()%>/comuboardlist.bo">- 기사</a></dd>
                <dd><a href="<%=request.getContextPath()%>/SGScomuboardlist.bo">- 산업기사</a></dd>
                <dd><a href="<%=request.getContextPath()%>/GScomuboardlist.bo">- 기능사</a></dd>
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
                  <a href="<%= request.getContextPath()%>/fList.fa">- 자주 묻는 질문</a>
               </dd>
               <dd>
                  <a href="<%=request.getContextPath()%>/QnaBoardServlet.do">- 1:1문의</a>
               </dd>
               <dd>
                  <a href="<%=request.getContextPath()%>/errorList.bo">- 신고</a>
               </dd>
				</dl>
			</li>
		</ul>
	</div>

	<div class="outer">
		<br>
		<h2 align="center">게시글 수정 페이지</h2>
		<div class="tableArea">
			<form id="updateForm" method="post" enctype="multipart/form-data">
				<table align="center">
					<tr>
						<td>제목 </td>
						<td colspan="3">
							<input type="text" size="65" name="title" value="<%=b.getBtitle() %>">
							<input type="hidden" name="bno" value="<%=b.getBno()%>">
						</td>
					</tr>
				  		<tr>
						<td>작성자 </td>
						<td colspan="3"><%= b.getBwriter() %></td>
					</tr>
				
					<tr>
						<td>새 파일 </td>
						<td colspan="3">
							<input type="file" name="filename"
							accept="image/png,image/jpg,image/bmp"/>
						</td>
					</tr>
					<tr>
						<td>내용 </td>
						<td colspan="3">
							<textarea name="content" cols="67" rows="15" style="resize:none;"><%=b.getBcontent() %></textarea>
						</td>
					</tr> 
									</table>
				<br>
				<div align="center">
					<button onclick="complete();">수정완료</button>
					<button onclick="deleteBoard();">삭제하기</button>
				</div>
				<script>
					function complete(){
						$("#updateForm").attr("action","<%= request.getContextPath()%>/bUpdate.bo");
					}
					
					function deleteBoard(){
						$("#updateForm").attr("action","<%= request.getContextPath()%>/bdelete.bo");
					}
				
				</script>
			</form>
	</div>
	</div>
	
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