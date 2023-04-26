package com.shinD.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.model.message.MessageService;
import com.shinD.model.message.MessageVO;

public class SelectReceiveMessageController implements CommonControllerInterface {
	
	//특정 메시지 받기->채팅방을 선택하기

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		
//		select MESSAGE_DATA from MESSAGES JOIN CHATROOMS ON MESSAGES.CHAT_CODE = CHATROOMS.CHAT_CODE
//				WHERE MESSAGES.CHAT_CODE = ? and not sender=?;
		
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		request.setCharacterEncoding("utf-8");
		String method = (String) data.get("method");
		String page="";
		
		MessageService mService = new MessageService();
		int chat_code = Integer.parseInt(request.getParameter("chat_code"));
		int sender = Integer.parseInt(request.getParameter("sender"));
		
		List<MessageVO> messagelist = new ArrayList<>();
		
		request.setAttribute("messagelist", messagelist);
		
		
		
		return "websocket.jsp";
	}
}
