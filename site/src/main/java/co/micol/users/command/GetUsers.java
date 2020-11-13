package co.micol.users.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import co.micol.board.common.Action;
import co.micol.users.dao.UsersDAO;
import co.micol.users.dao.UsersDTO;

public class GetUsers implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response)
			 {
		//파라미터를 DTO에 담기
		String id = request.getParameter("id");
		UsersDTO dto = new UsersDTO(id); 
		//조회 처리
		UsersDAO dao = new UsersDAO();
		UsersDTO userDTO = dao.getUser(dto);
		//조회결과를 응답
		try {
			response.getWriter().print(new JSONObject(userDTO));//단 건을 넘길때는 Object
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
