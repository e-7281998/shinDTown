<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="board.css" rel="stylesheet" />
</head>
<body class="main">
	<%@include file="../header.jsp"%>

	<div class="body">

		<div class="board_page">
			<div class="boards">
				<fieldset class="board_list" onclick="location.href='BoardDetail.jsp'">
					<legend>맛집게시판</legend>
					<ul class="board_title">
						<li>[피자스쿨] 내가 여기서 먹어sdfsf봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥123123123</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>

				<fieldset class="board_list" onclick="location.href='BoardDetail.jsp'">
					<legend>질문게시판</legend>
					<ul class="board_title">
						<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>

				<fieldset class="board_list" onclick="location.href='BoardDetail.jsp'">
					<legend>취업정보게시판</legend>
					<ul class="board_title">
						<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>

				<fieldset class="board_list" onclick="location.href='BoardDetail.jsp'">
					<legend>면접준비게시판</legend>
					<ul class="board_title">
						<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>

				<fieldset class="board_list" onclick="location.href='BoardDetail.jsp'">
					<legend>맛집게시판</legend>
					<ul class="board_title">
						<li>[피자스쿨] 내가 여기서 먹어sdfsf봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥123123123</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>

				<fieldset class="board_list" onclick="location.href='BoardDetail.jsp'">
					<legend>질문게시판</legend>
					<ul class="board_title">
						<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>

				<fieldset class="board_list" onclick="location.href='BoardDetail.jsp'">
					<legend>취업정보게시판</legend>
					<ul class="board_title">
						<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>

				<fieldset class="board_list" onclick="location.href='BoardDetail.jsp'">
					<legend>면접준비게시판</legend>
					<ul class="board_title">
						<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>
			</div>

			<div class="find">
				<div class="search">
					<form class="search_form">
						<input type="text" name="search" class="search_title"
							placeholder="게시판 명을 입력해 주세요 "> <input type="button"
							class="search_btn" value="찾기">
					</form>
				</div>

				<div class="list">
					<ul class="lists">
						<li>질문 게시판</li>
						<hr class="line"/>
						<li>맛집 게시판</li>
						<hr class="line" />
						<li>면접 게시판</li>
						<hr class="line"/>
						<li>취업정보 게시판</li>
						<hr class="line" />
					</ul>
				</div>


			</div>
		</div>
	</div>

</body>
</html>