<%@page import="com.kh.semi.comubaord.model.vo.ComuBoard"%>
<%@page import="com.kh.semi.comubaord.model.vo.PageInfo"%>
<%@page import="com.kh.semi.comuboardComment.model.vo.comuboardComment"%>
<%@page import="com.kh.semi.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
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
    <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/semi_menu_frame.css">
    <link rel="stylesheet" href="request.getContextPath()%>/resources/css/semi_user.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>

</body>
</html>