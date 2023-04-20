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
			<form class="form">
				<input type="text" name="name" class="name fom" minlength="1" maxlength="5" placeholder="이름은 한글" required />
				<input type="text" name="id" class="id fom" minlength="4" maxlength="10" placeholder="아이디는 4자리 이상 영문이나 숫자 " required /> 
				<input type="button" id="id_check" value="중복확인">
				<input type="text" name="pwd" class="pwd fom" minlength="6" maxlength="10" placeholder="비밀번호는 4자리 이상 영문이나 숫자" required/> 
				<input type="text" name="pwd_check" class="pwd_check fom" minlength="6" maxlength="10" placeholder="비밀번호 확인" required/> 
				<select name="class" class="class">
					<option value="1">1반</option>
					<option value="2">2반</option>
					<option value="0">관리자</option>
				</select> 
				<input type="text" name="verify" class="verify fom" minlength="1" placeholder="인증번호" required/> 
				<input type="button" id="certi_check" class="certi_fom" value="인증확인">
				<input type="button" class="submit" value="회원가입 하기">
			</form>
		</div>
	</div>
	
	<script>
	
	$(() => {
		$(".name").focus();
	})
	
	//인증번호 확인
	$("#certi_check").on('click', () => { 
		var classdata = {
				"class" : $(".class").val(),
				"verify" :  $(".verify").val()
			}
		
		$.ajax({
			url:"classCheck",
			data: classdata,
			success: (responseData)=>{ 
				if(responseData == -1)
					alert("인증번호가 다릅니다."); 
				else
					alert("인증번호가 일치합니다.")
			},
			error:(message) => {
 				console.log(message);
			}
		});
	})
	
	//id 중복 체크
	$("#id_check").on("click",() =>{
		var id = $.trim($(".id").val()) ;
		
		if(checkKor(id) || checkKor2(id) ||id.length < 4 ||id.length > 10){
			return;			
		} 
			$.ajax({
			url:"idDupCheck",
			data:{"id":$(".id").val()},
			success: (responseData)=>{ 
				if(responseData == 1){
					alert("이미 존재하는 아이디 입니다.");
					$(".id").focus().val("");
				}else 
					alert("사용 가능한 아이디 입니다."); 
					 
			},
			error:(message) => {
 				console.log(message);
			}
		});
	})
	
	//회원가입 신청
	$(".submit").on("click", () => {
		
		var name = $.trim($(".name").val());
		var id = $.trim($(".id").val()) ;
		var pwd = $.trim($(".pwd").val()) ;
		var pwdchek = $.trim($(".pwd_check").val());
		var verify = $.trim($(".verify").val());
		var userclass = $(".class").val(); 		
		 
		//name 체크
 		if(!checkKor(name) ||checkKor2(name) || checkNum(name) || checkEng(name)){
 			return;
		} 
		//id 체크
 		if(checkKor(id) || checkKor2(id)){
 			return;			
		} 
		//pwd체크
 		if(!checkNum(pwd) || !checkEng(pwd)){
			return;
		} 
		//pwd, pwd_check 확인
		if(!(pwd == pwdchek)){
			alert('비밀번호가 일치하지 않습니다.');
			return;
		}
		
		var userdata = {
			"name" : name,
			"id" : id,
			"pwd" : pwd,
			"class" : userclass,
			"verify" :  $(".verify").val()
		}
		
		 //회원가입 ajax로 보내기
		$.ajax({
			url:"signup",
			data: userdata,
			success: (responseData)=>{ 
				if(responseData == 1){
					alert("회원가입 성공");
					location.href = "MemberLogin.jsp";
				}else if(responseData == -1){
					alert('인증번호가 다릅니다.')
				}
				else{
 					alert("회원가입 실패");					
				}

			},
			error:(message) => {
 				console.log(message);
			}
		});
 	})
	
	//한글 체크 
	function checkKor(str) {
	    const regExp = /[가-힣]/i; 
	    if(regExp.test(str)){
	        return true;
	    }else{
	        return false;
	    }
	}
	function checkKor2(str) {
	    const regExp = /[ㄱ-ㅎㅏ-ㅣ]/i; 
	    if(regExp.test(str)){
	        return true;
	    }else{
	        return false;
	    }
	}
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
	 
		
		
 </script>
</body>
</html>