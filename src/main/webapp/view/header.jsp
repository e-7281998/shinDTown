<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="path" value="${pageContext.request.contextPath }" scope="application"></c:set>
 
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<style>

@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400&display=swap');
*{
font-family: 'Noto Sans KR', sans-serif;
}

button{
	cursor : pointer;
}
input[type="submit"]{
	cursor : pointer;
}
input[type="button"]{
	cursor : pointer;
}

fieldset{
	cursor : pointer;
}


.header {
	display: flex;
	justify-content: center;
}

.container {
	display: grid;
	width: 80%;
	grid-template-rows: 0.8fr;
	grid-template-columns: 0.3fr 1fr 4fr 500px 0.3fr;
	grid-template-areas: ". logo . bar_menu .";
	place-items : center center;
	padding-top : 5px;
}

.logo {
	grid-area: logo;
	justify-item: center;
	align-content: center;
	background-image : url('img/logo.png');
}

.bar_menu {
	grid-area: bar_menu;
	display : flex;
}
.menu{
margin : auto 10px;
}
a{
text-decoration: none;
}

a{
 text-decoration: none;
 color : black;
 cursor : pointer;
 }
 
 .header_line{
 	width: 90%;
 	box-shadow: 0 0 4px #558BCF;
 }

#logout{
border: none;
background: none;
}
</style>

<div class="header"> 
	<div class="container">
		<div class="logo"><a href="/shinDTown/board/main.com">
		<img src="/shinDTown/view/img/logo.png">
		</a>
		</div>
		<div class="bar_menu">
		
		<c:if test="${sessionScope.loginUser == null}">
		<a href="/shinDTown/view/memberView/MemberSignUp.com"><h2 class="menu">회원가입</h2></a>
		<a href="/shinDTown/view/memberView/MemberLogin.com"><h2 class="menu">로그인</h2></a>
		</c:if>
		
		<c:if test="${sessionScope.loginUser != null}">
		<a href="/shinDTown/board/main.com"><h2 class="menu">홈</h2></a>
		<a href="/shinDTown/board/read.com"><h2 class="menu">게시판</h2></a>
		<a href="/shinDTown/view/chatView/selectChatRoom.com"><h2 class="menu">채팅방</h2></a>
		<a href="/shinDTown/view/memberView/MemberUpdate.com"><h2 class="menu">마이페이지</h2></a>
		<a id="logout" href="/shinDTown/view/memberView/logout.com"><h2 class="menu">로그아웃</h2></a>
		</c:if>
		</div>
	</div>
</div>
<hr class="header_line"/>


<script>

$(function(){
	$('input[type="text"]').keydown(function() {
		  if (event.keyCode === 13) {
		    event.preventDefault();
		  };
		});
})
</script>