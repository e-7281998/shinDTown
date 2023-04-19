package com.shinD.controller.chat;

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
public class MessageVO {
	 private int MESSAGE_CODE;
	 private int CHAT_CODE;
	 private int SENDER;
	 private Date MESSAGE_CREATE;
	 private String MESSAGE_DATA;
	 private Boolean MESSAGE_OPEN;
}
