<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.semi.member.vo.Member"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>C4U 너만의 기사</title>
<link rel="stylesheet" href="../../resources/css/semi_menu_frame.css">
<link rel="stylesheet"
	href="../../resources/css/semi_mypage_changepersonalinfo.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
</script>
<script>
	function validate() {
		var pass = document.getElementById('password');
		var pass1 = document.getElementById('password2');
		var name = document.getElementById('name');
		var email = document.getElementById('email');

		// 이메일 검사
		// 4글자 이상이 나오고
		// @가 나오고 1글자 이상 주소 . 글자1~3
		if (!chk(/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/, email, "이메일 형식에 어긋납니다.")) {
			return false;
		}

		// 비밀번호 확인 검사
		// 비밀번호와 비밀번호 확인 값이 일치
		if (pass.value != pass1.value) {
			alert('비밀번호 확인!');
			return false;
		}

		return true;
	}
	function chk(re, ele, msg) {
		if (re.test(ele.value)) {
			return true;
		} else {
			alert(msg);

			// return false;
		}
	}
	function addrSearch() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullAddr = ''; // 최종 주소 변수
						var extraAddr = ''; // 조합형 주소 변수

						// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							fullAddr = data.roadAddress;

						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							fullAddr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
						if (data.userSelectedType === 'R') {
							//법정동명이 있을 경우 추가한다.
							if (data.bname !== '') {
								extraAddr += data.bname;
							}
							// 건물명이 있을 경우 추가한다.
							if (data.buildingName !== '') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
							fullAddr += (extraAddr !== '' ? ' (' + extraAddr
									+ ')' : '');
						}

						$('#address').val(fullAddr);

						// 커서를 상세주소 필드로 이동한다.
						$('#address2').focus();
					}
				}).open();
	};
</script>
</head>
<body>
	<%@ include file="../mypage/header.jsp"%>
	<p>
	<%@ include file="../mypage/leftmenu.jsp"%>
	<%
   		Member e = (Member)session.getAttribute("member");
 	%>
	<div id="changeup">
		<form id="changeupform" name="changeupform" 
			onsubmit="return validate();"
			action="${pageContext.request.contextPath}/changeup.do" method="POST">
			<h1>개인정보 수정</h1>
			<br>
			<ul>
				<li id="label1"><label>아이디&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					: <input type="text" name="id" maxlength="20" id="id"
					value="<%=e.getUserId() %>" readonly required>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="hidden" name="idDuplication" id="idDuplication"
					value="idUncheck"></li>


				<li><label>비밀번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					: <input type="password" name="password" maxlength="20"
					id="password" required></li>


				<li><label>비밀번호 확인&nbsp;&nbsp;</label> : <input type="password"
					id="password2" name="password2" placeholder="비밀번호를 다시 입력해주세요."
					maxlength="20"></li>


				<li><label>이름&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					: <input id="name" type="text" name="name" maxlength="4" value="<%= e.getUserName() %>" readonly required></li>


				<li><label>e-mail&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					: <input id="email" type="email" name="email" maxlength="30" value="<%= e.getEmail() %>"
					required></li>


				<li id="label2"><label>주소&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					: <input id="address" type="text" name="address" maxlength="100"
					>&nbsp;
					<button type="button" onclick="addrSearch();">검색</button></li>



				<li><label>상세주소&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</label>: <input id="address2" type="text" name="address2" maxlength="100"
					></li>


			</ul>
			<br>
			<div id="btn">
				<input type="submit" value="수정하기">&nbsp;&nbsp;<input
					type="reset" value="취소">
			</div>
		</form>
		
	</div>
	</p>
	<%@ include file="../mypage/footer.jsp"%>
</body>
</html>