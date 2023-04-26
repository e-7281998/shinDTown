<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="path" value="${pageContext.request.contextPath }"
	scope="application"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>

<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.5/index.global.min.js'></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<script src="../jq/jquery-3.6.4.min.js"></script>
<link href="${path}/view/main.css" rel="stylesheet" />
<script>
/* 	window.onload=function(){
		location.href='/shinDTown/board/main.jm';
	} */
</script>
</head>
<body class="main">
	<%@include file="header.jsp"%>

	<div class="body">
		<div class="main_page">

			<div class="main_board">
				<c:forEach items="${boardlist }" var = "board" varStatus="status" begin="1" end="4">
					<fieldset class="board_list_${status.count}"
						onclick="location.href='/shinDTown/board/detail.jm?BOARD_NAME=${board.BOARD_NAME }'">
						<legend>${board.BOARD_NAME }</legend>
							<c:forEach items="${boardpostlist }" var = "boardpost" varStatus ="status">
								<c:if test="${board.BOARD_CODE  == boardpost.BOARD_CODE }">
									<ul class="board_title">
										<li>-${boardpost.POST_TITLE }</li>
									</ul>
								</c:if>
							</c:forEach>
					</fieldset>
				</c:forEach>
			</div>



			<div class="more_btn">
				<button
					onclick="location.href='/shinDTown/shinDTown/board/read.jm'">더보기</button>
				<button
					onclick="location.href='/shinDTown/shinDTown/board/create.jm'">새게시판</button>
			</div>

			<div class="git">
				<h1>git</h1>
			</div>


			<div class="calendar">

				<div class="calendar" id="calendar"></div>

				<div id="modal" class="modal">
					<div class="modalcontent">
						<div class="modal_header">
							<p id="modal_title">일정을 입력하세요.</p>
							<button type="button" class="btn btn-secondary"
								id="closeCalendar">닫기</button>
						</div>
						<div class="modal_body">
							<label class="title">일정 제목</label> <input type="text"
								value="${plan.plan_title}" class="form-control" id="plan_title"
								name="plan_title"> <label class="content">일정 내용</label>
							<input type="text" class="form-control" id="plan_content"
								name="plan_content"> <label class="start_date"
								class="col-form-label">시작 날짜</label> <input type="date"
								class="form-control" id="start_date" name="start_date">
							<label class="end_date" class="col-form-label">종료 날짜</label> <input
								type="date" class="form-control" id="end_date" name="end_date">

							<label id="color_choice">색상</label>

							<div class="color_choice">

								<input type="button" id="blue" class="color" value="#BFC8D7"
									onclick="color_choice(blue)" /> <input type="button"
									id="yellow" class="color" value="#EADB80"
									onfocus="color_choice(yellow)" /> <input type="button"
									id="green" class="color" value="#A2B59F"
									onfocus="color_choice(green)" /> <input type="button" id="pink"
									class="color" value="#F1BCAE" onfocus="color_choice(pink)" /> <input
									type="button" id="orange" class="color" value="#F6B99D"
									onfocus="color_choice(orange)" />

							</div>

						</div>

						<div class="modal_footer">
							<div class="add">
								<button type="button" class="btn btn-warning" id="addCalendar">추가</button>
							</div>
							<button type="button" class="btn btn-secondary"
								id="updateCalendar">수정</button>
							<button type="button" class="btn btn-secondary"
								id="deleteCalendar">삭제</button>
						</div>
					</div>
					<div class="modal_layer"></div>
				</div>
			</div>



			<div class="hot_board">
				<fieldset class="board_list_5">
					<legend>hot 게시판</legend>
					<div class="left">
						<ul class="board_title">
							<c:forEach items="${posttop }" var="post" begin="0" end="3">
								<li>${post.POST_TITLE }</li>
							</c:forEach>
						</ul>
					</div>
					<div class="right">
						<ul class="board_title">
							<c:forEach items="${posttop }" var="post" begin="4" end="7">
								<li>${post.POST_TITLE }</li>
							</c:forEach>
						</ul>
					</div>
				</fieldset>

			</div>

		</div>
	</div>

	<script>
		var color_f;
		function color_choice(color_code) {
			$("#blue").css("border", "none");
			$("#pink").css("border", "none");
			$("#orange").css("border", "none");
			$("#yellow").css("border", "none");
			$("#green").css("border", "none");
			
			color_f = $(color_code).val();
			
			$(color_code).css("border", "3px solid #558BCF");
			console.log(color_f);
		}

		function editCalendar() {
			var plan_title = $("#plan_title").val();
			var plan_content = $("#plan_content").val();
			var start_date = $("#start_date").val();
			var end_date = $("#end_date").val();
			var color = color_f;

			if (plan_title == null || plan_title == "") {
				alert("제목을 입력해주세요");
				return;

			} else if (plan_content == null || plan_content == "") {
				alert("내용을 입력해주세요.");
				return;

			} else if (start_date == "" || end_date == "") {
				alert("날짜를 입력해주세요.");
				return;

			} else if (moment(end_date).isBefore(start_date)) {
				alert("종료일이 시작일보다 이전입니다.");
				return;

			} else {

				var obj = {
					"plan_title" : plan_title,
					"plan_content" : plan_content,
					"start_date" : start_date,
					"end_date" : end_date,
					"color" : color,
					"id" : "${loginUser.user_id}"
				}

			}
			return obj;
		}

		document
				.addEventListener(
						'DOMContentLoaded',
						function() {

							var calendarEl = document
									.getElementById('calendar');

							var calendar = new FullCalendar.Calendar(
									calendarEl,
									{
										timeZone : 'UTC',
										initialView : 'dayGridMonth',
										locale : 'ko',

										headerToolbar : {
											center : 'addEventButton'
										},

										eventDataTransform : function(event) {
											// 일정출력시 하루 덜 나오는 문제 해결을 위해 date 다듬는 함수
											if (event.end != event.start) {
												var end_date = event.end;
												event.end = moment(end_date)
														.add(1, 'days')
														.format(
																moment.HTML5_FMT.DATE);
											}
											console.log(event);
											return event;
										},

										events : function(info,
												successCallback,
												failureCallback) {
											// 일정 가져오는 코드
											if (("${loginUser.user_id}").length > 0) {
												$
														.ajax({
															method : "GET",
															url : "${path}/view/calendarView/ReadPlan.com",
															data : {
																"id" : "${loginUser.user_id}"
															},
															success : function(
																	responseData) {
																var eventList = [];
																var events = eval("("
																		+ responseData
																		+ ")");
																if (events != null) {
																	$
																			.each(
																					events.plans,
																					function(
																							index,
																							element) {
																						console
																								.log(element);
																						eventList
																								.push({
																									id : element.plan_code,
																									title : element.plan_title,
																									start : element.start_date,
																									end : element.end_date,
																									color : element.color
																								});
																					});

																}
																successCallback(eventList);
															},
															error : function() {
																console
																		.log("error!");
															}
														});
											}
										},
										eventClick : function(data) {
											// 이벤트 클릭시 불러지는 detail보기 함수
											var plan_code = data.event._def.publicId;
											console.log(plan_code);

											$(".add").hide();
											$("#updateCalendar").show();
											$("#deleteCalendar").show();

											$
													.ajax({
														method : "GET",
														data : {
															"id" : "${loginUser.user_id}"
														},
														url : "${path}/view/calendarView/DetailPlan?plan_code="
																+ plan_code + ".com",
														success : function(
																responseData) {
															var plan = eval("("
																	+ responseData
																	+ ")");
															$("#plan_title")
																	.val(
																			plan.plan_title);
															$("#plan_content")
																	.val(
																			plan.plan_content);
															$("#start_date")
																	.val(
																			plan.start_date);
															$("#end_date")
																	.val(
																			plan.end_date);
															
															switch(plan.color){
															case "#BFC8D7":{
																$("#blue").css("border", "2px solid #558BCF");
																break;
															} 
															case "#F8DAE2":{
																$("#pink").css("border", "2px solid #558BCF");
																break;
															} 
															case "#F6B99D":{
																$("#orange").css("border", "2px solid #558BCF");
																break;
															} 
															case "#EADB80":{
																$("#yellow").css("border", "2px solid #558BCF");
																break;
															} 
															case "#A2B59F":{
																$("#green").css("border", "2px solid #558BCF");
																break;
															} 
																
															}

															//다시
															$("#color").val(plan.color);
															//값을 가져오고 modal에 출력!
															$("#modal")
																	.fadeIn();
														},
														error : function() {
															console
																	.log("error");
														}
													});

											$("#updateCalendar")
													.on(
															"click",
															function() {
																console
																		.log("list: "
																				+ plan_code);
																var obj = editCalendar();
																if (plan_code == undefined) {
																	console
																			.log("pass");
																	//이전에 누른 값이 합쳐져서 변경되고 있어서 처리
																} else {
																	$
																			.ajax({
																				method : "POST",
																				url : "${path}/view/calendarView/DetailPlan?plan_code="
																						+ plan_code+".com",
																				data : obj,
																				success : function(
																						responseData) {
																					alert("내용이 수정되었습니다.");
																					location
																							.reload();
																				},
																				error : function() {
																					console
																							.log("error");
																				}
																			});

																}
															});

											$("#closeCalendar").click(
													function() {

														$("#modal").fadeOut();
														$("#plan_title")
																.val("");
														$("#plan_content").val(
																"");
														$("#start_date")
																.val("");
														$("#end_date").val("");
														//다시

														$("#color").val("");
														plan_code = undefined;

													});

											$("#deleteCalendar")
													.on(
															"click",
															function() {
																if (plan_code != undefined) {
																	$
																			.ajax({
																				data : {
																					"id" : "${loginUser.user_id}"
																				},
																				url : "${path}/view/calendarView/DeletePlan?plan_code="
																						+ plan_code+".com",
																				success : function(
																						responseData) {
																					alert("삭제 되었습니다.");
																					location
																							.reload();
																				}
																			});
																}
															});

										},
										customButtons : {
											addEventButton : {
												text : "일정 추가",
												click : function(e) {

													e.stopPropagation();
													console.log("addEvent");

													$(".add").show();
													$("#updateCalendar").hide();
													$("#deleteCalendar").hide();

													$("#modal").fadeIn();

												}

											}
										},
										editable : true,
										displayEventTime : true,
										selectable : true,
										dayMaxEvents : true,

									});
							calendar.render();
						});
		//
		$("#addCalendar").on("click", function(e) {
			e.preventDefault();
			e.stopPropagation();
			console.log("addcalendar");
			console.dir(e);
			alert("add");
			var obj = editCalendar();
			$.ajax({
				method : "POST",
				url : "${path}/view/calendarView/CreatePlan.com",
				data : obj,
				success : function(responseData) {
					alert("일정이 등록되었습니다");
					location.reload();
				},
				error : function() {
					console.log("error");
				}
			});

		});
		$("#closeCalendar")
		.click(
				function() {
					console.log("close");
					$("#modal").fadeOut();
					
				});

	</script>

</body>
</html>
