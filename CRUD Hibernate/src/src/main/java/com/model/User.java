package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@org.hibernate.annotations.GenericGenerator(name = "userPKGenerator", strategy = "enhanced-sequence", parameters = {
		@org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
		@org.hibernate.annotations.Parameter(name = "initial_value", value = "1") })

@Entity(name = "user")
@Table(name = "USER")
public class User {
	private Long id;
	private String username;
	private String address;
	private Date createdOn;	

	public User() {
	}
	public User(String username, String address) {
		setUsername(username);
		setAddress(address);
	}
	@Id
	@GeneratedValue(generator = "userPKGenerator")
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	@org.hibernate.annotations.CreationTimestamp
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}	
}
