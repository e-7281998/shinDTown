<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" session="true"%>
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
	<div id="_chatbox" >
 <div class="chatroom" id="chatroom"
action="http://localhost:9090/shinDTown/view/chatView/readConnectRoom.com"
method="get">
 <div class="text"></div>
 </div>
 <fieldset>
 <div id="messageWindow"></div>
 <br /> <input id="insertMessage" type="text" onkeyup="enterkey()" />
 <input id="send" type="button" value="send" onclick="send()" />
 </fieldset>
	</div>
	<!-- 유진코드 -->
	<div class="body">
 <div class="chats">
 <div class="chat_able" id="chat_able">
 <fieldset class="chat_field">
 <legend>접속중인 사람</legend>
 <ul>
 <c:forEach items="${memberList }" var="member" varStatus="status" >
 <c:if test="${user_name ne member.user_name}">
 <li><span>${member.user_name}</span>
 <button class="chatbtn" onclick="connectChat(${member.user_code })"
value="${member.user_code}">채팅하기</button>
 </li>
 <!-- <span id="unread">&hearts;</span> -->
 </c:if>
 </c:forEach>
 </ul>
 </fieldset>
 </div>
 <div class="chatlist" id="chatlist">
 <fieldset class="chat_field">
 <legend>채팅방 목록</legend>
 <ul>
 <c:forEach items="${chatRoomList}" var="chatroom">
 <li class="chat" value="${chatroom.chat_code}">${chatroom.friend_name}
 <button class="chatbtn"
onclick="readChat(${chatroom.chat_code},${chatroom.friend_code})"
value="${chatroom.chat_code}">
 채팅하기
 <span id="unread" style="display:none;">&hearts;</span>
 </button>
 </li>
 </c:forEach>
 </ul>
 </fieldset>
 </div>
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
	/*
 선택한 유저의 코드->user_code를 메시지와 함께 보내고
 그 코드가 일치하면 받는걸로 */
	var sender;	
	var content;
	function onMessage(event) {
	
 var message = event.data.split("|");
 sender = message[0];
 var idx = message[0].length;
 /* for(i=0; i<message.length; i++){
 if(message[i]=='|'){
 idx=i;
 }
 } */
 content = message[1];
 if (content == "") return;
 var originalMessage = $("#messageWindow").html();
 var receiveCode = message[0];
 if(receiveCode == ${user_code}){
 $("#messageWindow").html( originalMessage
 + "<p class='chat_content'>" + sender
 + " : " + content + "</p>");
 getMessage(num, sender, content);
 }
	}
	//보낸 메시지를 받는함수
	function getMessage(num, sender, content){
 $.ajax({
 url:"insertMessage.com",
 data:{"chat_code":num,"sender":sender,"message_data":content},
 success:function(data){
 alert(data+"성공!");
 },
 error:function(message){
 }
	})	
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
	function readChat(num2, f_code2){
 f_code = f_code2;
 num = num2;
 $.ajax({
 url:"readConnectRoom.com",
 data:{"chat_code" : num2},
 success:function(responseData){
 var events=eval("(" +responseData+")");
 $("#chatroom").html("");
 $.each(
 events.message,
 function(index, element){
 if("${loginUser.user_code}" == $.trim(element.sender)){
 $("#chatroom").append("<div class='me' '><p>"+element.sender+":"+ element.message_data + "</p> </div>");
 }else{
 $("#chatroom").append("<div class='other'><p>" +element.sender+":"+ element.message_data + "</p> </div>");
 }
 });
 }
	})
	}
	//채팅방이 없으면 만들기
	function connectChat(user_2_code){
 $("#_chatbox").show();
 f_code = user_2_code;//전송시 상대방 코드 저장
 num = user_2_code;
 $.ajax({
 url:"makeNewChatRoom.com",
 data:{"user_2_code" : user_2_code, "user_1_code": ${user_code}},
 success:function(responseData){
 f_code = user_2_code;
 num = responseData; //챗코드임
 }
 })
	}
	
	/* 읽지 않은 메시지 표시 */
	$(function(){
 getUnread();
	})
	function getUnread(){
	$.ajax({
 type:"POST",
 url:"selectNotReadMessage.com",
	
 data:{"user_code": ${user_code}},
 success:function(result){
 var jsonres;
 jsonres = JSON.parse(result);
 //li에 있는 id값을 가져와야함
	
 //chat_code랑 message_open 여부 구하기
 for(var i=0; i<jsonres.message.length; i++){
 var chat_code = jsonres.message[i].chat_code;
 var message_open = jsonres.message[i].message_open;
 //list[0].value가 chat_code를 의미하므로 이를 이용해서 구하기
 for(var j=0; j<jsonres.message.length; j++){
 if(list[j].value==chat_code && !message_open){
 //읽지않은 메시지가 존재함.
 list[j].innerHTML+="&hearts;";
 }
 }
 }
 }
	})
	}
	function showUnread(result){
	consol.log("showUnread>>>>>");
	$('#unread').html(result);
	}
	</script>
	</body>
</html>