package com.shinD.controller.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinD.controller.CommonControllerInterface;

public class BoardRead implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");//입력받은 값 가져오기
		request.setCharacterEncoding("utf-8");//인코딩
		String method = (String)data.get("method");
		String page = "";
		
		BoardService bserbive = new  BoardService();
		List<BoardVO> boardlist = bserbive.boardAll();//보드 목록을 받아서 리스트로 만듦
		
		request.setAttribute("boardlist", boardlist);//보드리스트 값 리퀘스트에넣기
	
		System.out.println(request);
		System.out.println(boardlist);
		return "responseBody:boardlist";
	}

}
