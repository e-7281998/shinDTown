package com.shinD.controller.plan;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.member.MemberService;
import com.shinD.model.userplan.PlanService;

public class DeletePlan implements CommonControllerInterface{

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest)data.get("request");
		
		String userId = (String) (request.getParameter("id"));
		PlanService ps = new PlanService();
		int plan_code = Integer.parseInt(request.getParameter("plan_code"));
		int result = ps.deletePlan(plan_code, userId);
		
		if(result == 0) {
			System.out.println("삭제 성공");
 			return "responseBody:1";
		} 
		else
			return "responseBody:-1";
				
		
	}

}
