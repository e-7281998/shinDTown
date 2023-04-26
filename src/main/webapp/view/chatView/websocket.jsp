<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/jq/jquery-3.6.4.min.js"></script>
<style>
.chat_content {color:blue;}
.impress{ color:red;}
.whisper{ color: green;}
</style>
</head>
<body>
    <%@ include file="../header.jsp" %> 
<%-- 	<!-- 로그인한 상태일 경우와 비로그인 상태일 경우의 chat_id설정 -->
	<c:set var="username" value="${loginUser.user_name}" />
	
	<c:if test="${(user_name ne '') and !(empty user_name)}">
		<input type="hidden" value='${user_name}' id='chat_id' />
	</c:if>
	<!-- 로그인하지않은경우 -->
	<c:if test="${(user_name eq '') or (empty user_name)}">
		<input type="hidden" value="<%=session.getId().substring(0, 6) %>" id='chat_id' />
	</c:if>
	 --%>
	<!-- 채팅창 -->
	
	<div id="_chatbox" >
		<fieldset>
		
			<div id="messageWindow"></div>
			<br /> 
			<input id="insertMessage" type="text" onkeyup="enterkey()"/> 
			<input id="send" type="button" value="send" onclick="send()" />
			
		</fieldset>
	</div>
	
	
	<!-- 유진코드 -->
	<div class="body">
		<div class="chats">

			<div class="chat_able" id="chatroom" >
			
			<form action="selectChatRoom.com" method="get">
				<fieldset class="chat_field">
					<legend>접속중인 사람</legend>
					<ul>
						<c:forEach items="${memberList }" var="member" varStatus="status" >
							<c:if test="${user_name ne member.user_name}">
								<p>${member.user_name}</p>
							</c:if >
							</c:forEach>
						<!-- <li class="chat" value="1">전은정</li>session값 가져오기
						<li class="chat" value="2">양유진</li>
						<li class="chat" value="3">유지만</li>
						<li class="chat" value="4">이진경</li>
						<li class="chat" value="1">전은정</li>
						<li class="chat" value="2">양유진</li>
						<li class="chat" value="3">유지만</li>
						<li class="chat" value="4">이진경</li>
						<li class="chat" value="1">전은정</li>
						<li class="chat" value="2">양유진</li>
						<li class="chat" value="3">유지만</li>
						<li class="chat" value="4">이진경</li> -->
				
						
					</ul>
				</fieldset>
				</form>
			</div>
			<div class="chatlist" id="chatroom" >
			<!-- ajax 이용해서 서버로 갔다가 온다 . form을 사용할 필요 없음. form은 버튼을 눌러서 데이터를 가져올때 필요함 -->
			
				<fieldset class="chat_field">
			
					<legend>채팅방 목록</legend>
					
					<ul>
					
						<c:forEach items="${chatRoomList}" var="chatroom" >
							<li class="chat" value="1" >${chatroom.friend_name} <button class="chatbtn" value="${chatroom.chat_code}" >채팅하기
								<span id="unread">&hearts;</span>
							</button>
						
							<input type="hidden" id="${chatroom.chat_code}_code" value="${chatroom.chat_code}"></li>
							
						</c:forEach>
						<!-- <li class="chat" value="1">전은정</li>
						<li class="chat" value="2">양유진</li>
						<li class="chat" value="3">유지만</li>
						<li class="chat" value="4">이진경</li> -->
					</ul>
				</fieldset>
				

			</div>

			<div class="chatroom" id="chatroom" action="http://localhost:9090/shinDTown/view/chatView/readConnectRoom.com" method="get">

				<div class="text">

					
				</div>
				<form>
				<div class="typing">
						<textarea class="type_area"></textarea>
						<input type="button" class="send" value="전송"></button>
				</div>
				</form>

			</div>
		</div>
	</div>
	
<%-- 	<c:set var="chatImage" value="${path}/images/chat.png" />
	<c:set var="chatImage2" value="${path}/images/hide.png" />
	<img class="chat" src="${chatImage}" width="100" height="100"/> --%>
</body>
<!-- 말풍선아이콘 클릭시 채팅창 열고 닫기 -->
<script>
/* 	$(".chat").on({
		"click" : function() {
			if ($(this).attr("src") == "${chatImage}") {
				$(".chat").attr("src", "${chatImage2}");
				$("#_chatbox").css("display", "block");
			} else if ($(this).attr("src") == "${chatImage2}") {
				$(".chat").attr("src", "${chatImage}");
				$("#_chatbox").css("display", "none");
			}
		}
	}); */
	

	
	
</script>
<script>
	//var webSocket = new WebSocket('ws://localhost:9090/shinDTown/websocket');
	var webSocket = new WebSocket('ws://'+location.host+'/shinDTown/selectChatRoom.com');
	webSocket.onerror = function(event) {onError(event)};
	webSocket.onopen = function(event) {onOpen(event)};
	webSocket.onmessage = function(event) {onMessage(event)};	
	
	var num;


	
	console.log("me>>>>>"+me);
