<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>

</head>
<body>
	<%@ include file="/view/mypage/header.jsp"%>
 
	  <%-- if(m != null && m.getUserId().equals("admin")) { --%>
	<div class="outer">
		<br>
		<h2 align="center">공지 사항 작성</h2>
		<div class="tableArea">
			<form action="<%= request.getContextPath() %>/fInsert.fa" method="post">
				<table>
					<tr>
						<td>제목 </td>
						<td colspan="3"><input type="text" size="50" name="title"></td>
					</tr>
					<tr>
						<td>작성자 </td>
						<td>
							<input type="text" value="글쓰기<%--= m.getUserName() --%>" name="writer" readonly>
							<input type="button" value="글쓰기<%--= m.getUserId() --%>" name="userId">
						</td>
						<td>작성일</td>
						<td><input type="date" name="date"></td>
					</tr>
					<tr>
						<td>내용 </td>
					</tr>
					<tr>
						<td colspan="4">
							<textarea name="content" cols="60" rows="15" style="resize:none;"></textarea>
						</td>
					</tr>
				</table>
				<br>
				<div align="center">
					<button type="reset">취소하기</button>
					<button type="submit">등록하기</button>
				</div>
				
			</form>
			
		</div>
	</div>
	<%-- } else {
		request.setAttribute("msg", "관계자 외에 접근이 불가능한 페이지입니다.");
		request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
	} --%>
	  <%@ include file="/view/mypage/footer.jsp"%>
</body>
</html>