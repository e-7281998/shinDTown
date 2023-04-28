<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="path" value="${pageContext.request.contextPath }" scope="application"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShinDTown</title>
 <link rel="shortcut icon" type="image/x-icon" href="${path}/view/img/logo.png">
<link href="${path}/view/boardView/boardDetail.css" rel="stylesheet" />
<script src="${path}/jq/jquery-3.6.4.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet"
      href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/11.7.0/styles/monokai-sublime.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/11.7.0/highlight.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.7.0/languages/1c.min.js" integrity="sha512-xgfaDoNzPFKxmI8PsnCBaSw5sJCy0l2QSPGc+S0ZLBcHeqvZltpqNyersdDwTF5+fFBpI2G6MdgjDtJ5xSityA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>hljs.initHighlightingOnLoad();</script>
</head>
<body class="main">
	<%@include file="../header.jsp"%>
	<div class="body">
	
		<div class="board_detail">
			<div class="board_title">
				<h1>${board_name }</h1>			
				<c:if test="${myboard[0].USER_CODE eq loginUser.user_code or loginUser.user_class eq 0}">
					<button id="board_delete" class="delcom">게시판삭제</button>
				</c:if>
			</div>
			<div class="board_list">
				<ul class="post_title">
				<c:forEach items="${pclist }" var="post" varStatus ="status">
					<div id="${post.POST_CODE}list }">
						<li id="${status.count}" value="${status.count}"> <span>${post.POST_TITLE }</span> [좋아요:<p class="like" id="${status.count}likes">${post.POST_LIKES }</p>] [댓글:<p class="com_count" id="${status.count }coms">${post.POST_COMS }</p>개]
						<c:if test="${post.USER_CODE eq loginUser.user_code or loginUser.user_class eq 0 }">
							<button class="delcom" style="visibility: hidden; float: right;" id ="${status.count }post_delete" value="${post.POST_CODE }" onclick="postdelete();">삭제</button>
						</c:if>
						<hr class="post_line"/>
						</li>
		
						
						<input type="hidden" id="${status.count}create" value="${post.POST_CREATE }">
						<input type="hidden" id="${status.count}content" value="${post.POST_CONTENT }">
						<input type="hidden" id="${status.count}post_user" value="${post.USER_NAME}">
						<p style="display:none;" id="${status.count}post_source">${post.POST_SOURCE}</p>						
						<input type="hidden" id="${status.count}code" value="${post.POST_CODE }">
					</div>
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
						<p>제목 : </p>
					</div>
					
					<div class="post_user">
						<p>이름: </p>
					</div>
					
					<div class="date" id ="date">
						<p>날짜: </p>
					</div>

					<div class="content">
						<p>내용:</p>
					</div>

					<div class="source" >
						<pre><code style="display:none;"></code></pre>
					</div>
					
					<div class="like">
						<button id="post_likebtn"><img id= "imgs"/></button>
						<p class="like_p" id="post_like"></p>
					</div>
					
					<div class="state">
						<p id="like_state"></p>
					</div>
				</div>

				<div class="comment">
					<form class="create_comment" >
						<input type="hidden" id="post_code" name="post_code">
						<input type="text" class="comment" id ="com_comment" name="com_comment" > 
						<input type="button" class="comment_btn" name="create_comment" onclick = "newComment()" value="등록" />
					</form>
				</div>
				

				<div class="comment_pos" id = "comments">
				</div>
				</div>
		</div>
	</div>

	
	<script>
	var user_name;
	var title;
	var date;
	var content;
	var coms;
	var board_name=$("#board_title h1").text();
	var post_delete;
	var post_like;
	var post_code;
	var licount;
	
	var com_like;
	var com_user;
	
	$(document).ready(function() {
		
		$("#board_delete").on("click",function(){
			$.ajax({
				method : "GET",
				data : {"board_name": "${board_name}"},
				url: "/shinDTown/board/delete.com",
				success: function(responseData){
					swal("게시판삭제!", "게시판이 삭제되었습니다!", 'success').then(function(){
						location.href='/shinDTown/board/read.com';
					})
				},
				error: function(){
					console.log("err");
				}
				
			});
		});
		
			$("li").on("click",function() {
				
				title= $(this).find("span").text();
				date = "#"+$(this).val()+"create";
				content = "#"+$(this).val()+"content";
				post_like = "#"+$(this).val()+"likes";
				code = "#"+$(this).val()+"code";
				user_name = "#"+$(this).val()+"post_user";
				coms="#"+$(this).val()+"coms";
				post_delete="#"+$(this).val()+"post_delete";
				source = "#"+$(this).val()+"post_source";
				var lastcount = licount;
				licount = "#"+$(this).val();
				$("#like_state").text("");

				
				post_code = $(code).val();
				
				$("#post_detail .title p ").text("제목 : " + title);
				$("#post_detail .date p ").text("작성일자 : " + $(date).val());
				$("#post_detail .post_user p ").text("작성자 : " + $(user_name).val());
				$("#post_detail .content p ").text("내용 : " + $(content).val());
				$("#post_detail .source p ").text($(source).val());
				if($(source).text() == ""){
					$("#post_detail .source pre code").css("display", "none");
					$("#post_detail .source pre code").text("");
				}else{				
					$("#post_detail .source pre code").css("display", "block");
					$("#post_detail .source pre code").html($(source).text());
				}
				
				console.log($(post_like).text() + "shfjkdhfjks");
				$("#post_like").text($(post_like).text());
				$("#post_code").val($(post_code).text());
				$("#post_detail").css("visibility", "visible"); 
				
				$(lastcount+" button").css("visibility", "hidden"); 
				$(post_delete).css("visibility","visible");
				
				
				
				if(post_likecheck(post_code)> 0){
					$("#imgs").attr("src", "${path}/view/img/balloon-heart-fill.svg");
					$("#imgs").val("안좋아");
				}else{
					$("#imgs").attr("src", "${path}/view/img/balloon-heart.svg");
					$("#imgs").val("좋아");
				}
			});
			
		});
	
	function postdelete(){
		$.ajax({
			data : {"post_code": $(post_delete).val()},
			url: "/shinDTown/post/delete.com",
			success: function(responseData){
				swal("게시글삭제!", "게시글이 삭제되었습니다!", 'success');
				$(licount).css("display","none");
				$("#post_detail").css("visibility", "hidden"); 
								
			},
			error: function(){
				console.log("err");
			}
			
		});
	}
	
		
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
					
 					var head = "<div class='comment_list' id='"+item.COM_CODE+"_list'><div class='comms'><div class='comm'>";
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
/* 	$(document).ready(function() {
		$(".comment_btn").click(function() {
			if($("#com_comment").val().length==0){
				alert("댓글을 입력해주세요!");
				return;
				$("#com_comment").focus;
				}
		});
	}); */
	$(function(){
		$("#post_likebtn").on("click",function(){
			console.log($("#imgs").val());
			//page이동 없이 게시글 좋아요 후 좋아요 부분 +
			if($("#imgs").val() == "좋아"){
				post_likes(post_code);
			}else{
				post_del_like(post_code);
			}
			
			})
		}); 
	
	function post_del_like(post_code){
		$.ajax({
			method : "GET",
			data : {"post_code": post_code},
			url: "/shinDTown/post/likedelete.com",
			success: function(responseData){
				$("#"+post_code+"_list").hide();
				$("#post_like").text(parseInt($("#post_like").text())-1);
				
				$(post_like).text(parseInt($(post_like).text())-1);	
				
				$("#imgs").attr("src","${path}/view/img/balloon-heart.svg");
				$("#imgs").val("좋아");
				$("#like_state").text("공감이 취소되었습니다!");
			},
			error: function(){
				console.log("err");
			}
			
		});
	}
	
	
	function post_likes(post_code){
		$.ajax({
				url:"/shinDTown/post/like.com",
				data:{"post_code":post_code},
				success:function(data){
					$("#post_like").text(parseInt($("#post_like").text())+1);
					$(post_like).text(parseInt($(post_like).text())+1);	
					$("#imgs").attr("src","${path}/view/img/balloon-heart-fill.svg");
					$("#imgs").val("안좋아");
					$("#like_state").text("공감되었습니다!");
					
			}
		})
	}
	
		
	function post_likecheck(post_code){
		console.log(post_code);
		var check;
		$.ajax({
			method : "GET",
			data : {"post_code": post_code},
			url: "/shinDTown/post/likecheck.com",
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
			if($("#com_comment").val().length==0){
				alert("댓글을 입력해주세요!");
				return;
				$("#com_comment").focus;
				}

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
					"<b>"+com_user+"</b>"+
					"<p>"+com_comment+"</p>" +
					foot);
				
				$(coms).text(parseInt($(coms).text())+1);
				
			},
			error : function(){
				console.log("실패");
			}
			
		})
	}
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

function delCom(com_code){
	console.log(com_code);
	$.ajax({
		method : "GET",
		data : {"com_code": com_code},
		url: "${path}/view/boardView/delete.com",
		success: function(responseData){
			$("#"+com_code+"_list").hide();
		},
		error: function(){
			console.log("err");
		}
		
	});
}
	
function getcom_userName(com_code){
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
				var origin = $("#"+com_code+ "_txt").text() ;
				$("#"+com_code+ "_txt").text(parseInt(origin) + 1);
				$("#"+com_code+"_btn2").text("취소");
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