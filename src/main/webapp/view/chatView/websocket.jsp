<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShinDTown</title>
<script src="${pageContext.request.contextPath}/jq/jquery-3.6.4.min.js"></script>
<style>
.chat_content {
	color: blue;
}

.impress {
	color: red;
}

.whisper {
	color: green;
}
</style>
</head>
<body>
	<%@ include file="../header.jsp"%>
	<div id="_chatbox" style="display: none">
		<div class="chatroom" id="chatroom"
			action="http://localhost:9090/shinDTown/view/chatView/readConnectRoom.com"
			method="get">
			<div class="text"></div>
		</div>
		<fieldset>
			<div id="messageWindow">!!!!</div>
			<br /> <input id="insertMessage" type="text" onkeyup="enterkey()" />
			<input id="send" type="button" value="send" onclick="send()" />
		</fieldset>
	</div>
	<!-- 유진코드 -->
	<div class="body">
		<div class="chats">
			<div class="chat_able" id="chat_able">
				<form action="selectChatRoom.com" method="get">
					<fieldset class="chat_field">
						<legend>접속중인 사람</legend>
						<ul>
							<c:forEach items="${memberList }" var="member" varStatus="status" >
								<c:if test="${user_name ne member.user_name}">
									<p>${member.user_name}</p>
									<button class="chatbtn"
											onclick="button(${chatroom.chat_code},${chatroom.friend_code})"
											value="${chatroom.chat_code}">
											채팅하기
											<!-- <span id="unread">&hearts;</span> -->
										</button>
								</c:if>
							</c:forEach>
						</ul>
					</fieldset>
				</form>
			</div>
			<div class="chatlist" id="chatlist">
				<!-- ajax 이용해서 서버로 갔다가 온다 . form을 사용할 필요 없음. form은 버튼을 눌러서 데이터를 가져올때 필요함 -->
				<fieldset class="chat_field">
					<legend>채팅방 목록</legend>
					<ul>
						<c:forEach items="${chatRoomList}" var="chatroom">
							<li class="chat" value="${chatroom.chat_code}">${chatroom.friend_name}
								*** ${chatroom.friend_code}
								<button class="chatbtn"
									onclick="button(${chatroom.chat_code},${chatroom.friend_code})"
									value="${chatroom.chat_code}">
									채팅하기
									<!-- <span id="unread">&hearts;</span> -->
								</button> <input type="hidden" id="${chatroom.chat_code}_code"
								value="${chatroom.chat_code}">
							</li>
						</c:forEach>
					</ul>
				</fieldset>
			</div>
			<!-- <div class="chatroom" id="chatroom" action="http://localhost:9090/shinDTown/view/chatView/readConnectRoom.com" method="get">
 <div class="text" >
 </div>
 </div> -->
		</div>
	</div>
