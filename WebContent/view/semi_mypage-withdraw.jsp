<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>C4U 너만의 기사</title>
<link rel="stylesheet" href="../resources/css/semi_mypage.css">
<link rel="stylesheet" href="../resources/css/semi_mypage_withdraw.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<%@ include file="mypage/header.jsp"%>
	<%@ include file="mypage/leftmenu.jsp"%>
	<div class="withdraw">
		<h3 style="font-size: 30px; margin: 0 20px 0 0; z-index: 3;">회원
			탈퇴</h3>
		<div class="form-id-pwd">
			<form id="find-id"
				action="${pageContext.request.contextPath}/mDelete.me" method="POST">
				<ul>
					<li><label>ID : </label><input type="text" name="Id"
						maxlength="15" required></li>
					<li><label>PWD : </label><input type="text" name="PWd"
						maxlength="15" required></li>
					<li><label>이름 : </label><input type="text" name="name"
						maxlength="4" required></li>
					<li><label>E-mail : </label><input type="email" name="email"
						maxlength="30" required></li>
				</ul>
				<button type="submit">탈퇴하기</button>
			</form>
		</div>
	</div>
	<%@ include file="mypage/footer.jsp"%>
</body>
</html>