<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<section>
		<div class="left-menu">
			<ul id="left-menu-share" class="left-menu1">
				<li>
					<dl>
						<dt>
							<div class="left-menu-name">마이페이지</div>
						</dt>
					</dl>
				</li>
				<div class="menu-line"></div>
				<li>
					<dl>
						<dd>
							<a href="<%=request.getContextPath()%>/view/mypage/semi_changepersonalinfo.jsp">- 개인정보수정</a>
						</dd>
						<dd>
							<a href="<%=request.getContextPath()%>/myscorelist.my">- 나의 점수보기</a>
						</dd>
						<dd>
							<a href="<%=request.getContextPath()%>/view/mypage/semi_mypage-massage_send.jsp">- 쪽지보내기</a>
						</dd>
						<!-- <dd>
							<a href="semi_mypage-massage_view.jsp">- 받은 쪽지</a>
						</dd> -->
						<dd style=" position:absolute; z-index: 100;">
							<a href="<%=request.getContextPath()%>/massageList.bo">- 받은 쪽지</a>
						</dd>
						<dd> 　</dd>
						<dd style=" position:absolute; z-index: 100;">
							<a href="<%=request.getContextPath()%>/commentCheck.bo">- 내 글/댓글 알림</a>
						</dd >
						<dd> 　</dd>
						<dd style=" position:absolute; z-index: 100;">
							<a href="<%=request.getContextPath()%>/view/mypage/semi_mypage-withdraw.jsp">- 탈퇴</a>
						</dd>

					</dl>
				</li>
			</ul>
		</div>
	</section>
</body>
</html>