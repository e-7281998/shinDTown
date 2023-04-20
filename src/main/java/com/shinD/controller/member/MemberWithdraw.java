package com.shinD.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.member.MemberService;

public class MemberWithdraw implements CommonControllerInterface{

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest)data.get("request");
		
		MemberService srevice = new MemberService();
		
		String id = request.getParameter("id");
		int result = srevice.withdrawUser(id);
		if(result == 0) {
			HttpSession session = request.getSession();
			session.invalidate();
 			return "responseBody:1";
		} 
		else
			return "responseBody:-1";
				
		
	}

}
