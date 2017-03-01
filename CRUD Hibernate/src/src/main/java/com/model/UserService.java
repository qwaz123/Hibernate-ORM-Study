package com.model;

import java.util.List;

public class UserService {
	private UserDao dao;
	private final int pageSize = 10;

	public UserService() {
		dao = new UserDaoHibernate();
	}

	public boolean isUserRegisted(String username) {
		return findByUsername(username) != null;
	}

	public User findByUsername(String username) {
		return dao.findByUsername(username);
	}

	public List<User> findAllUser() {
		return dao.findAll();
	}

	public User findById(Long id) {
		return dao.findById(id);
	}

	public User add(User user) throws UserRegisteredException {

		if (isUserRegisted(user.getUsername())) {
			throw new UserRegisteredException();
		}

		return dao.makePersistent(user);
	}

	public User update(User user) {
		return dao.makePersistent(user);
	}

	public void delete(User user) {
		dao.makeTransient(user);
	}

	public List<User> page(int pageIndex) {
		return dao.getLimitResult(pageIndex * pageSize, pageSize);
	}

	public long totalPage() {
		return dao.count() / pageSize;
	}

}
