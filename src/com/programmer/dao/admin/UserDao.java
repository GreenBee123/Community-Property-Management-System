package com.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.programmer.entity.admin.User;

@Repository
public interface UserDao {
	public User findByUsername(String username);
	public User findById(Long id);
	public List<User>findList(Map<String, Object>queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int add(User user);
	public int edit(User user);
	public int delete(String ids);
	public int editPassword(User user);
}
