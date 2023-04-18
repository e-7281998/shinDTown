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
			<form class="form">
				<input type="text" name="name" class="name fom" placeholder="이름" />
				<input type="text" name="id" class="id fom" placeholder="아이디" /> 
				<input type="text" name="pwd" class="pwd fom" placeholder="비밀번호" /> 
				<input type="text" name="pwd_check" class="pwd_check fom" placeholder="비밀번호 확인" /> 
				<select name="class" class="class">
					<option value="1">1반</option>
					<option value="2">2반</option>
					<option value="0">관리자</option>
				</select> 
				<input type="text" name="verify" class="verify fom" placeholder="인증번호" /> 
				<input type="submit" class="submit" value="회원가입 하기">
			</form>
		</div>
	</div>
</body>
</html>