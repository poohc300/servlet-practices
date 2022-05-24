
<%@page import="com.douzone.guestbook.vo.MemberVo"%>
<%@page import="com.douzone.guestbook.dao.MemberDao"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager" %>
<%@page import="com.douzone.guestbook.util.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	
	request.setCharacterEncoding("utf-8");
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String email = request.getParameter("email");
	String message = request.getParameter("message");
	
	MemberVo vo = new MemberVo();
	boolean result = false;
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//DBConnection dc = new DBConnection();
	try {
		//connection = dc.getConnection();
		//Connection connection = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.56.101:3306/guestbook?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println(connection);

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		Date date = new Date();
		String sql =
			"insert " +
			"into member " +
			"values(null, ?, ?, ?, ?)";

		pstmt = connection.prepareStatement(sql);

		pstmt.setString(1, firstName);
		pstmt.setString(2, lastName);
		pstmt.setString(3, email);
		pstmt.setString(4, message);
		pstmt.setString(5, date.toString());
		
		rs = pstmt.executeQuery();

	}	
	catch(SQLException e) {
		System.out.println("드라이버 로딩 실패:" + e);
		
	} finally {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	response.sendRedirect("/guestbook01");
%>