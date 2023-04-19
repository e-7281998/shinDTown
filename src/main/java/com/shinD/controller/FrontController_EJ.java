package com.shinD.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shinD.controller.member.MemberLogin;
import com.shinD.controller.member.MemberSignUp;
import com.shinD.model.member.MemberService;
 
@WebServlet({"/view/memberView/signup","/view/memberView/login"})
public class FrontController_EJ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ServletContext application;

 	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
 		application = getServletContext();
		//String path = request.getServletPath(); 
 		String path = request.getRequestURI().substring(request.getContextPath().length());
		CommonControllerInterface controller = null;
		Map<String, Object> data = new HashMap<>();
		data.put("method", request.getMethod());
		data.put("request", request);
		
		
		System.out.println("aaaa:" + request.getServletPath());
		System.out.println("bbb:" + path);
		
		switch (path) { 
		case "/view/memberView/signup":
 			controller = new MemberSignUp();
			 break;
		case "/view/memberView/login":
 			controller = new MemberLogin();
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
