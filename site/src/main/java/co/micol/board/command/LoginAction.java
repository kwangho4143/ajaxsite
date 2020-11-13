package co.micol.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.board.common.Action;
import co.micol.board.dao.MemberDao;
import co.micol.board.vo.MemberVO;

public class LoginAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 로그인 인증과정을 처리한다
		MemberDao dao = new MemberDao();
		MemberVO vo = new MemberVO();
		HttpSession session = request.getSession(false);
		
		vo.setId(request.getParameter("id"));
		vo.setPassword(request.getParameter("pw"));
		
		vo=dao.select(vo);
		session.setAttribute("id", vo.getId()); //session에 id 담음
		session.setAttribute("author", vo.getAuthor());
		session.setAttribute("name", vo.getName());
		request.setAttribute("vo", vo); //jsp 객체로? --
		return "jsp/main/loginResult.jsp";
	}

}
