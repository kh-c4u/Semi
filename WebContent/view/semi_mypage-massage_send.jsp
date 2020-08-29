<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>C4U 너만의 기사</title>
<link rel="stylesheet" href="../resources/css/semi_mypage.css" />
<link rel="stylesheet"
	href="../resources/css/semi_mypage_message_send.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://kit.fontawesome.com/c9096b9061.js" crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="mypage/header.jsp"%>
	<%@ include file="mypage/leftmenu.jsp"%>
	<div class="testbox">
        <div class="tablebox">
          <div class="title-write">
            <h1>쪽지보내기</h1>
          </div>

          <div>받는사람: 옆집형님</div>
          <textarea
            id="writeNote"
            style="resize: none; width: 500px;"
            rows="6"
            cols="2"
            title="쪽지 내용을 입력해 주세요."
          ></textarea>

          <div class="btns">
            <a href="#" class="btn replyBtn">답장</a>
            <a href="/semi_mypage_massage list.html" class="btn replyBtn">목록</a>
     
          </div>
        </div>
      </div>
	
	
	
	<%@ include file="mypage/footer.jsp"%>
</body>
</html>