package com.shinD.controller.post;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.controller.board.BoardService;
import com.shinD.model.post.PostService;

public class PostCreate implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		request.setCharacterEncoding("utf-8");//인코딩
		String page = "../view/postView/PostCreate.jsp";
		String method = (String)data.get("method");
		PostService pservice = new PostService();
		String board_name="";
		
		if(method.equals("GET")) {
			board_name= request.getParameter("BOARD_NAME");
			System.out.println(board_name);
			request.setAttribute("board_name", board_name);
		}else {
			
			
			return "redirect:../view/postView/PostCreate.jsp";
		}
		
		return "../view/postView/PostCreate.jsp";
	}

}
