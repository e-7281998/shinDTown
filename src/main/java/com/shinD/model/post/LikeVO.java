package com.shinD.model.post;

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
public class LikeVO {
	private int LIKE_CODE;
	private int USER_CODE;
	private int POST_CODE;
	private int COM_CODE;
	private int LIKEY;
}
