package com.shinD.controller.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.board.BoardPostVO;
import com.shinD.model.board.BoardService;
import com.shinD.model.board.BoardVO;

public class BoardRead implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");//입력받은 값 가져오기
		request.setCharacterEncoding("utf-8");//인코딩
		String method = (String)data.get("method");
		
		BoardService bservise = new  BoardService();
		
		if(method.equals("GET")) {
		List<BoardVO> boardlist = bservise.boardAll();//보드 목록
		List<BoardPostVO> boardpostlist = bservise.boardPostAll();//보드 목록+게시글 을 받아서 리스트로 만듦
		List<BoardVO> boardtop = bservise.boardTop();//보드 인기도 순 
		
		request.setAttribute("boardpostlist", boardpostlist);//보드리스트 값 리퀘스트에넣기
		request.setAttribute("boardtop", boardtop);//보드탑 값 리퀘스트에넣기
		request.setAttribute("boardlist", boardlist);//보드목록 값 리퀘스트에넣기
		}else {
			//보드 이름 검색한거 리스트로 만듦
			List<BoardVO> boardlist = bservise.boardAll();//보드 목록을 받아서 리스트로 만듦
			List<BoardPostVO> boardpostlist = bservise.boardPostAll();//보드 목록+ 게시글을 받아서 리스트로 만듦
			List<BoardVO> boardtop = bservise.boardTop();//보드 인기도 순 
			List<String> boardserch = bservise.boardSerch(request.getParameter("board_name"));//검색
			
			request.setAttribute("boardlist", boardlist);//보드리스트 값 리퀘스트에넣기
			request.setAttribute("boardpostlist", boardpostlist);//보드리스트 값 리퀘스트에넣기
			request.setAttribute("boardtop", boardtop);//보드탑 값 리퀘스트에넣기
			request.setAttribute("boardserch", boardserch);
		}
	
		//보드리드로 이동
		return "../view/boardView/BoardRead.jsp";
	}

}