/* 	//var me=${user_code }; */
/* 	
	선택한 유저의 코드->user_code를 메시지와 함께 보내고 
	그 코드가 일치하면 받는걸로 */
	function onMessage(event) {
		//console.log(event.data);
		var message = event.data.split("|");
		var sender = message[0];
	
		var content = message[1];
		if (content == "") return; 
		var originalMessage = $("#messageWindow").html();
		var receiveCode= content.substr(0,1);
		
		//console.log("시작>>>>>>>>>>>");
		
		if(receiveCode == ${user_code}){
			//console.log("유저코드 입니다>>>>>>"+ ${user_code});
			
			if (content.match("!")) {
				$("#messageWindow").html( originalMessage
										+ "<p class='chat_content'><b class='impress'>"
										+ sender + " : " + content
										+ "</b></p>");
			} else {
			
				$("#messageWindow").html( originalMessage
								+ "<p class='chat_content'>" + sender
								+ " : " + content + "</p>");
			
		}
			}
		
/* 		if (content.match("/")) {
			if (content.match(("/" + $("#chat_code").val()))) {
				 
				var temp = content.replace("/" + $("#chat_code").val(),
						"(귓속말) :").split(":");
				console.log(temp);
				if (temp[1].trim() != "") {
					$("#messageWindow").html(originalMessage
									+ "<p class='whisper'>"
									+ sender
									+ content.replace("/"
											+ $("#chat_code").val(),
											"(귓속말) :") + "</p>");
				}
			} 
		} else {
			if(receiveCode == ${user_code}){
			if (content.match("!")) {
				$("#messageWindow").html( originalMessage
										+ "<p class='chat_content'><b class='impress'>"
										+ sender + " : " + content
										+ "</b></p>");
			} else {
			
				$("#messageWindow").html( originalMessage
								+ "<p class='chat_content'>" + sender
								+ " : " + content + "</p>");
			
		}
			}
		} */
		 
		
		
	}  
	function onOpen(event) {
		//$("#messageWindow").html("<p class='chat_content'>안녕하세요. 채팅에 참여하였습니다.</p>");
	} 
	function onError(event) {
		alert(event.data);
	}
	
	
	function send() {
		//console.log("============>>>>>>"+$(this).val());

		//console.log("==========> " + $("#insertMessage").val());
		/* 어떤 정보를 넣어주려고 작성 */
		//채팅하기 누른 사람의 chat_code값으로 넣어주기
		

		console.log("num>>>>>>"+num+"me>>>>>>>"+${user_code });
		$.ajax({
			url:"insertMessage.com",
			data:{"chat_code":num,"sender":${user_code },"message_data":$("#insertMessage").val()},
			success:function(data){
				alert(data+"성공!");
			},
			error:function(message){
				
			}
		})
		
		//String userId = (String)session.getAttribute("");
		  
		 
		  //var friendId =;
		  //String friendId = (String)session.getAttribute("userId");
		  
	
	//	console.log("webSocket.jsp userid"+userId);
		
		var message = $("#insertMessage").val() ;
		if (message != "") {
			$("#messageWindow").html($("#messageWindow").html() + "<p class='chat_content'>나 : "
							+ message + "</p>");
		}
		
		webSocket.send("${user_name}"+ "|" + message); 
		$("#insertMessage").val("");
	}
	//     엔터키를 통해 send함
	function enterkey() {
		if (window.event.keyCode == 13) {
			send();
		}
	}
	//     채팅이 많아져 스크롤바가 넘어가더라도 자동적으로 스크롤바가 내려가게함
/* 	window.setInterval(function() {
		var elem = $('#messageWindow');
		elem.scrollTop = elem.scrollHeight;
	}, 0); */
	




	$(function(){
		$("#_chatbox").hide();
	})
	
	$(".chatbtn").on("click",function(){
		
		//클릭시 send와 enter가 나온다.
		//$("#_chatbox").show();
		$("#_chatbox").toggle(); // show -> hide , hide -> show
		
	})
	
/* $(function(){
	$.ajax({
		url:"selectChatRoom.jk",
		success:function(aa){
			alert("성공!"+aa);
		}
	})
})  */
 
$(".chatbtn").on("click",function(){

 
	//var chat_code = "#"+$(this).val()+"_code";
	//console.log("chat_code>>>>>>>>"+ chat_code);
	num= $(this).val();
	console.log("num>>>>."+num);
	$.ajax({
		url:"readConnectRoom.com",
		data:{"chat_code" : num},
		success:function(responseData){
			var events=eval("(" +responseData+")");
// 			console.log(responseData);
// 			console.log(events.message);
				$("#chatroom").html("");
				$.each(
						events.message,
						function(index, element){
							//console.log(element.message_data); 
							
							if("${loginUSer.userId}" == $.trim(element.sender)){
								$("#chatroom").append("<div class='me'><p>" + element.message_data + "</p> </div>");

							}else{
								$("#chatroom").append("<div class='other'><p>" + element.message_data + "</p> </div>");
							}
								
						});
			/*}); */
			
			}
	}) 
})

	/* 읽지 않은 메시지 표시 */
	$(function(){
		
	})
	function getUnread(){
		$.ajax({
			type:"POST",
			url:"selectNotReadMessage.com",
			data:{"user_code": encodeURIComponent(${user_code})},
			success:function(result){
				if(result >=1){
					showUnread(result);
				}else showUnread('');
			}
		})
	}
	function getInfiniteUnread(){
		setInterval(function(){
			getUnread();
		}, 4000);
	}
	
	function showUnread(result){
		$('#unread').html(result);
	}
	
	$(document).ready(function(){
		getInfiniteUnread();
	});
	
	
	

	</script>

</body>
</html>