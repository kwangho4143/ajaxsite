package co.micol.users.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.micol.board.dao.DAO;

public class EmpDAO extends DAO{
	PreparedStatement pstmt;
	ResultSet rs;
	
	// 사용자 목록 조회
    public List<Map<String, Object>> getEmpStat(){
        List<Map<String, Object>> userList = new ArrayList<>();
        String sql = "select department_id, count(*) "
        		+"from hr.employees "
        		+"where department_id is not null "
        		+"group by department_id";
        try {
           	pstmt = conn.prepareStatement(sql);
            
            rs = pstmt.executeQuery();
            while(rs.next()){
            	Map<String, Object> map = new HashMap<String, Object>();
                map.put("department_id",rs.getString("department_id"));
                map.put("cnt",rs.getInt(2));
                userList.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           close();
        }
        return userList;
    }    
    
    public void close() {
    	if(conn != null) {
    		try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
