package com.shinD.controller.message;

import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.message.MessageService;

public class SelectChatRoomController implements CommonControllerInterface{
	//내가 속한 채팅방목록
	@Override
	public String execute(Map<String, Object> data) throws Exception {

		HttpServletRequest request = (HttpServletRequest) data.get("request");//입력받은 값 가져오기
		
		
		request.setCharacterEncoding("utf-8");//인코딩
		String method = (String)data.get("method");
		String page = "";
		
		HttpSession session = request.getSession();
		int user_code = (int)(session.getAttribute("user_code"));
		
		MessageService mService = new  MessageService();
	
		List<ChatroomVO> chatRoomlist = mService.selectChatRoom(user_code);
		
		//request.setAttribute("chatRoomList", chatRoomlist);
		session.setAttribute("chatRoomList", chatRoomlist);  
	
		
		String path=request.getContextPath();
		
		//포워드로 보내기 why? request값을 넘겨줘야하기 때문에
		return "/view/chatView/websocket.jsp";
		//responseBody:"+path+"
		
	}
	
}
