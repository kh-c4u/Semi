<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.semi.member.vo.Member"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div id="main-header">
		<div class="main-header-logo">
			<a href="../semi_main.jsp"><img src="../resources/images/semiLogosize.png" /></a>
		</div>
		<%Member m = (Member)session.getAttribute("member"); %>
		<ul id='BeforeLogin' class="main-header-login">
            <%if(m == null) {%>
                <li><a href="view/semi_Login.jsp">로그인</a></li>
                <li><span>|</span><a href="semi_SignupForm.jsp">회원가입</a></li>
                <li><span>|</span><a href="/#">고객센터</a></li>
            <%}else{ %>
            	<li><a><%=m.getUserName()%>님</a></li>
            	<li><span>|</span><a href="../logOut.do">로그아웃</a></li>
                <li><span>|</span><a href="semi_mypage.jsp">마이페이지</a></li>
            <%} %>
			</ul>

		<ul class="main-header-navi">
			<li><a href="#">기사</a></li>
			<li><a href="#">산업기사</a></li>
			<li><a href="#">기능사</a></li>
		</ul>
	</div>
	<div id="main-box1"></div>
</body>
</html>