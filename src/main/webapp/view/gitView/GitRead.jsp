<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/jq/jquery-3.6.4.min.js"></script>
</head>
<body>
	 
	<div class="git"> 
		<img id="grass" src="">
		<form>
			<input type="text" id="git_id" class="id fom" placeholder="git 아이디" />
			<input type="button" id="git_register" class="submit" value="등록하기">
			<input type="button" id="git_update" class="submit" value="수정하기">
		</form>
 		<ul id="repos"> 
		</ul>	
	</div>
		
	<script>
		 window.onload = function(){
			$("#git_register").hide();
			$("#git_update").hide();
			
			$.ajax({
				url:"getGitId.com",
				data: {"user_code":${user_code}},
				success : (responseData) => {
					var gitBtn = $("#git_register")
					if(!(responseData == -1)){
 						$("#git_update").show(); 
						//repo, commit, wksel 불러오기
						getRepos(responseData);
					}else{
 						$("#git_register").show();
					}
				},
				error:(message)=>{
					console.log(message);
				}
			})
 		} 
		 
		 
		
		async function getRepos(gitId){
			//gitAPI
			//const url = "http://api.github.com"; 
			//유저 정보 가져옴
			//const url = "https://api.github.com/users/e-7281998";
			
			//git 잔디 불러오기
			$("#grass").attr( "src", "https://ghchart.rshah.org/"+gitId);
			//개인 repo 가져옴
 			const repoUrl = "https://api.github.com/users/"+gitId+"/repos";
			const repoRresponse = await fetch(repoUrl);
			const repoResult = await repoRresponse.json();
			 
			//repo명, 바로가기 링크 걸기
			var repoUl = document.getElementById("repos");
			var repoArr = [];
			repoUl.innerHTML="";
			for(var i=0; i<repoResult.length; i++){
 				var repoName = repoResult[i].name;
 				var repoLink = repoResult[i].html_url; 
				var repoDate = repoResult[i].pushed_at.slice(0,10);
				
				var repo = {"repoName":repoName,"repoLink":repoLink,"repoDate":repoDate};
				repoArr[i] = repo;
 			}
			
 			var sortRepo;
 			if(Object.keys(repoArr).length > 1){
				sortRepo = repoArr.sort((repo1, repo2) => { 
					if(repo1.repoDate > repo2.repoDate) return -1;
					if(repo1.repoDate < repo2.repoDate) return 1;
					return 0; 
				})
			}else{
				sortRepo = repoArr;
			}
			 
			//repo 화면에 부착하기
			for(var i=0; i<Math.min(5, sortRepo.length); i++){
				repoUl.innerHTML += "<li class='repo'><div><span class='repo_title'>"+sortRepo[i].repoName+"</span><a href="+sortRepo[i].repoLink+" target='_blank'>바로가기</a></div></li>"
			}
				
			
			//개인 repo의 commit 내용 불러오기 
			var repoLl = document.getElementsByClassName("repo") ; 
			for(var j=0; j<Math.min(5,sortRepo.length); j++){
				
				const commitUrl = "https://api.github.com/repos/"+gitId+"/"+sortRepo[j].repoName+"/commits";
				const commitResponse = await fetch(commitUrl);
				const commitResult= await commitResponse.json();
				
				var commitUl = document.createElement("ul");
				commitUl.classList.add("commits");
				
 				for(var i=0; i<Math.min(5, Object.keys(commitResult).length); i++){
					var msg = commitResult[i].commit.message;
 					var data = commitResult[i].commit.author.date.replace("T"," ").slice(0,16);

					commitUl.innerHTML += "<li class='commit'><span>"+msg+"</span><span>"+data+"</span></li>"; 
				}  
				repoLl[j].append(commitUl);
			}  
 		} 
 		
 		//gitID 등록하기
		$("#git_register").on("click", () => {
			$.ajax({
				url:"gitRegister.com",
				data: {"git_id":$("#git_id").val()},
				success : (responseData) => {
					if(responseData == 1){
 						$("#git_update").show();
						$("#git_register").hide();
						getRepos($("#git_id").val());
					}else{
						alert("git 등록 실패");
					}
				},
				error:(message)=>{
					console.log(message);
				}
			})
		})
		
		//gitID 수정하기
		$("#git_update").on("click", () => {
			$.ajax({
				url:"gitUpdate.com",
				data: {"git_id":$("#git_id").val()},
				success : (responseData) => {
					if(responseData == 1){
						alert('Git ID 수정 완료'); 
						getRepos($("#git_id").val());
					}else{
						alert("git 등록 실패");
					}
				},
				error:(message)=>{
					console.log(message);
				}
			})
		})
	</script> 
</body>
</html>