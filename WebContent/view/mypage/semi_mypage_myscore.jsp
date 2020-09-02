<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>C4U 너만의 기사</title>
<link rel="stylesheet" href="../../resources/css/semi_mypage.css">
<link rel="stylesheet" href="../../resources/css/semi_mypage_myscore.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<%@ include file="../mypage/header.jsp"%>
	<%@ include file="../mypage/leftmenu.jsp"%>


	<div>
		<h1 class="Nanum2" id="score-title">나의 점수보기</h1>

		<table class="score-table Nanum3">
			<thead>
				<tr>
					<th scope="cols">No.</th>
					<th scope="cols">Title.</th>
					<th scope="cols">Re-Examination.</th>
					<th scope="cols">Score.</th>

				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">1</th>
					<td>기능사(2020-07-17)</td>
					<td><a href="#">문제보기</a>/<a href="#">오답풀기</a></td>
					<td>95</td>

				</tr>
				<tr>
					<th scope="row">2</th>
					<td>정보처리기사(2020-08-20)</td>
					<td><a href="#">문제보기</a>/<a href="#">오답풀기</a></td>
					<td>80</td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td>기능사(2020-09-17)</td>
					<td><a href="#">문제보기</a>/<a href="#">오답풀기</a></td>
					<td>100</td>
				</tr>
			</tbody>
		</table>






	</div>





	<%@ include file="../mypage/footer.jsp"%>
</body>
</html>