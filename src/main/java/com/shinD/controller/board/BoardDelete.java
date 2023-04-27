package com.shinD.controller.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.board.BoardService;

public class BoardDelete implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");// 입력받은 값 가져오기
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");// 인코딩
		String method = (String) data.get("method");
		String page = "empDetail.jsp";

		BoardService bservice = new BoardService();

		String board_name = request.getParameter("board_name");// 리퀘스트에서 보드 이름이랑 유저 코드 받아오기
		int user_code = (int)session.getAttribute("user_code");

		int result = bservice.boardDelete(board_name);// 삭제명령
		
		if(result<0) {
			return "responseBody:NO";
		}
		return "responseBody:OK~";
	}

}