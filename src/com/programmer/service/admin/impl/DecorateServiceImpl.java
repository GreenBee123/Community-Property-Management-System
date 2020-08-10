package com.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmer.dao.admin.DecorateDao;
import com.programmer.entity.admin.Decorate;
import com.programmer.service.admin.DecorateService;

/**
 * 装修信息Dao类
 * 
 * @author 聂峻民
 *
 */
@Service
public class DecorateServiceImpl implements DecorateService {

	@Autowired
	private DecorateDao decorateDao;
	
	
	@Override
	public Decorate findDecorateById(Long decorateId) {
		// TODO Auto-generated method stub
		return decorateDao.findDecorateById(decorateId);
	}

	@Override
	public List<Decorate> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return decorateDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return decorateDao.getTotal(queryMap);
	}

	@Override
	public int add(Decorate decorate) {
		// TODO Auto-generated method stub
		return decorateDao.add(decorate);
	}

	@Override
	public int edit(Decorate decorate) {
		// TODO Auto-generated method stub
		return decorateDao.edit(decorate);
	}

	@Override
	public int delete(Long decorateId) {
		// TODO Auto-generated method stub
		return decorateDao.delete(decorateId);
	}

	@Override
	public int approval(Decorate decorate) {
		// TODO Auto-generated method stub
		return decorateDao.approval(decorate);
	}

}
