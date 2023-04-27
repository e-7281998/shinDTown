package com.shinD.controller.post;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.comment.CommentService;
import com.shinD.model.post.PostService;

public class PostCheck implements CommonControllerInterface {
	@Override
	public String execute(Map<String, Object> data) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest)data.get("request");
		HttpSession session = request.getSession();
		
		PostService pservice= new PostService();
		int post_code = Integer.parseInt(request.getParameter("post_code"));
		int user_code = (int)(session.getAttribute("user_code"));
		int result = pservice.CheckCom(post_code, user_code);
		
		return "responseBody:" + result ;
		
	}
}
