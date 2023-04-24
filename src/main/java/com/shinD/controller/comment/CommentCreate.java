package com.shinD.controller.comment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.comment.CommentService;
import com.shinD.model.comment.CommentVO;

public class CommentCreate implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");//입력받은 값 가져오기
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("utf-8");//인코딩
		String page = "";
		String method = (String)data.get("method");
		CommentService cservice = new CommentService();
		
		//메소드 형식 따라서
		if(method.equals("POST")) {
			int post_code = Integer.parseInt(request.getParameter("post_code"));
			System.out.println("포스트코드"+post_code);
			int user_code= (Integer)session.getAttribute("user_code");
			String com_comment = request.getParameter("com_comment");
			
			//commentVO형식의 데이터로 변환
			CommentVO com = makeCom(post_code,user_code,com_comment);
			
			cservice.CommentCreate(com);
			
			page = "redirect:/shinDTown/board/detail.jm";
			}else {
		
			}
	
		return page;
	}

	private CommentVO makeCom(int post_code, int user_code, String com_comment) {
		CommentVO com = new CommentVO();
		com.setPOST_CODE(post_code);
		com.setUSER_CODE(user_code);
		com.setCOM_COMMENT(com_comment);
		return com;
	}

}
