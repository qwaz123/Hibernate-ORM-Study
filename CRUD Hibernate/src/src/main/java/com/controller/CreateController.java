package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.model.UserRegisteredException;
import com.model.UserService;

@WebServlet(name = "CreateController", urlPatterns = { "/create" })
public class CreateController extends HttpServlet {

	private static final long serialVersionUID = 1100079903834429781L;

	public CreateController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/create.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> errorMsgs = new ArrayList<>();

		errorMsgs = validateParameter(request);

		if(!errorMsgs.isEmpty()){
			request.setAttribute("errorMsgs", errorMsgs);
			doGet(request, response);
			return;
		}
		
		User user = new User(request.getParameter("username").trim(), 
							 request.getParameter("address").trim());

		UserService service = new UserService();

		try {
			service.add(user);
		} catch (UserRegisteredException e) {
			errorMsgs.add("Username already used");
			request.setAttribute("errorMsgs", errorMsgs);
			doGet(request, response);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/create");
	}

	private boolean isEmpty(String paraneterName, HttpServletRequest request) {
		if (request.getParameter(paraneterName).trim().isEmpty()) {
			return true;
		}
		return false;
	}

	private List<String> validateParameter(HttpServletRequest request) {
		List<String> errorMsgs = new ArrayList<>();

		if (isEmpty("username", request)) {
			errorMsgs.add("Username is empty");
		}

		if (isEmpty("address", request)) {
			errorMsgs.add("Address is empty");
		}

		return errorMsgs;
	}

}
