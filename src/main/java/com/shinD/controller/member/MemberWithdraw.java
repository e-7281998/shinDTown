package com.shinD.controller.member;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.member.MemberService;
import com.shinD.model.member.MemberVO;

public class MemberWithdraw implements CommonControllerInterface{

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest)data.get("request");
		
		MemberService srevice = new MemberService();
		
		String id = request.getParameter("id");
		int result = srevice.withdrawUser(id);
		if(result == 0) {
			//여기부터 
			ServletContext app = request.getServletContext();
			HttpSession session = request.getSession();
			
			Object obj = app.getAttribute("memberList");		
			MemberVO member = (MemberVO)session.getAttribute("loginUser");
			List<MemberVO> memberList = null;
			
			//로그아웃하려는 유저 외에 접속한 사람이 있을경우 app의 memberList 다시 세팅하기
			if(obj != null) {
				memberList = (List<MemberVO>)obj;
				memberList.remove(member);
				app.setAttribute("memberList", memberList);
			}
			
			session.invalidate();  
 			return "responseBody:1";
		} 
		else
			return "responseBody:-1";
				
		
	}

}
