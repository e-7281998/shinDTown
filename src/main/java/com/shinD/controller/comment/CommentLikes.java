package com.shinD.controller.comment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.comment.CommentService;
import com.shinD.model.post.LikeVO;
public class CommentLikes implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		HttpServletResponse response = (HttpServletResponse) data.get("response");
		String method = (String)data.get("method");
		String page ="";
	
		int likes = 0;
		CommentService cs = new CommentService();
		
		if(method.equals("GET")) {
			int com_code = Integer.parseInt(request.getParameter("com_code"));
			likes = cs.Likes(com_code);
			request.setAttribute("likes", likes);
			
			return "responseBody:"+ likes;
			
		}else {
			int post_code = Integer.parseInt(request.getParameter("post_code"));
			int com_code = Integer.parseInt(request.getParameter("com_code"));
			int user_code = Integer.parseInt(request.getParameter("user_code"));
			
			System.out.println(post_code);
			System.out.println(com_code);
			System.out.println(user_code);
			
			LikeVO like = makeLike(post_code, com_code, user_code);
			cs.LikeCreate(like);
			
			return "redirect:/shinDTown/board/detail.jm";
		}
	}
	private LikeVO makeLike(int post_code, int com_code, int user_code) {
		LikeVO like = new LikeVO();
		like.setCOM_CODE(com_code);
		like.setPOST_CODE(post_code);
		like.setUSER_CODE(user_code);
		return like;
	}

}
