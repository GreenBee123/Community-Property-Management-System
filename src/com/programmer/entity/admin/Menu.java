package com.programmer.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class Menu {
	private int id;
	private Long parentId;//父类ids
	private Long _parentId;//用来匹配easyui的父类id
	
	private String name;//菜单名称
	private String url;//点击后的url
	private String icon;//菜单icon图标
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Menu(int id, Long parentId, String name, String url, String icon) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.url = url;
		this.icon = icon;
	}
	public Menu() {
		super();
	}
	public Long get_parentId() {
		_parentId=parentId;
		return _parentId;
	}
	public void set_parentId(Long _parentId) {
		this._parentId = _parentId;
	}
	
	
	
}
