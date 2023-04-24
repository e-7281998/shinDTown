package com.shinD.controller.comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.comment.CommentService;
import com.shinD.model.comment.CommentVO;

public class CommentList implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");//입력받은 값 가져오기
		HttpServletResponse response = (HttpServletResponse) data.get("response");
		request.setCharacterEncoding("utf-8");//인코딩
		String method = (String)data.get("method");
		HttpSession session = request.getSession();
		
		CommentService cservice = new CommentService();
		List<CommentVO> comlist = null;
	
		comlist = cservice.ComList(Integer.parseInt(request.getParameter("post_code")));
		request.setAttribute("comlist", comlist);
		
		JSONArray array = new JSONArray();
		for(CommentVO com:comlist) {
			JSONObject object = new JSONObject();
			
			object.put("COM_CODE", com.getCOM_CODE());
			object.put("POST_CODE", com.getPOST_CODE());
			object.put("USER_CODE", com.getUSER_CODE());
			object.put("COM_COMMENT", com.getCOM_COMMENT());
			object.put("COM_CREATE", com.getCOM_CREATE());
			
			array.add(object);
		}
		
		JSONObject comObject = new JSONObject();
		
		comObject.put("comlist",array);
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
		
		return "responseBody:"+comObject.toString();
	}

}
