package co.micol.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import co.micol.board.common.Action;
import co.micol.users.dao.EmpDAO;

public class EmpStat implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//DB조회
		EmpDAO dao = new EmpDAO();
		List<Map<String, Object>> list = dao.getEmpStat();
		//json string으로 변환
		String str = new JSONArray(list).toString();
		//response 통해서 결과 전달
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
