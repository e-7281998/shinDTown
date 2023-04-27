package com.shinD.controller.post;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.board.BoardService;
import com.shinD.model.post.PostService;

public class PostDelete implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");// 입력받은 값 가져오기
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");// 인코딩
		String method = (String) data.get("method");
		
		System.out.println("포스트딜리트 넘어왓냐");
		
		PostService pservice = new PostService();

		int post_code= Integer.parseInt(request.getParameter("post_code")) ;// 리퀘스트에서 보드 이름이랑 유저 코드 받아오기
		int user_code = (int)session.getAttribute("user_code");

		int result = pservice.PostDelete(post_code);
		
		if(result<0) {
			return "responseBody:"+result;
		}
		return "responseBody:"+result;
	}

}