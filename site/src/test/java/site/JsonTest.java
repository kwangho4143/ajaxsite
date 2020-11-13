package site;

import java.util.List;

import org.json.JSONArray;
import org.junit.Test;

import co.micol.board.dao.MemberDao;
import co.micol.board.vo.MemberVO;

public class JsonTest {
	
	@Test
	public void jsonobject() {
		MemberDao dao = new MemberDao();
		MemberVO vo = new MemberVO();
		vo.setFirst(1);
		vo.setLast(5);
		List<MemberVO> list = dao.selectAll(vo);
		JSONArray arr = new JSONArray(list);
		System.out.println(arr);
	}
}
