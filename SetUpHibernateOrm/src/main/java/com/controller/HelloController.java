package com.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.util.EntityManagerUtil;

@WebServlet(name = "HelloController", urlPatterns = { "/hello" })
public class HelloController extends HttpServlet {
	
	private static final long serialVersionUID = 1100079903834429781L;

	public HelloController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		request.getRequestDispatcher("/WEB-INF/HelloView.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		
		EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist( user );
		entityManager.getTransaction().commit();
		entityManager.close();

		doGet(request, response);
	}

}
