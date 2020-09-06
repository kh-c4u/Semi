<%@page import="com.kh.semi.member.vo.Member"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%  Member m = (Member)session.getAttribute("member");%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>C4U 너만의 기사</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/semi_menu_frame.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/semi_market.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

<div id="main-header">

    <div class="main-header-logo">
    <a href="<%= request.getContextPath()%>/semi_main.jsp"><img src="<%=request.getContextPath()%>/resources/images/semiLogosize.png" /></a></div>
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
        <li><a href="<%= request.getContextPath()%>/ExamBoardlist.ex">기사</a></li>
        <li><a href="#">산업기사</a></li>
        <li><a href="#">기능사</a></li>
    </ul>

</div>



<div id="main-box1"></div>

<!----------------------------------------------------------------------------------------------------->

    
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
                  <a href="<%= request.getContextPath()%>/fList.fa">- 자주 묻는 질문</a>
               </dd>
               <dd>
                  <a href="<%=request.getContextPath()%>/QnaBoardServlet.do">- 1:1문의</a>
               </dd>
               <dd>
                  <a href="<%=request.getContextPath()%>/errorList.bo">- 신고</a>
               </dd>
                    </dl>
                </li>
            </ul>
       
    </div>
    <!-- --------------------------------------장터 글쓰기------------------------------------------------------>

    <div id="write" class ="right_wrapper">
        <h1 class="Nanum2" id="write-title">글쓰기</h1>
        <hr>


        <form id= "board_wirte_form" action="<%= request.getContextPath() %>/buymarketInsert.bo"
        		method="post" enctype="multipart/form-data" name="board_form">
               <div class="writing">
					<label>제목 :</label>
					<input type="text" placeholder="제목을 입력하세요." name="title" style="padding:5px 100px 0px 10px;" required="required">
					<div class="select-box">
                        <select id="category-value" name="sale_status" required="required">
                            <option value selected="selected">구매 현황</option>
                            <option value="1">구매중</option>
                            <option value="2">구매완료</option>
                        </select>
                    </div>
            	</div>
				
				<br>
				<div>작성자 <%= m.getUserId() %>
                           <input type="hidden" name="userId" value="<%= m.getUserId() %>"/></div>
				 <textarea name="content" cols="40" rows="10"
                             style="width: 800px; height: 300px; resize: none" placeholder="구매 할 상품 : &#13;&#10; 상태 : &#13;&#10; 가격 : &#13;&#10; 연락처 : " 
                             required="required"></textarea>
						<br>
						
					<div id="btn_group1">
                    <div class="uploadfile">
                    <input type="file" name="filename" accept="image/png,image/jpg,image/bmp"/>
                    </div>
                    <div class="sub-buttons">
                    <button type="button" class="btn" id="cancel" onclick="cancle()">취소</button>
                    <button type="submit" class="btn" id="submit">등록</button>
                    </div>
                    </div>
						
						
        </form>
    </div>


    <script>
    function cancle(){
		location.href="<%=request.getContextPath()%>/buymarketList.bo";
		
	}
	
    </script>
   
  













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
<script>
    var semi_market_board_write = {
        init : function() {
            var _this = this;
            $("#cancel").click(function() {
                _this.cancel_click();
            });
            $("#submit").click(function() {
                _this.submit_click();
            });
        },
        cancel_click : function () {
        	$("#board_wirte_form")[0].reset();
            console.log('취소함다.');
        },
        submit_click : function() {
        	
        	$("#board_wirte_form").submit();
        	//$("form")[0].submit();  -- form 태그의 0번째를 찾아서 submit 함
            // location.href ="./semi_main.html";
            console.log('제출함다.');
        }
    };
    semi_market_board_write.init(); 




</script>
</html>