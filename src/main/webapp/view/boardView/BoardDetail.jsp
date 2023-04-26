<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="path" value="${pageContext.request.contextPath }" scope="application"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${path}/view/boardView/boardDetail.css" rel="stylesheet" />
<script src="${path}/jq/jquery-3.6.4.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body class="main">
	<%@include file="../header.jsp"%>
	<div class="body">
		<div class="board_detail">
			<div class="board_title">
				<h1>${board_name }</h1>
			</div>
			<div class="board_list">
				<ul class="post_title">
				<c:forEach items="${pclist }" var="post" varStatus ="status">
						<li id="${status.count}" value="${status.count}"> <span>${post.POST_TITLE }</span> [좋아요:<p class="like" id="${status.count}likes">${post.POST_LIKES }</p>] [댓글:<p class="com_count">${post.POST_COMS }개</p>]</li>
						<input type="hidden" id="${status.count}create" value="${post.POST_CREATE }">
						<input type="hidden" id="${status.count}content" value="${post.POST_CONTENT }">
						<%-- <input type="hidden" id="${status.count}likes" value="${post.POST_LIKES}"> --%>
						<input type="hidden" id="${status.count}code" value="${post.POST_CODE }">
				</c:forEach>
					</ul>
				<div class="posting">
					<button class="posting_btn"
						onclick="location.href='/shinDTown/post/create.com?board_name=${board_name}'">글쓰기</button>
				</div>
			</div>

			<div class="post_detail" id="post_detail" name ="post_detail" value="${post.POST_CODE }">
				<div class="post">

					<div class="title">
						<h3>제목 : </h3>
					</div>
					
					<div class="date" id ="date">
						<h3>날짜: </h3>
					</div>

					<div class="content">
						<p>내용:</p>
					</div>
					
					<div class="like">
						<p class="like_p">좋아유:</p>
						<p class="like_p" id="post_like"></p>
					</div>
					
					<button id="post_likebtn">좋아</button>

				</div>

				<div class="comment">
					<form class="create_comment">
						<input type="hidden" id="post_code" name="post_code">
						<input type="text" class="comment" id ="com_comment" name="com_comment" > 
						<input type="button" name="create_comment"onclick = "newComment()" value="등록" />
					</form>
				</div>
				

				<div class="comment_pos" id = "comments">
				</div>
		</div>
	</div>
	
	<script>
	var post_code;
	var post_like;
	
	var title;
	var date;
	var content;
	var com_like;
	var code;
	var com_user;
	
	$(document).ready(function() {
			$("li").on("click",function() {
				
				title= $(this).find("span").text();
				date = "#"+$(this).val()+"create";
				content = "#"+$(this).val()+"content";
				post_like = "#"+$(this).val()+"likes";
				code = "#"+$(this).val()+"code";
				
				/* console.log($(code).val()); */
				post_code = $(code).val();
				
				$("#post_detail .title h3 ").text(title);
				$("#post_detail .date h3 ").text($(date).val());
				$("#post_detail .content p ").text($(content).val());
				$("#post_like").text($(post_like).text());
				$("#post_code").val($(code).val());
				$("#post_detail").css("visibility", "visible"); 
			});
		});
		
	$(function(){
		$("li").on("click",function (e){
			//page이동 없이 서버에 요청보내고 응답받기 :ajax
		$.ajax({
			url:"/shinDTown/comment/list.com",
			data:{"post_code":$("#"+$(this).val()+"code").val()},
			success:function(data){
				
				$("#comments").html("");
				var responseData = eval("(" + data + ")");

				$.each(responseData.comlist, function(index,item){
					
					getlikes(item.COM_CODE);
					getcom_userName(item.COM_CODE);
					
 					var head = "<div class='comment_list'><div class='comms'><div class='comm'>";
 					var foot = "</div><button class='good' onclick='setlike("+item.COM_CODE+")' id='"+item.COM_CODE+
 					"_btn2' value='"+item.COM_CODE+"'>공감</button><p class='points' id='" +item.COM_CODE+ "_txt'>" + com_like 
 					+"</p><button class='delcom' id= '" +item.COM_CODE+ "_btn' onclick='delCom("+item.COM_CODE+")'>삭제</button>"
 					+" </div><hr class='line'/></div>";
	 					
					$("#comments").append(head + 
					"<p>"+com_user+"</p>"+
 					"<p>"+item.COM_COMMENT+"</p>" +
 					foot);
					
					var check = checklike(item.COM_CODE);
					console.log("check : " + check);
					
					if(checklike(item.COM_CODE, ${user_code}) > 0){
						$("#"+item.COM_CODE+"_btn2").text("취소");	
					}
					
					if($.trim(item.USER_CODE) == ${user_code} || ${loginUser.user_class} == "0" ){
						console.log("일치");
						$("#"+item.COM_CODE+ "_btn").css("visibility", "visible"); 
					}else{
						$("#"+item.COM_CODE+ "_btn").css("visibility", "hidden"); 
					}
					
				});	
				
			},
			error:function(message){
				console.log(message);
			}
		});
		
	});
	});
	$(document).ready(function() {
		$("#com").click(function() {
			if($("#comment").val().length==0){
				alert("댓글을 입력해주시오");
				$("#comment").focus;
				return false;
				}
		});
	});
	$(function(){
		$("#post_likebtn").on("click",function(){
			//page이동 없이 게시글 좋아요 후 좋아요 부분 +
			$.ajax({
					url:"/shinDTown/post/like.com",
					data:{"post_code":$("#post_code").val()},
					success:function(data){
					if(data="OK"){
					$("#post_like").text(parseInt($("#post_like").text())+1);
					$(post_like).text(parseInt($(post_like).text())+1);
					}
					}
				})
			})
		});
	
		function getlikes(a){
			console.log("com_code: " +a);
			$.ajax({
				method : "GET",
				data : {"com_code": a},
				async : false,
				url: "${path}/view/boardView/likes.com",
				success: function(responseData){
					com_like = responseData;
				},
				error: function(){
					console.log("err");
				}
				
			});
			
			return com_like;
		}
	function newComment(){
		
			var com_comment = $("#com_comment").val();

		$.ajax({
			method : "POST",
			data : {"post_code" : post_code, "user_code" : ${user_code}, "com_comment" : com_comment},
			url : "/shinDTown/comment/create.com",
			async: false,
			success: function(){
				
				var com_code = parseInt(getcom_code(post_code, ${user_code}, com_comment));
				
				getlikes(com_code);
				getcom_userName(com_code);
				
				var head = "<div class='comment_list' id='"+com_code+"_list'><div class='comms'><div class='comm'>";
					var foot = "</div><button class='good' onclick='setlike("+com_code+")' id='"+com_code+
					"_btn2' value='"+com_code+"'>공감</button><p class='points' id='"+com_code+ "_txt'>" + com_like
					+"</p><button class='delcom' id= '" +com_code+ "_btn' onclick='delCom("+com_code+")'>삭제</button>"
					+" </div><hr class='line'/></div>";
					
					
				$("#com_comment").val("");
				$("#comments").append(head +
					"<p>"+com_user+"</p>"+
					"<p>"+com_comment+"</p>" +
					foot);
				
				
			},
			error : function(){
				console.log("실패");
			}
			
		})
	}
