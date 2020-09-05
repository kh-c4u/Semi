<%@page import="com.kh.Qna.vo.Qna"%>
<%@page import="com.kh.semi.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.kh.Qna.vo.*" %>
    <%@ page import="java.util.*" %>
    <%
   Member m = (Member)session.getAttribute("member");
%>
    
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <title>C4U 너만의 기사</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/semi_menu_frame.css">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/semi_menu_qna.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<script type="text/javascript" >

$( document ).ready(function() {  
    //input을 datepicker로 선언
    $("#fromDt").datepicker({
        dateFormat: 'yy-mm-dd' //Input Display Format 변경
        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
        ,changeYear: true //콤보박스에서 년 선택 가능
        ,changeMonth: true //콤보박스에서 월 선택 가능                
        ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
        ,buttonImage: "<%=request.getContextPath()%>/resources/img/cal.gif" //버튼 이미지 경로
        ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
        ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
        ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
        ,minDate: "-1Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
        ,maxDate: "+1Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                
    });                    
        //input을 datepicker로 선언
        $("#toDt").datepicker({
            dateFormat: 'yy-mm-dd' //Input Display Format 변경
            ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
            ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
            ,changeYear: true //콤보박스에서 년 선택 가능
            ,changeMonth: true //콤보박스에서 월 선택 가능                
            ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
            ,buttonImage: "<%= request.getContextPath()%>/resources/img/cal.gif" //버튼 이미지 경로
            ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
            ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
            ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
            ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
            ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
            ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
            ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트     
            ,minDate: "-1Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
                ,maxDate: "+1Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)           
        });                    
    
    //초기값을 오늘 날짜로 설정
    <% 	String toDt = (String)request.getAttribute("toDt"); 
    	String fromDt = (String)request.getAttribute("fromDt"); 
    if( "".equals(fromDt) || fromDt == null){%>
    $('#fromDt').datepicker('setDate', ''); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)  
    <% }
    if( "".equals(toDt) || toDt == null){%>
        
        //초기값을 오늘 날짜로 설정
        $('#toDt').datepicker('setDate', ''); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)   
        <% } %>
        $('#regist').click(function() { 
            var tableQForm = document.tableQ;            
            //var title = tableQ.title.value;
        	var categoryName = $("select[name=categoryName]").val();
        	$('#categoryNameTmp').val(categoryName);


        	document.getElementById('tableQ').submit();
        })
/* 
        function search() {
        	document.getElementById('search').submit();
        } */
        $('#searchPeriod').change(function() { 
        	console.log($('#searchPeriod').val());
        	if($('#searchPeriod').val() == 1){
        	    $('#fromDt').datepicker('setDate', '-1M'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)  
            $('#toDt').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후) 
        	}
        	else if($('#searchPeriod').val() == 3){
        	    $('#fromDt').datepicker('setDate', '-3M'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)  
                $('#toDt').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후) 
           	}
        	else if($('#searchPeriod').val() == 12){
        	    $('#fromDt').datepicker('setDate', '-1Y'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)  
                $('#toDt').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후) 
           	}
        	else if($('#searchPeriod').val() == "ALL"){
        	    $('#fromDt').datepicker('setDate', ''); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)  
                $('#toDt').datepicker('setDate', ''); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후) 
           	}
        	else if($('#searchPeriod').val() == "DIRECT"){
        	    $('#fromDt').datepicker('setDate', ''); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)  
                $('#toDt').datepicker('setDate', ''); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후) 
           	}
        });
});

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

