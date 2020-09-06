<%@page import="com.kh.semi.mypage.model.vo.MyScore"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>C4U 너만의 기사</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/semi_mypage.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/semi_mypage_myscore.css">
<%
	ArrayList<MyScore> scorelist = (ArrayList<MyScore>)request.getAttribute("scoreList");
%>

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
					<th scope="cols">Score.</th>

				</tr>
			</thead>
			<tbody>
				<%int i = 1;
				for(MyScore ms : scorelist){
				%>
					<tr>
					<th scope="row"><%=i%></th>
					<td><%=ms.getTc_name() %></td>
					<td><%=ms.getScore() %></td>
					</tr>
				<% 
				i++;}
				%>
				
				
				
			</tbody>
		</table>






	</div>





	<%@ include file="../mypage/footer.jsp"%>
</body>
</html>