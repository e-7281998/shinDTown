package com.shinD.model.message;

import java.util.List;

import com.shinD.controller.message.ChatroomVO;

public class MessageService {
	MessageDAO messageDao = new MessageDAO();

			
	//지금 접속한 유저와의 채팅방만 조회
	 public List<ChatroomVO> selectChatRoom(int user_code) {
		return messageDao.selectChatRoom(user_code);
	 }
	 
		//내가 속한 채팅방목록 조회
	 public List<ChatroomVO> MessageToConnectMember(int chat_code, int chat_user_1_code, int chat_user_2_code) {
		return messageDao.MessageToConnectMember(chat_code, chat_user_1_code, chat_user_2_code);
	 }
	 
	//나와 상대가 접속한 채팅방의 메시지들 조회→지금 접속한 유저와의 채팅 클릭 후 실행
	 public List<MessageVO> readConnectRoom(int chat_code) {

		return messageDao.readConnectRoom(chat_code);
	 }
	 
		//쪽지 보내기
	 public int insertMessage(MessageVO messageVO) {
		return messageDao.insertMessage(messageVO);
	 
	 }
	 
		//쪽지 받기
	 public List<MessageVO> selectReceiveMessage(int message_chat_code, int sender) {
		return messageDao.selectReceiveMessage(message_chat_code, sender);
	 
	 }
	
	 public List<MessageVO> selectNotReadMessage(int user_code) {
		 return messageDao.selectNotReadMessage(user_code);
	 }
		 
	 public List<MessageVO> selectReadOneMessage(int message_code) {
		 return messageDao.selectReadOneMessage(message_code);
	 }
	 
	 
	public int makeNewChatRoom(int user_1_code, int user_2_code) {
		return messageDao.makeNewChatRoom(user_1_code, user_2_code);
	}
}
