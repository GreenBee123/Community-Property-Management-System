package com.programmer.entity.admin;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 停车位销售信息类
 * 
 * @author 聂峻民
 *
 */
@Component
public class SaleParking {
	private Long id;//销售信息id
	private String saleId;//销售号
	private int statu;//状态
	private User user;//用户信息
	private Parking parking;//停车位信息
	private String contact;//联系方式
	private String license_number;//车牌号
	private Date sale_date;//销售日期
	
	
	
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	public String getSaleId() {
		return saleId;
	}
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Parking getParking() {
		return parking;
	}
	public void setParking(Parking parking) {
		this.parking = parking;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getLicense_number() {
		return license_number;
	}
	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}
	public Date getSale_date() {
		return sale_date;
	}
	public void setSale_date(Date sale_date) {
		this.sale_date = sale_date;
	}
	
	
}
