package com.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmer.dao.admin.RoleDao;
import com.programmer.entity.admin.Role;
import com.programmer.service.admin.RoleService;

/**
 * 
 * 角色Service类
 * @author 聂峻民
 *
 */
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roledao;
	
	@Override
	public List<Role> findRoleList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return roledao.findRoleList(queryMap);
	}

	@Override
	public int add(Role role) {
		// TODO Auto-generated method stub
		return roledao.add(role);
	}

	@Override
	public int edit(Role role) {
		// TODO Auto-generated method stub
		return roledao.edit(role);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return roledao.delete(id);
	}

	@Override
	public Role findRoleById(Long id) {
		// TODO Auto-generated method stub
		return roledao.findRoleById(id);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return roledao.getTotal(queryMap);
	}

}
