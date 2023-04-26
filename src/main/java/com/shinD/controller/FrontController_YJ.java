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

import com.shinD.controller.comment.ComCode;
import com.shinD.controller.comment.CommentCheck;
import com.shinD.controller.comment.CommentDelete;
import com.shinD.controller.comment.CommentLikes;
import com.shinD.controller.comment.DeleteLike;
import com.shinD.controller.plan.CreatePlan;
import com.shinD.controller.plan.DeletePlan;
import com.shinD.controller.plan.DetailPlan;
import com.shinD.controller.plan.ReadPlan;
 
@WebServlet({"/view/calendarView/ReadPlan", "/view/calendarView/CreatePlan", 
	"/view/calendarView/DetailPlan", "/view/calendarView/DeletePlan", "/view/boardView/likes",
	"/view/boardView/delete", "/view/boardView/checklike", "/view/boardView/deletelike",
	"/view/boardView/getcomcode"})
public class FrontController_YJ extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
 	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		String path = request.getServletPath(); 
		CommonControllerInterface controller = null;
		Map<String, Object> data = new HashMap<>();
		data.put("method", request.getMethod());
		data.put("request", request);
		data.put("response", response);
		
		switch (path) {
		case "/view/calendarView/ReadPlan.com": {
			controller = new ReadPlan();
			break;
		}
		case "/view/calendarView/CreatePlan.com": {
			controller = new CreatePlan();
			break;
		}
		case "/view/calendarView/DetailPlan.com" : {
			controller  = new DetailPlan();
			break;
		}
		case "/view/calendarView/DeletePlan.com" : {
			controller = new DeletePlan();
			break;
		}
		case "/view/boardView/likes.com" : {
			controller = new CommentLikes();
			break;
		}
		case "/view/boardView/delete.com" : {
			controller = new CommentDelete();
			break;
		}
		case "/view/boardView/checklike.com" : {
			controller = new CommentCheck();
			break;
		}
		case "/view/boardView/deletelike.com" : {
			controller = new DeleteLike();
			break;
		}
		case "/view/boardView/getcomcode.com" : {
			controller = new ComCode();
			break;
		}
		
		}
	
		
		 
		String page = "";
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
