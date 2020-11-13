package co.micol.board.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.board.command.LoginAction;
import co.micol.board.command.LoginForm;
import co.micol.board.command.LogoutAction;
import co.micol.board.command.MainAction;
import co.micol.board.command.MemberForm;
import co.micol.board.command.MemberInsert;
import co.micol.board.command.MemberListAction;
import co.micol.board.common.Action;
import co.micol.users.command.DeleteUsers;
import co.micol.users.command.GetUsers;
import co.micol.users.command.GetUsersList;
import co.micol.users.command.InsertUsers;
import co.micol.users.command.UpdateUsers;

@WebServlet("*.do")
@MultipartConfig(maxFileSize=1024*1024*2  	//size of any uploaded file
				,maxRequestSize = 1024*1024*10  //overall size of all uploaded files
				,location="d:/img"
				,fileSizeThreshold = 1024)
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Action> map = new HashMap<String, Action>();
    public FrontController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		//ajax 요청
		map.put("/ajax/memberList.do", new AjaxMemberList());
		map.put("/ajax/memberInsert.do", new AjaxMemberInsert2());
		
		//users 
		map.put("/ajax/usersList.do", new GetUsersList());//전체조회
		map.put("/ajax/users.do", new GetUsers()); //단건조회
		map.put("/ajax/usersInsert.do", new InsertUsers()); //등록
		map.put("/ajax/usersUpdate.do", new UpdateUsers()); //수정
		map.put("/ajax/usersDelete.do", new DeleteUsers()); //삭제
		
		map.put("/ajax/empStat.do", new EmpStat());
		
		
		// 요청들을 처리
		map.put("/main.do", new MainAction()); //처음들어오는 페이지처리 index.jsp
		map.put("/login.do", new LoginAction()); // 로그인 메뉴를 처리하는것
		map.put("/loginForm.do",new LoginForm()); //로그인 폼 호출
		map.put("/memberList.do", new MemberListAction()); //회원전체리스트 보기
		map.put("/memberForm.do", new MemberForm()); //회원가입 창 호출
		map.put("/memberInsert.do", new MemberInsert()); //회원가입 데이터 넘김
		map.put("/logout.do", new LogoutAction()); //로그아웃
		
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 실제로 화면 보여주는 곳 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//
		request.setCharacterEncoding("UTF-8"); //한글 깨짐 방지를 위해
		response.setContentType("text/html; charset=utf-8");
		
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = uri.substring(contextPath.length()); //실제 들어오는 요청페이지를 찾음
	
		Action command = map.get(path);
		String viewPage = command.exec(request, response);//명령이 수행되는 부분,수행되고나서 보여줄 페이지 선택
		if(viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
	
	}

}
