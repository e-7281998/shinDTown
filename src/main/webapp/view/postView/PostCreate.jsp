<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="path" value="${pageContext.request.contextPath }" scope="application"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShinDTown</title>
 <link rel="shortcut icon" type="image/x-icon" href="${path}/view/img/logo.png">
<link href="${path}/view/postView/post.css" rel="stylesheet" />
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="${path}/jq/jquery-3.6.4.min.js"></script>
</head>
<body class="main">
	<%@include file="../header.jsp"%>
	<div class="body">
		<div class="post">

			<div class="post_selector">
				<fieldset class="post_selector">
					<legend>추가항목</legend> <input type="checkbox" id="code"
						name="code" value="code"> <label for="code">코드</label>
				</fieldset>
			</div>

			<form class="posting" method = "POST" action = "/shinDTown/post/create.com">
				<input type = "hidden" id="board_name" name="board_name" value="${board_name }">
				<div class="post_form">
					<label>제목</label> <input type="text" name="post_title" class="title"
						id="title" />
				</div>

				<div class="post_form" id="form_content">
					<label>내용</label>
					<textarea name="post_content" class="content"></textarea>
				</div>



				<div class="post_form" id="form_code" style="display:none;">
					<label>코드</label>
					<textarea name="post_source" class="code"></textarea>
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