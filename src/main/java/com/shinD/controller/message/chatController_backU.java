package com.shinD.controller.message;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.message.MessageService;

public class chatController_backU implements CommonControllerInterface{

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		System.out.println("chatControle  start");
		//쪽지 보내기
		String page="websocket.jsp";
		String method=(String) data.get("method");
		
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		
		// 입력된 파라메터를 읽어서 DB에 저장하기위해옴
		//System.out.println("111111");
		MessageVO message = makeMessage(request);
		System.out.println("22222222 makeMessage가 안만들어짐");
		MessageService service = new MessageService();
		int result = service.insertMessage(message);

		return page;
		
	}
	
	

	
	private MessageVO makeMessage(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		int message_code = Integer.parseInt(request.getParameter("message_code"));
		int chat_code = Integer.parseInt(request.getParameter("chat_code"));
		int sender=Integer.parseInt(request.getParameter("sender"));
		String message_data=request.getParameter("message_data");
		
		MessageVO mem = new MessageVO();
		//mem.setMessage_code(message_code); 자동생성
		mem.setChat_code(chat_code);
		mem.setSender(sender);
		mem.setMessage_data(message_data);
			
		return mem;
	}
}
