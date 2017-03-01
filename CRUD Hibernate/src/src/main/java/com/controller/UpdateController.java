package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.model.UserService;

@WebServlet(name = "UpdateController", urlPatterns = { "/update" })
public class UpdateController extends HttpServlet {

	private static final long serialVersionUID = -8299480960684202723L;

	private UserService service;

	public UpdateController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service = new UserService();
		
		long id = Long.parseLong(request.getParameter("id"));
		User user = service.findById(id);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		service = new UserService();
		
		long id = Long.parseLong(request.getParameter("id"));
		String address = request.getParameter("address");
		User user = service.findById(id);
		user.setAddress(address);

		service.update(user);

		response.sendRedirect(request.getContextPath() + "/update?id=" + user.getId());
	}

}
