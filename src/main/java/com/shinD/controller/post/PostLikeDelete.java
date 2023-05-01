package com.shinD.controller.post;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.comment.CommentService;
import com.shinD.model.post.PostService;

public class PostLikeDelete implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest)data.get("request");
		HttpSession session = request.getSession();
		int user_code = (int) session.getAttribute("user_code");
		PostService pservice = new PostService();
		int post_code= Integer.parseInt(request.getParameter("post_code"));
		
		int result = pservice.postLikeDel(post_code,user_code);
		
		if(result == 0) {
			System.out.println("삭제 성공");
 			return "responseBody:1";
		} 
		else
			return "responseBody:-1";
				
	}

}