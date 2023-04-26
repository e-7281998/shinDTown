package com.shinD.model.message;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/*@ServerEndpoint는 클라이언트에서 서버로 접속 할 주소로 지정.
@OnMessage에서는 클라이언트로 부터 메시지가 도착했을때 처리.
@OnOpen은 클라이언트에서 서버로 접속할 때의 처리.
@OnClose는 접속이 끊겼을때 처리*/

@ServerEndpoint("/selectChatRoom.com")
public class WebSocket {

	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

	// 웹 소켓 연결시 호출
	@OnOpen
	public void onOpen(Session session) {
		System.out.println(session);
		clients.add(session);
	}

	// 웹소켓 메시지 수신시 호출
	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		System.out.println("~" + message);
		synchronized (clients) {
			for (Session client : clients) {
				if (!client.equals(session)) {
					client.getBasicRemote().sendText(message);
				}
			}
		}
	}

	
	
	// 웹 소켓이 닫힐 때 세션 제거
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
	}
	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
	
	
	
}