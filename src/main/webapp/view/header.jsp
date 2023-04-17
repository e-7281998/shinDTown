<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
.header {
	display: flex;
	justify-content: center;
}

.container {
	display: grid;
	width: 80%;
	grid-template-rows: 1fr;
	grid-template-columns: 0.3fr 1fr 3fr 2fr 0.3fr;
	grid-template-areas: ". logo . bar_menu .";
	place-items : center center;
	padding : 20px 0;
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

hr {
	margin: 0px 0px 40px 0px;
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
			<h2 class="menu">회원가입</h2>
			<h2 class="menu"></h2>
			<h2>로그인</h2>
		</div>
	</div>
</div>

<hr />

