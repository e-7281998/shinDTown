package com.shinD.controller.comment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.comment.CommentService;

public class ComCode implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		HttpServletResponse response = (HttpServletResponse) data.get("response");
	
		int com_code = 0;
		CommentService cs = new CommentService();
		int post_code = Integer.parseInt(request.getParameter("post_code"));
		int user_code = Integer.parseInt(request.getParameter("user_code"));
		String com_comment = (String) (request.getParameter("com_comment"));
		
		com_code = cs.getcom(post_code, user_code, com_comment);
		request.setAttribute("com_code", com_code);
			
		return "responseBody: "+com_code;
	}
}