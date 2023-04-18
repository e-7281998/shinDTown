package com.shinD.model.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		//공유객체에서 쓰레드에 안전한 리스트를 만든다.
		List<PrintWriter> outList = Collections.synchronizedList(new ArrayList<>()) ;

		
		
		while(true) {//계속 해야 여러개의 클라이언트에게 메시지를 받을 숭 ㅣㅆ음.
			Socket socket = serverSocket.accept(); // client와 통신하기 위한것. 접속할때마다 생성된다.
			
			System.out.println("접속:" + socket);
			// 여러개의 클라이언트에게 메시지를 ㅏ받아서 여러개의 메시지를 보내야함.

			ChatThread chatThread = new ChatThread(socket, outList);// chat소켓으로 별도로 일을 시킴->공유객체 outList
			chatThread.start();
	}
}
}

class ChatThread extends Thread{
	private Socket socket;
	private List<PrintWriter> outList;
	private PrintWriter out;
	private BufferedReader in;
	
	public ChatThread(Socket socket, List<PrintWriter> outList) {//현재 연결된 클라이언트랑만 통신하기 위한것.
	
		this.socket = socket;
		this.outList = outList;
		
		//1.socket으로부터 읽어들일 수 있는 객체를 얻는다.
		//2.socket에게 쓰기 위한 객체를 얻는다(현재 연결된 클라이언트에게 쓰는 객체)
	

		try {
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			outList.add(out);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
	

	

	public void run() {
		// 3.클라이언트가 보낸 메시지를 읽는다.
		// 4.접속된 모든 클라이언트에게 메시지를 보낸다.(현재 접속된 모든 클라이언트에게 쓸 수 있는 객체가 필요함)

		String line = null;
		try {
			while ((line = in.readLine()) != null) {
				for (int i = 0; i < outList.size(); i++) {// 접속한 모든 클라이언트에게 메시지를 전송한다.
					PrintWriter o = outList.get(i);
					o.println(line);
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {// 접속이 끊어질때

			try {
				outList.remove(out);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

	}

}