package com.shinD.controller.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.board.BoardService;

public class BoardDelete implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");//입력받은 값 가져오기
		request.setCharacterEncoding("utf-8");//인코딩
		String method = (String) data.get("method");
		String page = "empDetail.jsp";

		BoardService bservice = new BoardService();
		
		if(method.equals("GET")) {
			
		}else {
			String board_name= request.getParameter("board_name");//리퀘스트에서 보드 이름이랑 유저 코드 받아오기
			int user_code = Integer.parseInt(request.getParameter("user_code"));
			
			int result = bservice.boardDelete(board_name, user_code);//삭제명령
			//결과값이 1이면 삭제됨
			if(result>=1) {
				System.out.println(board_name+":"+user_code+"삭제햇습니다");//테스트하려고 찍어둠
			}else{
				System.out.println("삭제권한이 없습니다..");
			}
		}
		
		
		return "responseBody:삭제성공~";
	}

}
