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
}
