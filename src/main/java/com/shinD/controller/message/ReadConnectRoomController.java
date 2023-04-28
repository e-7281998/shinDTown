package com.shinD.controller.message;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.message.MessageService;
import com.shinD.model.message.MessageVO;

public class ReadConnectRoomController implements CommonControllerInterface{
	//나와 상대가 접속한 채팅방의 메시지들 조회→접속하지 않아도 채팅방 클릭시 메시지 조회 가능
	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		HttpServletResponse response = (HttpServletResponse) data.get("response");
		
		request.setCharacterEncoding("utf-8");
		String method = (String) data.get("method"); 
		String page="websocket.jsp";
		
		MessageService mService = new MessageService();
		int chat_code = Integer.parseInt(request.getParameter("chat_code"));
		
		//메시지 조회
		List<MessageVO> messagelist = mService.readConnectRoom(chat_code);
		
		JSONArray array = new JSONArray();
		
		for(MessageVO mem: messagelist) {
			JSONObject object = new JSONObject();
			
			object.put("message_code", mem.getMessage_code());
			object.put("sender", mem.getSender());
			object.put("message_create", mem.getMessage_create());
			object.put("message_data", mem.getMessage_data());
			object.put("message_open", mem.isMessage_open());
			
			array.add(object);
		}
		
		JSONObject memObj = new JSONObject();
		
		memObj.put("message", array);
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		System.out.println(">>>>>>>>>"+memObj.toString());
		
		return "responseBody:"+memObj.toString();
	}

}

