package com.programmer.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.programmer.entity.admin.Authority;
/**
 * 权限service接口
 * 
 * @author 聂峻民
 *
 */
@Service
public interface AuthorityService {
	public int add(Authority authority);
	public int delete(Long roleId);
	public List<Authority>findAuthorityByRoleId(Long roleId);
}
