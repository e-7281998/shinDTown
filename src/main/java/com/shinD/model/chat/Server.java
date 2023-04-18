//package com.shinD.model.chatModel;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import org.json.simple.JSONObject;
//
//public class Server {
////    public static void main(String arg[])
////    {
//    	ServerSocket serverSocket;
//    	ExecutorService threadPool = Executors.newFixedThreadPool(100);//100개의 클라이언트가 동시에 채팅할 수 있도록 한다.
//    	Map<String, SocketClient> chatRoom = Collections.synchronizedMap(new HashMap());
//    	
//    	//서버 시작함
//    	public void start() throws IOException{
//    		serverSocket = new ServerSocket(50001);
//    		//50001번 port에 바인딩하는 ServerSocket 생성
//    		System.out.println("서버 시작됨");
//    		
//    		Thread thread = new Thread(()->{
//    			//작업 스레드가 처리할 것을 람다식으로 제공함.
//    			try {
//    				while(true) {
//    					Socket socket = serverSocket.accept();//연결 수락
//    					SocketClient sc = new SocketClient(this, socket);//통신용 SocketClient 반복해서 생성
//    				}
//    			}catch(IOException e) {
//    				
//    			}
//    		});
//    		thread.start();
//    	}
//    	
//    	//메소드: 클라이언트 연결 시 SOcketClient 생성 및 추가하는 역할
//    	public void addSocektClient(SocketClient socketClient) {
//    		//연결된 클라이언트의 SocketClient를 chatRoom에 추가하는 역할을 한다.
//    		
//    		String key = socketClient.chatName+"@"+socketClient.clientIp;//키 : chatName@clientIp 값:SocketClient
//    		chatRoom.put(key, socketClient);
//    		System.out.println(key+"님이 들어오셨습니다.");
//    		
//    	}
//    	//연결이 끊긴 클라이언트의 SocketClient를 chatRoom에서 제거하는 역할 -> chatRoom 자체가 제거되나..? 
//    	public void removeSocketClient(SocketClient socketClient) {
//    		String key = socketClient.chatName+"@"+socketClient.clientIp;
//    		chatRoom.remove(key);
//    		System.out.println(key+"님이 나갔습니다.");
//    	}
//    	
//    	//채팅방에 있는 클라이언트에게 JSON메시지를 보냄
//    	public void sendToAll(SocketClient sender, String message) {
//    		
//    		JSONObject root = new JSONObject();
//    		root.put("clientIp", sender.clientIp);
//    		root.put("chatName", sender.chatName);
//    		root.put("message", message);
//    		
//    		String json=root.toString();
//    		
//    		Collection<SocketClient> socketCLients = chatRoom.values();
//    		for(SocketClient sc: socketClients) {
//    			if(sc==sender) continue;
//    			sc.send(json);
//    		}
//    		
//    		
//    	}
//    	
//    	//메소드:서버 종료->채팅 서버를 종료시킴
//    	public void stop() {
//    		try {
//    			serverSocket.close();
//    			threadPool.shutdownNow();
//    			chatRoom.values().stream().forEach(sc->sc.close());
//    			System.out.println("[서버] 종료됨");
//    		
//    		}catch(IOException e1) {}
//    	}
//    	public static void main(String[] args) {
//			try {
//				ChatServer chatServer = new ChatServer();
//				chatServer.start();
//				
//				System.out.println("-----------------------------------");
//				System.out.println("서버를 종료하려면 q를 입력하고 Enter");
//				System.out.println("-----------------------------------");
//				
//				Scanner sc= new Scanner(System.in);
//				while(true) {
//					String key = sc.nextLine();
//					if(key.equals("q")) break;
//				}
//				sc.close();
//				chatServer.stop();
//			}catch(IOException e) {
//				System.out.println("[서버]"+e.getMessage());
//				
//			}
//		}
//    	
////        //접속한 Client와 통신하기 위한 Socket
////        Socket socket = null;    
////        //채팅방에 접속해 있는 Client 관리 객체                
////        User user = new User();        
////        //Client 접속을 받기 위한 ServerSocket            
////        ServerSocket server_socket=null;              
////        
////        int count = 0;                            
////        Thread thread[]= new Thread[10];             
////        
////        try {
////            server_socket = new ServerSocket(80);
////            //Server의 메인쓰레드는 게속해서 사용자의 접속을 받음
////            while(true)
////            {
////                socket = server_socket.accept();
////
////                thread[count] = new Thread((Runnable) new Receiver(user,socket));
////                thread[count].start();
////                count++;
////            }
////        }catch(Exception e) {};
//    //}
//}