package com.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmer.dao.admin.UserDao;
import com.programmer.entity.admin.User;
import com.programmer.service.admin.UserService;
/**
 * 用户Service类
 * 
 * @author 聂峻民
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userdao;
	
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userdao.findByUsername(username);
	}

	@Override
	public List<User> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return userdao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return userdao.getTotal(queryMap);
	}

	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
		return userdao.add(user);
	}

	@Override
	public int edit(User user) {
		// TODO Auto-generated method stub
		return userdao.edit(user);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return userdao.delete(ids);
	}

	@Override
	public int editPassword(User user) {
		// TODO Auto-generated method stub
		return userdao.editPassword(user);
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userdao.findById(id);
	}

}
