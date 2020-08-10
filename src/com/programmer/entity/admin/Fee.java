package com.programmer.entity.admin;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Fee {
	private Long Id;
	private House house;
	private Date date;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
