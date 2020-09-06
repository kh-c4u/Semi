<%@page import="com.kh.semi.errorbaord.model.vo.ErrorBoard"%>
<%@page import="com.kh.semi.comubaord.model.vo.PageInfo"%>
<%@page import="com.kh.semi.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
	Member m = (Member)session.getAttribute("member");
%>
<% 
	ArrayList<ErrorBoard> list = (ArrayList<ErrorBoard>)request.getAttribute("list"); 
	PageInfo pi = (PageInfo)request.getAttribute("pi");

	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>C4U 너만의 기사</title>
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath()%>/resources/css/semi_menu_frame.css">
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath()%>/resources/css/semi_comu_gisa.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
	<div id="main-header">
		<div class="main-header-logo">
			<a href="<%= request.getContextPath()%>/semi_main.jsp"><img
				src="<%= request.getContextPath()%>/resources/images/semiLogosize.png" /></a>
		</div>
		<ul id='BeforeLogin' class="main-header-login">
			<%if(m == null) {%>
			<li><a href="view/member/semi_Login.jsp">로그인</a></li>
			<li><span>|</span><a href="view/member/semi_SignupForm.jsp">회원가입</a></li>
			<li><span>|</span><a href="/#">고객센터</a></li>
			<%}else{ %>
			<li><a><%=m.getUserName()%>님</a></li>
			<li><span>|</span><a href="logOut.do">로그아웃</a></li>
			<li><span>|</span><a href="view/mypage/semi_changepersonalinfo.jsp">마이페이지</a></li>
			<%} %>
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
						<a href="<%=request.getContextPath()%>/SGScomuboardlist.bo">- 산업기사</a>
					</dd>
					<dd>
						<a href="<%=request.getContextPath()%>/GScomuboardlist.bo">- 기능사</a>
					</dd>
				</dl>
			</li>
			<div class="menu-line"></div>
			<li>
				<dl>
					<dt>
						<div>장터</div>
					</dt>
				<dd><a href="<%=request.getContextPath()%>/buymarketList.bo">- 삽니다</a></dd>
              	<dd><a href="<%=request.getContextPath()%>/marketselectList.bo">- 팝니다</a></dd>
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
						<a href="<%=request.getContextPath()%>/errorList.bo">- 신고</a>
					</dd>
				</dl>
			</li>
		</ul>
	</div>

	<div class="semi-comu-gisa" style="float: left;">
		<div id="page-title" class="page-title">산업기사</div>
		<div class="page-guide">
			<span>해당 게시판은 오류 및 신고 게시판입니다. 다양한 의견을 게시해주세요:)</span>
		</div>
		<div class="search-box">
			<div class="input-wrap" style="margin-left: 300px;">검색 : 
				<input type="text" id="searchWord" name="searchWord"
					style="width: 150px;">
			</div>
			<div id="btns">
				<button style="border:none; background:none;"  onclick="search();" class="btns" ><img
					src="<%= request.getContextPath()%>/resources/images/seach_gisa.png">
					</button>
			</div>
			
			<script>
			function search(){
				location.href="<%= request.getContextPath()%>/SGSsearchBoard1.bo"+ "?con="+$('#searchCondition').val()+"&skw=" + $("input[name=searchType]:checked").val()+"&keyword="+$('#searchWord').val();
			}
			</script>
		</div>
		
		<div class="table-wrap">
			<table class="table-gisa" style="word-break: break-all;">
				<colgroup id="colGroupNO">
					<col width="2%">
					<col width="5%">
					<col width=25%>
					<col width="3%">
					<col width="5%">
					<col width="3%">
				</colgroup>
				<thead id="th">
					<tr>
						<th>No.</th>
						<th>카테고리</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회</th>
					</tr>
				</thead>
				<tbody id="serach-Result">
					

					<% for(ErrorBoard b : list){ %>
					<tr id="contents1">
						<input type="hidden" value="<%= b.getBno() %>" />
						<td><%= b.getBno() %></td>
						<td><%= b.getBtypestr() %></td>
						<td><%= b.getBtitle() %></td>
						<td><%= b.getBwriter() %></td>
						<td><%= b.getBdate() %></td>
						<td><%= b.getBcount() %></td>
					</tr>
					<% } %>
				</tbody>
			</table>


		</div>
		<div class="paging-wrap">
			<div class="writeBtn">
				<% if(m != null){ %>
				<button onclick="location.href='view/comuboard/semi_comu_write_SGS.jsp'"
					id="writeB">글쓰기</button>
				<% } %>

			</div>
			<div class="pagination" id="paging-link">
				<button
					onclick="location.href='<%= request.getContextPath() %>/SGScomuboardlist.bo?currentPage=1'"><<</button>
				<%  if(currentPage <= 1){  %>
				<button disabled>이전</button>
				<%  }else{ %>
				<button
					onclick="location.href='<%= request.getContextPath() %>/SGScomuboardlist.bo?currentPage=<%=currentPage - 1 %>'">이전</button>
				<%  } %>

				<% for(int p = startPage; p <= endPage; p++){
					if(p == currentPage){	
			%>
				<button disabled><%= p %></button>
				<%      }else{ %>
				<button
					onclick="location.href='<%= request.getContextPath() %>/SGScomuboardlist.bo?currentPage=<%= p %>'"><%= p %></button>
				<%      } %>
				<% } %>

				<%  if(currentPage >= maxPage){  %>
				<button disabled>다음</button>
				<%  }else{ %>
				<button
					onclick="location.href='<%= request.getContextPath() %>/SGScomuboardlist.bo?currentPage=<%=currentPage + 1 %>'">다음</button>
				<%  } %>
				<button
					onclick="location.href='<%= request.getContextPath() %>/SGScomuboardlist.bo?currentPage=<%= maxPage %>'">>></button>

			</div>
		</div>
	</div>
	
	<script>
		$(function(){
			$(".table-gisa td").click(function(){
				var bno = $(this).parent().find("input").val();
				location.href="<%=request.getContextPath()%>/SGSCselectOne.bo?bno=" + bno;
			});
		});
	</script>

	<div id="main-footer">
		<div class="main-footer-wrap">
			<div>
				<img
					src="<%= request.getContextPath()%>/resources/images/semiLogosize.png" />
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