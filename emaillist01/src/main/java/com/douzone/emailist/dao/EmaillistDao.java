package com.douzone.emailist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.emailist.util.DBConnection;
import com.douzone.emailist.vo.EmaillistVo;

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
			"   select a.no, a.no, a.title, a.price, b.name" +
			"     from book a, category b" +
			"    where a.category_no = b.no" +
			" order by a.no";
		
		pstmt = connection.prepareStatement(sql);			
		rs = pstmt.executeQuery();
			
		while(rs.next()) {
			EmaillistVo vo = new EmaillistVo();
			
			vo.setNo(rs.getLong(1)); 
			vo.setName(rs.getString(2)); 
			vo.setPrice(rs.getLong(3)); 
			vo.setCategoryName(rs.getString(4)); 
			
			result.add(vo);
			}
		
		dc.close(connection, pstmt, rs);

		return result;
		
	}
}