//만약 사용자가 같은 내용을 여러번 적었다면.,,.? 구분 안되긴 함..(중복처리 막아야 함)
function getcom_code(post_code, user_code, com_comment){
	var com_code;
	$.ajax({
		method : "GET",
		async: false,
		data : {"com_comment": com_comment, "post_code" : post_code, "user_code" : "${user_code}"},
		url: "${path}/view/boardView/getcomcode.com",
		success: function(responseData){
			com_code = responseData;
		},
		error: function(){
			console.log("err");
		}
		
	});	
	return com_code;
}

function delCom(com_code, user_code){
	$.ajax({
		method : "GET",
		data : {"com_code": com_code, "user_code" : user_code},
		url: "${path}/view/boardView/delete.com",
		success: function(responseData){
			alert("삭제 완료");
			$("#"+com_code+"_list").hide();
		},
		error: function(){
			console.log("err");
		}
		
	});
}
	
function getcom_userName(com_code){
		console.log("이름 가져오기 :" + com_code);
		$.ajax({
			method : "GET",
			data : {"com_code": com_code},
			async : false,
			url: "${path}/view/boardView/getComUserName.com",
			success: function(responseData){
				com_user = responseData;
			},
			error: function(){
				console.log("err");
			}
			
		});
		return com_user;
}
	
function setlike(com_code){
	console.log(com_code)
	if($("#"+com_code+"_btn2").text() == "취소"){
		console.log("취소");
		
		$.ajax({
			method : "POST",
			data : {"com_code": com_code, "post_code" : post_code, "user_code" : "${user_code}"},
			url: "${path}/view/boardView/deletelike.com",
			success: function(responseData){
				if(responseData > 0){
					console.log("cancle  : " + responseData);
					var origin = $("#"+com_code+ "_txt").text() ;
					$("#"+com_code+ "_txt").text(parseInt(origin) - 1);
					$("#"+com_code+"_btn2").text("공감");
				}
			},
			error: function(){
				console.log("err");
			}
			
		});	
	}else{
		$.ajax({
			method : "POST",
			data : {"com_code": com_code, "post_code" : post_code, "user_code" : "${user_code}"},
			url: "${path}/view/boardView/likes.com",
			success: function(responseData){
				console.log($("#"+com_code+ "_txt"));
				var origin = $("#"+com_code+ "_txt").text() ;
				$("#"+com_code+ "_txt").text(parseInt(origin) + 1);
				$("#"+com_code+"_btn2").text("취소");
				alert("공감!");
			},
			error: function(){
				console.log("err");
			}
			
		});	
	}
		
}
function checklike(com_code){
	var check;
	$.ajax({
		method : "GET",
		data : {"com_code": com_code, "user_code" : "${user_code}"},
		url: "${path}/view/boardView/checklike.com",
		async : false,
		success: function(responseData){
			check =  responseData;
		},
		error: function(){
			console.log("err");
		}
	});
	return check;	
}


</script>
	
</body>
</html>