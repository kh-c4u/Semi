<%@page import="com.kh.examQuestion.model.vo.ExamQuestion"%>
<%@page import="com.kh.eaxmAnswer.model.vo.PageInfo"%>
<%@page import="com.kh.semi.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>

<%
	ArrayList<ExamQuestion> b = (ArrayList<ExamQuestion>) request.getAttribute("list");
	Collections.reverse(b);
	out.println("<script>");
	out.println("var list = [];");
	for (ExamQuestion list : b) {out.println("list.push(\"" + list.toString() + "\");");}
	System.out.println("b.size() : " + b.size());
	
	out.println("</script>");
	Member m = (Member) session.getAttribute("member");
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	int currentPage = (int) request.getAttribute("currentPage");
	int maxPage = (int) request.getAttribute("maxPage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>C4U 너만의 기사</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/semi_menu_frame.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/semi_exam_page.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>C4U 너만의 기사</title>
</head>
<body>
	<div id="main-header">
		<div class="main-header-logo">
			<a href="<%=request.getContextPath()%>/semi_main.jsp"><img
				src="<%=request.getContextPath()%>/resources/images/semiLogosize.png" /></a>
		</div>
		<ul id='BeforeLogin' class="main-header-login">
			<%
				if (m == null) {
			%>
			<li><a href="view/member/semi_Login.jsp">로그인</a></li>
			<li><span>|</span><a href="view/member/semi_SignupForm.jsp">회원가입</a></li>
			<li><span>|</span><a
				href="<%= request.getContextPath()%>/fList.fa">고객센터</a></li>
			<%
				} else {
			%>
			<li><a><%=m.getUserName()%>님</a></li>
			<li><span>|</span><a href="logOut.do">로그아웃</a></li>
			<li><span>|</span><a
				href="view/mypage/semi_changepersonalinfo.jsp">마이페이지</a></li>
			<%
				}
			%>
		</ul>

		<ul class="main-header-navi">
			<li><a href="<%= request.getContextPath()%>/ExamBoardlist.ex">기사</a></li>
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
						<a href="<%=request.getContextPath()%>/SGScomuboardlist.bo">-
							산업기사</a>
					</dd>
					<dd>
						<a href="<%=request.getContextPath()%>/GScomuboardlist.bo">-
							기능사</a>
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
						<a href="<%=request.getContextPath()%>/buymarketList.bo">- 삽니다</a>
					</dd>
					<dd>
						<a href="<%=request.getContextPath()%>/marketselectList.bo">-
							팝니다</a>
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
						<a href="<%= request.getContextPath()%>/fList.fa">- 자주 묻는 질문</a>
					</dd>
					<dd>
						<a href="<%=request.getContextPath()%>/QnaBoardServlet.do">-
							1:1문의</a>
					</dd>
					<dd>
						<a href="<%=request.getContextPath()%>/errorList.bo">- 신고</a>
					</dd>
				</dl>
			</li>
		</ul>
	</div>

	<div class="right-menu">
		<div id="paging"></div>
		<script type="text/javascript">
		var oldArray0 = list.pop().split(',');
		var oldArray1 = list.pop().split(',');
		var oldArray2 = list.pop().split(',');
		var oldArray3 = list.pop().split(',');
		var anCountArray = new Array();
				function paging(currentPage,maxPage,anCountArray) {
					var $pingingView = $("#paging");
	        		
	        		/* console.log("oldArray0 : " + oldArray0); */
	        		
			        /* console.log("list[].size() : " + list.length);
			        console.log("oldArray0.size() : " + oldArray0.length);
			         console.log("oldArray0 : " + oldArray0); 
			        console.log("tc[0] : " + oldArray0[0]);
			        console.log("qn[1] : " + oldArray0[1]);
			        console.log("qc[2] : " + oldArray0[2]);
			        console.log("an[3] : " + oldArray0[3]);
			        console.log("ac[4] : " + oldArray0[4]);
			        
			        console.log("ac0[4] : " + oldArray0[4]);
			        console.log("ac1[4] : " + oldArray1[4]);
			        console.log("ac2[4] : " + oldArray2[4]);
			        console.log("ac3[4] : " + oldArray3[4]); */
			        // tc qn qc ac
			        
					var html = "";
					
					html += "<div id=\"eaxm\"> <textarea id=\"eaxm\" name=\"eaxm\" disabled class=\"Nanum2\">"+ oldArray0[2] +" </textarea></div><div id=\"answer\">";
		            html += "<input style=\"width: 13px; height: 17px; position: absolute;\" type=\"radio\"name=\"answer1\" value=\"1\"><textarea disabled id=\"answer\" name=\"answer\">"+ oldArray0[4] +"</textarea><br> ";
		            html += "<input style=\"width: 13px; height: 17px; position: absolute;\" type=\"radio\"name=\"answer1\" value=\"2\"><textarea disabled id=\"answer\" name=\"answer\">"+oldArray1[4]+"</textarea><br> ";
		            html += "<input style=\"width: 13px; height: 17px; position: absolute;\" type=\"radio\"name=\"answer1\" value=\"3\"><textarea disabled id=\"answer\" name=\"answer\">"+oldArray2[4]+" </textarea><br> ";
		            html += "<input style=\"width: 13px; height: 17px; position: absolute;\" type=\"radio\"name=\"answer1\" value=\"4\"><textarea disabled id=\"answer\" name=\"answer\">"+oldArray3[4]+" </textarea>";
		            html += "<div class='btnN'><button href=# id='next' type='button'>다음</button></div> ";
					$("#paging").html(html); // 페이지 목록 생성
					/* $("#paging div button").css("color", "red"); */
					 $("#paging div button").click(function(){
				            var $item = $(this);
				            var $id = $item.attr("id");
				            var inputValue = $("input[name='answer1']:checked").val();
				            if(typeof inputValue == 'undefined'){
				            	alert("잘못입력하셨습니다.");
				            }else if($id == 'next'){
				            	if(currentPage+4<maxPage){
				            		currentPage+=4;
				            		anCountArray.push(inputValue);
				            		console.log(anCountArray);
				            		oldArray0 = list.pop().split(',');
					        		oldArray1 = list.pop().split(',');
					        		oldArray2 = list.pop().split(',');
					        		oldArray3 = list.pop().split(',');
				            	}else{
				            		console.log("오버남");
				            	}
				            }
				            /* console.log("currentPage : " + currentPage);
				            console.log("maxPage : " + maxPage); */
				            paging(currentPage,maxPage,anCountArray);
				        });
				}
				$("document").ready(function() {
					paging(
							<%=currentPage%>,<%=maxPage%>,anCountArray);
			});
		</script>
	</div>
	</div>
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