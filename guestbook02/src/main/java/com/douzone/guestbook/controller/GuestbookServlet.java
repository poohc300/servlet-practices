package com.douzone.guestbook.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.guestbook.dao.GuestbookDao;
import com.douzone.guestbook.vo.GuestbookVo;

public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String action = request.getParameter("a");
		try {
			if("form".equals(action)) {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/form.jsp");
				rd.forward(request, response);
			} else if("add".equals(action)) {
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String message = request.getParameter("message");
				
				GuestbookVo vo = new GuestbookVo();
				vo.setName(name);
				vo.setPassword(password);
				vo.setMessage(message);
				
				new GuestbookDao().insert(vo);
				
				response.sendRedirect(request.getContextPath() + "/gb");			
			} else {
				List<GuestbookVo> list = new GuestbookDao().findAll();
				
				
				request.setAttribute("list", list);
	
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
				rd.forward(request, response);
			}
			System.out.println("ok!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGet(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}