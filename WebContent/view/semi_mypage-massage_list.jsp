<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>C4U 너만의 기사</title>
<title>C4U 너만의 기사</title>
<link rel="stylesheet" href="../resources/css/semi_mypage.css" />
<link rel="stylesheet" href="../resources/css/semi_mypage_list.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/c9096b9061.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="mypage/header.jsp"%>

	<%@ include file="mypage/leftmenu.jsp"%>
	<div class="testbox">
		<div class="tablebox">
			<table class="table" border="0"
				style="text-align: center; padding: 0; margin: 0; min-width: 100%;">
				<div class="title-write">
					<h1>받은 쪽지</h1>
				</div>

				<thead>
					<tr style="text-align: center;">
						<th><input type="checkbox" name="" id="" /></th>
						<th>보낸사람</th>
						<th>내용</th>
						<th>날짜</th>
						<th>차단</th>
					</tr>
				</thead>

				<tbody class="postsbody">
					<tr>
						<td style="width: 5%;"><input type="checkbox" name="" id="" />
						</td>
						<td style="width: 15%;">옆집형님</td>
						<td
							style="width: 60%; text-align: left; text-indent: 10px; font-family: sans-serif;">
							<a href="semi_mypage_massage view.html">안녕하세요,
								혹시 문제집 어디서 구하셨는지 알 수 있을까요?</a>
						</td>

						<td style="width: 15%;">2020.08.06</td>
						<td style="width: 5%;"><a href="#">차단</a></td>
					</tr>
				</tbody>
			</table>
			<div class="paging">
				<a href="#" class="fas fa-angle-double-left"></a> <a href="#"
					class="fas fa-angle-left"></a>
				<!-- <a href="#">이전</a> -->
				<a href="#">1</a> <a href="#">2</a> <a href="#">3</a> <a href="#">4</a>
				<a href="#">5</a>
				<!-- <a href="#">다음</a> -->
				<a href="#" class="fas fa-angle-right"></a> <a href="#"
					class="fas fa-angle-double-right"></a>
			</div>
			<div class="searchbox-qna">
				<select name="" id="search-qna-sel">
					<option value="닉네임">닉네임</option>
					<option value="내용">내용</option>
				</select> <input type="text" class="search-qna-bar" /> <input type="submit"
					value="검색" class="search-qna-btn" />
			</div>
			<div class="btns">
				<a href="#" class="btn deleteBtn">삭제</a> <a href="#"
					class="btn reportBtn">신고</a> <a href="#" class="btn saveBtn">보관</a>
				<a href="semi_mypage_massage send.html" class="btn replyBtn">답장</a>
			</div>
		</div>
	</div>
	<%@ include file="mypage/footer.jsp"%>
</body>
</html>