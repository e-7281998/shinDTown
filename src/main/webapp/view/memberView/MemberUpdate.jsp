<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="member.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/jq/jquery-3.6.4.min.js"></script>
</head>
<body>
	<%@include file="../header.jsp"%>
	<div class="body">
		<div class="signup">
			<form class="form_3">
				<h3 class="name_t">이름</h3>
				<input type="text" name="name" class="name fom" value="${user_name}" disabled/> 
				<h3 class="id_t">아이디</h3>
				<input type="text" name="id" class="id fom" value="${loginUser.user_id}" disabled/>  
				<h3 class="pwd_t">비밀번호</h3>
				<input type="text" name="pwd" class="pwd fom" minlength="6" maxlength="10" placeholder="6자리 이상 영문이나 숫자"/>  
				<h3 class="class_t" >반번호</h3>
				<input type="text" name="class" class="class fom" value="${loginUser.user_class}" disabled/> 
				<input type="button" class="withdraw" value="탈퇴하기">
				<input type="submit" class="edit" value="비밀번호 변경하기">
			</form>
		</div>
	</div>
	<script>
		
		$(".edit").on("click", (e) => {
			e.preventDefault();
			
			if(!confirm("비밀번호를 정말 변경하시겠습니까?")){
				$(".pwd").val("");
				return;
			}
			
			var pwd = $.trim($(".pwd").val());
			
			if(!(checkNum(pwd) || checkEng(pwd))){
				alert("숫자나 영문 이외는 불가");
				return;
			} 
			
			$.ajax({
				url:"updatePwd",
				data:{"pwd":pwd, "id" : "${loginUser.user_id}"},
				success:(responseData) => {
					if(responseData == 1)
						alert("비밀번호 변경을 완료했습니다.");
					else
						alert("비밀번호 변경 실패");
					$(".pwd").val("").focus();

				},
				error:(message)=>{
					console.log(message);
				}
			})
		})
		
		//숫자 체크
		function checkNum(str){
		    const regExp = /[0-9]/i;
		    if(regExp.test(str)){
		        return true;
		    }else{
		        return false;
		    }
		}
		//영문 체크
		function checkEng(str){
		    const regExp = /[a-zA-Z]/i; // 영어
		    if(regExp.test(str)){
		        return true;
		    }else{
		        return false;
		    }
	}
	
		//탈퇴하기
		$(".withdraw").on("click", (e) => {
			//e.preventDefault();

			if(!confirm("신디타운을 정말 탈퇴하시겠습니까?"))
				return;
			
			$.ajax({
				url: "withdraw",
				data : {"id":"${loginUser.user_id}"},
				success: (responseData) => {
					if(responseData == 1){
						alert("탈퇴를 성공적으로 처리하였습니다."); 
						location.href="/shinDTown/index.jsp";
					}else
						alert("탈퇴에 실패했습니다.");
				},
				error:(message) => {
					console.log(message);
				}
			})
		})
	</script>
</body>
</html>