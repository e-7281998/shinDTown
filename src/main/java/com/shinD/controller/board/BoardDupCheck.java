package com.shinD.controller.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.CommonControllerInterface;

public class BoardDupCheck implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		
		String board_name = request.getParameter("board_name");
		System.out.println(board_name);
		BoardService bservice = new BoardService();
		int result =  bservice.boardCheck(board_name);
	
		
		return "responseBody:"+result;
	}

}
