package com.shinD.model.comment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class CommentService{
	CommentDAO dao = new CommentDAO();
	//댓글 생성
	public int CommentCreate(CommentVO comment) {
		return dao.CommentCreate(comment);
	}
}
