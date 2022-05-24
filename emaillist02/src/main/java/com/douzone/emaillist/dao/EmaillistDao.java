package com.douzone.emaillist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.emaillist.util.DBConnection;
import com.douzone.emaillist.vo.EmaillistVo;

public class EmaillistDao {
	public boolean insert(EmaillistVo vo) {
			
			boolean result = false;
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			DBConnection dc = new DBConnection();
			
			try {
				connection = dc.getConnection();
			
				String sql =
					"insert " +
					"into emaillist " +
					"values(null, ?, ?, ?)";
				pstmt = connection.prepareStatement(sql);
		
				pstmt.setString(1, vo.getFirstName());
				pstmt.setString(2, vo.getLastName());
				pstmt.setString(3, vo.getEmail());
				
				rs = pstmt.executeQuery();
	
			}	
			catch(SQLException e) {
				System.out.println("드라이버 로딩 실패:" + e);
				
			} finally {
				dc.close(connection, pstmt, rs);
			}
			
			result = true;
			return result;		
			
		}

	public List<EmaillistVo> findAll() throws SQLException{
		List<EmaillistVo> result = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DBConnection dc = new DBConnection();
		connection = dc.getConnection();
	

		
		String sql =
			"   select no, first_name, last_name, email" +
			"     from emaillist" + 
			" order by no desc";
		
		pstmt = connection.prepareStatement(sql);			
		rs = pstmt.executeQuery();
			
		while(rs.next()) {
			Long no = rs.getLong(1);
			String firstName = rs.getString(2);
			String lastName = rs.getString(3);
			String email = rs.getString(4);
			
			EmaillistVo vo = new EmaillistVo();
			vo.setNo(no);
			vo.setFirstName(firstName);
			vo.setLastName(lastName);
			vo.setEmail(email); 
			
			result.add(vo);
			}
		
		dc.close(connection, pstmt, rs);

		return result;
		
	}
}
