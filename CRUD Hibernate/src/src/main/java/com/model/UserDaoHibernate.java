package com.model;

import com.dao.GenericHibernateDao;

public class UserDaoHibernate extends GenericHibernateDao<User, Long> implements UserDao {

	public UserDaoHibernate(){
		this(User.class);
	}
	protected UserDaoHibernate(Class<User> entityClass) {
		super(entityClass);
	}
	
	public User findByUsername(String username){
		return findBy("username", username);
	}

}
