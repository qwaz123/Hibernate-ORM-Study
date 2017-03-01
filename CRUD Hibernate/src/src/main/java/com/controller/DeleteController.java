package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.model.UserService;

@WebServlet(name = "DeleteController", urlPatterns = { "/delete" })
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = -8299480960684202723L;
	private UserService service;

	public DeleteController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service = new UserService();
		
		long id = Long.parseLong(request.getParameter("id"));
		User user = service.findById(id);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/delete.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service = new UserService();
		
		long id = Long.parseLong(request.getParameter("id"));
		User user = service.findById(id);
		service.delete(user);

		response.sendRedirect(request.getContextPath() + "/read?page=0");
	}

}
