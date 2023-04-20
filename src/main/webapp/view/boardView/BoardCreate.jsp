<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="board.css" rel="stylesheet" />
<script src="../../jq/jquery-3.6.4.min.js"></script>
</head>
<body class="main">
	<%@include file="../header.jsp"%>
	<div class="body">
		<div class="board_page_2">
			<form class="posting_form">
			
				<div class="new_title">
					<label class="label_1">게시판명</label> 
					<input type="text" name="title" class="title" id="title" />
						<button class="dup_check" onclick="location.href=''">중복확인</button>
				</div>

				<div class="new_pre">
					<label class="label_2">게시판 설명</label> 
					<input type="text" name="content" class="content" />
				</div>

				<div class="board_btn">
					<input type="submit" value="새 게시판 등록" class="boardbtn">
				</div>
				
			</form>


		</div>
	</div>
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