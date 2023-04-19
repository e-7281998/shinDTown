<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="member.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/jquery-3.6.4.min.js"></script>
</head>
<body>
	<%@include file="../header.jsp"%>
	<div class="body">
		<div class="signup">
			<form class="form" mehtod="post" action="signup">
				<input type="text" name="name" class="name fom" placeholder="이름" />
				<input type="text" name="id" class="id fom" placeholder="아이디" /> 
				<input type="button" id="id_check" value="중복확인">
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
	
	<script>
	$(() => {
		
 		//page이동 없이 서버에 요청 보내고 응답받기 : ajax
		$(".id").on("click",() =>{
  			$.ajax({
				url:"idDupCheck",
				data:{"id":$(".id").val()},
				success: (responseData)=>{ 
 					var message = responseData ==1 ? "이미 존재하는 아이디" : "사용 가능한 아이디";
 					console.log(message);
					/* $("#message").text(message);
					if(responseData == 1){
						$("#m_email").val("");
						$("#m_email").focus();
					}  */
				},
				error:(message) => {
					alert(message);
				}
			});
		})
	});
</script>
</body>
</html>