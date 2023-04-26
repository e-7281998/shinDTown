package com.shinD.controller.git;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.git.GitService;

public class Git implements CommonControllerInterface{

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		
		String[] pathArr = request.getRequestURI().split("/");
		String path = pathArr[pathArr.length-1];
		
		if(path.equals("gitRegister.com") )
			return registerGit(request);
		else if(path.equals("gitUpdate.com") )
			return updateGiId(request);
		else if(path.equals("getGitId.com") )
			return getGitId(request);
		else 
			return getGitId(request);
		 
	} 
	//깃 수정하기
	private String updateGiId(HttpServletRequest request) {
		GitService service = new GitService();
		
		HttpSession session = request.getSession();
		int user_code = (int)session.getAttribute("user_code");
		String git_id = request.getParameter("git_id");
 		int result = service.updateGiId(user_code, git_id);
		
 		return "responseBody:"+result;
	}
	//git 불러오기
	private String getGitId(HttpServletRequest request) {
		GitService service = new GitService();
		String user_code = request.getParameter("user_code");
		String result = service.getGitId(user_code);
		if(result == null)
			return "responseBody:-1";
		return "responseBody:"+result;
	}

	//git 등록하기
	private String registerGit(HttpServletRequest request) {
		GitService service = new GitService();
		
		HttpSession session = request.getSession();
		int user_code = (int)session.getAttribute("user_code");
		String git_id = request.getParameter("git_id");
 		int result = service.registerGit(user_code, git_id);
		
 		return "responseBody:"+result;
	}

}
