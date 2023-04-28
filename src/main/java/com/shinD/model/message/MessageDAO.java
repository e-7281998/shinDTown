package com.shinD.model.message;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.shinD.controller.message.ChatroomVO;
import com.shinD.util.MySQLUtil;


public class MessageDAO {
	//필드 안에 있는 변수이므로 따로 초기화 안해줘도 됨.
		Connection conn;
		Statement st;
		PreparedStatement pst; //?지원
		PreparedStatement pst2; //?지원
		CallableStatement cst;//sp지원
		ResultSet rs; //결과값을 넣어야함
		int resultCount; //insert, update, delete건수
		int result=0;
		
		//채팅방 목록에 없는 사람과의 채팅을 하게 된다면 방이 새로 만들어져야함.
		//목록에 없는 사람은 어떻게 구하지?
		
		//목록은 chatRoomList라는 세션에 저장을 했음.
		//chatRoomList에 있는 유저코드를 가져와서 존재하는지 접속중인 유저코드와 비교하자
		
		//chatroom에서 1번이 나거나 2번이 나일경우 채팅방이 존재하기 떄문에 sql문 실행이 안됨
		
		public int makeNewChatRoom(int user_1_code, int user_2_code) {
			conn = MySQLUtil.getConnection();// db연결
			
			try {
				String checkSql="""
						select count(*) as count from chatrooms 
						where (CHAT_USER_1_CODE=? and CHAT_USER_2_CODE=?)
						or(CHAT_USER_2_CODE=? and CHAT_USER_1_CODE=?);
						""";
				
				String sql = """
						insert into chatrooms values(null, ?, ?) 			
						""";
				//chatcode를 알아내는 sql문 작성
				
				String findChatCode = """
						select chat_code from chatrooms 
						where (CHAT_USER_1_CODE=? and CHAT_USER_2_CODE=?)
						or(CHAT_USER_2_CODE=? and CHAT_USER_1_CODE=?);
						""";
				
				
				//checkSql을 실행해서 그 값이 0이먄 실행.
				
				pst = conn.prepareStatement(checkSql);

				pst.setInt(1, user_1_code);
				pst.setInt(2, user_2_code);
				pst.setInt(3, user_1_code);//내가 2번인 경우이므로
				pst.setInt(4, user_2_code);
				rs=pst.executeQuery();
				
				while(rs.next()) {
					result = rs.getInt("count");
				}
				MySQLUtil.dbDisconnect(rs, pst, conn);
				conn = MySQLUtil.getConnection();// db연결 
				
				if(result==0) {
					pst = conn.prepareStatement(sql);
					
					pst.setInt(1, user_1_code);
					pst.setInt(2, user_2_code);
					pst.executeUpdate();
					
					MySQLUtil.dbDisconnect(rs, pst, conn);	
					conn = MySQLUtil.getConnection();// db연결 
				}
				//chat_code찾기!
				pst = conn.prepareStatement(findChatCode);

				pst.setInt(1, user_1_code);
				pst.setInt(2, user_2_code);
				pst.setInt(3, user_1_code);//내가 2번인 경우이므로
				pst.setInt(4, user_2_code);
				
				while(rs.next()) {
					//result에 챗코드가 옵니다요
					result = rs.getInt("chat_code");
				}  
				
				
		    } catch (SQLException e) {
		      e.printStackTrace();
		    } finally {
		    
		      MySQLUtil.dbDisconnect(rs, pst, conn);
		    }
			
			return result;
		}
			
