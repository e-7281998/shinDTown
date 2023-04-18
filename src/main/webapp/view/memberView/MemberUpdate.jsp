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
			<form class="form_3">
				<h3 class="name_t">이름</h3>
				<input type="text" name="name" class="name fom"/> 
				<h3 class="id_t">아이디</h3>
				<input type="text" name="id" class="id fom"/>  
				<h3 class="pwd_t">비밀번호</h3>
				<input type="text" name="pwd" class="pwd fom"/>  
				<h3 class="class_t">반번호</h3>
				<input type="text" name="class" class="class fom"/> 
				<input type="submit" class="withdraw" value="탈퇴하기">
				<input type="submit" class="edit" value="비밀번호 변경하기">
			</form>
		</div>
	</div>
</body>
</html>