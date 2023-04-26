package com.shinD.controller.comment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.comment.CommentService;

public class com_userName implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest)data.get("request");
		HttpServletResponse response = (HttpServletResponse) data.get("response");
		
		CommentService cs = new CommentService();
		int com_code = Integer.parseInt(request.getParameter("com_code"));
		
		String result = cs.com_userName(com_code);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
		System.out.println(result);
		
		return "responseBody:" + result;
		
	}
}
