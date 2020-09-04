<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.semi.mypage.model.vo.commentCheck"%>
<%@page import="com.kh.semi.mypage.model.vo.PageInfo_massage"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>C4U 너만의 기사</title>
<link rel="stylesheet" href="resources/css/semi_mypage.css">
<link rel="stylesheet" href="resources/css/semi_mypage_Comments.css">
<%
	ArrayList<commentCheck> list = (ArrayList<commentCheck>) request.getAttribute("list");
	PageInfo_massage pi = (PageInfo_massage)request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	System.out.println("\nlistCount : " + listCount
			+"\ncurrentPage : " + currentPage
			+"\nmaxPage : " + maxPage
			+"\nstartPage : "+ startPage
			+"\nendPage : "+endPage
	);
%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/c9096b9061.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="../mypage/header.jsp"%>

	<%@ include file="../mypage/leftmenu.jsp"%>
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
			<%
				for (commentCheck b : list) {
			%>
			<tr id="tdmain">
				<td class="td1" id="<%=b.getBno()%>"><%=b.getBno()%></td>
		 		<td class="td1 td2"><%=b.getTypecontent()  +" - "+ b.getTypename() %></td>
				<td class="td1 td2"><%=b.getBtitle()%></td>
				<td class="td1 td2"><%=b.getBdate()%></td>
				<td class="td1 td2"><%=b.getBcount()%></td>
				<td class="td1 td2"><%=b.getBcommentnum()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<br><br>
		<div class="pagingArea" align="center">
			<button  class="fas fa-angle-double-left" onclick="location.href='<%= request.getContextPath() %>/commentCheck.bo?currentPage=1'"></button>
			<%  if(currentPage <= 1){  %>
			<button disabled><</button>
			<%  }else{ %>
			<button class="fas fa-angle-left" onclick="location.href='<%= request.getContextPath() %>/commentCheck.bo?currentPage=<%=currentPage - 1 %>'"></button>
			<%  } %>
			
			<% for(int p = startPage; p <= endPage; p++){
					if(p == currentPage){	
			%>
				<button disabled><%= p %></button>
			<%      }else{ %>
				<button onclick="location.href='<%= request.getContextPath() %>/commentCheck.bo?currentPage=<%= p %>'"><%= p %></button>
			<%      } %>
			<% } %>
			<%  if(currentPage >= maxPage){  %>
			<button disabled>></button>
			<%  }else{ %>
			<button class="fas fa-angle-right" onclick="location.href='<%= request.getContextPath() %>/commentCheck.bo?currentPage=<%=currentPage + 1 %>'"></button>
			<%  } %>
			<button class="fas fa-angle-double-right" onclick="location.href='<%= request.getContextPath() %>/commentCheck.bo?currentPage=<%= maxPage %>'"></button>
			<br>
	</div>
	<br><br><br>
	<%@ include file="../mypage/footer.jsp"%>
	<script>
 	$(function(){
		$(".comments-table td").mouseenter(function(){
			$(this).parent().css({"background":"darkgray", "cursor":"pointer"});
		}).mouseout(function(){
			$(this).parent().css({"background":"white"});
		}).click(function(){
			/* var cno = $(this).parent().children().eq(0).attr('id'); */
			var bno = $(this).parent().children().eq(0).attr('id');;
			var kategorie = $(this).parent().children().eq(1).text(); 
			
			if(kategorie.indexOf("커뮤니티")!=-1){
				if(kategorie.indexOf("기능사")!=-1){
					location.href="<%=request.getContextPath()%>/GSCselectOne.bo?bno=" + bno;
				}else if(kategorie.indexOf("산업기사")!=-1){
					location.href="<%=request.getContextPath()%>/SGSCselectOne.bo?bno=" + bno;
				}else if(kategorie.indexOf("기사")!=-1){
					location.href="<%=request.getContextPath()%>/CselectOne.bo?bno=" + bno;
				}
			}else if(kategorie.indexOf("장터")!=-1){
				if(kategorie.indexOf("팝니다")!=-1){
					location.href="<%=request.getContextPath()%>/marketboardselectOne.bo?bno=" + bno+"&gubun=0";
				}else if(kategorie.indexOf("삽니다")!=-1){
					location.href="<%=request.getContextPath()%>/buymarketselectOne.bo?bno=" + bno+"&gubun=0";
				}
			}
			<%-- location.href="<%=request.getContextPath()%>/massage_selectOne.no?cno=" + cno; --%>
		});
	});
	</script>
</body>
</html>