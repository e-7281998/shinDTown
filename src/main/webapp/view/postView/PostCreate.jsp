<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="post.css" rel="stylesheet" />
<script src="../../jq/jquery-3.6.4.min.js"></script>
</head>
<body class="main">
	<%@include file="../header.jsp"%>
	<div class="body">
		<div class="post">

			<div class="post_selector">
				<fieldset class="post_selector">
					<legend>추가항목</legend>
					<input type="checkbox" id="photo" name="photo" value="photo">
					<label for="photo">사진</label> <input type="checkbox" id="code"
						name="code" value="code"> <label for="code">코드</label>
				</fieldset>
			</div>

			<form class="posting">

				<div class="post_form">
					<label>제목</label> <input type="text" name="title" class="title"
						id="title" />
				</div>

				<div class="post_form">
					<label>내용</label> <input type="text" name="content" class="content" />
				</div>

				<div class="post_photo" id="form_photo" style="visibility:hidden;">
					<label>사진</label> <input type="file" name="photo" class="photo"
						disabled />
				</div>


				<div class="post_form" id="form_code" style="display:none;">
					<label>코드</label>
					<textarea name="code" class="code"></textarea>
					<!-- 					<label>코드</label> <input type="text" name="code" class="code" /> -->
				</div>

				<div class="post_form btn">
					<input type="submit" value="등록" class="postbtn">
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