package com.shinD.controller.message;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.member.MemberVO;
import com.shinD.model.message.MessageService;

public class MessageToConnectMemberController implements CommonControllerInterface{
	//지금 접속한 유저와의 채팅방만 조회
	
	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");//입력받은 값 가져오기
		request.setCharacterEncoding("utf-8");//인코딩
		String method = (String)data.get("method");
		String page = "websocket.jsp";
		
		MessageService mService = new  MessageService();
		int chatCode = Integer.parseInt(request.getParameter("chat_code"));
		int chat_user_1_code = Integer.parseInt(request.getParameter("chat_user_1_code"));
		int chat_user_2_code = Integer.parseInt(request.getParameter("chat_user_2_code"));
		
		
		//현재 로그인한 사람
		ServletContext app = request.getServletContext();
		HttpSession session = request.getSession();
		
		MemberVO mem = (MemberVO) app.getAttribute("user_code");
		
		List<MemberVO> memberList= null;
		
		if(mem!=null) {
			memberList = (List<MemberVO>) mem;
			if(memberList.contains(chat_user_1_code) || memberList.contains(chat_user_2_code)) {
				List<ChatroomVO> chatRoomlist = mService.MessageToConnectMember(chatCode,chat_user_1_code, chat_user_2_code);
				
				request.setAttribute("chatRoomList", chatRoomlist );//보드리스트 값 리퀘스트에넣기
			}		
		}
					
		//포워드로 보내기 why? request값을 넘겨줘야하기 때문에
		return page;
		
	}
}
