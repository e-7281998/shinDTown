<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="path" value="${pageContext.request.contextPath }" scope="application"></c:set>
 
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
	grid-template-rows: 1fr;
	grid-template-columns: 0.3fr 1fr 4fr 500px 0.3fr;
	grid-template-areas: ". logo . bar_menu .";
	place-items : center center;
	padding-top : 10px;
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

hr {
	margin: 0px 0px 40px 0px;
}
a{
 text-decoration: none;
 color : black;
 cursor : pointer;
 }

#logout{
border: none;
background: none;
}
</style>

<!-- 세션에 로그인 한 사용자를 받아와서 존재할 경우 -> (게시판 보러가기, 쪽지확인, 내정보 확인하기로 가기!) -->

<div class="header"> 
	<div class="container">
		<div class="logo"><a href="/shinDTown/index.jsp">
		<img src="/shinDTown/view/img/logo.png">
		</a>
		</div>
		<div class="bar_menu">
		
		<c:if test="${sessionScope.loginUser == null}">
		<a href="/shinDTown/view/memberView/MemberSignUp.jsp"><h2 class="menu">회원가입</h2></a>
		<a href="/shinDTown/view/memberView/MemberLogin.jsp"><h2 class="menu">로그인</h2></a>
		</c:if>
		
		<c:if test="${sessionScope.loginUser != null}">
		<a href="/shinDTown/view/main.jsp"><h2 class="menu">홈</h2></a>
		<a href="/shinDTown/board/read.jm"><h2 class="menu">게시판</h2></a>
		<a href="/shinDTown/view/chatView/selectChatRoom.com"><h2 class="menu">채팅방</h2></a>
		<a href="/shinDTown/view/memberView/MemberUpdate.jsp"><h2 class="menu">마이페이지</h2></a>
		<a id="logout" href="/shinDTown/view/memberView/logout"><h2 class="menu">로그아웃</h2></a>
		</c:if>
		</div>
	</div>
</div>
