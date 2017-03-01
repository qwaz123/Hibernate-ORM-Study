package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.model.UserService;

@WebServlet(name = "ReadController", urlPatterns = { "/read" })
public class ReadController extends HttpServlet {
	
	private static final long serialVersionUID = 1100079903834429781L;

	public ReadController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int index = 0;
		String pageIndex = request.getParameter("page");
		if(!pageIndex.isEmpty()) {
			index = Integer.parseInt(request.getParameter("page"));
		}
		
		UserService service = new UserService();
		List<User> users = service.page(index);
		request.setAttribute("users", users);
		
		request.getRequestDispatcher("/WEB-INF/read.jsp").forward(request, response);
	}
}
