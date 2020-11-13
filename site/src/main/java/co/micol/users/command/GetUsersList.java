package co.micol.users.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import co.micol.board.common.Action;
import co.micol.users.dao.UsersDAO;
import co.micol.users.dao.UsersDTO;

public class GetUsersList implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response)
			{
		//파라미터를 DTO에 담기
		//조회 처리
		UsersDAO dao = new UsersDAO();
		List<UsersDTO> userList =dao.getUserList();
		//조회 정보를 응답
		try {
			response.getWriter().print(new JSONArray(userList));//여러 건을 넘길때는 Array
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
