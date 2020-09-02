<%@page import="com.kh.semi.faq.model.vo.*"%>
<%@page import="java.util.*"%>
<%@page import="com.kh.semi.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>

<%
	Member m = (Member)session.getAttribute("member");
%>	
<% 
	ArrayList<Faq> list = (ArrayList<Faq>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>C4U 너만의 기사 FAQ</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/c9096b9061.js" crossorigin="anonymous"></script>

<link rel="stylesheet" href="resources/css/semi_FAQ.css" />
<link rel="stylesheet" href="resources/css/semi_menu_frame.css">

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

    <div class="section">
        <h2 id="section-title">자주 묻는 질문</h2>
        <div id="btn-panel">
            <p class="allon">
                <a class="btn-panel-a" href="#">전체</a>
            </p>
            <p class="allon">
                <button type="button" onclick="회원정보">회원정보</button>
            </p>
            <p class="allon">
                <a class="btn-panel-a" href="#">자료 이용</a>
            </p>
            <p class="allon">
                <a class="btn-panel-a" href="#">결제</a>
            </p>
            <p class="allon">
                <a class="btn-panel-a" href="#">사이트 이용</a>
            </p>
            <p class="allon">
                <a class="btn-panel-a" href="#">장애관련</a>
            </p>
            <p class="allon">
                <a class="btn-panel-a" href="#">기타</a>
            </p>
        </div>
     
		<script>	
		  $(function(){
	            $('.tr11').slideUp();
			
	            $('.fa-angle-double-down').click(function(){
	               // $(this).next('p').slideDown();
					
	                $(this).parent().next('tr').slideToggle();
	            });
	        });
	    </script>	 
			
        <section>
            <table class="tb0">
            	<tr class="border">
	                <th class="th3 num Nanum1" >번호</th>
	                <th class="th3 tag Nanum1">분류</th>
	                <th>제목</th>
	                
                </tr>
                <% for(Faq f : list){ %>
                	<tr>
                	    <td class="th3 num Nanum1"><%= f.getFno() %></td>
                        <td class="th3 tag Nanum1"><%= f.getFcategory() %></td>
                        <td class="th4"><%= f.getFtitle() %></td>
                        <td class="tagtda fas fa-angle-double-down"></td>
                	</tr>
               	 	<tr class="tr11">
               			<td><%= f.getFcontents() %></td>
               		</tr>
            	<% } %>    
            </table>
	     </section>
	     
	     
      
    
         <a href="./semi_menu_qna.html" class="page-guide2 white">1:1 문의</a>
         <a href="<%= request.getContextPath()%>/view/faq/semi_faqWrite.jsp" id= "admin-write" class="page-guide2 white right">글쓰기</a>
       
        	<div class="pagingArea" align="center">
			<button onclick="location.href='<%= request.getContextPath() %>/fList.fa?currentPage=1'"><<</button>
			<%  if(currentPage <= 1){  %>
			<button disabled><</button>
			<%  }else{ %>
			<button onclick="location.href='<%= request.getContextPath() %>/fList.fa?currentPage=<%=currentPage - 1 %>'"><</button>
			<%  } %>
			
			<% for(int p = startPage; p <= endPage; p++){
					if(p == currentPage){	
			%>
				<button disabled><%= p %></button>
			<%      }else{ %>
				<button onclick="location.href='<%= request.getContextPath() %>/fList.fa?currentPage=<%= p %>'"><%= p %></button>
			<%      } %>
			<% } %>
				
			<%  if(currentPage >= maxPage){  %>
			<button disabled>></button>
			<%  }else{ %>
			<button onclick="location.href='<%= request.getContextPath() %>/fList.fa?currentPage=<%=currentPage + 1 %>'">></button>
			<%  } %>
			<button onclick="location.href='<%= request.getContextPath() %>/fList.fa?currentPage=<%= maxPage %>'">>></button>
			
        
	</div>
     <script>
                 function hiddenatag() {
                if(getElementById() == "admin"){
                    $("#admin-write").show();
                }else{
                    $("#admin-write").hide();
                }
            }
   			</script>
              
           
    
    

   </div>
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
                    <dd><a href="#">- 기사</a></dd>
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

   <%@ include file="/view/mypage/footer.jsp"%>


</body>

</html>








 
