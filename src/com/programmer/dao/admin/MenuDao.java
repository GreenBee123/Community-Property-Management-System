package com.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.programmer.entity.admin.Menu;
@Repository
public interface MenuDao {
	public int add(Menu menu);
	public List<Menu>findList(Map<String, Object>queryMap);
	public List<Menu>findTopList();
	public int getTotal(Map<String, Object>queryMap);
	public int delete(Long id);
	public int edit(Menu menu);
	public List<Menu>findChildrenList(Long parentId);
	public List<Menu>findListByIds(String ids);
}
