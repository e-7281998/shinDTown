package com.shinD.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.shinD.model.message.MessageService;
import com.shinD.model.message.MessageVO;

public class SelectNotReadMessageController implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		String page = "websocket.jsp";
		String method = (String) data.get("method");
		
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		MessageService service = new MessageService();
		HttpServletResponse response = (HttpServletResponse) data.get("response");
		HttpSession session = request.getSession();
		
		MessageVO messagevo = new MessageVO();
		System.out.println("message>>>>>"+messagevo);
		List<MessageVO> messagelist = service.selectNotReadMessage(Integer.parseInt(request.getParameter("user_code")));
		System.out.println("messagelist>>>>>>>"+ messagelist);
		
		JSONArray array = new JSONArray();
		
		
		for(MessageVO mem: messagelist) {
			JSONObject object = new JSONObject();
			
			object.put("message_code", mem.getMessage_code());
			object.put("sender", mem.getSender());
			object.put("message_create", mem.getMessage_create());
			object.put("message_data", mem.getMessage_data());
			object.put("message_open", mem.isMessage_open());
			object.put("chat_code", mem.getChat_code());
			array.add(object);
			
		}
		
		JSONObject memObj = new JSONObject();
		
		memObj.put("message", array);
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	
		return "responseBody:"+memObj.toString();
	}

	private MessageVO makeEmp(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		int message_code = Integer.parseInt(request.getParameter("message_code"));
		int chat_code = Integer.parseInt(request.getParameter("chat_code"));
		int sender=Integer.parseInt(request.getParameter("sender"));
		String message_data=request.getParameter("message_data");
		
		MessageVO mem = new MessageVO();
		mem.setMessage_code(message_code);
		mem.setChat_code(chat_code);
		mem.setSender(sender);
		mem.setMessage_data(message_data);
		
		return mem;
	}
	

}
