<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="boardDetail.css" rel="stylesheet" />
<script src="../../jq/jquery-3.6.4.min.js"></script>
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
					<li value="1" id="1">[피자스쿨] 내가 여기서 먹어sdfsf봤는데,, [좋아요:<p class="like">1</p>] [댓글:<p class="com_count">1</p>]</li>
					<li>[어사출또] 여기 맛있더라,, 라면 + 초밥123123asdaasdasdasdasdasdadasdasdasdas124554543</li>
					<li>[CU] 편의점 꿀팁 알려준다...</li>
					<li>[피자스쿨] 내가 여기서 먹어sdfsf봤는데,,</li>
					<li>[어사출또] 여기 맛있더라,, 라면 + 초밥123123123</li>
					<li>[CU] 편의점 꿀팁 알려준다...</li>
					<li>[피자스쿨] 내가 여기서 먹어sdfsf봤는데,,</li>
					<li>[어사출또] 여기 맛있더라,, 라면 + 초밥123123123</li>
					<li>[CU] 편의점 꿀팁 알려준다...</li>
				</ul>
				<div class="posting">
					<button class="posting_btn"
						onclick="location.href='../postView/PostCreate.jsp';">글쓰기</button>
				</div>
			</div>

			<div class="post_detail" id="post_detail">
				<div class="post">

					<div class="title">
						<h3>제목 : 배불러요</h3>
					</div>
					
					<div class="date">
						<h3>2023/04/19</h3>
					</div>

					<div class="content">
						<p>오늘 점심으로 고기를 먹으러 갔었는데 너무 배부르게 잘 먹구 나왔습니다잇~</p>
					</div>
					
					<div class="like">
						<p>좋아요 : 2</p>
					</div>

				</div>

				<div class="comment">
					<form class="create_comment">
						<input type="text" class="comment" name="comment"> <input
							type="submit" value="등록">
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
					<hr class="line"/>
					
					<div class="comms">
						<div class="comm">
							<p>저도 고기 먹었는디..</p>
						</div>
						<button class="good">공감</button>
						<p class="points">1</p>
					</div>
					<hr class="line"/>
					
					
					<div class="comms">
						<div class="comm">
							<p>저도 고기 먹었는디..</p>
						</div>
						<button class="good">공감</button>
						<p class="points">1</p>
					</div>
					<hr class="line" />
					
					<div class="comms">
						<div class="comm">
							<p>저도 고기 먹었는디..</p>
						</div>
						<button class="good">공감</button>
						<p class="points">1</p>
					</div>
					<hr class="line" />
				

				</div>
			</div>
		</div>
	</div>
	
	<script>
		$(document).ready(function() {
			$("li").click(function() {
				console.log($(this).val());
				$("#post_detail").css("visibility", "visible");

			});
		});
	</script>
	
</body>
</html>