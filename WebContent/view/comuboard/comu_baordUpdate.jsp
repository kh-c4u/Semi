<%@page import="com.kh.semi.comubaord.model.vo.ComuBoard"%>
<%@page import="com.kh.semi.comubaord.model.vo.PageInfo"%>
<%@page import="com.kh.semi.comuboardComment.model.vo.comuboardComment"%>
<%@page import="com.kh.semi.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
   Member m = (Member)session.getAttribute("member");
   ComuBoard b = (ComuBoard)request.getAttribute("board");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>C4U 너만의 기사</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/semi_menu_frame.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/semi_comu_write.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<div id="main-header">
		<div class="main-header-logo">
			<a href="<%= request.getContextPath()%>/semi_main.jsp"> <img
				src="<%=request.getContextPath()%>/resources/images/semiLogosize.png" /></a>
		</div>
		<ul id='BeforeLogin' class="main-header-login">
			<li><a href="#">로그인</a></li>
			<li><span>|</span><a href="/#">회원가입</a></li>
			<li><span>|</span><a href="/#">고객센터</a></li>
		</ul>

		<ul class="main-header-navi">
			<li><a href="#">기사</a></li>
			<li><a href="#">산업기사</a></li>
			<li><a href="#">기능사</a></li>
		</ul>

	</div>

	<div id="main-box1"></div>


	<div class="left-menu">
		<ul id="left-menu-share" class="left-menu1">

			<li>
				<dl>
					<dt>
						<div class="left-menu-name">게시판</div>
					</dt>
				</dl>
			</li>
			<div class="menu-line"></div>
			<li>
				<dl>
					<dt>
						<div>커뮤니티</div>
					</dt>
					<dd>
						<a href="<%=request.getContextPath()%>/comuboardlist.bo">- 기사</a>
					</dd>
					<dd>
						<a href="#">- 산업기사</a>
					</dd>
					<dd>
						<a href="#">- 기능사</a>
					</dd>
				</dl>
			</li>
			<div class="menu-line"></div>
			<li>
				<dl>
					<dt>
						<div>장터</div>
					</dt>
					<dd>
						<a href="#">- 삽니다</a>
					</dd>
					<dd>
						<a href="#">- 팝니다</a>
					</dd>
				</dl>
			</li>
			<div class="menu-line"></div>
			<li>
				<dl>
					<dt>
						<div>고객센터</div>
					</dt>
					<dd>
						<a href="#">- 자주 묻는 질문</a>
					</dd>
					<dd>
						<a href="#">- 1:1문의</a>
					</dd>
					<dd>
						<a href="#">- 신고</a>
					</dd>
				</dl>
			</li>
		</ul>
	</div>



	<div id="write" class="Nanum2 right_wrapper">
		<h1 style="font-size: 30px; text-align: left">글쓰기</h1>



		<form id="submitForm" method="post" enctype="multipart/form-data">
			<div class="option-type">
				<input name="bno" type="hidden" value="<%=b.getBno()%>">
				<label>제목 :</label> <input type="text" placeholder="제목을 입력하세요."
					name="title" style="padding: 5px 100px 0px 10px;"
					value="<%= b.getBtitle().replace("\"", "&#34;") %>">
				<div class="select-box">
					<select id="category-name" name="category" required="required">
						<option disabled="disabled" >게시판 선택</option>
						<option value="1">공부팁</option>
						<option value="2">합격수기</option>
						<option value="3">수강후기</option>
						<option value="4">무료인강추천</option>
					</select>
				</div>


			</div>

			<textarea name="content" cols="40" rows="10"
				style="width: 800px; height: 300px; resize: none"
				required="required" <%= b.getBcontent() %>></textarea>
			<br>

			<!-- 취소/등록 버튼 -->
			<div id="btn_group">
				<div class="attachfile">
					<input type="file" name="filename"
						accept="image/png,image/jpg,image/bmp" />
				</div>
				<div class="can-sub-buttons">
					<button type="submit" class="btn" 
						onclick="deleteBoard();">삭제</button>
					<button type="submit" class="btn" 
						onclick="complete();">완료</button>
				</div>

				<script>
	
		
		function deleteBoard(){
			// delete 는 예약어 이므로 deleteNotice 로 ...!
			$("#submitForm").attr("action","<%=request.getContextPath() %>/cbDelete.bo");
		}
		function complete(){
			$("#submitForm").attr("action","<%=request.getContextPath() %>/comuboardUpdate.bo");
			
		}
	
    	</script>

			</div>

		</form>
	</div>





	<div style="clear: both"></div>



	<div id="main-footer">
		<div class="main-footer-wrap">
			<div>
				<img
					src="<%=request.getContextPath()%>/resources/images/semiLogosize.png" />
			</div>
			<div>
				<ul>
					<li>고객센터 1544 – 1004 (평일 9:00 ~ 12:30, 13:30 ~ 18:00)
						help@c4you.com</li>
					<li><span>㈜합격하조</span> <span>대표이사 유승제</span> <span>사업자
							등록번호 1004-1004</span></li>
					<li>서울시 강남구 역삼대로</li>
				</ul>
			</div>
		</div>
	</div>


</body>
</html>