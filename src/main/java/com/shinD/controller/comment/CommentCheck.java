package com.shinD.controller.comment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.comment.CommentService;

public class CommentCheck implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		
HttpServletRequest request = (HttpServletRequest)data.get("request");
		
		CommentService cs = new CommentService();
		int com_code = Integer.parseInt(request.getParameter("com_code"));
		int user_code = Integer.parseInt(request.getParameter("user_code"));
		int result = cs.CheckCom(com_code, user_code);
		
		return "responseBody:" + result ;
		
	}

}
