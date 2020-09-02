<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.semi.mypage.model.vo.*"%>
<%
	massage n = (massage) request.getAttribute("massage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>C4U 너만의 기사</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/semi_mypage.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/semi_mypage_myscore.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="./css/semi_menu_qna.css" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/c9096b9061.js"
	crossorigin="anonymous"></script>
<style>
.testbox {
	width: 100%;
	min-height: 500px;
	/* background-color: blue; */
	float: right;
	clear: left;
	margin: 20px;
}

#con1 {
	/* font-family: 'Segoe UI',Arial,'Malgun Gothic','Gulim','sans-serif'; */
	/* background-color: #f0f0f0; */
	float: right;
	margin-right: 113px;
	margin-bottom: 50px;
	width: 1000px;
	min-height: 545px;
	padding: 20px;
}

a {
	color: black;
}

table a:hover {
	text-decoration: underline;
}

table {
	border-collapse: collapse;
	background-color: skyblue;
}

table, th, td {
	border-bottom: 1px solid black;
	padding: 10px;
}

th {
	font-size: 16px;
}

.fas {
	font-size: medium;
	vertical-align: middle;
}

.noticebody {
	background: hsl(240, 12%, 94%);
}

.postsbody {
	background: white;
}

.notice-qnabox {
	border: 1px solid rgb(255, 162, 177);
	padding: 3px 4px;
	border-radius: 4px;
	background-color: pink;
	color: red;
}

.notice-qna {
	color: red;
}

.title-write {
	width: 100%;
	text-align: left;
}

.tablebox {
	width: 100%;
	display: flex;
	flex-direction: column;
	align-items: left;
}

.paging {
	/* text-align: center; */
	padding: 10px;
}

.paging>a {
	padding: 5px;
}

.btns {
	display: flex;
	margin-top: 50px;
	text-align: center;
	margin-left: auto;
}

.btn {
	display: flex;
	margin-left: 5px;
}

.deleteBtn, .replyBtn, .reportBtn, .saveBtn {
	/* background: yellowgreen; */
	/* background: #ffe500; */
	/* background-color: powderblue; */
	/* background-color: lightblue; */
	background-color: blanchedalmond;
	padding: 10px 20px;
	border-radius: 2px;
	font-size: 20px;
	font-family: sans-serif;
	font-weight: 500;
}

.btns .fas {
	vertical-align: auto;
	font-size: 18px;
}

.search-qna {
	margin-top: 10px;
}

#search-qna-sel {
	vertical-align: middle;
}

.search-qna-btn {
	vertical-align: middle;
}

.search-qna-bar {
	vertical-align: middle;
	width: 200px;
}
</style>
</head>
<body>

	<%@ include file="../mypage/header.jsp"%>

	<%@ include file="../mypage/leftmenu.jsp"%>
	<section id="con1">
		<div class="testbox">
			<div class="tablebox">
				<div class="title-write">
					<h1>받은 쪽지</h1>
				</div>

				<div id="cwriter" class="cwriter" style="border-bottom: 1px solid gray; margin: 10px 10px;">
						보낸 사람: <%= n.getCwriter() %></div>
					<div
						style="border-bottom: 1px solid gray; min-height: 100px; margin: 10px 10px; font-family: sans-serif;">
						<%= n.getCcontent() %></div>
				</div>

				<div class="btns">
					<a href="<%=request.getContextPath()%>/massageDelet.bo?cno= <%= n.getCno() %>" class="btn deleteBtn">삭제</a>
					<!-- <a href="#"class="btn reportBtn">신고</a> <a href="#" class="btn saveBtn">보관</a> -->
					<a href="<%=request.getContextPath()%>/view/mypage/semi_mypage-massage_send.jsp?cwriter=<%=n.getCwriter()%>" class="btn replyBtn">답장</a>
					<a href="<%=request.getContextPath()%>/massageList.bo" class="btn replyBtn">목록</a>
				</div>
			</div>
		</div>
	</section>




	<%@ include file="../mypage/footer.jsp"%>

</body>
</html>