</body>
<script>
	var webSocket = new WebSocket('ws://'+location.host+'/shinDTown/selectChatRoom.com');
	webSocket.onerror = function(event) {onError(event)};
	webSocket.onopen = function(event) {onOpen(event)};
	webSocket.onmessage = function(event) {onMessage(event)};
	var num;
	var f_code;
	function onMessage2(event) {
	console.log(event.data);
	var message = event.data.split("|");
	var sender = message[0];
	var content = message[1];
	console.log(content)
	if (content == "") return;
	var originalMessage = $("#messageWindow").html();
	var receiveCode= content.substr(0,1);
	//console.log("시작>>>>>>>>>>>");
	//if(receiveCode == ${user_code}){
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
	// }
	}
	/*
		선택한 유저의 코드->user_code를 메시지와 함께 보내고
		그 코드가 일치하면 받는걸로 */
		
	function onMessage(event) {
	//console.log(event.data);
		var message = event.data.split("|");
		var sender = message[0];
		var idx = message[0].length;
		//var idx=0;
		console.log("event.data:"+event.data);
		/* for(i=0; i<message.length; i++){
		 if(message[i]=='|'){
		 idx=i;
		 }
		 } */
		var content = message[1];
		if (content == "") return;
		var originalMessage = $("#messageWindow").html();
		console.log("idx>>>>>>>"+idx);
		//var receiveCode= content.substr(0,idx);
		//var receiveCode = content.substr(0,idx);
		//var receiveCode = message[0];
		var receiveCode = message[0];
		console.log("content:"+content);
		console.log("receiveCode:"+receiveCode);
		console.log("내 코드:"+${user_code});
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
	}
	function onOpen(event) {
	//$("#messageWindow").html("<p class='chat_content'>안녕하세요. 채팅에 참여하였습니다.</p>");
	}
	function onError(event) {
	alert(event.data);
	}
	function send() {
	$.ajax({
	url:"insertMessage.com",
	data:{"chat_code":num,"sender":${user_code },"message_data":$("#insertMessage").val()},
	success:function(data){
	alert(data+"성공!");
	},
	error:function(message){
	}
	})
	var message = $("#insertMessage").val() ;
	if (message != "") {
	$("#messageWindow").html($("#messageWindow").html() + "<p class='chat_content'>나 : "
	+ message + "</p>");//추가한 부분
	}
	webSocket.send(f_code+ "|" + message);
	$("#insertMessage").val("");
	}
	// 엔터키를 통해 send함
	function enterkey() {
	if (window.event.keyCode == 13) {
	send();
	}
	}
	// 채팅이 많아져 스크롤바가 넘어가더라도 자동적으로 스크롤바가 내려가게함
	/* window.setInterval(function() {
	 var elem = $('#messageWindow');
	 elem.scrollTop = elem.scrollHeight;
		}, 0); */
	/* $(function(){
	 $("#_chatbox").hide();
		}) */
	/* $(".chatbtn").on("click",function(){
	 //클릭시 send와 enter가 나온다.
	 //$("#_chatbox").show();
	 $("#_chatbox").toggle(); // show -> hide , hide -> show
		}) */
	function button(num2, f_code2){
	//$(".chatbtn").on("click",function(){
	alert("눌렷니?");
	$("#_chatbox").toggle();
	f_code = f_code2;
	/* if($("#chatroom ").css("display") == "block") $("#chatroom ").css("display","block");
		else $("#chatroom").css("display","none"); */
	num = num2;
	console.log(num2);
	console.log("f_code:"+f_code);
	//var chat_code = "#"+$(this).val()+"_code";
	//console.log("chat_code>>>>>>>>"+ chat_code);
	//num= $(this).val();
	console.log("num>>>>."+num2);
	$.ajax({
	url:"readConnectRoom.com",
	data:{"chat_code" : num2},
	success:function(responseData){
	var events=eval("(" +responseData+")");
	$("#chatroom").html("");
	$.each(
	events.message,
	function(index, element){
	if("${loginUSer.userId}" == $.trim(element.sender)){
	$("#chatroom").append("<div class='me'><p>" + element.message_data + "</p> </div>");
	}else{
	console.log("chatroom!!!!!!!!!!");
	$("#chatroom").append("<div class='other'><p>" + element.message_data + "</p> </div>");
	}
	});
	}
	})
	//})
	}
	/* 읽지 않은 메시지 표시 */
	$(function(){
	getUnread();
	})
	function getUnread(){
	$.ajax({
	type:"POST",
	url:"selectNotReadMessage.com",
	//data:{"user_code": encodeURIComponent(${user_code})},
	data:{"user_code": ${user_code}},
	success:function(result){
	/* if(result.size() >=1){
	 console.log("result>>>>>>>"+result);
	 showUnread(result);
	 }else showUnread(''); */
	var jsonres;
	jsonres = JSON.parse(result);
	//li에 있는 id값을 가져와야함
	var list = $(".chat_field li");
	//console.log("22length>>"+jsonres.message.length);
	//chat_code랑 message_open 여부 구하기
	for(var i=0; i<jsonres.message.length; i++){
	var chat_code = jsonres.message[i].chat_code;
	var message_open = jsonres.message[i].message_open;
	console.log("list[i].value>>>>>>>"+list[i].value+" "+"chat_code>>>>>>"+chat_code+" message_open>>"+message_open);
	//list[0].value가 chat_code를 의미하므로 이를 이용해서 구하기
	for(var j=0; j<jsonres.message.length; j++){
	if(list[j].value==chat_code && !message_open){
	//읽지않은 메시지가 존재함.
	list[j].innerHTML+="&hearts;";
	}
	}
	}
	//console.log(list[0].value);
	/* for(var i=0; i<Object.keys("message").length; i++){
	 console.log();
	 } */
	}
	})
	}
	/* {"message":[{"message_data":null,"message_code":0,
	 "sender":0,"message_create":null,"message_open":false},
	 {"message_data":null,"message_code":0,"sender":0,
	 "message_create":null,"message_open":false},
	 {"message_data":null,"message_code":0,"sender":0,
	 "message_create":null,"message_open":false}]} */
	/*
		function getInfiniteUnread(){
	 setInterval(function(){
	 getUnread();
	 }, 4000);
		} */
	function showUnread(result){
	consol.log("showUnread>>>>>");
	$('#unread').html(result);
	}
	/* $(document).ready(function(){
	 getInfiniteUnread();
		});
	 */
	</script>
	</body>
</html>