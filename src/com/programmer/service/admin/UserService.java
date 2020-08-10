package com.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.programmer.entity.admin.User;

/**
 * 用户服务类
 * 
 * @author 聂峻民
 *
 */
@Service
public interface UserService {
	public User findByUsername(String username);
	public User findById(Long id);
	public List<User>findList(Map<String, Object>queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int add(User user);
	public int edit(User user);
	public int delete(String ids);
	public int editPassword(User user);
}
