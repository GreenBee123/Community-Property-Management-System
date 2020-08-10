package com.programmer.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmer.dao.admin.AuthorityDao;
import com.programmer.entity.admin.Authority;
import com.programmer.service.admin.AuthorityService;

/**
 * 权限service类
 * 
 * @author 聂峻民
 *
 */

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityDao authoritydao;
	
	@Override
	public int add(Authority authority) {
		// TODO Auto-generated method stub
		return authoritydao.add(authority);
	}

	@Override
	public int delete(Long roleId) {
		// TODO Auto-generated method stub
		return authoritydao.delete(roleId);
	}

	@Override
	public List<Authority> findAuthorityByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return authoritydao.findAuthorityByRoleId(roleId);
	}

}
