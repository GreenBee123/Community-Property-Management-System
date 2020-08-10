package com.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.programmer.entity.admin.Fee;
@Service
public interface FeeService {
	public List<Fee>findList(Map<String, Object>queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int add(Fee fee);
	public int edit(Fee fee);
}
