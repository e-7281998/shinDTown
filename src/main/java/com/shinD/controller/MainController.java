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

import com.shinD.controller.git.Git;
import com.shinD.controller.member.MemberLogin;
import com.shinD.controller.member.MemberLogout;
import com.shinD.controller.member.MemberSignUp;
import com.shinD.controller.member.MemberUpdate;
import com.shinD.controller.member.MemberWithdraw;
 
@WebServlet("*.com")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
 		String path = request.getServletPath();  
 		
		CommonControllerInterface controller = null;
		
		Map<String, Object> data = new HashMap<>();
		data.put("method", request.getMethod());
		data.put("request", request); 
		
		switch (path) { 
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
