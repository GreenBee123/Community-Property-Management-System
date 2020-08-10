package com.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmer.dao.admin.FeeDao;
import com.programmer.entity.admin.Fee;
import com.programmer.service.admin.FeeService;

@Service
public class FeeServiceImpl implements FeeService{

	@Autowired
	private FeeDao feeDao; 
	
	@Override
	public List<Fee> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return feeDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return feeDao.getTotal(queryMap);
	}

	@Override
	public int add(Fee fee) {
		// TODO Auto-generated method stub
		return feeDao.add(fee);
	}

	@Override
	public int edit(Fee fee) {
		// TODO Auto-generated method stub
		return feeDao.edit(fee);
	}

}
