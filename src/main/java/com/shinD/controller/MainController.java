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

import com.shinD.controller.board.BoardCreate;
import com.shinD.controller.board.BoardDelete;
import com.shinD.controller.board.BoardDetail;
import com.shinD.controller.board.BoardDupCheck;
import com.shinD.controller.board.BoardMain;
import com.shinD.controller.board.BoardRead;
import com.shinD.controller.comment.ComCode;
import com.shinD.controller.comment.CommentCheck;
import com.shinD.controller.comment.CommentCreate;
import com.shinD.controller.comment.CommentDelete;
import com.shinD.controller.comment.CommentLikes;
import com.shinD.controller.comment.CommentList;
import com.shinD.controller.comment.DeleteLike;
import com.shinD.controller.comment.com_userName;
import com.shinD.controller.git.Git;
import com.shinD.controller.member.MemberLogin;
import com.shinD.controller.member.MemberLogout;
import com.shinD.controller.member.MemberSignUp;
import com.shinD.controller.member.MemberUpdate;
import com.shinD.controller.member.MemberWithdraw;
import com.shinD.controller.message.InsertMessageController;
import com.shinD.controller.message.MessageToConnectMemberController;
import com.shinD.controller.message.ReadConnectRoomController;
import com.shinD.controller.message.SelectChatRoomController;
import com.shinD.controller.plan.CreatePlan;
import com.shinD.controller.plan.DeletePlan;
import com.shinD.controller.plan.DetailPlan;
import com.shinD.controller.plan.ReadPlan;
import com.shinD.controller.post.PostCheck;
import com.shinD.controller.post.PostCreate;
import com.shinD.controller.post.PostDelete;
import com.shinD.controller.post.PostLike;
import com.shinD.controller.post.PostLikeDelete;
 
@WebServlet("*.com")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
 		String path = request.getServletPath();  
 		
		CommonControllerInterface controller = null;
		
		Map<String, Object> data = new HashMap<>();
		data.put("method", request.getMethod());
		data.put("request", request); 
		data.put("response", response);

		
		switch (path) { 
		//은정
		case "/view/memberView/signup.com":
		case "/view/memberView/idDupCheck.com":
		case "/view/memberView/classCheck.com":
		case "/view/memberView/MemberSignUp.com":
 			controller = new MemberSignUp();
			 break;
		case "/view/memberView/login.com":
		case "/view/memberView/MemberLogin.com":
 			controller = new MemberLogin();
			 break; 
		case "/view/memberView/withdraw.com":
 			controller = new MemberWithdraw();
			 break; 
		case "/view/memberView/updatePwd.com":
		case "/view/memberView/MemberUpdate.com":
 			controller = new MemberUpdate();
			 break; 
		case "/view/memberView/logout.com":
 			controller = new MemberLogout();
			 break; 
		case "/view/gitView/gitRegister.com":
 		case "/view/gitView/getGitId.com":
		case "/view/gitView/gitUpdate.com":
 			controller = new Git();
			 break; 
		//지만
		case "/board/main.com":
			controller = new BoardMain();
			break;
		case "/board/create.com":
			controller = new BoardCreate();
			break;
		case "/board/read.com":
			controller = new BoardRead();
			break;
		case "/board/delete.com":
			controller = new BoardDelete();
			break;
		case "/board/dupcheck.com":
			controller = new BoardDupCheck();
			break;
		case "/board/detail.com":
			controller = new BoardDetail();
			break;
		case "/post/delete.com":
			controller = new PostDelete();
			break;
		case "/post/create.com":
			controller = new PostCreate();
			break;
		case "/post/likecheck.com":
			controller = new PostCheck();
			break;
		case "/post/like.com":
			controller = new PostLike();
			break;
		case "/post/likedelete.com":
			controller = new PostLikeDelete();
			break;
		case "/comment/create.com":
			controller = new CommentCreate();
			break;
		case "/comment/list.com":
			controller = new CommentList();
			break;
		//유진
		case "/view/calendarView/ReadPlan.com":
			controller = new ReadPlan();
			break;
		case "/view/calendarView/CreatePlan.com":
			controller = new CreatePlan();
			break;
		case "/view/calendarView/DetailPlan.com": 
			controller  = new DetailPlan();
			break;
		case "/view/calendarView/DeletePlan.com": 
			controller = new DeletePlan();
			break;
		case "/view/boardView/likes.com":
			controller = new CommentLikes();
			break;
		case "/view/boardView/delete.com":
			controller = new CommentDelete();
			break;
		case "/view/boardView/checklike.com":
			controller = new CommentCheck();
			break;
		case "/view/boardView/deletelike.com":
			controller = new DeleteLike();
			break;
		case "/view/boardView/getcomcode.com":
			controller = new ComCode();
			break;
		case "/view/boardView/getComUserName.com":
			controller = new com_userName();
			break;
		//진경
		case "/view/chatView/makeNewChatRoom.com":
			//controller = new MakeNewChatRoomController();
			break;	
			
		case "/view/chatView/selectChatRoom.com":
			controller = new SelectChatRoomController();
			break;
		case "/view/chatView/insertMessage.com":
 			controller = new InsertMessageController();
			 break;
		case "/view/chatView/messageToConnectMember.com":
 			controller = new MessageToConnectMemberController();
			 break;
		case "/view/chatView/readConnectRoom.com":
 			controller = new ReadConnectRoomController();
			 break;
		case "/view/chatView/selectReceiveMessage.com":
 			controller = new SelectReceiveMessageController();
 			break;
		case "/view/chatView/selectNotReadMessage.com":
			//System.out.println("notReadMem>>>>>>>");
 			controller = new SelectNotReadMessageController();
			 break;	 
		case "/view/chatView/selectReadOneMessage.com":
 			controller = new SelectReadOneMessageController();
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
		}else if(page.indexOf("responseBody:") >= 0){
 			response.getWriter().append(page.substring(13));
		}else if(page.indexOf("download") >= 0){ 
		}else { 
			RequestDispatcher rd;
			rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		
		
		
	}

}
