package com.shinD.controller.plan;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.userplan.PlanService;
import com.shinD.model.userplan.PlanVO;

public class ReadPlan implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		HttpServletResponse response = (HttpServletResponse) data.get("response");
		
		List<PlanVO> plans = null;
		request.setCharacterEncoding("utf-8");
		String method = (String) data.get("method");
		String page = "main.jsp";
		PlanService ps = new PlanService();
		
		String userId = (String) request.getParameter("id");
		
		if(method.equals("GET")) {
			plans = ps.selectPlans(userId);
			request.setAttribute("plans", plans);
		}
		
		JSONArray array = new JSONArray();
		
		for(PlanVO plan : plans) {
			JSONObject object = new JSONObject();
			
			object.put("plan_code", plan.getPlan_code());
			object.put("plan_title", plan.getPlan_title());
			object.put("plan_content", plan.getPlan_content());
			object.put("start_date", ""+ plan.getStart_date() +"");
			object.put("end_date",  ""+ plan.getEnd_date() + "");
			object.put("color", plan.getColor());
			
			array.add(object);
		}
		JSONObject planObject = new JSONObject();
		
		planObject.put("plans", array);
		response.setContentType("text/plan");
		response.setCharacterEncoding("utf-8");
		
		return "responseBody:"+planObject.toString();
	}

}
