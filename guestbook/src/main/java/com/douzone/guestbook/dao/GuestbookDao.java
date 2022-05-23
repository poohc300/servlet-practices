package com.douzone.guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.douzone.guestbook.util.DBConnection;
import com.douzone.guestbook.vo.GuestbookVo;

public class GuestbookDao {
	public boolean insert(GuestbookVo vo) {
			
			boolean result = false;
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			DBConnection dc = new DBConnection();
			
			try {
				connection = dc.getConnection();
				Date date = new Date();
				
				String sql =
					"insert " +
					"into guestbook " +
					"values(null, ?, ?, ?, ?)";
				// title, content, current
				pstmt = connection.prepareStatement(sql);
		
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setString(3, date.toString());
				pstmt.setLong(4, vo.getMemberNo());
				
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
	public boolean delete(GuestbookVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DBConnection dc = new DBConnection();
		
		try {
			connection = dc.getConnection();
		
			String sql =
				"delete " +
				"from guestbook " +
				"where no = ?";
			pstmt = connection.prepareStatement(sql);
	
			pstmt.setLong(1, vo.getNo());
		
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

	public List<GuestbookVo> findAll() throws SQLException{
		List<GuestbookVo> result = new ArrayList<>();
		
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
			GuestbookVo vo = new GuestbookVo();
			
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
