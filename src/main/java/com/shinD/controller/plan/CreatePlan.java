package com.shinD.controller.plan;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.member.MemberService;
import com.shinD.model.member.MemberVO;
import com.shinD.model.userplan.PlanService;
import com.shinD.model.userplan.PlanVO;
import com.shinD.util.DateUtil;

public class CreatePlan implements CommonControllerInterface{

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest)data.get("request");
		request.setCharacterEncoding("utf-8");
		
		String page = "main.jsp";
		String method = (String) data.get("method");

		
		if(method.equals("POST")) {
			PlanVO plan = makeplan(request);
			PlanService ps = new PlanService();
			ps.insertPlan(plan, request.getParameter("id"));
			System.out.println("일정 생성 완료");
		}
		
		page = "responseBody:일정생성완료";
		
		return page;
	} 
	
	private PlanVO makeplan(HttpServletRequest request) throws UnsupportedEncodingException {
		
		String plan_title = request.getParameter("plan_title");
		String plan_content = request.getParameter("plan_content");
		Date start_date = DateUtil.convert_date(request.getParameter("start_date"));
		Date end_date = DateUtil.convert_date(request.getParameter("end_date"));
		String color = request.getParameter("color");
		
		PlanVO plan = new PlanVO();
		plan.setPlan_title(plan_title);
		plan.setPlan_content(plan_content);
		plan.setStart_date(start_date);
		plan.setEnd_date(end_date);
		plan.setColor(color);
	
		return plan;
	}

}
