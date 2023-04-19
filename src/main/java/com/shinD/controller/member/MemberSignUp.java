package com.shinD.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.member.MemberService;
import com.shinD.model.member.MemberVO;
import com.shinD.util.EncryptUtil;

public class MemberSignUp implements CommonControllerInterface{

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest)data.get("request");
		
		MemberService service = new MemberService();
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		int classNum = Integer.parseInt(request.getParameter("class"));
		
		//비밀번호 암호화
		EncryptUtil encrypt = new EncryptUtil();
		String salt = encrypt.getSalt();
		pwd = encrypt.getEncrypt(pwd, salt);
		System.out.println("pwd.... => " + pwd);
		//회원가입 신청
		int result = service.registertUser(name, id, pwd, classNum, salt);
		String path = request.getContextPath();	
		return "redirect:"+path+"/view/memberView/MemberLogin.jsp";
	} 
}