		//내가 들어있는 채팅방의 message_open여부를 배열에 받아옴
		//원래 있던 chatcode와 들어있는 chatcode와 비교해서 같으면서 매시지가 오픈이 1이면 하트로!(안읽었음으로)
		
		
		//내가 속한 채팅방목록
		 public List<ChatroomVO> selectChatRoom(int user_code) {
				//검증하는 sql 문
	
				List<ChatroomVO> chatRoomlist = new ArrayList<>();
				conn=MySQLUtil.getConnection();//db연결
				
				try {
					
					String sql="""
							select a.chat_code, a.user_code as user1_code ,a.user_name as user1_name,  
							b.user_code as user2_code, b.user_name as user2_name
							from (select * from chatrooms
									join users on chatrooms.chat_user_1_code = users.user_code) a
									join
										(select * from chatrooms
											join users on chatrooms.chat_user_2_code = users.user_code) b
							on a.chat_code= b.chat_code
							where  a.user_code = ? or  b.user_code=?
							""" ;//모든메시지를 다 읽어라
				
					pst = conn.prepareStatement(sql);
				

					pst.setInt(1, user_code);
					pst.setInt(2, user_code );
				
					//st=conn.createStatement();//통로를 만들어야함.sql문장을 보내면 됨.
					
					rs = pst.executeQuery();//실행은 디비에서 하고 결과는 자바쪽으로 온것
					
					
					while (rs.next()) {// 데이터를 읽어서 arraylist에 담음.
						ChatroomVO chatroom = new ChatroomVO();

						chatroom.setChat_code(rs.getInt("chat_code"));

						if (rs.getInt("user1_code") == user_code) {
							chatroom.setFriend_code(rs.getInt("user2_code"));
							chatroom.setFriend_name(rs.getString("user2_name"));

						} else {
							chatroom.setFriend_code(rs.getInt("user1_code"));
							chatroom.setFriend_name(rs.getString("user1_name"));
						}

						chatRoomlist.add(chatroom);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					MySQLUtil.dbDisconnect(rs, st, conn);
				}
				return chatRoomlist;
			}

			// 지금 접속한 유저와의 채팅방 ->리턴값 :접속한 채팅방
			public List<ChatroomVO> MessageToConnectMember(int chat_code, int chat_user_1_code, int chat_user_2_code) {
				List<ChatroomVO> chatRoomList = new ArrayList<>();
				conn = MySQLUtil.getConnection();// db연결

				try {
					String sql = """
							select * from chatrooms
							where CHAT_USER_1_CODE = ? ,
							CHAT_USER_2_CODE=?orCHAT_USER_1_CODE = ?  ,
							CHAT_USER_2_CODE=?;
							""";
					pst = conn.prepareStatement(sql);

					pst.setInt(1, chat_user_1_code);
					pst.setInt(2, chat_user_2_code);
					pst.setInt(3, chat_user_1_code);
					pst.setInt(4, chat_user_2_code);

					result = pst.executeUpdate();

					while (rs.next()) {// 데이터를 읽어서 arraylist에 담음. 값은 하나인데 배열말고 리스트에 저장->검색이 용이
						ChatroomVO chat = makeEmp4(rs);
						chatRoomList.add(chat);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {

					MySQLUtil.dbDisconnect(rs, pst, conn);
				}
				return chatRoomList;
			}
		
		
			//나와 상대가 접속한 채팅방의 데이터→접속하지 않아도 채팅 내용 확인 가능
		 public List<MessageVO> readConnectRoom(int chat_code) {
				//검증하는 sql 문
	
				List<MessageVO> messagelist = new ArrayList<>();
				conn=MySQLUtil.getConnection();//db연결
				
				try {
					
					String sql="""
							select * from MESSAGES 
							WHERE CHAT_CODE 
								=?
							""" ;//모든메시지를 다 읽어라
				
					pst = conn.prepareStatement(sql);
		

					pst.setInt(1, chat_code);
					
				
					st=conn.createStatement();//통로를 만들어야함.sql문장을 보내면 됨.
					
					rs = pst.executeQuery();//실행은 디비에서 하고 결과는 자바쪽으로 온것
				
					
					
					while(rs.next()) {//데이터를 읽어서 arraylist에 담음.
						MessageVO message = new MessageVO();
						message.setMessage_code(rs.getInt("message_code"));
						message.setChat_code(rs.getInt("chat_code"));
						message.setSender(rs.getInt("sender"));
						message.setMessage_create(rs.getDate("message_create"));
						message.setMessage_data(rs.getString("message_data"));
						message.setMessage_open(rs.getBoolean("message_open"));
						messagelist.add(message);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					MySQLUtil.dbDisconnect(rs, st, conn);
				}
			
				//System.out.println(messagelist+"<<<<<<message");
				return messagelist;
			}
		
		
		
		
		//메시지 보내기
		 public int insertMessage(MessageVO messageVO) {
			 	
			 	conn=MySQLUtil.getConnection();//db연결
			    
			    try {
			      String sql = "insert into messages(chat_code,sender,message_data) values(?,?,?)";
			      pst = conn.prepareStatement(sql);
			      
			      pst.setInt(1, messageVO.getChat_code());
			      pst.setInt(2, messageVO.getSender());
			      pst.setString(3, messageVO.getMessage_data());
			      System.out.println("message======"+messageVO.getMessage_data());
					
			      pst.executeUpdate();
			      
			    } catch (SQLException e) {
			      e.printStackTrace();
			    } finally {
			    
			      MySQLUtil.dbDisconnect(rs, pst, conn);
			    }
			    return result;
			  }
		 
		 //메시지 받기
		 public List<MessageVO> selectReceiveMessage(int message_chat_code, int sender) {
				//검증하는 sql 문
	
				List<MessageVO> messagelist = new ArrayList<>();
				conn=MySQLUtil.getConnection();//db연결
				
				try {
					
					String sql="""
							select MESSAGE_DATA from MESSAGES 
							JOIN CHATROOMS ON MESSAGES.CHAT_CODE = CHATROOMS.CHAT_CODE
							WHERE MESSAGES.CHAT_CODE = ? and not sender=?;
							""" ;//모든메시지를 다 읽어라
				
					pst = conn.prepareStatement(sql);
		

					pst.setInt(1, message_chat_code);
					pst.setInt(2, sender);
				
				
					st=conn.createStatement();//통로를 만들어야함.sql문장을 보내면 됨.
					
					rs = pst.executeQuery();//실행은 디비에서 하고 결과는 자바쪽으로 온것
					
					while(rs.next()) {//데이터를 읽어서 arraylist에 담음.
						MessageVO message = makeEmp(rs);
						messagelist.add(message);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					MySQLUtil.dbDisconnect(rs, st, conn);
				}
				return messagelist;
			}
		
		 
		 //읽지 않은 메시지
		 public List<MessageVO> selectNotReadMessage(int user_code) { 
				//검증하는 sql 문
	
				List<MessageVO> messagelist = new ArrayList<>();
				conn=MySQLUtil.getConnection();//db연결
				
				try {
					
					String sql="""
							select distinct(messages.chat_code) as chat_code from MESSAGES 
							JOIN CHATROOMS ON MESSAGES.CHAT_CODE = CHATROOMS.CHAT_CODE
							WHERE MESSAGE_OPEN = '0' and (chatrooms.chat_user_1_code=? or chatrooms.chat_user_2_code=?)
							""" ;//
					//
				
					pst = conn.prepareStatement(sql);
					
					pst.setInt(1, user_code);
					pst.setInt(2, user_code);
				

					//st=conn.createStatement();//통로를 만들어야함.sql문장을 보내면 됨. pst, st 둘중 하나만 해도됨.
					
					rs = pst.executeQuery();//실행은 디비에서 하고 결과는 자바쪽으로 온것
					
					while(rs.next()) {//데이터를 읽어서 arraylist에 담음.
						MessageVO message = new MessageVO();
						message.setChat_code(rs.getInt("chat_code"));
						messagelist.add(message);
					}
					System.out.println(("messagelist>>>"+messagelist));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					MySQLUtil.dbDisconnect(rs, st, conn);
				}
				return messagelist;
			}
		
		 
		 
		 
		 
		 //특정 메시지 읽기
		 public List<MessageVO> selectReadOneMessage(int message_code) {
				//검증하는 sql 문
	
				List<MessageVO> messagelist = new ArrayList<>();
				conn=MySQLUtil.getConnection();//db연결
				
				try {
					
					String sql1="""
							select MESSAGE_DATA from MESSAGES 
							where MESSAGE_CODE = ?
							""" ;//모든메시지를 다 읽어라
					
					String sql2="""
							update MESSAGES set MESSAGE_OPEN ='1' where MESSAGE_CODE = ? 
							""" ;//읽은건 다 1로 처리
					//message_code를 받아와 변경
					
					
					pst = conn.prepareStatement(sql1);
					pst2 = conn.prepareStatement(sql2);

					pst.setInt(1, message_code);
					pst2.setInt(1, message_code );
				
					st=conn.createStatement();//통로를 만들어야함.sql문장을 보내면 됨.
					
					rs = pst.executeQuery();//실행은 디비에서 하고 결과는 자바쪽으로 온것
					rs = pst2.executeQuery();
					
					
					while(rs.next()) {//데이터를 읽어서 arraylist에 담음.
						MessageVO message = makeEmp(rs);
						messagelist.add(message);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					MySQLUtil.dbDisconnect(rs, st, conn);
				}
				return messagelist;
			}


			private MessageVO makeEmp3(ResultSet rs) throws SQLException {
				MessageVO message = new MessageVO();

				message.setMessage_code(rs.getInt("MESSAGE_CODE"));
				return message;
			}

			//
			private MessageVO makeEmp(ResultSet rs) throws SQLException {
				MessageVO message = new MessageVO();// 값이 너무 많아서 직접 넣기가 복잡함
				// MESSAGE_DATA
			

				return message;
			}
			
			private ChatroomVO makeEmp4(ResultSet rs) throws SQLException {
				ChatroomVO chat = new ChatroomVO();
				
//				chat.setCHAT_CODE(rs.getInt("CHAT_CODE"));
//				chat.setCHAT_USER_1_CODE(rs.getInt("CHAT_USER_1_CODE"));
//				chat.setCHAT_USER_2_CODE(rs.getInt("CHAT_USER_2_CODE"));
//			
				
				return chat;
			}


}
