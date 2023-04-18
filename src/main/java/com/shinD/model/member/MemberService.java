package com.shinD.model.member;

import java.util.List;

public class MemberService {
	MemberDAO dao = new MemberDAO();
	public List<MemberVO> registertUser(String userName, String userId,String userPwd, int useClass){
		return dao.registertUser(userName, userId, userPwd, useClass);
	}
}
