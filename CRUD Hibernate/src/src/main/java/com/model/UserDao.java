package com.model;

import com.dao.GenericDao;

public interface UserDao extends GenericDao<User, Long> {
	User findByUsername(String username);
}
