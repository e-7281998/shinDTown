package com.shinD.model.userplan;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinD.util.DateUtil;
import com.shinD.util.MySQLUtil;

public class PlanDAO {

	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	Statement st;
	int result = 0;

	// 플랜 전체 조회
	public List<PlanVO> selectPlans(String userId) {
		String tableName = userId + "_plan";
		System.out.println(tableName);
		String sql = "select * from " + tableName;
		List<PlanVO> plans = new ArrayList<>();
		conn = MySQLUtil.getConnection();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				PlanVO plan = makeplan(rs);
				plans.add(plan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLUtil.dbDisconnect(rs, st, conn);
		}
		return plans;
	}
	
	// 플랜 하나 조회
	public PlanVO selectPlan(int plan_code, String userId) {
		String tableName = userId + "_plan";
		String sql = "select * from " + tableName +" where plan_code=?";
		
		PlanVO plan = null;
		conn = MySQLUtil.getConnection();
		
		System.out.println(plan_code);
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, plan_code);
				rs = pst.executeQuery();
				
				while (rs.next()) {
					plan = makeplan(rs);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				MySQLUtil.dbDisconnect(rs, pst, conn);
			}
			return plan;
		}

	// 플랜 생성
	public int insertPlan(PlanVO plan, String userId) {
		String tableName = userId + "_plan";
		String sql = "insert into "+ tableName+"(PLAN_TITLE,PLAN_CONTENT,START_DATE,END_DATE,COLOR) values(?,?,?,?,?)";
		System.out.println(tableName);
		System.out.println(plan);
		conn = MySQLUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);

			pst.setString(1, plan.getPlan_title());
			pst.setString(2, plan.getPlan_content());
			pst.setDate(3, (Date) plan.getStart_date());
			pst.setDate(4, (Date) plan.getEnd_date());
			pst.setString(5, plan.getColor());

			result = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}
		return result;
	}

	// 플랜 수정
	public int updatePlan(PlanVO plan , int plan_code , String userId) {
		String tableName = userId + "_plan";
		String sql = "update "+ tableName +" set plan_title = ? , plan_content = ?, start_date = ?, end_date = ?, color = ? where plan_code =?";
		conn = MySQLUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);

			pst.setString(1, plan.getPlan_title());
			pst.setString(2, plan.getPlan_content());
			pst.setDate(3, plan.getStart_date());
			pst.setDate(4, plan.getEnd_date());
			pst.setString(5, plan.getColor());
			pst.setInt(6, plan_code);

			result = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}
		return result;
	}

	// 플랜 삭제
	public int deletePlan(int plan_code , String userId) {
		String tableName = userId + "_plan";
		String sql = "delete from "+ tableName +" where PLAN_CODE = ?;";

		conn = MySQLUtil.getConnection();

		try {
			pst = conn.prepareStatement(sql);

			pst.setInt(1, plan_code);

			result = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			MySQLUtil.dbDisconnect(null, pst, conn);
		}
		System.out.println("삭제 결과" + result);
		return result;
	}


	private PlanVO makeplan(ResultSet rs) throws SQLException {
		PlanVO plan = new PlanVO();

		plan.setPlan_code(rs.getInt("plan_code"));
		plan.setPlan_title(rs.getString("plan_title"));
		plan.setPlan_content(rs.getString("plan_content"));
		plan.setStart_date(rs.getDate("start_date"));
		plan.setEnd_date(rs.getDate("end_date"));
		plan.setColor(rs.getString("color"));
		return plan;
	}

}
