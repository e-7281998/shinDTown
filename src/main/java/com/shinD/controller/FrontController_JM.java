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

import com.shinD.controller.board.BoardCreate;
import com.shinD.controller.board.BoardDelete;
import com.shinD.controller.board.BoardRead;
 
@WebServlet("*.do")
public class FrontController_JM extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
 	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		String path = request.getServletPath(); 
		CommonControllerInterface controller = null;
		Map<String, Object> data = new HashMap<>();
		data.put("method", request.getMethod());
		data.put("request", request);
		
		System.out.println(path);
		switch (path) { 
		//보드 생성 컨트롤
		case "/board/create.do":
 			controller = new BoardCreate();
			 break;
		
		//보드 목록 컨트롤
		case "/board/read.do":
			controller = new BoardRead();
			break;

		//보드 삭제 컨트롤
		case "/board/delete.do":
			controller = new BoardDelete();
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
