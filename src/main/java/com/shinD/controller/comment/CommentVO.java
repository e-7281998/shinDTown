package com.shinD.controller.comment;

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
public class CommentVO {
	 private int COM_CODE;
	 private int POST_CODE;
	 private int USER_CODE;
	 private String COM_COMMENT;
	 private int COM_LIKES;
	 private Date COM_CREATE;
}
