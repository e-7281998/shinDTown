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
		
		if(path.equals("gitRegister") )
			return registerGit(request);
		else if(path.equals("withdraw") )
			return withdrawGit(request);
		else 
			return readGit(request);
		 
	}

	//git 삭제하기
	private String withdrawGit(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	//git 불러오기
	private String readGit(HttpServletRequest request) {
 		return null;
	}

	//git 등록하기
	private String registerGit(HttpServletRequest request) {
		GitService service = new GitService();
		
		HttpSession session = request.getSession();
		int user_code = (int)session.getAttribute("user_code");
		String git_id = request.getParameter("git_id");
		System.out.println("sql 보낼거야 ) user_code: " + user_code + " || git_id: "+git_id);
		int result = service.registerGit(user_code, git_id);
		
 		return "responseBody:"+result;
	}

}
