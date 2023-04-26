package com.shinD.model.post;

import java.util.List;

public class PostService {
	PostDAO dao = new PostDAO();
	
	// 게시글 목록(제목)
	public List<PostVO> PostSelect(int board_code) {
		return dao.PostSelect(board_code);
	}
	// 게시글 목록(게시글 번호로 검색)
	public PostVO PostDetail(int post_code) {
		return dao.PostDetail(post_code);
	}
		//게시글 모든 정보받아받아 게시글 생성
	public int PostCreate(String board_name,int user_code,String post_title,String post_content,String post_image, String post_source) {
		return dao.PostCreate(board_name, user_code, post_title, post_content, post_image, post_source);
	}
	//게시글+댓글 목록(게시판 번호로 검색)
	public List<PostCommentVO> PostComSelect(int board_code){
			return dao.PostComSelect(board_code);
		}
	
	//인기게시글 순서대로 가져오기
		public List<PostCommentVO> PostTop(){
			return dao.PostTop();
		}
		//게시글 좋아요
		public int postLike(int user_code,int post_code) {
			return dao.postLike(user_code, post_code);
		}
}
