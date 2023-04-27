<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShinDTown</title>
 <link rel="shortcut icon" type="image/x-icon" href="${path}/view/img/logo.png">
<link href="member.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/jq/jquery-3.6.4.min.js"></script>
</head>
<body>
<%@include file="../header.jsp"%>
	<div class="body">
		<div class="signup">
			<form class="form_2" method="post" action="login.com">
				<input type="text" name="id" class="id fom" placeholder="아이디" />
				<input type="password" name="pwd" class="pwd fom" placeholder="비밀번호" /> 
				<input type="submit" class="submit" value="로그인 하기">
			</form>
		</div>
	</div>

<script>
	$(() => {
		$(".id").focus();
	})
</script>
</body>
</html>