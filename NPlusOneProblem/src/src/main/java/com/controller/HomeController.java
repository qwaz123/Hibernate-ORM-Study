package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Country;
import com.model.Image;
import com.model.User;
import com.util.EntityManagerUtil;

@WebServlet(name = "HomeController", urlPatterns = { "/" })
public class HomeController extends HttpServlet {

	static { // Create Some Data
		EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		for (int i = 0; i < 5; i++) {
			User user = new User();
			user.setUsername("username_" + i);
			Country country = new Country("country_" + i, "capital_" + i);
			user.setCountry(country);
			List<Image> imgs = new ArrayList<>();
			for (int j = 0; j < 10; j++) {
				Image image = new Image("image_" + i + "_" + j, "png");
				image.setUser(user);
				imgs.add(image);
				entityManager.persist(image);
			}

			entityManager.persist(country);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	private static final long serialVersionUID = 1100079903834429781L;

	public HomeController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		List<User> users = entityManager.createQuery("select u from User u", User.class).getResultList();
		// select * from IMAGE
		for (User u : users) {
			System.out.println("------Country name is " + u.getCountry().getName()+"--------");
			// select * from USER where id ?
		}

		System.out.println(
				"=======================Lazily loaded collections also has same problem=====================================");
		users = entityManager.createQuery("select u from User u", User.class).getResultList();

		for (User u : users) {
			System.out.println("-------The total image is " + u.getImages().size()+"----------");
		}

		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

		entityManager.close();
	}
}
