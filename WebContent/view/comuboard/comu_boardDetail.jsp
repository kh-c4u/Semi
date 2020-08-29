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
    <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css//semi_menu_frame.css">
	<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css//semi_posting.css">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css//semi_user.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div id="main-header">
        <div class="main-header-logo"><a href="<%= request.getContextPath()%>/semi_main.jsp"><img src="/Semi/resources/images/semiLogosize.png" /></a></div> 
        <ul id='BeforeLogin' class="main-header-login">
            <li><a href="#">로그인</a></li>
            <li><span>|</span><a href="/#">회원가입</a></li>
            <li><span>|</span><a href="/#">고객센터</a></li>
        </ul>
       
        <ul class="main-header-navi">
            <li><a href="<%=request.getContextPath()%>/comuboardlist.bo">기사</a></li>
            <li><a href="#">산업기사</a></li>
            <li><a href="#">기능사</a></li>
        </ul>
            
        </div>
  
    <div id="main-box1"></div>

   
    <div class="left-menu" >
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
                <dd><a href="<%=request.getContextPath()%>/comuboardlist.bo">- 기사</a></dd>
                <dd><a href="#">- 산업기사</a></dd>
                <dd><a href="#">- 기능사</a></dd>
            </dl>
        </li>
        <div class="menu-line"></div>
        <li>
            <dl>
                <dt>
                    <div>장터</div>
                </dt>
                <dd><a href="#">- 삽니다</a></dd>
                <dd><a href="#">- 팝니다</a></dd>
            </dl>
        </li>
        <div class="menu-line"></div>
        <li>
            <dl>
                <dt>
                    <div>고객센터</div>
                </dt>
                <dd><a href="#">- 자주 묻는 질문</a></dd>
                <dd><a href="#">- 1:1문의</a></dd>
                <dd><a href="#">- 신고</a></dd>
            </dl>
        </li>
    </ul>
    </div>


<% if(m != null ) { %>
<div class="sub-right-content">
	<div class="page-title Nanum2">게시판</div>  
	<div class="left pb5 mr5"><a href="<%= request.getContextPath()%>/resources" class="btn3">목록</a></div>
		
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
				
					<td colspan="3" class="pl20" style="border-right: 1.7px solid black;">
						<%= b.getBtitle() %>
					</td>
					<th class="tcenter vm" >첨부파일</th>
					<td class="pl20"><a href="<%= request.getContextPath()%>/resources/boardUploadFiles/<%=b.getBoardfile() %>"
							   download="<%= b.getBoardfile() %>">
							<%=b.getBoardfile() %>
							</a></td>
				</tr>
				밑에서부터 하면 됨
				<tr>
					<th class="tcenter vm">작성자</th>
					<td class="pl20 r-noline">
				
							<span class="darkgray pr20"><%= b.getBwriter() %></span>
					
					</td>
					<th class="tcenter vm">작성일</th>
					<td class="pl20 r-noline"><%= b.getBdate() %></td>
					<th class="tcenter vm">조회수</th>
					<td class="pl20"><%= b.getBcount() %></td>
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
						<div class="bbs-content" id="content">
						 <p id="content"></p></div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
<div class="comment">
	<div class="replyWriteArea">

		<form action="/myWeb/insertComment.bo" method="post">
			<input type="hidden" name="writer" value="<%=m.getUserId()%>"/>
			<input type="hidden" name="bno" value="<%=b.getBno() %>" />
			<input type="hidden" name="refcno" value="0" />
			<input type="hidden" name="clevel" value="1" />
			<table class="writeC">
				<tr>
					<td>댓글 작성 : </td>
					<td id="txt"><textArea rows="3" cols="80" id="replyContent" name="replyContent"></textArea></td>
					<td><button type="submit" id="addReply">댓글 등록</button></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="replySelectArea">
		<table id="replySelectTable">
			<tr style="border: 1px solid rgb(160, 160, 160); background: skyblue;">
			  
			  <td><b>댓글</b></td>
			  <td colspan="2" style="width: 700px;">2020-08-10</td>

			  <td style="text-align: right;">

			  <input type="hidden" name="cno" value="<%=bco.getCno()%>"/ ">
							  
						<button type="button" class="updateBtn" 
							onclick="updateReply(this);">수정</button>
							
						<button type="button" class="updateConfirm"
							onclick="updateConfirm(this);"
							style="display:none;" >저장</button> &nbsp;&nbsp;
							
						<button type="button" class="deleteBtn"
							onclick="deleteReply(this);">삭제</button>
						</td>
						<tr class="comment replyList<%=bco.getClevel()%>">
							<td style="width: 100px; font-size: 15px;">설윤희 : </td>
							<td colspan="2" style="background : transparent;">
							<textarea class="reply-content" cols="105" rows="1"
							 readonly="readonly">안녕하세요</textarea>
							</td>
							<td></td>
						</tr>
		</table>
	</div>
</div>
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