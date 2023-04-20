package com.shinD.model.member;

import java.util.List;

public class MemberService {
	MemberDAO dao = new MemberDAO();
	public int registertUser(String userName, String userId,String userPwd, int userClass, String salt){
		return dao.registertUser(userName, userId, userPwd, userClass, salt);
	}
	public String getSalt(String id) {
		return dao.getSalt(id);
	}
	public MemberVO loginCheck(String id, String pwd) {
		return dao.loginCheck(id, pwd);
	}
	public int dupCheck(String id) {
		return dao.dupCheck(id);
	}
	public int withdrawUser(String id) {
		return dao.withdrawUser(id);		
	}
}
