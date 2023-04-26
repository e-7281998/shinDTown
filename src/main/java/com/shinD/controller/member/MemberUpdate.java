package com.shinD.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.member.MemberService;
import com.shinD.util.EncryptUtil;

public class MemberUpdate implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		MemberService service = new MemberService();
		
		HttpServletRequest request = (HttpServletRequest)data.get("request");
		
		String[] pathArr = request.getRequestURI().split("/");
		String path = pathArr[pathArr.length-1];
		
		if(path.equals("MemberUpdate.com"))
			return "MemberUpdate.jsp";
		
		String id = (String) request.getParameter("id");
		String pwd = (String) request.getParameter("pwd");
		 
		EncryptUtil encrypt = new EncryptUtil();
		String salt = encrypt.getSalt();
		pwd = encrypt.getEncrypt(pwd, salt);
 		int result = service.updatePwd(id, pwd, salt);
 		
		return "responseBody:"+result;
		}

}
