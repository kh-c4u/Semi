<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>C4U 너만의 기사</title>
<link rel="stylesheet" href="../resources/css/semi_mypage.css">
<link rel="stylesheet" href="../resources/css/semi_Comments.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/c9096b9061.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="mypage/header.jsp"%>

	<%@ include file="mypage/leftmenu.jsp"%>
	<div>
		<p id="select-title">내 글 댓글 알림 화면</p>
		<table class="comments-table">
			<tr>
				<th>No.</th>
				<th>카테고리</th>
				<th>제목</th>
				<th>작성일</th>
				<th>조회</th>
				<th>댓글 수</th>
			</tr>
			<tr>
				<td>1</td>
				<td>공부팁</td>
				<td>가입했음 등업점해줘여</td>
				<td>2020.08.12</td>
				<td>4</td>
				<td>1</td>
			</tr>
			<tr>
				<td>2</td>
				<td>공부팁</td>
				<td>공부 하기 싫을 때</td>
				<td>2020.08.12</td>
				<td>4</td>
				<td>1</td>
			</tr>
			<tr>
				<td>3</td>
				<td>공부팁</td>
				<td>팀프로젝트 팁</td>
				<td>2020.08.12</td>
				<td>4</td>
				<td>1</td>
			</tr>
			<tr>
				<td>4</td>
				<td>공부팁</td>
				<td>책구매시 팁</td>
				<td>2020.08.12</td>
				<td>4</td>
				<td>1</td>
			</tr>
			<tr>
				<td>5</td>
				<td>공부팁</td>
				<td>전기기능사 필기 팁</td>
				<td>2020.08.12</td>
				<td>4</td>
				<td>1</td>
			</tr>
			<tr>
				<td>6</td>
				<td>공부팁</td>
				<td>나 전기기술사다 질문받는다</td>
				<td>2020.08.12</td>
				<td>4</td>
				<td>1</td>
			</tr>
		</table>
		<div class="paging">
			<a href="#" class="fas fastext fa-angle-double-left"></a> <a href="#"
				class="fas fastext fa-angle-left"></a>
			<!-- <a href="#">이전</a> -->
			<a href="#">1</a> <a href="#">2</a> <a href="#">3</a> <a href="#">4</a>
			<a href="#">5</a>
			<!-- <a href="#">다음</a> -->
			<a href="#" class="fas fastext fa-angle-right"></a> <a href="#"
				class="fas fastext fa-angle-double-right"></a>
		</div>
	</div>
	<%@ include file="mypage/footer.jsp"%>
</body>
</html>