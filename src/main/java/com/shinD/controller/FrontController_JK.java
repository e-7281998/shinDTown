package com.shinD.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinD.controller.message.MessageToConnectMemberController;
import com.shinD.controller.message.ReadConnectRoomController;
import com.shinD.controller.message.SelectChatRoomController;
import com.shinD.controller.message.chatController_backU;
 


@WebServlet("*.jk")
public class FrontController_JK extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
 	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		String path = request.getServletPath(); 
		CommonControllerInterface controller = null;
		Map<String, Object> data = new HashMap<>();
		data.put("method", request.getMethod());
		data.put("request", request);
		
		
		
		//해야할것: session에 loginUser가 있기떄문에 저장해야함.
		switch (path) { 
		//chat룸으로 이동
		case "/view/chatView/chat.jk":
 			controller = new chatController_backU();
			 break;
			 
		case "/view/chatView/selectChatRoom.jk":
 			controller = new SelectChatRoomController();
			 break;
		 
		case "/view/chatView/MessageToConnectMember.jk":
 			controller = new MessageToConnectMemberController();
			 break;
		
		case "/view/chatView/ReadConnectRoomController.jk":
 			controller = new ReadConnectRoomController();
			 break;
			 
			 
		default:
			break;
		}
		
		 
		String page = null;
		try {
			page = controller.execute(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(page.indexOf("redirect:") >= 0) {
 			response.sendRedirect(page.substring(9));
		}else if(page.indexOf("responseBody") >= 0){
 			response.getWriter().append(page.substring(13));
		}else if(page.indexOf("download") >= 0){ 
		}else { 
			RequestDispatcher rd;
			rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		
		
		
	}

}
