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
import com.shinD.controller.message.InsertMessageController;
 


@WebServlet("*.jk")
public class FrontController_JK extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
 	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		String path = request.getServletPath(); 
		CommonControllerInterface controller = null;
		Map<String, Object> data = new HashMap<>();
		data.put("method", request.getMethod());
		data.put("request", request);
		data.put("response", response);
		
		
		
		//해야할것: session에 loginUser가 있기떄문에 저장해야함.
		switch (path) { 
		
		// 내가 속한 채팅방목록
		case "/view/chatView/selectChatRoom.jk":
			controller = new SelectChatRoomController();
			break;
		
		//chat보내기
		case "/view/chatView/insertMessage.jk":
 			controller = new InsertMessageController();
			 break;
		
		// 지금 접속한 유저와의 채팅방 ->리턴값 :접속한 채팅방
		case "/view/chatView/messageToConnectMember.jk":
 			controller = new MessageToConnectMemberController();
			 break;
		
		//나와 상대가 접속한 채팅방의 데이터→접속하지 않아도 채팅 내용 확인 가능	 
		case "/view/chatView/readConnectRoom.jk":
 			controller = new ReadConnectRoomController();
			 break;
		
		//메시지 받기
		case "/view/chatView/selectReceiveMessage.jk":
 			controller = new SelectReceiveMessageController();
 			break;
 			
		//읽지 않은 메시지
		case "/view/chatView/selectNotReadMessage.jk":
 			controller = new SelectNotReadMessageController();
			 break;	 
		
		//특정 메시지 읽기
		case "/view/chatView/selectReadOneMessage.jk":
 			controller = new SelectReadOneMessageController();
			 break;	 
//		//접속중인 사람 목록 보기
//		case "/view/chatView/messageToConnectMember.jk":
// 			controller = new MessageToConnectMemberController();
//			 break;	 
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
