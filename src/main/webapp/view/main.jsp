<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="path" value="${pageContext.request.contextPath }"
	scope="application"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>ShinDTown</title>
<link rel="shortcut icon" type="image/x-icon" href="${path}/view/img/logo.png">
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.5/index.global.min.js'></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script src="../jq/jquery-3.6.4.min.js"></script>
<link href="${path}/view/main.css" rel="stylesheet" />
<script>
</script>
</head>
<body class="main">
	<%@include file="header.jsp"%>

	<div class="body">
		<div class="main_page">

			<div class="main_board">
				<c:forEach items="${boardlist }" var = "board" varStatus="status" begin="1" end="4">
					<fieldset class="board_list_${status.count}"
						onclick="location.href='/shinDTown/board/detail.com?BOARD_NAME=${board.BOARD_NAME }'">
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
					onclick="location.href='/shinDTown/board/read.com'">더보기</button>
				<button
					onclick="location.href='/shinDTown/board/create.com'">새게시판</button>
			</div>

			<div class="git">
				<div class="git_title">
					<h3 id="user_git_id">GIT 아이디를 등록해주세요</h3>
				</div>
			<div class="git_edit">
				<form>
			<input type="text" id="git_id" class="id fom" placeholder="git 아이디" />
			<input type="button" id="git_register" class="submit" value="등록하기">
			<input type="button" id="git_update" class="submit" value="수정하기">
			</form>
				</div>
			
				<div class="git_body">
					<div class="git_img">
						<img id="grass" src="${path}/view/img/git.png">
					</div>
						<ul id="repos" class="git_list"> 
						</ul>
				</div>
				<div class="tabkey">
					<button class="repo_check" onclick="setRepo()" id="repo" style="display: none;">REPO CHECK</button>
				</div>
			</div>
			
			<div id="modal2" class="modal2">
					<div class="modalcontent2">
						<div class="modal_header2">
							<p id="modal_repo">commit 목록</p>
							<button type="button" class="btn btn-secondary"
								id="close_repo">닫기</button>
						</div>
						<div class="modal_body2"id ="modal_body2">

						</div>
						</div>
					<div class="modal_layer"></div>
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
									onclick="color_choice(blue)" /> 
									
									<input type="button"
									id="yellow" class="color" value="#EADB80"
									onfocus="color_choice(yellow)" /> 
									
									<input type="button"
									id="green" class="color" value="#A2B59F"
									onfocus="color_choice(green)" /> 
									
									<input type="button" id="pink"
									class="color" value="#F8DAE2" onfocus="color_choice(pink)" /> 
									
									<input
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
		}

		function editCalendar() {
			var plan_title = $("#plan_title").val();
			var plan_content = $("#plan_content").val();
			var start_date = $("#start_date").val();
			var end_date = $("#end_date").val();
			var color = color_f;

			if (plan_title == null || plan_title == "") {
				swal("입력 필요", "제목을 입력해주세요.", 'info').then(function(){
					return;                 
				})

			} else if (plan_content == null || plan_content == "") {
				
				swal("입력 필요", "내용을 입력해주세요.", 'info').then(function(){
					return;                 
				})

			} else if (start_date == "" || end_date == "") {
				
				swal("입력 필요", "날짜를 입력해주세요.", 'info').then(function(){
						return;                 
				})

			} else if (moment(end_date).isBefore(start_date)) {
				
				swal("확인 필요", "종료일이 시작일보다 이전입니다.", 'warning').then(function(){
						return;                 
				})

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
		
		var gitId;

		document.addEventListener('DOMContentLoaded', function() {
			
			$("#git_register").hide();
			$("#git_update").hide();
			
			$.ajax({
				url:"${path}/view/gitView/getGitId.com",
				data: {"user_code":${user_code}},
				success : (responseData) => {
					gitId = responseData;
					$("#grass").show();
					$("#repo").show();
					var gitBtn = $("#git_register")
					if(!(responseData == -1)){
 						$("#git_update").show(); 
						getRepos(responseData);
					}else{
 						$("#git_register").show();
					}
				},
				error:(message)=>{
					console.log(message);
				}
			})
			

			var calendarEl = document.getElementById('calendar');

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
											if (event.end != event.start) {
												var end_date = event.end;
												event.end = moment(end_date)
														.add(1, 'days')
														.format(
																moment.HTML5_FMT.DATE);
											}
											return event;
										},

										events : function(info,
												successCallback,
												failureCallback) {
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

											var plan_code = data.event._def.publicId;

											$(".add").hide();
											$("#updateCalendar").show();
											$("#deleteCalendar").show();

											$.ajax({
														method : "GET",
														data : {
															"id" : "${loginUser.user_id}"
														},
														url : "${path}/view/calendarView/DetailPlan.com?plan_code="
																+ plan_code,
														success : function(
																responseData) {
															var plan = eval("("
																	+ responseData
																	+ ")");
															$("#plan_title")
																	.val(plan.plan_title);
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
																$("#blue").css("border", "3px solid #558BCF");
																break;
															} 
															case "#F8DAE2":{
																$("#pink").css("border", "3px solid #558BCF");
																break;
															} 
															case "#F6B99D":{
																$("#orange").css("border", "3px solid #558BCF");
																break;
															} 
															case "#EADB80":{
																$("#yellow").css("border", "3px solid #558BCF");
																break;
															} 
															case "#A2B59F":{
																$("#green").css("border", "3px solid #558BCF");
																break;
															} 
																
															}

															
															$("#color").val(plan.color);
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

																} else {
																	$
																			.ajax({
																				method : "POST",
																				url : "${path}/view/calendarView/DetailPlan.com?plan_code="
																						+ plan_code,
																				data : obj,
																				success : function(
																						responseData) {
																					swal("일정 수정", "일정이 수정되었습니다!", 'success').then(function(){
																						location.reload();                 
																					})
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
																				url : "${path}/view/calendarView/DeletePlan.com?plan_code="
																						+ plan_code,
																				success : function(
																						responseData) {
																					swal("일정삭제!", "일정이 삭제되었습니다!", 'warning').then(function(){
																						location.reload();                 
																					})
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
			var obj = editCalendar();
			$.ajax({
				method : "POST",
				url : "${path}/view/calendarView/CreatePlan.com",
				data : obj,
				success : function(responseData) {
					swal("일정등록!", "새로운 일정이 등록되었습니다!", 'success').then(function(){
						location.reload();                 
					})
				},
				error : function() {
					console.log("error");
				}
			});

		});
		$("#closeCalendar")
		.click(
				function() {
					$("#modal").fadeOut();
					
				});
		
		var sortRepo;
		async function getRepos(gitId){
			//gitAPI
			//const url = "http://api.github.com"; 
			//유저 정보 가져옴
			//const url = "https://api.github.com/users/e-7281998";
			
			//git 잔디 불러오기
			$("#grass").attr( "src", "https://ghchart.rshah.org/558BCF/"+gitId);
			//개인 repo 가져옴
 			const repoUrl = "https://api.github.com/users/"+gitId+"/repos";
			const repoRresponse = await fetch(repoUrl);
			const repoResult = await repoRresponse.json();
			 
			$("#user_git_id").text(gitId);
			
			//repo명, 바로가기 링크 걸기
			var repoUl = document.getElementById("repos");
			var repoArr = [];
			repoUl.innerHTML="";
			for(var i=0; i<repoResult.length; i++){
 				var repoName = repoResult[i].name;
 				var repoLink = repoResult[i].html_url; 
				var repoDate = repoResult[i].pushed_at.slice(0,10);
				
				var repo = {"repoName":repoName,"repoLink":repoLink,"repoDate":repoDate};
				repoArr[i] = repo;
 			}
			
 			if(Object.keys(repoArr).length > 1){
				sortRepo = repoArr.sort((repo1, repo2) => { 
					if(repo1.repoDate > repo2.repoDate) return -1;
					if(repo1.repoDate < repo2.repoDate) return 1;
					return 0; 
				})
			}else{
				sortRepo = repoArr;
			}
			 
			//repo 화면에 부착하기
			
			for(var i=0; i<Math.min(5, sortRepo.length); i++){
				console.log(gitId);
				repoUl.innerHTML += "<li class='repo' style='list-style: none'><div class='list'><span class='repo_title' id='"+i
				+"_repo' onclick='getCommit("+i+")'>" +sortRepo[i].repoName+"</span><a href="+sortRepo[i].repoLink+" target='_blank'>보러가기</a></div></li>"
			}
	 		
 		}
		
		async function getCommit(repo_code){
			
			var gitid = $("#user_git_id").text();
			console.log(gitid);
			$("#modal2").fadeIn();
			
			var repoLl = document.getElementById("modal_body2") ; 
	
				const commitUrl = "https://api.github.com/repos/"+$.trim(gitid)+"/"+sortRepo[repo_code].repoName+"/commits";
				const commitResponse = await fetch(commitUrl);
				const commitResult= await commitResponse.json();

				
				var commitUl = document.createElement("ul");
				commitUl.classList.add("commits");
				 
				for(var i= 0; i<Math.min(5, Object.keys(commitResult).length); i++){
					var msg = commitResult[i].commit.message;
 					var data = commitResult[i].commit.author.date.replace("T"," ").slice(0,16);
 					
 					commitUl.innerHTML += "<li class='commit'><span>"+msg+"</span><span>"+data+"</span></li>"; 
 					console.log(msg);
 					
				}  
				repoLl.append(commitUl);
				
		}
 		//gitID 등록하기
		$("#git_register").on("click", () => {
			$.ajax({
				url:"${path}/view/gitView/gitRegister.com",
				data: {"git_id":$("#git_id").val()},
				success : (responseData) => {
					if(responseData == 1){
 						$("#git_update").show();
						$("#git_register").hide();
						$("#grass").show();
						$("#repo").show();
						getRepos($("#git_id").val());
					}else{
						swal("실패!", "Git ID 등록이 실패하였습니다, 다시 시도해주세요","error");
					}
				},
				error:(message)=>{
					console.log(message);
				}
			})
		})
		
		//gitID 수정하기
		$("#git_update").on("click", () => {
			$.ajax({
				url:"${path}/view/gitView/gitUpdate.com",
				data: {"git_id":$("#git_id").val()},
				success : (responseData) => {
					if(responseData == 1){
						swal("성공", "Git ID가 수정되었습니다","success").then(function(){
							getRepos($("#git_id").val());
							
						})
					}else{
						swal("실패!", "Git ID 수정이 실패하였습니다, 다시 시도해주세요","error");;
					}
				},
				error:(message)=>{
					console.log(message);
				}
			})
		})
		
		var toggle = 1;
		function setRepo(){
			if(toggle){
				$("#grass").hide();
	 			$(".git_list").show();
	 			toggle = 0;
			}else{
				$("#grass").show();
	 			$(".git_list").hide();
	 			toggle = 1;
			}
 		}
 		
		$("#close_repo").on("click", function(){
			$("#modal2").fadeOut();
			$(".commit").remove();
			
		})

	</script>

</body>
</html>
