<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link href="main.css" rel="stylesheet" />
</head>
<body class="main">
	<%@include file="header.jsp"%>

	<div class="body">
		<div class="main_page">

			<div class="main_board">
				<fieldset class="board_list_1">
					<legend>맛집게시판</legend>
					<!-- for문으로 각 게시판에서 DB에서 상단에 있는 3개씩 가져오기 -->
					<ul class="board_title">
						<li class="title">[피자스쿨] 내가 여기서 먹어sdfsf봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥123123123</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>


				<fieldset class="board_list_2">
					<legend>질문게시판</legend>
					<ul class="board_title">
						<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>

				<fieldset class="board_list_3">
					<legend>취업정보게시판</legend>
					<ul class="board_title">
						<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>

				<fieldset class="board_list_4">
					<legend>면접준비게시판</legend>
					<ul class="board_title">
						<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>
			</div>
			
			<div class="more_btn">
				<button>더보기</button>
				<button onclick="location.href="">새게시판</button>
			</div>
			
			<div class="calendar">
			<h1>h1</h1>
			</div>
			
			<div class="git">
			<h1>git</h1>
			</div>


			<div class="hot_board">
				<fieldset class="board_list_5">
					<legend>hot 게시판</legend>
					<div class="left">
						<ul class="board_title">
							<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
							<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
							<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
							<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
						</ul>
					</div>
					<div class="right">
						<ul class="board_title">
							<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
							<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
							<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
							<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
						</ul>
					</div>
				</fieldset>

			</div>

		</div>
	</div>

</body>
</html>
