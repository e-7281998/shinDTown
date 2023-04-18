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
import javax.servlet.http.HttpSession;
 
@WebServlet("/*")
public class FrontController_YJ extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
 	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		String path = request.getServletPath(); 
		CommonControllerInterface controller = null;
		Map<String, Object> data = new HashMap<>();
		data.put("method", request.getMethod());
		data.put("request", request);
		
		/*
		switch (path) { 
		case "/site-result/adminPicAjax.do":
 			controller = new AdminPicAjaxController();
			 break;
		 
		default:
			break;
		}
		*/
		 
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
