package com.shinD.controller.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinD.controller.CommonControllerInterface;

public class BoardRead implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		String method = (String)data.get("method");
		String page = "";
		
		BoardService bserbive = new  BoardService();
		List<BoardVO> boardlist = bserbive.boardAll();
		
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		request.setAttribute("boardlist", boardlist);
		
		System.out.println(request);
		System.out.println(boardlist);
		return null;
	}

}
