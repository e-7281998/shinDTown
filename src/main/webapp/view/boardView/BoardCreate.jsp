<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="path" value="${pageContext.request.contextPath }" scope="application"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${path}/view/boardView/board.css" rel="stylesheet" />
<script src="${path}/jq/jquery-3.6.4.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</head>
<body class="main">
	<%@include file="../header.jsp"%>
	<div class="body">
		<div class="board_page_2">
			<form class="posting_form" action = "/shinDTown/board/create.jm" method="POST">
			
				<div class="new_title">
					<label class="label_1">게시판명</label> 
					<input type="text" name="board_name" id="title" />
					<input type="button" id="dup_check" value="중복체크" />
						<!-- <button class="dup_check">중복확인</button> -->
				</div>

				<div class="new_pre">
					<label class="label_2">게시판 설명</label> 
					<input type="text" name="content" id="content" />
				</div>

				<div class="board_btn">
					<input type="submit" value="새 게시판 등록" class="boardbtn">
				</div>
				
			</form>


		</div>
	</div>
	<script>
$(function(){
		$("#dup_check").on("click",function(){
			//page이동 없이 서버에 요청보내고 응답받기 :ajax
		$.ajax({
			url:"/shinDTown/board/dupcheck.jm",
			data:{"board_name":$("#title").val()},
			success:function(responseData){
				$("#message").text(responseData);
				if(responseData==1){
					alert("사용이 불가능합니다");
				
				}
				else if(responseData==0){
					alert("사용이 가능합니다");
					
				}
			},
			error:function(message){
				$("#message").text(message);
				
			}
		});
		})
	});
	</script>
	<script>
		$(document).ready(function() {
			let p_toggle = true;
			let c_toggle = true;

			$("#photo").click(function() {

				p_toggle = !p_toggle;
				$("input.photo").prop("disabled", p_toggle);
				if (p_toggle) {
					$("#form_photo").css("visibility", "hidden");
				} else {
					$("#form_photo").css("visibility", "visible");
				}

			});
			$("#code").click(function() {
				c_toggle = !c_toggle;
				$("textarea.code").prop("disabled", c_toggle);
				if (c_toggle) {
					$("#form_code").hide();
					$("textarea.code").hide();
				} else {
					
					$("#form_code").show();
					$("textarea.code").show();
					$("textarea.code").css("background", "white");
				}
			});

		});
	</script>

</body>
</html>