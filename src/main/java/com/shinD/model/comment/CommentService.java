package com.shinD.model.comment;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinD.model.post.LikeVO;



public class CommentService{
	CommentDAO dao = new CommentDAO();
	//댓글 생성
	public int CommentCreate(CommentVO comment) {
		return dao.CommentCreate(comment);
	}
	//댓글
	public List<CommentVO> ComList(int post_code){
		return dao.ComList(post_code);
	}
	
	public String com_userName(int com_code) {
		return dao.com_userName(com_code);
	}
	
	public int Likes(int com_code) {
		return dao.Likes(com_code);
	}
	
	public int LikeCreate(LikeVO like) {
		return dao.LikeCreate(like);
	}
	
	public int deleteComment(int com_code) {
		return dao.deleteComment(com_code);
	}
	
	public int CheckCom(int com_code, int user_code) {
		return dao.CheckCom(com_code, user_code);
	}
	public int DeleteLike(int com_code , int user_code) {
		return dao.DeleteLike(com_code, user_code);
	}
	public int getcom(int post_code, int user_code, String com_comment) {
		return dao.getcom(post_code, user_code, com_comment);
	}
}
