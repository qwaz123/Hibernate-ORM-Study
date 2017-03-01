package com.model;

public class UserRegisteredException extends Exception {

	private static final long serialVersionUID = -1327203453813370432L;

	public UserRegisteredException() {
		super("Username already used.");
	}

}
