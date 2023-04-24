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
		<img src="https://ghchart.rshah.org/e-7281998">
		<form>
			<input type="text" id="git_id" class="id fom" placeholder="git 아이디" />
			<input type="button" id="git_register" class="submit" value="git 아이디">
		</form>
		<input type="button" id="git_read" class="submit" value="git 불러오기">
		<ul id="repos">
			<li class="repo">
				<div class="repo_title"><span>repo1</span><a>link1</a></div>
				<ul class="commits">
					<li class="commit">repo date</li>
					<li class="commit">repo date</li>
					<li class="commit">repo date</li>
				</ul>
			</li>
			<li class="repo">
				<div class="repo_title"><span>repo2</span><a>link2</a></div>
				<ul class="commits">
					<li class="commit">repo date</li>
					<li class="commit">repo date</li>
					<li class="commit">repo date</li>
				</ul>
			</li>
			 
		</ul>	
	</div>
		
	<script>
		const readBtn = document.getElementById("git_read");
		readBtn.addEventListener("click", getRepos)
		async function getRepos(){
			//gitAPI
			//const url = "http://api.github.com";
			
			//유저 정보 가져옴
			//const url = "https://api.github.com/users/e-7281998";
			
			//개인 repo 가져옴
			const repoUrl = "https://api.github.com/users/e-7281998/repos";
			const repoRresponse = await fetch(repoUrl);
			const repoResult = await repoRresponse.json();
			
			var repos = document.getElementById("repos");
			//repoResult.forEach((repo) => {
				//repo명
				//console.log(repo.name);
				//repo 링크
				//console.log(repo.html_url);
				
				//<div class="repo_title"><span>repo1</span><a>link1</a></div>
			//})
			for(var i=0; i<repoResult.length; i++){
				var repoLi = document.createElement("li");
				//var repo = repos[i];
				//var repoLink = repo[i].html_url
				//repoLi.innerHTML = "<div class='repo_title'><span>"+repo+"</span><a>"+repoLink+"</a></div>";
				console.log(repos[i]);
				//repoLi.innerHTML = "<div class='repo_title'><span>repo</span><a>link</a></div>";
				//repos.append(repoLi);
			}
			
				
			
			//개인 repo의 commit 내용 불러오기
			const commitUrl = "https://api.github.com/repos/e-7281998/shinDTown/commits"
			const commitResponse = await fetch(commitUrl);
			const commitResult= await commitResponse.json();
			
			for(var i=0; i<10; i++){
				console.log(commitResult[i]);
				console.log(commitResult[i].commit.message);
				const author = commitResult[i].commit.author.date.replace("T"," ").slice(0,16);
				console.log(author);
			}
 			
			//console.log(result);
		} 
	</script>
	<script>
		//gitID 등록하기
		$("#git_register").on("click", () => {
			$.ajax({
				url:"gitRegister",
				data: {"git_id":$("#git_id").val()},
				success : (responseData) => {
					if(responseData == 1){
						alert('git 등록 완료');
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