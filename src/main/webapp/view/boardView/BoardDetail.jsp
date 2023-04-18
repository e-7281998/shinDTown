<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="boardDetail.css" rel="stylesheet" />
</head>
<body class="main">
	<%@include file="../header.jsp"%>
	<div class="body">
		<div class="board_detail">
			<div class="board_title">
				<h1>질문게시판</h1>
			</div>
			<div class="board_list">
				<ul class="post_title">
					<li>[피자스쿨] 내가 여기서 먹어sdfsf봤는데,,</li>
					<li>[어사출또] 여기 맛있더라,, 라면 + 초밥123123123</li>
					<li>[CU] 편의점 꿀팁 알려준다...</li>
					<li>[피자스쿨] 내가 여기서 먹어sdfsf봤는데,,</li>
					<li>[어사출또] 여기 맛있더라,, 라면 + 초밥123123123</li>
					<li>[CU] 편의점 꿀팁 알려준다...</li>
					<li>[피자스쿨] 내가 여기서 먹어sdfsf봤는데,,</li>
					<li>[어사출또] 여기 맛있더라,, 라면 + 초밥123123123</li>
					<li>[CU] 편의점 꿀팁 알려준다...</li>
				</ul>
			</div>

			<div class="post_detail">
				<div class="post">

					<div class="post_title">
						<h3>제목 : 배불러요</h3>
					</div>

					<div class="post_content">
						<p>오늘 점심으로 고기를 먹으러 갔었는데 너무 배부르게 잘 먹구 나왔습니다잇~</p>
					</div>

				</div>

				<div class="comment">
					<form class="create_comment">
						<input type="text" class="comment" name="comment"> 
						<input type="submit" value="등록">
					</form>
				</div>

				<div class="comment_list">

					<div class="comms">
						<div class="comm">
							<p>저도 고기 먹었는디..</p>
							</div>
							<button class="good">공감</button>
							<p class="points">1</p>
					</div>
				</div>
			</div>
</body>
</html>