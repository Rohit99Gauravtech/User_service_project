package com.user.service.application.userService.dto;

import java.util.List;

public class UserDto {
	
	private Integer uid;
	private String name;
	private String adddr;
	private List<OrdersDto> orders;
	

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
	public List<OrdersDto> getOrders() {
		return orders;
	}
	public void setOrders(List<OrdersDto> orders) {
		this.orders = orders;
	}
	

}
