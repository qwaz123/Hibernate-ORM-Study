package com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Country implements Serializable{
   
	private static final long serialVersionUID = 8823558791465328019L;
	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;
	private String name; 
    private String capital;
    
    public Country(){}
    
    public Country(String name, String capital) {
    	this.name = name;
    	this.capital = capital;
    }
    
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
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
    
    
}
