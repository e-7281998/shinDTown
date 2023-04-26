package com.shinD.controller.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.board.BoardPostVO;
import com.shinD.model.board.BoardService;
import com.shinD.model.board.BoardVO;
import com.shinD.model.post.PostCommentVO;
import com.shinD.model.post.PostService;
import com.shinD.model.post.PostVO;

public class BoardMain implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");//입력받은 값 가져오기
		request.setCharacterEncoding("utf-8");//인코딩
		String method = (String)data.get("method");
		
		BoardService bservise = new  BoardService();
		PostService pservice = new PostService();
		
		if(method.equals("GET")) {
			List<BoardVO> boardlist = bservise.boardAll();//보드 목록
			List<BoardPostVO> boardpostlist = bservise.boardPostAll();//보드 목록+게시글 을 받아서 리스트로 만듦
			List<BoardVO> boardtop = bservise.boardTop();//보드 인기도 순 
			List<PostCommentVO> posttop = pservice.PostTop();//인기 게시글 순으로 가져오기
			
			request.setAttribute("boardpostlist", boardpostlist);//보드리스트 값 리퀘스트에넣기
			request.setAttribute("boardtop", boardtop);//보드탑 값 리퀘스트에넣기
			request.setAttribute("boardlist", boardlist);//보드목록 값 리퀘스트에넣기
			request.setAttribute("posttop", posttop);//인기 게시글 리퀘스트에넣기
			}else {
				
			}
		
		return "../view/main.jsp";
	}

}
