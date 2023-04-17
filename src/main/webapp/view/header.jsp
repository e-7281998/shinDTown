<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<style>

   .header{
   display: flex;
   width : 90%;
   align-items: flex-end;
   }
   #logo{
   padding-left: 12%;
   width: 80%;
   height: 80%;
   }
  .bar_menu {
  	display: inline-flex;
  	width : 90%;
  	justify-content: end;
  	flex-direction: row;
  	align-items: flex-end;
  }
  .bar_menu h2{
  padding : 10px;
  }
  hr{
  margin : 0px 0px 40px 0px;
  }
  
</style>

<!-- 세션에 로그인 한 사용자를 받아와서 존재할 경우 -> (게시판 보러가기, 쪽지확인, 내정보 확인하기로 가기!) -->

<div class="header">
<div id="logo">
  <a>로고자</a>
  </div>
  <div class="bar_menu">
    <h2>회원가입</h2>
    <h2></h2>
    <h2>로그인</h2>
  </div>
</div>
<hr />

