<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.semi.mypage.model.vo.PageInfo_massage"%>
<%@page import="com.kh.semi.mypage.model.vo.massage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	ArrayList<massage> list = (ArrayList<massage>)request.getAttribute("list"); 
	PageInfo_massage pi = (PageInfo_massage)request.getAttribute("pi");
		int listCount = pi.getListCount();
		int currentPage = pi.getCurrentPage();
		int maxPage = pi.getMaxPage();
		int startPage = pi.getStartPage();
		int endPage = pi.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>C4U 너만의 기사</title>
<title>C4U 너만의 기사</title>
<link rel="stylesheet" href="resources/css/semi_mypage.css" />
<link rel="stylesheet" href="resources/css/semi_mypage_list.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/c9096b9061.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="../mypage/header.jsp"%>

	<%@ include file="../mypage/leftmenu.jsp"%>
	<div class="testbox">
		<div class="tablebox">
			<table class="table" border="0" id="listArea"
				style="text-align: center; padding: 0; margin: 0; min-width: 100%;">
				<div>
					<h1>받은 쪽지</h1>
				</div>
	<!-- 			.title-write {
    width: 100%;
  
    text-align: left;
  } -->

				<thead>
					<tr style="text-align: center; display: block;">
						<th>읽음여부</th>
						<th>보낸사람</th>
						<th>제목</th>
						<th>내용</th>
						<th>날짜</th>
					</tr>
				</thead>

				<tbody style="background: white;">
					<% for(massage b : list){%>
					<tr>
						<td class="td1" id="<%= b.getCno() %>">
						<%= b.getCchecked() == 1 ?"읽음":"안읽음"%></td>
						<td class="td1 td2"><%= b.getCtowriter() %></td>
						<td class="td1 td2"><%= b.getCtitle() %></td>
						<td class="td1 td2"><%= b.getCcontent() %></td>
						<td class="td1 td2"><%= b.getCdate() %></td>
					</tr>
					<% } %>
				</tbody>
			</table>
		

			<%-- 페이지 처리 --%>
			<div class="pagingArea" align="center">
			<button  class="fas fa-angle-double-left" onclick="location.href='<%= request.getContextPath() %>/massageList.bo?currentPage=1'"></button>
			<%  if(currentPage <= 1){  %>
			<button disabled><</button>
			<%  }else{ %>
			<button class="fas fa-angle-left" onclick="location.href='<%= request.getContextPath() %>/massageList.bo?currentPage=<%=currentPage - 1 %>'"></button>
			<%  } %>
			
			<% for(int p = startPage; p <= endPage; p++){
					if(p == currentPage){	
			%>
				<button disabled><%= p %></button>
			<%      }else{ %>
				<button onclick="location.href='<%= request.getContextPath() %>/massageList.bo?currentPage=<%= p %>'"><%= p %></button>
			<%      } %>
			<% } %>
				
			<%  if(currentPage >= maxPage){  %>
			<button disabled>></button>
			<%  }else{ %>
			<button class="fas fa-angle-right" onclick="location.href='<%= request.getContextPath() %>/massageList.bo?currentPage=<%=currentPage + 1 %>'"></button>
			<%  } %>
			<button class="fas fa-angle-double-right" onclick="location.href='<%= request.getContextPath() %>/massageList.bo?currentPage=<%= maxPage %>'"></button>
		</div>		
			
		
			
			
			
			
			<div class="searchbox-qna">
				<select name="" class="searchCondition" id="searchCondition">
					<option value="ctowriter">보낸사람</option>
					<option value="ctitle">제목</option>
					<option value="ccontent">내용</option>
				</select>
				<input type="search" class="search-qna-bar" id="keyword" placeholder="키워드를 입력하세요!"/>
				<input type="button"value="검색" class="search-qna-btn" onclick="search();"/>
			</div>
			<!-- <div class="btns">
				<a href="#" class="btn deleteBtn">삭제</a> <a href="#"
					class="btn reportBtn">신고</a> <a href="#" class="btn saveBtn">보관</a>
				<a href="semi_mypage_massage send.html" class="btn replyBtn">답장</a>
			</div> -->
		</div>
	</div>
	<script>
	$(function(){
		$("#listArea td").mouseenter(function(){
			$(this).parent().css({"background":"darkgray", "cursor":"pointer"});
		}).mouseout(function(){
			$(this).parent().css({"background":"white"});
		}).click(function(){
			var cno = $(this).parent().children().eq(0).attr('id');
			location.href="<%=request.getContextPath()%>/massage_selectOne.no?cno=" + cno;
		});
	});
	function search(){
		location.href="<%=request.getContextPath()%>/mypagesearchNotice.no?con="+$('#searchCondition').val()+"&keyword="+$('#keyword').val();
	}
</script>
	<%@ include file="../mypage/footer.jsp"%>
</body>
</html>