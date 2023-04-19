package com.shinD.controller.board;

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
public class BoardVO {
	private int BOARD_CODE;
	private int USER_CODE;
	private String BOARD_NAME;
	
}
