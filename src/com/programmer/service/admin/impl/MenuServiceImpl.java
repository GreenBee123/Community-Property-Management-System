package com.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmer.dao.admin.MenuDao;
import com.programmer.entity.admin.Menu;
import com.programmer.service.admin.MenuService;

@Service 
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuDao menudao; 
	
	@Override
	public int add(Menu menu) {
		// TODO Auto-generated method stub
		return menudao.add(menu);
	}

	@Override
	public List<Menu> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return menudao.findList(queryMap);
	}

	@Override
	public List<Menu> findTopList() {
		// TODO Auto-generated method stub
		return menudao.findTopList();
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return menudao.getTotal(queryMap);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return menudao.delete(id);
	}

	@Override
	public int edit(Menu menu) {
		// TODO Auto-generated method stub
		return menudao.edit(menu);
	}

	@Override
	public List<Menu> findChildrenList(Long parentId) {
		// TODO Auto-generated method stub
		return menudao.findChildrenList(parentId);
	}

	@Override
	public List<Menu> findListByIds(String ids) {
		// TODO Auto-generated method stub
		return menudao.findListByIds(ids);
	}
	
}
