package com.shinD.controller.post;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.post.PostService;

public class PostLike implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		request.setCharacterEncoding("utf-8");//인코딩
		HttpSession session =  request.getSession();
		
		//유저코드랑 게시글 코드 받아옴
		int user_code = (Integer)session.getAttribute("user_code");
		int post_code = Integer.parseInt(request.getParameter("post_code"));
		
		PostService pservice = new PostService();
		int result = pservice.postLike(user_code, post_code);//게시글 증가
		if(result>0) {	
			return "responseBody:OK";
		}
		
		return "responseBody:false";
	}

}