<div class="sub-right-content">
	<div class="page-title">1:1 문의</div>
	<!-- mypage-status start -->
	<table class="tab-table">
		<tr>
			<td>
				<div class="info">
					<div class="info-top left">
						<strong><%= request.getAttribute("user_name") %></strong>님
					
										
					</div>
				</div>
			</td>
		</tr>
	</table>
	<!-- mypage-status end -->
	<!-- table-wrap start -->
	<form id="tableQ" method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/QnaWriteServlet.bo">
	<div class="table-wrap pt10">
		<table class="tstyle2">
			<colgroup>
				<col width="15%" />
				<col width="85%" />
				<col  />
			</colgroup>
			<tbody>
				
				<tr>
					<th style="text-align:center;">카테고리</th>
					<td colspan='3'>
					<input type="hidden" id="categoryNameTmp" name="categoryNameTmp" >
						<select id="categoryName" name="categoryName">
							<option value>선택</option>
							<option value = "0">기사</option>
							<option value = "1">기능사</option>
							<option value = "2">산업기사</option>
							<option value = "3">장터</option>
							<option value = "4">문제오류</option>
						</select>
					</td>
				</tr>
				<tr>
					<th style="text-align:center;">제목</th>
					<td colspan='3'><input type="text" id="title" name="title" style="width:99%;" maxlength="100"/></td>
				</tr>
				<tr>
					<th style="text-align:center;">문의내용</th>
					<td colspan='3'><textarea id="content" name="content" style="height:80px; width:99%;"></textarea></td>
				</tr>
				<tr>
					<th style="text-align:center;">첨부파일</th>
					<td class="pl20 r-noline">
						<div class="_uploaded_files"></div>
					</td>
					<td class="pl20">
						<div class="_uploaded_files2"></div>
					</td>
					<td class="tcenter"><input type="file" id="file" name="file" src="<%=request.getContextPath()%>/resources/img/btn-file1.gif" class="v-img6" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- table-wrap end -->
	<div class="right"><a class="btn1" id="regist"  href="#">1:1 문의등록</a></div>
	</form>
	<form method="get" action="<%=request.getContextPath()%>/QnaBoardServlet.bo" id="search" name="search">
	<div class="page-subtitle pt20 clear">1:1 문의내역</div>
	<div class="table-top pt10 pb5">
		<div class="select-box">
			<span>
				<select id="searchPeriod" name="searchPeriod" >
					<option value="1" >최근 1개월</option>
					<option value="3" >최근 3개월</option>
					<option value="12" >최근 12개월</option>
					<option value="ALL" selected>전체</option>
					<option value="DIRECT" >직접입력</option>
				</select>
			</span>
			<span>
				<input type="text" id="fromDt" name="fromDt"  maxlength="8"  value="<%= request.getAttribute("fromDt") %>" style="width:80px;" />
				
				<input type="text" id="toDt" name="toDt" maxlength="8" value="<%= request.getAttribute("toDt") %>" style="width:80px;" />
			</span>	
		</div>
		<a href="#" onclick="document.getElementById('search').submit();"  class="btn8" style="margin-top:3px;">조회</a>
	</div>
	</form>
	<!-- table-wrap start -->
	<div class="table-wrap">
		<table class="tstyle2-1" style="word-break:break-all;">
			<colgroup>
				<col width="15%" />
				<col width="" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
			</colgroup>
			<thead>
				<tr>
					<th>카테고리</th>
					<th>제목</th>
					<th>문의일</th>
					<th>상태</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<% if(false){ %>
				<tr>
					<td colspan="5">문의한 내역이 없습니다.</td>
				</tr>			
				<% } else {%>
				
					<%-- String categoryname = request.getParameter("categoryname");
						String title = request.getParameter("title");
						String reg_date = (String)request.getAttribute("reg_date");
						String state = (String)request.getAttribute("state");
						 --%>
						 <% List<Qna> qnalist = new ArrayList();
						 qnalist =  (List<Qna>) request.getAttribute("qnalist");
						  %>
					<%  for (int i = 0; i < qnalist.size(); i++) { %>
						  <% String category = qnalist.get(i).getCategoryName(); %>
						  <% int state = qnalist.get(i).getState(); %>
				<tr>
					<%-- <th><%= (String)request.getParameter("categoryname") %></th>
					<th><%= (String)request.getParameter("title") %></th>
					<th><%= (String)request.getParameter("reg_date") %></th>
					<th><%= (String)request.getParameter("state") %></th> --%>
						<% if("0".equals(category)) {%>
						<th>기사</th>
						<% }else if("1".equals(category)){ %>
						<th>기능사</th>
						<%} else if("2".equals(category)){ %>
						<th>산업기사</th>
						<%} else if("3".equals(category)){%>
						<th>장터</th>
						<% }else if("4".equals(category)) {%>
						<th>문제오류</th>
						<% } else { %>
						<th>분류미정</th><%} %>
						<th><a href="<%=request.getContextPath()%>/QnadetailServlet.bo?seq=<%= qnalist.get(i).getSeq()%>"><%= qnalist.get(i).getTitle() %></a></th>
						<th><%= qnalist.get(i).getReg_date() %></th>
						<% if( state == 0) {%>
						<th>처리중</th>
						<% }else if(state == 1){ %>
						<th>처리완료</th><%} %>
						<th></th>
				</tr>	
						<%} %>
				<% } %>
				
			</tbody>
		</table>
	</div>
	<!-- table-wrap end -->
	<!-- paging-wrap start -->
	<div class="paging-wrap">
		<!-- paging-link start -->
		<div class="paging-link">
			
			&nbsp;
			
		</div>
		<div class="pagingArea" align="center">
			<button onclick="location.href='/myWeb/selectList.bo?currentPage=1'"><<</button>
			
			<button disabled><</button>
			
			
			
				<button disabled>1</button>
			
			
				<button onclick="location.href='resources/selectList.bo?currentPage=2'">2</button>
			
			
				<button onclick="location.href='/selectList.bo?currentPage=3'">3</button>
			
			
				<button onclick="location.href='/selectList.bo?currentPage=4'">4</button>
			
			
				<button onclick="location.href='/selectList.bo?currentPage=5'">5</button>
			
			
				<button onclick="location.href='/selectList.bo?currentPage=6'">6</button>
			
			
				<button onclick="location.href='/selectList.bo?currentPage=7'">7</button>
			
			
				<button onclick="location.href='/selectList.bo?currentPage=8'">8</button>
			
			
				<button onclick="location.href='/selectList.bo?currentPage=9'">9</button>
			
			
				<button onclick="location.href='/selectList.bo?currentPage=10'">10</button>
			
			
				
			
			<button onclick="location.href='/selectList.bo?currentPage=2'">></button>
			
			<button onclick="location.href='/selectList.bo?currentPage=26'">>></button>
			
		</div>
		<!-- paging-link end -->
	</div>
	
	<!-- paging-wrap end -->
</div>

<!-- sub-right-content end -->

<iframe id="hiddenFrame" name="hiddenFrame" style="width:0px; height:0px; display:none;"></iframe>

</div>
</div>



<div id="main-footer">
	<div class="main-footer-wrap">
		<div><img src="<%=request.getContextPath()%>/resources/images/semiLogosize.png"/></div>
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
 <script type="text/javascript" >
        function search() {
        	document.getElementById('search').submit();
        }


</script>
	</html>