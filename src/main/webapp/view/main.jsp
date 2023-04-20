<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link href="main.css" rel="stylesheet" />

<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.5/index.global.min.js'></script>
<script src="../jq/jquery-3.6.4.min.js"></script>

</head>
<body class="main">
	<%@include file="header.jsp"%>

	<div class="body">
		<div class="main_page">

			<div class="main_board">
				<fieldset class="board_list_1"
					onclick="location.href='/shinDTown/view/boardView/BoardDetail.jsp'">
					<legend>맛집게시판</legend>
					<!-- for문으로 각 게시판에서 DB에서 상단에 있는 3개씩 가져오기 -->
					<ul class="board_title">
						<li class="title">[피자스쿨] 내가 여기서 먹어sdfsf봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥123123123</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>


				<fieldset class="board_list_2"
					onclick="location.href='/shinDTown/view/boardView/BoardDetail.jsp'">
					<legend>질문게시판</legend>
					<ul class="board_title">
						<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>

				<fieldset class="board_list_3"
					onclick="location.href='/shinDTown/view/boardView/BoardDetail.jsp'">
					<legend>취업정보게시판</legend>
					<ul class="board_title">
						<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>

				<fieldset class="board_list_4"
					onclick="location.href='/shinDTown/view/boardView/BoardDetail.jsp'">
					<legend>면접준비게시판</legend>
					<ul class="board_title">
						<li>[피자스쿨] 내가 여기서 먹어봤는데,,</li>
						<li>[어사출또] 여기 맛있더라,, 라면 + 초밥 추천</li>
						<li>[CU] 편의점 꿀팁 알려준다...</li>
					</ul>
				</fieldset>
			</div>



			<div class="more_btn">
				<button
					onclick="location.href='/shinDTown/view/boardView/BoardRead.jsp'">더보기</button>
				<button
					onclick="location.href='/shinDTown/view/boardView/BoardCreate.jsp'">새게시판</button>
			</div>

			<div class="git">
				<h1>git</h1>
			</div>


			<div class="calendar">
				<div class="calendar" id="calendar"></div>

				<div class="modal fade" id="calendarModal" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">일정을 입력하세요.</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div class="form-group">

									<label for="taskId" class="col-form-label">일정 제목</label> <input
										type="text" class="form-control" id="calendar_title"
										name="calendar_title"> <label for="taskId"
										class="col-form-label">일정 내용</label> <input type="text"
										class="form-control" id="calendar_content"
										name="calendar_content"> <label for="taskId"
										class="col-form-label">시작 날짜</label> <input type="date"
										class="form-control" id="calendar_start_date"
										name="calendar_start_date"> <label for="taskId"
										class="col-form-label">종료 날짜</label> <input type="date"
										class="form-control" id="calendar_end_date"
										name="calendar_end_date">
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-warning" id="addCalendar">추가</button>
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal" id="sprintSettingModalClose">취소</button>
							</div>

						</div>
					</div>
				</div>



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

	<script>
	
	$(document).ready(function(){
		
	});
		document.addEventListener('DOMContentLoaded', function() {
			var calendarEl = document.getElementById('calendar');
			var calendar = new FullCalendar.Calendar(calendarEl, {
				timeZone : 'UTC',
				initialView : 'dayGridMonth',
				events : [ {
					title : '해피해피',
					start : '2023-04-19',
					end : '2023-04-22'
				} ],
				headerToolbar : {
					center : 'addEventButton'
				},
				customButtons : {
					addEventButton : {
						text : "일정 추가",
						click : function() {
							$("#calendarModal").modal("show");
							$("#addCalendar").on(
									"click",
									function() {
										var title = $("#calendar_title").val();
										var content = $("#calendar_content")
												.val();
										var start_date = $(
												"#calendar_start_date").val();
										var end_date = $("#calendar_end_date")
												.val();

										if (title == null || title == "") {
											alert("제목을 입력하세요")
										} else if (content == null
												|| content == "") {
											alert("내용을 입력하세요.");
										} else if (start_date == ""
												|| end_date == "") {
											alert("날짜를 입력하세요.");
										} else if (new Date(end_date)
												- new Date(start_date) < 0) {
											alert("종료일이 시작일보다 먼저입니다.");
										} else {
											var obj = {
												"title" : title,
												"content" : content,
												"start" : start_date,
												"end" : end_date
											}
											console.log(obj);
										}
									});
						}
					}
				},
				editable : true,
				displayEventTime : false
			});
			calendar.render();
		});
	</script>

</body>
</html>
