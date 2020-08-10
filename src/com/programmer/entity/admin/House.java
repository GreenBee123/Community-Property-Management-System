package com.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 住宅类
 * 
 * @author 聂峻民
 *
 */
@Component
public class House {
	private Long id;
	private Long houseId;
	private String name;
	private double floor_area;
	private double usable_floor_area;
	private int statu;
	private User user;
	
	
	public Long getHouseId() {
		return houseId;
	}
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
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
	public double getFloor_area() {
		return floor_area;
	}
	public void setFloor_area(double floor_area) {
		this.floor_area = floor_area;
	}
	public double getUsable_floor_area() {
		return usable_floor_area;
	}
	public void setUsable_floor_area(double usable_floor_area) {
		this.usable_floor_area = usable_floor_area;
	}
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
