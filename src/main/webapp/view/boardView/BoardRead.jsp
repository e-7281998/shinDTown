<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="path" value="${pageContext.request.contextPath }" scope="application"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${path}/view/boardView/board.css" rel="stylesheet" />
</head>
<body class="main">
	<%@include file="../header.jsp"%>

	<div class="body">

		<div class="board_page">
			<div class="boards">
				<c:forEach items="${boardlist }" var = "board" begin="1" end="4" >
					<fieldset class="board_list" onclick="location.href='BoardDetail.jsp'">
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
			
				
				<c:forEach items="${boardtop }" var = "board" varStatus ="status">
				<fieldset class="board_list" onclick="location.href='BoardDetail.jsp'">
					<legend> <c:out value="${board.BOARD_NAME }"/></legend>
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

			<div class="find">
				<div class="search">
					<form class="search_form"  method="post" action = "/shinDTown/board/read.do">
						<input type="text" name="search" class="search_title"
							placeholder="게시판 이름을 입력해 주세요 "> <input type="button"
							class="search_btn" value="찾기">
					</form>
				</div>

				<div class="list">
					<ul class="lists">
					<c:forEach items="${boardserch }" var = "boardser" varStatus ="status">
						<li>${boardser }</li>
						<hr class="line"/>
					</c:forEach>	
					</ul>
				</div>

				<div class="create_board">
					<button class="new_board" onclick="location.href='BoardCreate.jsp'">새
						게시판 만들기</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>