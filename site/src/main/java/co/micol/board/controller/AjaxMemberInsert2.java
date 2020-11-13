package co.micol.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONObject;

import co.micol.board.common.Action;
import co.micol.board.common.FileUtil;
import co.micol.board.dao.MemberDao;
import co.micol.board.vo.MemberVO;
import co.micol.board.command.*;

@MultipartConfig(maxFileSize=1024*1024*2  	//size of any uploaded file
				,maxRequestSize = 1024*1024*10  //overall size of all uploaded files
				,location="d:/img"
				,fileSizeThreshold = 1024)
public class AjaxMemberInsert2 implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		//회원정보 데이터  DB 에입력
		MemberDao dao = new MemberDao();
		MemberVO vo = new MemberVO();
		
		vo.setId(request.getParameter("id"));
		vo.setName(request.getParameter("name"));
		vo.setPassword(request.getParameter("pw"));
		vo.setAddress(request.getParameter("address"));
		vo.setTel(request.getParameter("tel"));
		vo.setEnterdate(Date.valueOf(request.getParameter("enterdate")));
	
		
		int n = dao.insert(vo);
			try {
				response.getWriter().print(new JSONObject(vo));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return null;
	}

}
