package co.micol.board.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.board.common.Action;
import co.micol.board.common.Paging;
import co.micol.board.dao.MemberDao;
import co.micol.board.vo.MemberVO;

public class MemberListAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//회원 전체 리스트 보기
		MemberDao dao = new MemberDao();//DB 액세스 객체		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
//		list = dao.selectAll();
//		request.setAttribute("members", list); //jsp 에서 쓸 변수 members
		
		//페이징
		//페이지 번호 파라미터
		String strp = request.getParameter("p");
		//페이지 번호가 없으면 1로 초기화
		int p = 1;
		if(strp != null && ! strp.equals("")) {
			p=Integer.parseInt(strp);
		}
		//레코드 건수 조회
		Paging paging = new Paging();
		paging.setPageUnit(2); //한페이지에 출력할 레코드수??? 레코드랑 페이지랑 뭔차인데 ㅋㅋㅋ
		paging.setPageSize(3); // 한페이지에 출력할 페이지수??
		paging.setPage(p);
		MemberDao cntdao = new MemberDao();
		MemberVO vo = new MemberVO();
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		paging.setTotalRecord(cntdao.count(vo));
		//해당 페이지의 리스트만 조회
		request.setAttribute("paging", paging);
		//목록결과와 페이징 객체를 저장
		list = dao.selectAll(vo);
		request.setAttribute("members", list);
		
		
		return "jsp/member/memberList.jsp";
	}

}
