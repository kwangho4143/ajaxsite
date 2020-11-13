package co.micol.board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.micol.board.vo.MemberVO;

public class MemberDao extends DAO{
	private PreparedStatement pstmt; //sql 명령문 실행
	private ResultSet rs; //select 후 결과셋 받기
	private MemberVO vo;
//	private final String SELECT_ALL = "SELECT * FROM MEMBER";
	private final String SELECT_ALL = "select * " + 
			"from (" + 
			"          select a.*, rownum rn " + 
			"          from ( select * " + 
			"                    from member " + 
			"                    order by id) a " + 
			"        ) b " + 
			"where rn between ? and ? ";
	private final String SELECT = "SELECT * FROM MEMBER WHERE ID =? AND PASSWORD = ?";
	private final String INSERT = "INSERT INTO MEMBER(ID, NAME, PASSWORD, ADDRESS, TEL, ENTERDATE, IMG) "
			+ "VALUES(?,?,?,?,?,?,?)";
	private final String UPDATE = "UPDATE MEMBER "
			+"SET NAME=?, PASSWORD=?, ADDRESS=?, TEL=? "
			+"WHERE ID=?";
	private final String DELETE = "DELETE FROM MEMBER "
			+"WHERE ID=?";
	
	public List<MemberVO> selectAll(MemberVO mvo){//전체조회
		List<MemberVO> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(SELECT_ALL);
			pstmt.setInt(1, mvo.getFirst());
			pstmt.setInt(2, mvo.getLast());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new MemberVO(
				rs.getString("id"),
				rs.getString("name"),
				rs.getString("password"),
				rs.getString("address"),
				rs.getString("tel"),
				rs.getDate("enterdate"),
				rs.getString("author"),
				rs.getString("img"));
			list.add(vo);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
//	public List<MemberVO> selectAll(){//전체조회
//		List<MemberVO> list = new ArrayList<>();
//		
//		try {
//			pstmt = conn.prepareStatement(SELECT_ALL);
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				vo = new MemberVO(
//				rs.getString("id"),
//				rs.getString("name"),
//				rs.getString("password"),
//				rs.getString("address"),
//				rs.getString("tel"),
//				rs.getDate("enterdate"),
//				rs.getString("author"),
//				rs.getString("img"));
//			list.add(vo);
//			}
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}finally {
//			close();
//		}
//		return list;
//	}
	
	public int count(MemberVO vo) {//페이징 !!!!!!!!!!!!!!!!!!!!!
		int cnt = 0;
		try {
			String sql = "select count(*) from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();//첫번째행으로간다?
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return cnt;
		
	}
	
	public MemberVO select(MemberVO vo) {//한행 검색
		try {
			pstmt = conn.prepareStatement(SELECT);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setName(rs.getString("name"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setEnterdate(rs.getDate("enterdate"));
				vo.setAuthor(rs.getString("author"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return vo;
		
	}
	
	public int insert(MemberVO vo) {//인서트
		int n= 0;
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getTel());
			pstmt.setDate(6, vo.getEnterdate());
			pstmt.setString(7, vo.getImg());
			n = pstmt.executeUpdate();
			System.out.println(n+"건이 입력되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return n;
	}
	
	public int delete(MemberVO vo) {//delete
		int n = 0;
		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setString(1, vo.getId());
			n = pstmt.executeUpdate();
			System.out.println(n + "건 삭제되었습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
	
	public int update(MemberVO vo) {//update
		int n = 0;
		try {
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getId());
			n = pstmt.executeUpdate();
			System.out.println(n+"건이 수정되었습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
