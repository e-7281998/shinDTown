package com.shinD.controller.message;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.controller.message.ChatroomVO;
import com.shinD.model.message.MessageService;

public class MakeNewChatRoomController implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		
		System.out.println("makeNewChatController ");
		
		String page = "websocket.jsp";
		String method = (String) data.get("method");
		
		HttpServletRequest request  = (HttpServletRequest) data.get("request");
		int user_1_code = Integer.parseInt(request.getParameter("user_1_code"));
		int user_2_code = Integer.parseInt(request.getParameter("user_2_code"));
		
		MessageService service = new MessageService();
		
		int result = service.makeNewChatRoom(user_1_code, user_2_code);
				
		return "responseBody:"+result; //수정
	}


}
