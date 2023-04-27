package com.shinD.model.post;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostCommentVO {
	private int POST_CODE;
	private int BOARD_CODE;
	private int USER_CODE;
	private String POST_TITLE;
	private String POST_CONTENT;
	private String POST_IMAGE;
	private String POST_SOURCE;
	private Date POST_CREATE;
	private String USER_NAME;
	private int POST_COMS;
	private int POST_LIKES;
}
