package com.shinD.controller.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.board.BoardPostVO;
import com.shinD.model.board.BoardService;
import com.shinD.model.board.BoardVO;
import com.shinD.model.post.PostService;
import com.shinD.model.post.PostVO;

public class BoardDetail implements CommonControllerInterface {
	static String board_name;//스태틱에 올려서 리다이렉트시 값 저장해놓기
	
	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");//입력받은 값 가져오기
		request.setCharacterEncoding("utf-8");//인코딩
		String method = (String)data.get("method");
		if(request.getParameter("BOARD_NAME")!=null) {
		board_name = request.getParameter("BOARD_NAME");//보드이름 변수에 저장
		}
		
		PostService pservice = new PostService();
		BoardService bservise = new  BoardService();
		
		if(method.equals("GET")) {
	
			int bcode = bservise.boardSerchCode(board_name);//보드 이름을 받아서 검색
			List<PostVO>postlist = pservice.PostSelect(bcode);//게시글 리스트를 보드번호를 받아서 뽑기
			
			
			request.setAttribute("board_name", board_name);//보드이름 보내주기
			//request.setAttribute("boardlist", boardlist);//보드목록 값 리퀘스트에넣기
			request.setAttribute("postlist", postlist);//보드목록 값 리퀘스트에넣기
			}else {
				
			}
		
		return "../view/boardView/BoardDetail.jsp";
	}

}
