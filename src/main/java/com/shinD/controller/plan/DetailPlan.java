package com.shinD.controller.plan;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.userplan.PlanService;
import com.shinD.model.userplan.PlanVO;
import com.shinD.util.DateUtil;

public class DetailPlan implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		HttpServletResponse response = (HttpServletResponse) data.get("response");
		
		PlanVO plan = new PlanVO();
		request.setCharacterEncoding("utf-8");
		String method = (String) data.get("method");
		String page = "main.jsp";
		PlanService ps = new PlanService();
		String userId = (String) (request.getParameter("id"));
		
		if(method.equals("GET")) {
			
			int plan_code = Integer.parseInt(request.getParameter("plan_code"));
			plan = ps.selectPlan(plan_code , userId);
			
			request.setAttribute("plan", plan);
			
			System.out.println("detail : " + plan);
			
		}else {
			int plan_code = Integer.parseInt(request.getParameter("plan_code"));
			plan = makeplan(request);
			ps.updatePlan(plan, plan_code, userId);
		}
		
		JSONObject object = new JSONObject();
		
		object.put("plan_code", plan.getPlan_code());
		object.put("plan_title", plan.getPlan_title());
		object.put("plan_content", plan.getPlan_content());
		object.put("start_date", ""+ plan.getStart_date() +"");
		object.put("end_date",  ""+ plan.getEnd_date() + "");
		object.put("color", plan.getColor());
		
		response.setContentType("text/plan");
		response.setCharacterEncoding("utf-8");
		return "responseBody:"+ object;
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
