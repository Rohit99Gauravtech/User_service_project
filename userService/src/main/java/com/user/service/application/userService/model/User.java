package com.user.service.application.userService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;
	private String name;
	private String adddr;
	
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdddr() {
		return adddr;
	}
	public void setAdddr(String adddr) {
		this.adddr = adddr;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", adddr=" + adddr + "]";
	}

}
