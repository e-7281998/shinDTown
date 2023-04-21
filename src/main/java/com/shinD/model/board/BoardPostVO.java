package com.shinD.model.board;

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
public class BoardPostVO {
	private int BOARD_CODE;
	private int USER_CODE;
	private String BOARD_NAME;
	private int POST_CODE;
	private String POST_TITLE;
}
