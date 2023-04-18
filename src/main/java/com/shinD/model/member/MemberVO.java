package com.shinD.model.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberVO {
	int USER_CODE;
	String USER_NAME;
	String USER_ID;
	String USER_PWD;
	int USER_CLASS;
}
