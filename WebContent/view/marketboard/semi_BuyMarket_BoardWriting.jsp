<%@page import="com.kh.semi.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%  Member m = (Member)session.getAttribute("member"); %>       
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
        <div class="main-header-logo"><a href="<%= request.getContextPath()%>/semi_main.jsp"><img src="<%=request.getContextPath()%>/resources/images/semiLogosize.png" /></a></div> 
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

<!----------------------------------------------------------------------------------------------------->
<section class="section_wrapper nanum1" >
    <div class ="left_wrapper">
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
                        <dd><a href="semi_comu_gisa.html">- 기사</a></dd>
                        <dd><a href="semi_comu_sanupgisa.html">- 산업기사</a></dd>
                        <dd><a href="semi_comu_ginungsa.html">- 기능사</a></dd>
                    </dl>
                </li>
                <div class="menu-line"></div>
                <li>
                    <dl>
                        <dt>
                            <div>장터</div>
                        </dt>
                   	<dd><a href="<%=request.getContextPath()%>/buymarketList.bo">- 삽니다</a></dd>
              		<dd><a href="<%=request.getContextPath()%>/marketselectList.bo">- 팝니다</a></dd></dd>
                    </dl>
                </li>
                <div class="menu-line"></div>
                <li>
                    <dl>
                        <dt>
                            <div>고객센터</div>
                        </dt>
                        <dd><a href="semi_FAQ.html">- 자주 묻는 질문</a></dd>
                        <dd><a href="#">- 1:1문의</a></dd>
                        <dd><a href="semi_menu_qna_error.html">- 신고</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <!-- --------------------------------------장터 글쓰기------------------------------------------------------>

    <div id="write" class ="right_wrapper">
        <h1 class="Nanum2" id="write-title">글쓰기</h1>
        <hr>


        <form id= "board_wirte_form" action="<%= request.getContextPath() %>/buymarketInsert.bo"
        		method="post" enctype="multipart/form-data" name="board_form">
               <div>

                <table>
                    <tr>
                        <td class="Nanum1">제목
                      
                            <input type="text"  id="title"  class="Nanum2" name="title" placeholder="제목을 입력하세요." required="required">
                                              
                            <select id="sale_status" class="Nanum1" name="sale_status" required="required">
                                <option value >---판매 상태---</option>
                                <option value="sale">판매중</option>
                                <option value="complete">판매완료</option>
                            </select>
                        </td>
                    </tr>
                   <tr>
                       <td>작성자
                      <%= m.getUserId() %>
                           <input type="hidden" name="userId" value="<%= m.getUserId() %>"/>
                       </td>
                   </tr>
                   <tr>
                        <td>첨부파일
                            <input type="file" name="filename" accept="image/png, image/jpg, image/bmp" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                   <textarea  name="content" id="content-con" class="Nanum1" cols="40" rows="10" required="required">
               		 판매 상품 : &#13;&#10; 상태 : &#13;&#10; 가격 : &#13;&#10; 연락처 : </textarea>
                        </td>
                    </tr>

                </table>

            </div>


        </form>
    </div>


    <!-- 취소/등록 버튼 -->
    
    <div id="btn_group">

          <button type="button" class="Nanum2" id="cancel" >취소</button>
           <button type="button" class="Nanum2" id="submit">등록</button>

    </div>
  



</section>









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