package com.douzone.guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.douzone.guestbook.util.DBConnection;
import com.douzone.guestbook.vo.MemberVo;

public class MemberDao {
	public boolean insert(MemberVo vo) {
		
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
				"into member " +
				"values(null, ?, ?, ?)";
			pstmt = connection.prepareStatement(sql);
	
			pstmt.setString(1, vo.getFirstName());
			pstmt.setString(2, vo.getLastName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getMessage());
			pstmt.setString(5, date.toString());
			
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

public List<MemberVo> findAll() throws SQLException{
	List<MemberVo> result = new ArrayList<>();
	
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	DBConnection dc = new DBConnection();
	connection = dc.getConnection();


	String sql =
		"   select *" +
		"   from member" +
		"   order by no";
	
	pstmt = connection.prepareStatement(sql);			
	rs = pstmt.executeQuery();
		
	while(rs.next()) {
		MemberVo vo = new MemberVo();
		
		vo.setNo(rs.getLong(1)); 
		vo.setFirstName(rs.getString(2));
		vo.setLastName(rs.getString(3));
		vo.setEmail(rs.getString(4));
		result.add(vo);
		}
	
	dc.close(connection, pstmt, rs);

	return result;
	
}
}
