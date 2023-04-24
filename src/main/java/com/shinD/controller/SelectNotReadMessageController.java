package com.shinD.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.message.MessageVO;
import com.shinD.model.message.MessageService;

public class SelectNotReadMessageController implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		String page = "websocket.jsp";
		String method = (String) data.get("method");
		
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		MessageService service = new MessageService();
		
		MessageVO messagevo = new MessageVO();
		
		
		return null;
	}

}
