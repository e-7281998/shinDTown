package com.shinD.controller.message;

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
public class ChatroomVO {
	private int chat_code;
	private int friend_code;
	private String friend_name;
	
	private int chat_user_1_code;
	private int chat_user_2_code;
}