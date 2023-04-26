package com.shinD.controller.member;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.member.MemberService;
import com.shinD.model.member.MemberVO;
import com.shinD.util.EncryptUtil;

public class MemberSignUp implements CommonControllerInterface{

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest)data.get("request");
		
		String[] pathArr = request.getRequestURI().split("/");
		String path = pathArr[pathArr.length-1];
		
		// id 중복 확인인지, 회원가입인지 확인하기
		if(path.equals("MemberSignUp.com"))
			return "MemberSignUp.jsp";
		else if(path.equals("signup.com"))
			return signHandle(request);
		else if(path.equals("classCheck.com"))
			return certiCheck(request);
		else
			return idDupcheck(request);
		
	} 
	//id 중복 체크
	public String idDupcheck(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println("아이디 체크하러 옴" + id);
		MemberService service = new MemberService();
		int result = service.dupCheck(id);
		
		return "responseBody:"+result;
	}
	
	//반 인증번호 확인
	public String certiCheck(HttpServletRequest request) {
 		ServletContext sc = request.getServletContext();
 		
		int classNum = Integer.parseInt(request.getParameter("class"));
		String verify = request.getParameter("verify"); 
		String certiValue = sc.getInitParameter("class"+classNum);
		 
		if(certiValue.equals(verify))	//인증번호 맞음
			return "responseBody:0";
		else
			return "responseBody:-1";
	}
		
	//회원가입
	public String signHandle(HttpServletRequest request) {
		
		String certicheck = certiCheck(request);
		if(certicheck.contains("-1")) {
			return "responseBody:-1";
		} 
		
		String path = request.getContextPath();	
		
		MemberService service = new MemberService();
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		int classNum = Integer.parseInt(request.getParameter("class"));
		
		 
		//비밀번호 암호화
		EncryptUtil encrypt = new EncryptUtil();
		String salt = encrypt.getSalt();
		pwd = encrypt.getEncrypt(pwd, salt);
 		//회원가입 신청
		int result = service.registertUser(name, id, pwd, classNum, salt);
		return "responseBody:"+result; 
	}
}
