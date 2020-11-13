package co.micol.board.command;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import co.micol.board.common.Action;
import co.micol.board.common.FileUtil;
import co.micol.board.dao.MemberDao;
import co.micol.board.vo.MemberVO;
import co.micol.board.command.*;

@MultipartConfig(maxFileSize=1024*1024*2  	//size of any uploaded file
				,maxRequestSize = 1024*1024*10  //overall size of all uploaded files
				,location="d:/img"
				,fileSizeThreshold = 1024)
public class MemberInsert implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//회원정보 데이터  DB 에입력
		MemberDao dao = new MemberDao();
		MemberVO vo = new MemberVO();
		
		vo.setId(request.getParameter("id"));
		vo.setName(request.getParameter("name"));
		vo.setPassword(request.getParameter("pw"));
		vo.setAddress(request.getParameter("address"));
		vo.setTel(request.getParameter("tel"));
		vo.setEnterdate(Date.valueOf(request.getParameter("enterdate")));
		
		String appPath = request.getServletContext().getRealPath("/images");
		System.out.println(appPath);
		
		try {
			for (Part part : request.getParts()) {
				String fileName = FileUtil.extractFileName(part);
				if(!fileName.equals("")) {
					//파일명 중복검사
					String uploadFile = appPath + File.separator + fileName; //System.currentTimeMillis();
					File renameFile = FileRenamePolicy.rename(new File(uploadFile));
					try {
						part.write(renameFile.getAbsolutePath());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				vo.setImg(renameFile.getName()); //2번?
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		vo.setImg(request.getParameter("img"));
		
		int n = dao.insert(vo);
		String page;
		if(n != 0) {
			page ="jsp/member/insertSucess.jsp";
		}else {
			page ="jsp/member/insertFail.jsp";
		}
		
		
		return page;
	}

}
