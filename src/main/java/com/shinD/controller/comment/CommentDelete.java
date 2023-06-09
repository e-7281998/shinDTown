package com.shinD.controller.comment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.comment.CommentService;

public class CommentDelete implements CommonControllerInterface{

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest)data.get("request");
		
		CommentService cs = new CommentService();
		int com_code = Integer.parseInt(request.getParameter("com_code"));
		int result = cs.deleteComment(com_code);
		
		if(result == 0) {
			System.out.println("삭제 성공");
 			return "responseBody:1";
		} 
		else
			return "responseBody:-1";
				
		
	}

}