package com.shinD.controller.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.board.BoardService;

public class BoardDupCheck implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		
		String board_name = request.getParameter("board_name");
		BoardService bservice = new BoardService();
		int result =  bservice.boardCheck(board_name);//보드 중복체크
	
		
		return "responseBody:"+result;
	}

}
