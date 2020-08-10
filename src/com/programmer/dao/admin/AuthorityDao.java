package com.programmer.dao.admin;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.programmer.entity.admin.Authority;

/**
 * 权限Dao类
 * 
 * @author 聂峻民
 *
 */
@Repository
public interface AuthorityDao {
	public int add(Authority authority);
	public int delete(Long roleId);
	public List<Authority>findAuthorityByRoleId(Long roleId);
}
