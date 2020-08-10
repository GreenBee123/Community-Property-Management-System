package com.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 权限实体类
 * 
 * @author 聂峻民
 *
 */
@Component
public class Authority {
	private Long id;
	private Long roleId;
	private Long menuId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
	
}
