package com.shinD.controller.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.member.MemberService;
import com.shinD.model.member.MemberVO;

public class MemberSignUp implements CommonControllerInterface{

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest)data.get("request");
		System.out.println("넘어옴");
		
		List<MemberVO> service = new MemberService().registertUser("dd", "dd", "dd", 0);
		//int result = service.registertUser(null, null, null, 0)

		return "responseBody:ok";
	}
	
	private MemberVO doHandle(HttpServletRequest request) {
		MemberVO vo = new MemberVO();
		String encoding = "utf-8";
		 
		return null;
	}
}
