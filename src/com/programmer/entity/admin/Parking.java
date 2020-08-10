package com.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 停车位类
 * 
 * @author 聂峻民
 *
 */
@Component
public class Parking {
	private Long id;//车位ID
	private int statu;//车位状态
	private int category;//车位类别
	private double presale_prices;//预售价格
	private double prelease_prices;//预租价格
	private double area;//面积大小
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public double getPresale_prices() {
		return presale_prices;
	}
	public void setPresale_prices(double presale_prices) {
		this.presale_prices = presale_prices;
	}
	public double getPrelease_prices() {
		return prelease_prices;
	}
	public void setPrelease_prices(double prelease_prices) {
		this.prelease_prices = prelease_prices;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	
}
