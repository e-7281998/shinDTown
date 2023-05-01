package com.shinD.controller.post;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.shinD.controller.CommonControllerInterface;
import com.shinD.model.post.PostService;
import com.shinD.model.post.PostVO;

public class PostCreate implements CommonControllerInterface {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		request.setCharacterEncoding("utf-8");//인코딩
		HttpSession session = request.getSession();
		String page="../view/postView/PostCreate.jsp";//이상하면 다시 돌아오기
		String method = (String)data.get("method");
		PostService pservice = new PostService();
		String board_name= request.getParameter("board_name");//보드이름저장
		session.setAttribute("board_name", board_name);
		
		
		if(method.equals("GET")) {
	
		}else {
			//너무 길어서 나눠씀
			int user_code= (Integer)session.getAttribute("user_code");
			String post_title = request.getParameter("post_title");
			String post_content = request.getParameter("post_content");
			String post_image = request.getParameter("post_image");
			String post_source = request.getParameter("post_source");
			pservice.PostCreate(board_name,user_code,post_title,post_content,post_image,post_source);//받은 정보로 게시판 생성
			
			System.out.println("생성 완료햇습니다.");
			
			//보드디테일로 돌아감
			page = "redirect:/shinDTown/board/detail.com";
			
		}
		
		return page;
	}
	//이미지 업로드 고민해보기
//	private PostVO doHandle(HttpServletRequest request)	throws ServletException, IOException {
//		PostVO vo = new PostVO();
//		
//		String encoding = "utf-8";
//		
//		String currentPath = request.getServletContext().getRealPath("uploads");
//		
//		File currentDirPath = new File(currentPath);
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		factory.setRepository(currentDirPath);
//		factory.setSizeThreshold(1024 * 1024);
//
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		try {
//			List items = upload.parseRequest( request);
//			for (int i = 0; i < items.size(); i++) {
//				FileItem fileItem = (FileItem) items.get(i);
//
//				if (fileItem.isFormField()) {
//					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
//
//					String calName=fileItem.getFieldName();
//					String calValue=fileItem.getString(encoding);
//					if(calName.equals("BOARD_CODE")) vo.setBOARD_CODE(i);
//					if(calName.equals("email")) vo.setEmail(fileItem.getString(encoding));
//					if(calName.equals("pass")) vo.setPass(fileItem.getString(encoding));
//					
//					String mname= request.getParameter("manager_name");
//					String email = request.getParameter("email");
//			  		String pass = request.getParameter("pass");
//				} else {
//					System.out.println("getFieldName:" + fileItem.getFieldName());
//					System.out.println("getName:" + fileItem.getName());
//					System.out.println("fileItem:" + fileItem.getSize() + "bytes");
//
//					if (fileItem.getSize() > 0) {
//						int idx = fileItem.getName().lastIndexOf("\\");
//						if (idx == -1) {
//							idx = fileItem.getName().lastIndexOf("/");
//						}
//						String fileName = fileItem.getName().substring(idx + 1);
//						File uploadFile = new File(currentDirPath + "\\" + fileName);
//						fileItem.write(uploadFile);
//						
//						//이미지 이름이 DB에 저장되어야한다
//						vo.setPic(fileName);
//					} // end if
//				} // end if
//			} // end for
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("adminvo:"+vo);
//		return vo;
//}

}
