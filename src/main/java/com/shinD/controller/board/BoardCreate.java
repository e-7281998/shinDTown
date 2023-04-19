package com.shinD.controller.board;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;



public class BoardCreate implements CommonControllerInterface {
	
	@Override
	public String execute(Map<String, Object> data) throws Exception{
		String page = "BoardCreate.jsp";
		String method = (String)data.get("method");
		
		
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		
		if(method.equals("POST")) {
			//보드 중복체크
			BoardService bservice = new BoardService();
			int result = bservice.boardCheck(request.getParameter("board_name"));
			
			//보드 생성
			if(result == 1) {
				BoardVO board = makeBoard(request);
				bservice.BoardCreate(board);
			}else {
				System.out.println("해당 게시판명은 사용중입니다");
			}
//			HttpSession session = request.getSession();
//			session.setAttribute("message", result);
			//재요청하기: browser로 내려가서 새로운 요청으로 기다려라
			page = "redirect:emplist.do";
			
		}else {//GET...부서. 직책. 매니저 선택하고자 한다.
			
			
			page = "empInsert.jsp";
		}
		
		return page;
	}

	private BoardVO makeBoard(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		int board_code = Integer.parseInt(request.getParameter("board_code"));
		int user_code = Integer.parseInt(request.getParameter("user_code"));
		String board_name = request.getParameter("board_name");
		
		BoardVO board = new BoardVO();
		board.setBOARD_CODE(board_code);
		board.setUSER_CODE(user_code);
		board.setBOARD_NAME(board_name);
		
		
		return board;
	}
}
