package com.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
//@org.hibernate.annotations.BatchSize(size = 10)
public class User {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;
	private String username;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Country country;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST, mappedBy="user")
	private List<Image> images;
	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
}
