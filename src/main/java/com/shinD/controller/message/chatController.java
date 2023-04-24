package com.shinD.controller.message;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.member.MemberDAO;
import com.shinD.model.member.MemberVO;
import com.shinD.model.message.MessageService;



public abstract class chatController implements CommonControllerInterface{
	//chatcode로 읽어서 색깔입혀야함  ->접속 여부 확인
	
	
	
	
	//내가 속한 채팅방목록 조회
	public String execute2(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");//입력받은 값 가져오기
		request.setCharacterEncoding("utf-8");
		String method = (String)data.get("method");
		String page = "";
		
		MessageService mService = new  MessageService();
		int chatCode = Integer.parseInt(request.getParameter("chat_code"));
		int chat_user_1_code = Integer.parseInt(request.getParameter("chat_user_1_code"));
		int chat_user_2_code = Integer.parseInt(request.getParameter("chat_user_2_code"));
		
		
		
		List<ChatroomVO> chatRoomlist = mService.MessageToConnectMember(chatCode,chat_user_1_code, chat_user_2_code);
		
		request.setAttribute("chatRoomList", chatRoomlist );//보드리스트 값 리퀘스트에넣기
	
		
		//포워드로 보내기 why? request값을 넘겨줘야하기 때문에
		return "websocket.jsp";
	}
	
	//나와 상대가 접속한 채팅방의 메시지들 조회
	
//	public String execute3(Map<String, Object> data) throws Exception {
////		HttpServletRequest request = (HttpServletRequest) data.get("request");//입력받은 값 가져오기
////		request.setCharacterEncoding("utf-8");//인코딩
////		String method = (String)data.get("method");
////		String page = "";
////		
////		MessageService mService = new  MessageService();
////		int chatCode = Integer.parseInt(request.getParameter("chat_code"));
////		int chat_user_1_code = Integer.parseInt(request.getParameter("chat_user_1_code"));
////		int chat_user_2_code = Integer.parseInt(request.getParameter("chat_user_2_code"));
////		
////
////		List<MessageVO> messagelist = mService.readConnectRoom(chat_user_1_code, chat_user_2_code);
////		
////		request.setAttribute("messagelist", messagelist );//보드리스트 값 리퀘스트에넣기
////	
////		
////		//포워드로 보내기 why? request값을 넘겨줘야하기 때문에
////		return "websocket.jsp";
//	}
//	
	
	

	/*
	 * //쪽지 받기 public String execute5(Map<String, Object> data) throws Exception {
	 * String page="websocket.jsp"; String method=(String) data.get("method");
	 * 
	 * HttpServletRequest request = (HttpServletRequest) data.get("request");
	 * MessageService service = new MessageService();
	 * 
	 * if(method.equals("POST")) { //입력된 파라메터를 읽어서 DB에 저장하기위해옴 // int
	 * message_chat_code = // List<MessageVO> messagelist =
	 * service.selectReceiveMessage(); //입력된 파라메터를 읽어서 DB에 저장하기위해옴 MessageVO message
	 * = makeEmp(request);
	 * 
	 * List<MessageVO> result =
	 * service.selectReceiveMessage(message.getChat_code(),message.getSender()); }
	 * 
	 * 
	 * return page;
	 * 
	 * }
	 */
	
	
	//읽지 않은 쪽지
	public String execute6(Map<String, Object> data) throws Exception {
		String page="websocket.jsp";
		String method=(String) data.get("method");
		
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		MessageService service = new MessageService();
		
		MessageVO messagevo = new MessageVO();
			
		
		return page;
	}
	
	
	
	
	//쪽지 읽기
//	public String execute7(Map<String, Object> data) throws Exception {
//		HttpServletRequest request = (HttpServletRequest) data.get("request");//입력받은 값 가져오기
//		request.setCharacterEncoding("utf-8");//인코딩
//		String method = (String)data.get("method");
//		String page = "";
//		
//		MessageService mService = new  MessageService();
//		int chatCode = Integer.parseInt(request.getParameter("chat_code"));
//		int chat_user_1_code = Integer.parseInt(request.getParameter("chat_user_1_code"));
//		int chat_user_2_code = Integer.parseInt(request.getParameter("chat_user_2_code"));
//		
//
//		List<MessageVO> messagelist = mService.readConnectRoom(chat_user_1_code, chat_user_2_code);
//		
//		request.setAttribute("messagelist", messagelist );//보드리스트 값 리퀘스트에넣기
//	
//		
//		//포워드로 보내기 why? request값을 넘겨줘야하기 때문에
//		return "websocket.jsp";
//	}


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
