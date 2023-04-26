package com.shinD.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.model.message.MessageService;
import com.shinD.model.message.MessageVO;

public class SelectReadOneMessageController implements CommonControllerInterface {
	
	@Override
	public String execute(Map<String, Object> data) throws Exception {
		
		String page="websocket.jsp";
		String method = (String) data.get("method");
		
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		MessageService mService = new MessageService();
		
		MessageVO message = makeEmp(request);
		
		System.out.println("message>>>>>>>>>>.."+message);
		
		
		return null;
	}

	private MessageVO makeEmp(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		int message_code = Integer.parseInt(request.getParameter("message_code"));
		int chat_code = Integer.parseInt(request.getParameter("chat_code"));
		int sender = Integer.parseInt(request.getParameter("sender"));
		String message_data = request.getParameter("message_data");
		
		MessageVO mem=new MessageVO();
		mem.setMessage_code(message_code);
		mem.setChat_code(chat_code);
		mem.setSender(sender);
		mem.setMessage_data(message_data);
		
		mem.setMessage_open(true);
		
		
		return mem;
		
	}
}
