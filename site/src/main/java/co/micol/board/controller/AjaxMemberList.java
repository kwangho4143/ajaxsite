package co.micol.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import co.micol.board.common.Action;
import co.micol.board.dao.MemberDao;
import co.micol.board.vo.MemberVO;

public class AjaxMemberList implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		MemberDao dao = new MemberDao();
		MemberVO vo = new MemberVO();
		vo.setFirst(1);
		vo.setLast(5);
		List<MemberVO> list = dao.selectAll(vo);
		JSONArray arr = new JSONArray(list);
		try {
			response.getWriter().print(arr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
