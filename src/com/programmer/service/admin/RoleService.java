package com.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.programmer.entity.admin.Role;

/**
 * 
 * 角色Service类
 * @author 聂峻民
 *
 */
@Service
public interface RoleService {
	public List<Role> findRoleList(Map<String, Object>queryMap);
	public int add(Role role);
	public int edit(Role role);
	public int delete(Long id);
	public Role findRoleById(Long id);
	public int getTotal(Map<String, Object>queryMap); 
}
