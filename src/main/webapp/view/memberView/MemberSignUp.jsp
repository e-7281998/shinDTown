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
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
	<%@include file="../header.jsp"%>
	<div class="body">
		<div class="signup">
			<form class="form">
				<input type="text" name="name" class="name fom" minlength="1" maxlength="5" placeholder="이름은 한글" required />
				<input type="text" name="id" class="id fom" minlength="4" maxlength="10" placeholder="아이디는 4자리 이상 영문이나 숫자 " required /> 
				<input type="button" id="id_check" value="중복확인">
				<input type="password" name="pwd" class="pwd fom" minlength="6" maxlength="10" placeholder="비밀번호는 6자리 이상 영문이나 숫자" required/> 
				<input type="password" name="pwd_check" class="pwd_check fom" minlength="6" maxlength="10" placeholder="비밀번호 확인" required/> 
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
			url:"classCheck.com",
			method : "post",
			data: classdata,
			success: (responseData)=>{ 
				if(responseData == -1)
					swal("인증실패!", "인증번호가 다릅니다!", 'warning');
				else
					swal("인증성공!", "인증번호가 일치합니다!", 'success');
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
			url:"idDupCheck.com",
			data:{"id":$(".id").val()},
			success: (responseData)=>{ 
				if(responseData == 1){
					swal("아이디 중복!", "같은 아이디가 존재합니다!", 'warning').then(function(){
						$(".id").focus().val("");
					})
				}else 
					swal("사용가능!", "사용가능한 아이디 입니다!", 'success');
					 
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
  		if(!checkKor(name) || name.legnth >5 || checkSpc(name) || checkKor2(name)){
 			swal("조건 확인!", "이름은 5자리 이하 한글만 가능합니다.", 'info');
 				return;
		} 
		//id 체크
  		if(!(checkNum(id) || checkEng(id)) || checkSpc(id) || id.length < 4 || id.length > 10){
 			swal("조건 확인!", "아이디는 4~10자리 영문이나 숫자만 가능합니다.", 'info');
 				return;
 			
		} 
		//pwd체크
 		if(!(checkNum(pwd) || checkEng(pwd)) || checkSpc(pwd) || pwd.length <6 || pwd.length > 10){
 			swal("조건 확인!", "비밀번호는 6~10자리 이하 영문이나 숫자만 가능합니다.", 'info');
 				return;
 			
		} 
		//pwd, pwd_check 확인
		if(!(pwd == pwdchek)){
			swal("비밀번호 불일치!", "비밀번호가 일치하지 않습니다.", 'error');
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
			url:"signup.com",
			data: userdata,
			success: (responseData)=>{ 
				if(responseData == 1){
					swal("회원가입 성공!", "회원 가입에 성공하셨습니다!", 'success').then(function (){
						location.href = "MemberLogin.jsp";
		 			});
				}else if(responseData == -1){
					swal("인증번호 오류!", "인증번호를 확인해주세요!", 'error');
				}
				else{
					swal("회원가입 실패!", "회원 가입에 실패하셨습니다!", 'error');
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
	//특수문자 체크
	function checkSpc(str){
		const regExp =  /[~!@#$%^&*()_+|<>?:{}-]/i;
		 if(regExp.test(str)){
		        return true;
		    }else{
		        return false;
		    }
	}
	 
		
		
 </script>
</body>
</html>