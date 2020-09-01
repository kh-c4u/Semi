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
   // 댓글 리스트
   ArrayList<comuboardComment> clist
     = (ArrayList<comuboardComment>)request.getAttribute("clist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>C4U 너만의 기사</title>
<link rel="stylesheet"
	href="<%= request.getContextPath()%>/resources/css/semi_menu_frame.css">
<link rel="stylesheet"
	href="<%= request.getContextPath()%>/resources/css/semi_posting.css">
<link rel="stylesheet"
	href="<%= request.getContextPath()%>/resources/css/semi_user.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>C4U 너만의 기사</title>
</head>
<body>
	<div id="main-header">
		<div class="main-header-logo">
			<a href="<%= request.getContextPath()%>/semi_main.jsp"><img
				src="<%=request.getContextPath()%>/resources/images/semiLogosize.png" /></a>
		</div>
		<ul id='BeforeLogin' class="main-header-login">
			<%if(m == null) {%>
			<li><a href="view/member/semi_Login.jsp">로그인</a></li>
			<li><span>|</span><a href="view/member/semi_SignupForm.jsp">회원가입</a></li>
			<li><span>|</span><a href="/#">고객센터</a></li>
			<%}else{ %>
			<li><a><%=m.getUserName()%>님</a></li>
			<li><span>|</span><a href="logOut.do">로그아웃</a></li>
			<li><span>|</span><a href="view/semi_mypage-withdraw.jsp">마이페이지</a></li>
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


	<% if(m != null ) { %>
	<div class="sub-right-content">
		<div class="page-title Nanum2">게시판</div>
		<div class="left pb5 mr5">
			<a href="<%= request.getContextPath()%>/comuboardlist.bo"
				class="btn3">목록</a>

		</div>
		<div class="left pb5 mr5">
			<% if( m != null && m.getUserId().equals(b.getBwriterId())) { %>
			<a
				href="<%= request.getContextPath()%>/cbUpView.bo?bno=<%=b.getBno()%>"
				class="btn3">수정</a>
			<% } %>
		</div>

		<div class="table-wrap">
			<table class="tstyle2" style="word-break: break-all">
				<colgroup>
					<col width="12%" />
					<col width="25%" />
					<col width="12%" />
					<col width="25%" />
					<col width="12%" />
					<col width="" />
				</colgroup>
				<tbody>
					<tr>
						<th class="tcenter vm">제목</th>

						<td colspan="3" class="pl20"
							style="border-right: 1.3px solid grey;"><%= b.getBtitle() %>
						</td>
						<% if(b.getBoardfile() != null && b.getBoardfile().length() > 0) { %>
						<th class="tcenter vm">첨부파일</th>
						<td class="pl20"><a
							href="<%= request.getContextPath()%>/resources/boardUploadFiles/<%=b.getBoardfile() %>"
							download="<%= b.getBoardfile() %>"> <%=b.getBoardfile() %>
						</a></td>
						<% } %>
					</tr>

					<tr>
						<th class="tcenter vm">작성자</th>
						<td class="pl20 r-noline"><span class="darkgray pr20"><%= b.getBwriterId() %></span>

						</td>
						<th class="tcenter vm">작성일</th>
						<td class="pl20 r-noline"><%= b.getBdate() %></td>
						<th class="tcenter vm">조회수</th>
						<td class="pl20"><%= b.getBcount() %></td>
					</tr>
				</tbody>
			</table>
			<table class="tstyle2" style="word-break: break-all">
				<colgroup>
					<col width="" />
				</colgroup>
				<tbody>
					<tr>
						<td class="t-noline l-line">
							<div class="bbs-content" id="content">
								<p id="content"><%= b.getBcontent() %></p>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="comment">
			<div class="replyWriteArea">

				<form action="<%= request.getContextPath()%>/CinsertComment.bo"
					method="post">
					<input type="hidden" name="writer" value="<%=m.getUserId()%>" /> <input
						type="hidden" name="bno" value="<%=b.getBno() %>" /> <input
						type="hidden" name="refcno" value="0" /> <input type="hidden"
						name="clevel" value="1" />
					<table class="writeC">
						<tr>
							<td>댓글 작성 :</td>
							<td id="txt"><textArea rows="3" cols="80" id="replyContent"
									name="replyContent" required></textArea></td>
							<td><button type="submit" id="addReply">댓글 등록</button></td>
						</tr>
					</table>
				</form>
			</div>

			<div id="replySelectArea">
				<% if (clist != null) { %>
				<% for(comuboardComment bco : clist) { %>
				<table id="replySelectTable"
					style="margin-left : <%= (bco.getClevel()-1) * 15 %>px;
                   width : <%= 950 - ((bco.getClevel()-1) * 15)%>px;"
					class="replyList<%=bco.getClevel()%>">

					<tr
						style="border: 1px solid rgb(160, 160, 160); background: rgb(200, 233, 247);">
						<td>작성자</td>
						<td colspan="2" style="width: 700px;"><%= bco.getCdate() %></td>

						<td style="text-align: right;"><input type="hidden"
							name="cno" value="<%=bco.getCno()%>"/ "> <%if(m.getUserId().equals(bco.getCwriterId())) { %>
							<input type="hidden" name="cno" value="<%=bco.getCno()%>" />

							<button type="button" class="updateBtn"
								onclick="updateReply(this);">수정</button>

							<button type="button" class="updateConfirm"
								onclick="updateConfirm(this);" style="display: none;">저장</button>
							&nbsp;&nbsp;

							<button type="button" class="deleteBtn"
								onclick="deleteReply(this);">삭제</button> <% } else if( bco.getClevel() < 3) { %>
							<input type="hidden" name="writer" value="<%=m.getUserId()%>" />
							<input type="hidden" name="refcno" value="<%= bco.getCno() %>" />
							<input type="hidden" name="clevel" value="<%=bco.getClevel() %>" />
							<button type="button" class="insertBtn"
								onclick="reComment(this);">댓글 달기</button>&nbsp;&nbsp;

							<button type="button" class="insertConfirm"
								onclick="reConfirm(this);" style="display: none;">등록</button> <% } %>
						</td>
					</tr>

					<tr class="comment replyList<%=bco.getClevel()%>">
						<td style="width: 100px; font-size: 15px;"><%= bco.getCwriterId() %>
							:</td>
						<td colspan="2" style="background: transparent;"><textarea
								class="reply-content" cols="105" rows="1" readonly="readonly"
								required><%= bco.getCcontent() %></textarea></td>
						<td></td>
					</tr>
				</table>

				<% } } else { %>
				<p>
					현재 등록된 댓글이 없습니다.<br> 첫 댓글의 주인공이 되어 보세요!
				</p>
				<% } %>
			</div>
		</div>
	</div>
	<script>
   function updateReply(obj) {
      // 현재 위치와 가장 근접한 textarea 접근하기
      $(obj).parent().parent().next().find('textarea')
      .removeAttr('readonly');
      
      // 수정 완료 버튼을 화면 보이게 하기
      $(obj).siblings('.updateConfirm').css('display','inline');
      
      // 수정하기 버튼 숨기기
      $(obj).css('display', 'none');
   }
   
   function updateConfirm(obj) {
      // 댓글의 내용 가져오기
      var content
        = $(obj).parent().parent().next().find('textarea').val();
      
      // 댓글의 번호 가져오기
      var cno = $(obj).siblings('input').val();
      
      // 게시글 번호 가져오기
      var bno = '<%=b.getBno()%>';
      
      location.href="<%= request.getContextPath()%>/updateComment.bo?"
             +"cno="+cno+"&bno="+bno+"&content="+content;
   } 
   
   
   
   function deleteReply(obj) {
      // 댓글의 번호 가져오기
      var cno = $(obj).siblings('input').val();
      
      // 게시글 번호 가져오기
      var bno = '<%=b.getBno()%>';
      
      location.href="<%= request.getContextPath()%>/comentDelete.co"
      +"?cno="+cno+"&bno="+bno;
   }
   
   function reComment(obj){
      // 추가 완료 버튼
      $(obj).siblings('.insertConfirm').css('display','inline');
      
      // 클릭한 버튼 숨기기
      $(obj).css('display', 'none');
      
      // 내용 입력 공간 만들기
      var htmlForm = 
         '<tr class="comment"><td></td>'
            +'<td colspan="3" style="background : transparent;">'
               + '<textarea class="reply-content" style="text-align: left;margin-bottom:10px; width:600px; border:1.4px solid grey; cols="100px" rows="2"></textarea>'
            + '</td>'
         + '</tr>';
      
      $(obj).parents('table').append(htmlForm);
      
   }
   
   function reConfirm(obj) {
      // 댓글의 내용 가져오기
      
      // 참조할 댓글의 번호 가져오기
      var refcno = $(obj).siblings('input[name="refcno"]').val();
      var level = Number($(obj).siblings('input[name="clevel"]').val()) + 1;
      
      // console.log(refcno + " : " + level);
      
      // 게시글 번호 가져오기
      var bno = '<%=b.getBno()%>';
      
      var parent = $(obj).parent();
      var grandparent = parent.parent();
      var siblingsTR = grandparent.siblings().last();
      
      var content = siblingsTR.find('textarea').val();
      
      location.href='<%= request.getContextPath()%>/CinsertComment.bo'
                 + '?writer=<%= m.getUserId() %>' 
                 + '&replyContent=' + content
                 + '&bno=' + bno
                 + '&refcno=' + refcno
                 + '&clevel=' + level;
   }
   </script>
	<% } else {
      request.setAttribute("msg", "회원만 가능한 서비스 입니다.");
      request.getRequestDispatcher("/view/common/errorPage.jsp").forward(request, response);
   }
   %>


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