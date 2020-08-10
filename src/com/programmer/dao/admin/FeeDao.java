package com.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.programmer.entity.admin.Decorate;
import com.programmer.entity.admin.Fee;

@Repository
public interface FeeDao {
	public List<Fee>findList(Map<String, Object>queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int add(Fee fee);
	public int edit(Fee fee);
}
