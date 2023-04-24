package com.shinD.model.userplan;

import java.sql.Date;
import java.util.List;

public class PlanService {
	PlanDAO dao = new PlanDAO();
	
	public List<PlanVO> selectPlans(String userId) {
		return dao.selectPlans(userId);
	}
	
	public PlanVO selectPlan(int plan_code, String userId) {
		return dao.selectPlan(plan_code, userId);
	}
	
	public int insertPlan(PlanVO plan, String userId) {
		return dao.insertPlan(plan, userId);
	}
	
	public int updatePlan(PlanVO plan, int plan_code, String userId) {
		return dao.updatePlan(plan, plan_code, userId);
	}
	
	public int deletePlan(int plan_code, String userId) {
		return dao.deletePlan(plan_code, userId);
	}

	

}
