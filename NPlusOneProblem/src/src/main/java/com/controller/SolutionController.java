package com.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.util.EntityManagerUtil;

@WebServlet(name = "SolutionController", urlPatterns = { "/solution" })
public class SolutionController extends HttpServlet {

	private static final long serialVersionUID = -1197465607582947113L;

	public SolutionController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Dynamic eager fetching:
		EntityManager em = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
		List<User> users = em.createQuery("select u from User u join fetch u.country", User.class).getResultList();
		// select  u.* c.* from User u
		// inner join COUNTRY c on u.COUNTRY_ID = COUNTRY_ID
		em.close(); // --------> Detach all
		for (User user : users) {
			System.out.println("Country name is " + user.getCountry().getName());
		}

		// for collection 
		em = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
		users = em.createQuery("select u from User u left join fetch u.images", User.class).getResultList();
		// select  u.* i.* from User u
		// left outer join IMAGE i on u.ID = i.USER_ID
		em.close(); // --------> Detach all
		for (User user : users) {
			System.out.println("The total image is " + user.getImages().size());
		}
		
		request.getRequestDispatcher("/WEB-INF/solution.jsp").forward(request, response);
	}
}
