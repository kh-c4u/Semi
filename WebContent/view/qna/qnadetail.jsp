<%@page import="com.kh.semi.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.kh.Qna.vo.*" %>
    <%@ page import="java.util.*" %>
    <%
   Member m = (Member)session.getAttribute("member");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>C4U 너만의 기사</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/semi_menu_frame.css">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/semi_user.css">   
    <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/semi_menu_qna.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<script type="text/javascript" >




/* 
var categoryName = document.getElementById("categoryName");
 
selectOption = selectOption.options[categoryName.selectedIndex].value; */

function regist() {
    var tableQForm = document.tableQ;            
    //var title = tableQ.title.value;
	
	//document.tableQ.action = "/SemiProject/QnaWriteServlet.do";

	/* document.getElementById('tableQ').action = "/SemiProject/QnaWriteServlet.do"; */
	document.getElementById('tableQ').submit();
}
</script>
<body>
	<div id="main-header">
		<div class="main-header-logo">
			<a href="<%= request.getContextPath()%>/semi_main.jsp"> <img
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
						<a href="#">- 신고</a>
					</dd>
				</dl>
			</li>
		</ul>
	</div>

<!-- sub-right-content start -->
<div class="sub-right-content">
	<div class="page-title"><br>1대1 문의</div>  
	<div class="left pb5 mr5"><a href="#" class="btn3">목록</a></div>
		
		
	<div class="left pb5"><a href="#" class="btn3">다음 <span class="blue">&gt;</span></a></div>
		
	<!-- -->

	
	
	<!-- table-wrap start -->
	<div class="table-wrap">
		<table class="tstyle2" style="word-break:break-all">
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
					
						<%  Qna qnaVo = (Qna) request.getAttribute("qnaVo"); %>
				
					<td colspan="5" class="pl20">
					<%= qnaVo.getTitle() %>
					</td>
							
				</tr>
				<tr>
					<th class="tcenter vm">작성자</th>
					<td class="pl20 r-noline">
					
						<a href="#">
							<span class="darkgray pr20"><%= qnaVo.getUser_id() %></span>
						</a>
					
					</td>
					<th class="tcenter vm">작성일</th>
					<td class="pl20"><%= qnaVo.getReg_date() %></td>	
					<th class="tcenter vm">첨부파일</th>
					<td class="pl20"><a href="#"><%= qnaVo.getFileName()%></a></td>	
				</tr>
			
			</tbody>
		</table>
		<table class="tstyle2" style="word-break:break-all">
			<colgroup>
				<col width="" />
			</colgroup>
			<tbody>
				<tr>
					<td class="t-noline l-line">
						<div class='bbs-content' style='font-family:나눔고딕;font-size:13px;width:1020px;word-wrap:break-word;white-space:normal;overflow-x:hidden;padding:1px;max-width:700px;'><p><%= qnaVo.getContent() %></p><p>&nbsp;</p><p>&nbsp;</p></div>
					
						
					
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	
	<!-- table-wrap start -->
	<div class="table-wrap">
		<table class="tstyle2">
			<colgroup>
				<col width="" />
			</colgroup>
			<tbody>
						 <% List<Qna> qnaReplylist =  (List<Qna>) request.getAttribute("qnaReplylist");
						 int size = (int)request.getAttribute("replyCnt");
						  %>
				<tr>
					<th class="pl20 vm"><strong>답변 <%= size %>개</strong></th>
				</tr>
			</tbody>
		</table>
		<table class="tstyle2" style="word-break:break-all">
			<colgroup>
				<col width="" />
			</colgroup>
			<tbody>
			
					<% if(size != 0) {
					for (int i = 0; i < size; i++) { %>
				<tr>
				
					<td class="t-noline l-line">

						
							<div class="left pl15">
								<span class="darkgray pr20">admin</span>
								<span class="f11 gray"><%= qnaReplylist.get(i).getReg_date() %></span>
								<a href="<%= request.getContextPath()%>/QnaReplyDServlet.do?seq=<%= qnaReplylist.get(i).getSeq()%>">삭제</a>
							</div>
						

						<div class="right" id="div_comment_btn422228" name="div_comment__btn422228">
						
						</div>
						<div id="div_comment422228" name="div_comment422228" class="clear pl15 tx-content-container"><p><%= qnaReplylist.get(i).getContent() %>&nbsp;</p></div>

					</td>
				</tr>
					<% } }%>
			</tbody>
		</table>
	</div>
	<!-- table-wrap end -->
	<!-- table-wrap start -->
	<% if("admin".equals(request.getAttribute("user_id"))){ %>
	<form action="<%= request.getContextPath()%>/QnaReplyWriteServlet.do" name="tableQ" id="tableQ" >
	<input type="hidden" id="seq" name="seq" value='<%= request.getAttribute("seq")%>'>
	<div id="div_editor" name="div_editor" style='font-family:나눔고딕;font-size:13px;'>
		<textarea id="content" rows="4" class='' style='width:795px;' maxlength='40000' name='content' placeholder="답변을 적어주세요."></textarea>
	</div>
	
	<!-- table-wrap end -->
	
	
						
			
			

	<br>
	
	
	<div class="btn-center" id="div_btnAddComment" name="div_btnAddComment"><a onclick="regist();" class="btn4">답변 등록</a></div>

	<div id="div_btnModComment" name="div_btnModComment" style="display:none;">
		<div class="right mr5"><a href="#;" class="btn4">취소</a></div>  <!--등록한 사용자만 볼수있음-->
		<div class="right mr5"><a href="#" class="btn4">답변 수정</a></div> <!--등록한 사용자만 볼수있음-->
	</div>   <div>
                    <c:if test="${pagination.curRange ne 1 }">
                        <a href="#" onClick="fn_paging(1)">[처음]</a> 
                    </c:if>
                    <c:if test="${pagination.curPage ne 1}">
                        <a href="#" onClick="fn_paging('${pagination.prevPage }')">[이전]</a> 
                    </c:if>
                    <c:forEach var="pageNum" begin="${pagination.startPage }" end="${pagination.endPage }">
                        <c:choose>
                            <c:when test="${pageNum eq  pagination.curPage}">
                                <span style="font-weight: bold;"><a href="#" onClick="fn_paging('${pageNum }')">${pageNum }</a></span> 
                            </c:when>
                            <c:otherwise>
                                <a href="#" onClick="fn_paging('${pageNum }')">${pageNum }</a> 
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${pagination.curPage ne pagination.pageCnt && pagination.pageCnt > 0}">
                        <a href="#" onClick="fn_paging('${pagination.nextPage }')">[다음]</a> 
                    </c:if>
                    <c:if test="${pagination.curRange ne pagination.rangeCnt && pagination.rangeCnt > 0}">
                        <a href="#" onClick="fn_paging('${pagination.pageCnt }')">[끝]</a> 
                    </c:if>
                </div>
                
                <div>
                    총 게시글 수 : ${pagination.listCnt } /    총 페이지 수 : ${pagination.pageCnt } / 현재 페이지 : ${pagination.curPage } / 현재 블럭 : ${pagination.curRange } / 총 블럭 수 : ${pagination.rangeCnt }
                </div>
	
	</form>
		<% } %>

	
	<!-- table-wrap start -->
	<div class="table-wrap">
	</div>
	<!-- table-wrap end -->

	<!-- table-wrap start -->
	<div class="table-wrap">
	</div>
	<!-- table-wrap end -->

</div>




<div id="main-footer">
    <div class="main-footer-wrap">
        <div><img src="<%= request.getContextPath()%>/resources/images/semiLogosize.png"/></div>
        <div>
            <ul>
                <li>고객센터 1544 – 1004 (평일 9:00 ~ 12:30, 13:30 ~ 18:00)  help@c4you.com</li>
                <li><span>㈜합격하조</span>   <span>대표이사 유승제</span>   <span>사업자 등록번호 1004-1004</span> </li>
                <li>서울시 강남구 역삼대로</li>
            </ul>
        </div>
    </div>
</div>

       
</body>
</html>