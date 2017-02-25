package com.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "IMAGE")
public class Image implements Serializable {

	private static final long serialVersionUID = -4861363108774020175L;
	
	@Id
	@GeneratedValue(generator = "ID_GENERATOR")	
	private Long id;
	private String name;
	private String type;
	
	public Image(){}
	public Image(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn
	private User user;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
