package com.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmer.dao.admin.HouseDao;
import com.programmer.entity.admin.Decorate;
import com.programmer.entity.admin.House;
import com.programmer.entity.admin.User;
import com.programmer.service.admin.HouseService;


/**
 * 
 * 住房Service类
 * @author 聂峻民
 *
 */
@Service
public class HouseServiceImpl implements HouseService{

	@Autowired
	private HouseDao houseDao;
	
	@Override
	public House findHouseById(Long houseId) {
		// TODO Auto-generated method stub
		return houseDao.findHouseById(houseId);
	}

	@Override
	public List<House> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return houseDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return houseDao.getTotal(queryMap);
	}

	@Override
	public int add(House house) {
		// TODO Auto-generated method stub
		return houseDao.add(house);
	}

	@Override
	public int edit(House house) {
		// TODO Auto-generated method stub
		return houseDao.edit(house);
	}

	@Override
	public int delete(Long houseId) {
		// TODO Auto-generated method stub
		return houseDao.delete(houseId);
	}

	@Override
	public House findHouseByUserId(Long userId) {
		// TODO Auto-generated method stub
		return houseDao.findHouseByUserId(userId);
	}

}
