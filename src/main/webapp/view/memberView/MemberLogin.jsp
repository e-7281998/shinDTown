<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="member.css" rel="stylesheet" />
</head>
<body>
<%@include file="../header.jsp"%>
	<div class="body">
		<div class="signup">
			<form class="form_2" method="post" action="login">
				<input type="text" name="id" class="id fom" placeholder="아이디" />
				<input type="text" name="pwd" class="pwd fom" placeholder="비밀번호" /> 
				<input type="submit" class="submit" value="로그인 하기">
			</form>
		</div>
	</div>

</body>
</html>