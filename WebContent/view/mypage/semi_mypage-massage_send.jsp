<%@page import="javafx.scene.control.Alert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>C4U 너만의 기사</title>
<link rel="stylesheet" href="../../resources/css/semi_mypage.css" />
<link rel="stylesheet"
	href="../../resources/css/semi_mypage_message_send.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/c9096b9061.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="../mypage/header.jsp"%>
	<%@ include file="../mypage/leftmenu.jsp"%>
	<div class="section">
		<div class="title-write Nanum1">쪽지보내기</div>
		<form action="${pageContext.request.contextPath}/semi_mypage-massage_send.do"method="POST">
			<input name="id"id="id" class="id Nanum1" type="text" maxlength="20" placeholder="ID를 입력해주세요" value="<%=request.getParameter("cwriter")==null?"":request.getParameter("cwriter")%>" required>
			<br> <br>
			<input name="titlename"id="titlename" class="titlename Nanum1" type="text"maxlength="20" placeholder="제목을 입력해주세요" required> <br> <br>
			<textarea name="writeNote" id="writeNote" class="writeNote Nanum1" style="resize: none; width: 500px;" rows="6"
				cols="2" placeholder="쪽지 내용을 입력해 주세요." required></textarea> <br>
			
			
			<input class="btn" type="submit" value="보내기">
			<input class="btn" type="reset" value="취소">
		</form>
	</div>


	<%@ include file="../mypage/footer.jsp"%>
</body>
</html>