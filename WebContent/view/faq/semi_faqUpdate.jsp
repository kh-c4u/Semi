<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.semi.faq.model.vo.*"%>
<% Faq f = (Faq)request.getAttribute("faq"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>공지 사항 수정</title>
</head>
<body>
	<%@ include file="/view/mypage/header.jsp"%>
	<%-- if(m != null && m.getUserId().equals("admin")){ --%>
	<div class="outer">
		<br>
		<h2 align="center">공지 사항 수정</h2>
		<div class="tableArea">
			<form id="updateForm" method="post">
				<table>
					<tr>
						<td>제목 </td>
						<td colspan="3">
							<input type="text" size="50" name="title" 
							       value="<%= f.getFtitle().replace("\"", "&#34;") %>">
							<input type="button" name="nno" value="<%= f.getFno() %>">
						</td>
					</tr>
					
					<tr>
						<td>내용 </td>
					</tr>
					<tr>
						<td colspan="4">
							<textarea name="content" cols="60" rows="15" style="resize:none;"><%= f.getFcontents() %></textarea>
						</td>
					</tr>
				</table>
				<br>
				<div align="center">
					<button onclick="complete();">작성완료</button>
					<button onclick="deleteFaq();">삭제하기</button>
				</div>
				<script>
					function complete(){
						$("#updateForm").attr("action","<%=request.getContextPath() %>/fUpdate.fa");
						
					}
					
					function deleteFaq(){
						// delete 는 예약어 이므로 deleteNotice 로 ...!
						$("#updateForm").attr("action","<%=request.getContextPath() %>/fDelete.fa");
					}
				
				</script>
			</form>
			
		</div>
	</div>
	<%-- } else {
		request.setAttribute("msg", "관계자 외에 접근이 불가능한 페이지입니다.");
		request.getRequestDispatcher("view/common/errorPage.jsp").forward(request, response);
	 } --%>
	 <%@ include file="/view/mypage/footer.jsp"%>

</body>
</html>