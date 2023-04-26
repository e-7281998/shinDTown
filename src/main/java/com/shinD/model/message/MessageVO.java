package com.shinD.model.message;

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
	 private int message_code;
	 private int chat_code;
	 private int sender;
	 private Date message_create;
	 private String message_data;
	 private boolean message_open;
}
