package com.shinD.controller.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.member.MemberService;
import com.shinD.model.member.MemberVO;
import com.shinD.util.EncryptUtil;

public class MemberLogin implements CommonControllerInterface{

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest)data.get("request");
 		
		MemberService service = new MemberService();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//id에 해당하는 salt값 가져오기
		String salt = service.getSalt(id);
		//입력한 pwd+salt 암호화 
		pwd = new EncryptUtil().getEncrypt(pwd, salt);
		//로그인 시도
		MemberVO member = service.loginCheck(id, pwd);
		
		ServletContext app = request.getServletContext();
		Object obj = app.getAttribute("memberList");
		List<MemberVO> memberList = null;
		
		if(member != null) {//로그인 성공 
			if(obj == null) {
				memberList = new ArrayList<>();
			}else {
				memberList = (List<MemberVO>)obj;
			}
			memberList.add(member);
			app.setAttribute("memberList", memberList);
			
			for(MemberVO vo:memberList) {
				//System.out.println(vo);
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", member);
			session.setAttribute("user_code", member.getUser_code());
			session.setAttribute("user_name", member.getUser_name());
			 			
			String path = request.getContextPath();			
			return  "redirect:"+path+"/index.jsp";
		}else {	//로그인 실패
			System.out.println("로그인 실패");
			String path = request.getContextPath();			

			return  "redirect:"+path+"/view/memberView/MemberLogin.jsp";
		}
	}

}
