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
import com.douzone.guestbook.vo.MemberVo;

public class GuestbookDao {
	public boolean insert(GuestbookVo vo) {
		
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DBConnection dc = new DBConnection();
		
		try {
			connection = dc.getConnection();
			
			String sql =
					" insert" +
					"   into guestbook" +
					" values (null, ?, ?, ?, now())";
			pstmt = connection.prepareStatement(sql);
	
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			
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

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			DBConnection dc = new DBConnection();
			conn = dc.getConnection();
			
			String sql =
					" delete" +
					"   from guestbook" +
					"  where no=?" +
					"    and password=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return result;		
	}

public List<GuestbookVo> findAll() throws SQLException{
	List<GuestbookVo> list = new ArrayList<>();
	
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	DBConnection dc = new DBConnection();
	connection = dc.getConnection();


	String sql =
			"   select no, name, date_format(reg_date, '%Y/%m/%d %H:%i:%s') as reg_date, message" +
			"     from guestbook" +
			" order by reg_date desc";
	
	pstmt = connection.prepareStatement(sql);			
	rs = pstmt.executeQuery();
		
	while(rs.next()) {
		Long no = rs.getLong(1);
		String name = rs.getString(2);
		String regDate = rs.getString(3);
		String message = rs.getString(4);
		
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		vo.setName(name);
		vo.setRegDate(regDate);
		vo.setMessage(message);
		
		list.add(vo);
	}
	
	dc.close(connection, pstmt, rs);

	return list;
	
}
}
