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
import com.shinD.controller.board.BoardDetail;
import com.shinD.controller.board.BoardDupCheck;
import com.shinD.controller.board.BoardRead;
import com.shinD.controller.post.PostCreate;


@WebServlet("*.jm")
public class FrontController_JM extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();
		CommonControllerInterface controller = null;
		Map<String, Object> data = new HashMap<>();
		data.put("method", request.getMethod());
		data.put("request", request);

		System.out.println(path);
		switch (path) {
		// 보드 생성 컨트롤
		case "/board/create.jm":
			controller = new BoardCreate();
			break;

		// 보드 목록 컨트롤
		case "/board/read.jm":
			controller = new BoardRead();
			break;

		// 보드 삭제 컨트롤
		case "/board/delete.jm":
			controller = new BoardDelete();
			break;

		// 보드 중복 체크
		case "/board/dupcheck.jm":
			controller = new BoardDupCheck();
			break;

		// 보드 디테일
		case "/board/detail.jm":
			controller = new BoardDetail();
			break;

		// 게시글 생성
		case "/post/create.jm":
			controller = new PostCreate();
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

		if (page.indexOf("redirect:") >= 0) {
			response.sendRedirect(page.substring(9));
		} else if (page.indexOf("responseBody:") >= 0) {
			response.getWriter().append(page.substring(13));
			System.out.println("여기왓다감");
		} else if (page.indexOf("download") >= 0) {
		} else {
			RequestDispatcher rd;
			rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}

	}

}
