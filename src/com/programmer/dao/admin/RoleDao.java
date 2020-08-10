package com.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.programmer.entity.admin.Role;

/**
 * 角色Dao类
 * 
 * @author 聂峻民
 *
 */
@Repository
public interface RoleDao {
	public List<Role> findRoleList(Map<String, Object>query);
	public int add(Role role);
	public int edit(Role role);
	public int delete(Long id);
	public Role findRoleById(Long id);
	public int getTotal(Map<String, Object>queryMap); 
}
