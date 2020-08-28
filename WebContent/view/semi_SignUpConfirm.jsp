<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>C4U 너만의 기사</title>
    <link rel="stylesheet" href="../resources/css/semi_menu_frame.css">
    <link rel="stylesheet" href="../resources/css/semi_Signup_Confirm.css">
    <link rel="stylesheet" href="../resources/css/semi_Signup.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<body>
    <div id="main-header">
        <div class="main-header-logo"><a href="#"><img src="../resources/images/semiLogosize.png" /></a></div>
        <ul id='BeforeLogin' class="main-header-login">
            <li><a href="#">로그인</a></li>
            <li><span>|</span><a href="/#">회원가입</a></li>
            <li><span>|</span><a href="/#">고객센터</a></li>
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

    <div id="signupbanner">
        <table>
            <tr>
                <td>1. 회원 약관</td>
                <td>2. 회원 가입</td>
                <td style="background: rgba(12, 93, 199, 0.603);
                color: rgb(0, 0, 0);">3. 회원 가입 결과</td>
            </tr>
        </table>
    </div>
    <div id="signUpCompleate">
        <div id="complete" >
            <div class="signuplogo"><a href="#"><img src="../resources/images/semiLogosize.png" /></a>
            </div>
            <div >
                <h1>회원가입이 완료되었습니다.</h1><br>
                <h3>
                    C4U 자격증 회원가입을 환영합니다.<br>
                    아래 버튼을 누르시면 로그인 페이지로 이동합니다.
                </h3>

                <button type="button" onclick="location.href='semi_Login.jsp'">로그인 하기</button>

            </div>


        </div>
    </div>























    <div id="main-footer">
        <div class="main-footer-wrap">
            <div><img src="../resources/images/semiLogosize.png" /></div>
            <div>
                <ul>
                    <li>고객센터 1544 – 1004 (평일 9:00 ~ 12:30, 13:30 ~ 18:00) help@c4you.com</li>
                    <li><span>㈜합격하조</span> <span>대표이사 유승제</span> <span>사업자 등록번호 1004-1004</span> </li>
                    <li>서울시 강남구 역삼대로</li>
                </ul>
            </div>
        </div>
    </div>


</body>

</html>