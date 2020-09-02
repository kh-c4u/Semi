<%@page import="com.kh.semi.member.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.semi.market.model.vo.MarketBoard"%>
<%@page import="com.kh.semi.marketcomment.model.vo.MarketBoardComment"%>
<%@page import="com.kh.semi.market.model.vo.PageInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%  Member m = (Member)session.getAttribute("member"); %>    
<% 
	ArrayList<MarketBoard> list = (ArrayList<MarketBoard>)request.getAttribute("list"); 
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
    <meta charset="UTF-8" />
    <title>C4U 너만의 기사</title>

    <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/semi_market1.css" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script
      src="https://kit.fontawesome.com/c9096b9061.js"
      crossorigin="anonymous"
    ></script>
    <style>
      .testbox {
        width: 100%;
        min-height: 500px;
        /* background-color: blue; */
        float: right;
        clear: left;
        margin: 20px;
      }

      #con1 {
        display: flex;
        position: relative;
        width: 100%;
        height: 100%;
        /* background-color: pink; */
      }

      a {
        color: black;
      }
      table a:hover {
        text-decoration: underline;
      }
      table {
        border-collapse: collapse;
      }

      table,
      th,
      td
      {
        border-bottom: 1px solid black;
        padding: 10px;
      }

      th {
        font-size: 16px;
        background-color: skyblue;
      }


      .fas {
        font-size: medium;
        vertical-align: middle;
      }

      .table {
      }

      .noticebody {
        background: hsl(240, 12%, 94%);
      }

      .postsbody {
        background: white;
      }


      .notice-qna {
        color: red;
      }

      .testbox {
        /* display: flex; */
        /* justify-content: center; */
      }

      .title-write {
        width: 100%;

        text-align: left;
      }

      .tablebox {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
      }

      .paging {
        /* text-align: center; */
        padding: 10px;
      }
      .paging > a {
        padding: 5px;
      }

      .btns {
        margin-top: 50px;
      }
      .writeBtn {
        text-align: right;
        border: 1px solid black;
        background: lightgray;
        padding: 6px 12px;
        border-radius: 4px;
      }

      .search-qna {
        margin-top: 10px;
      }
      #search-qna-sel {
        vertical-align: middle;
      }

      .search-qna-btn {
        vertical-align: middle;
      }

      .search-qna-bar {
        vertical-align: middle;
        width: 200px;
      }
    </style>

    <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script></script>
    

    
  </head>
  <body>
	 
    <div id="main-header">
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
      <div class="main-header-logo">
        <a href="<%= request.getContextPath()%>/semi_main.jsp"><img src="<%= request.getContextPath()%>/resources/images/semiLogosize.png" /></a>
        <ul class="main-header-navi">
        <li><a href="#">기사</a></li>
        <li><a href="#">산업기사</a></li>
        <li><a href="#">기능사</a></li>
      </ul>
      </div>
    </div>
    
    
                
    <div id="main-box1"></div>
    
 <!----------------------------------------------------------------------------------------------------->

    <section id="con1">
      <div class="left-menu" style="max-height: 500px;">
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
              <dd><a href="<%=request.getContextPath()%>/SGScomuboardlist.bo">- 산업기사</a></dd>
              <dd><a href="<%=request.getContextPath()%>/GScomuboardlist.bo">- 기능사</a></dd>
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
              <dd><a href="#">- 자주 묻는 질문</a></dd>
              <dd><a href="mailto:bs_khm@naver.com">- 1:1문의</a></dd>
              <dd><a href="/semi_menu_qna list.html">- 신고</a></dd>
            </dl>
          </li>
        </ul>
      </div>
      <div class="testbox">
        <div class="tablebox">
          <table
            class="table Nanum1"
            border="0"
            style="text-align: center; padding: 0; margin: 0; min-width: 100%;">
			


            <div class="title-write">
              <h1>장터</h1>
            </div>

            <thead id="listArea">
              <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
                <th>판매현황</th>
                <th>첨부파일</th>
              </tr>
          
            <% for(MarketBoard b : list){ %>
            <tr>
            	<input type="hidden" value="<%= b.getBno() %>"/>
            	<td><%= b.getBno() %></td>
            	<td><%= b.getBtitle() %></td>
            	<td><%= b.getBwriter()%></td>
            	<td><%= b.getBdate() %></td>
            	<td><%= b.getBcount() %></td>
            	<td><%= b.getBcondition() %></td>
            	<% if( b.getBoardfile() != null) { %>
				<td> ◎ </td>
				<%} else { %>
				<td> X </td>
				<% } %>
			</tr>
			<% } %>
		  </thead>
               </table>
       
       <%-- 페이지 처리 --%>
		<div class="pagingArea" align="center">
			<button onclick="location.href='<%= request.getContextPath() %>/buymarketList.bo?currentPage=1'"><<</button>
			<%  if(currentPage <= 1){  %>
			<button disabled><</button>
			<%  }else{ %>
			<button onclick="location.href='<%= request.getContextPath() %>/buymarketList.bo?currentPage=<%=currentPage - 1 %>'"><</button>
			<%  } %>
			
			<% for(int p = startPage; p <= endPage; p++){
					if(p == currentPage){	
			%>
				<button disabled><%= p %></button>
			<%      }else{ %>
				<button onclick="location.href='<%= request.getContextPath() %>/buymarketList.bo?currentPage=<%= p %>'"><%= p %></button>
			<%      } %>
			<% } %>
				
			<%  if(currentPage >= maxPage){  %>
			<button disabled>></button>
			<%  }else{ %>
			<button onclick="location.href='<%= request.getContextPath() %>/buymarketList.bo?currentPage=<%=currentPage + 1 %>'">></button>
			<%  } %>
			<button onclick="location.href='<%= request.getContextPath() %>/buymarketList.bo?currentPage=<%= maxPage %>'">>></button>
			
		</div>
       
	<div class="searchArea" align="center">
			<select id="searchCondition" name="searchCondition">
				<option>---</option>
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="search">
			<button type="submit">검색하기</button>
			
			
			<% if(m != null){ %>
				<button onclick="location.href='view/marketboard/semi_BuyMarket_BoardWriting.jsp'">작성하기</button>
			<% } %>
			
		</div>
	</div>
	
	<script>
		$(function(){
			$("#listArea td").mouseenter(function(){
				$(this).parent().css({"background":"#CEF6F5", "cursor":"pointer"});
			}).mouseout(function(){
				$(this).parent().css({"background":"white"});
			}).click(function(){
				var bno = $(this).parent().find("input").val();
				location.href="<%=request.getContextPath()%>/buymarketselectOne.bo?bno=" + bno; 
		
			});
		});
	</script>       
       
       
        </div>
     
    </section>
  </body>
  <footer id="main-footer">
    <div class="main-footer-wrap">
      <div><img src="<%= request.getContextPath()%>/resources/images/semiLogosize.png" /></div>
      <div>
        <ul>
          <li>
            고객센터 1544 – 1004 (평일 9:00 ~ 12:30, 13:30 ~ 18:00)
            help@c4you.com
          </li>
          <li>
            <span>㈜합격하조</span> <span>대표이사 유승제</span>
            <span>사업자 등록번호 1004-1004</span>
          </li>
          <li>서울시 강남구 역삼대로</li>
        </ul>
      </div>
    </div>
  </footer>
</html>
