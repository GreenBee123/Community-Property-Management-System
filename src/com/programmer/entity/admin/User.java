package com.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 用户实体类
 * 
 * @author 聂峻民
 *
 */
@Component
public class User {
	private Long id;
	private String username;
	private String password;
	private String photo;// 頭像照片地址
	private int sex;// 0位置，1男，2女
	private Integer age;
	private String address;
	private Long roleId;// 用户角色

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User() {
		super();
	}

	public User(Long id, String username, String password, String photo, int sex, Integer age, String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.photo = photo;
		this.sex = sex;
		this.age = age;
		this.address = address;
	}
}
