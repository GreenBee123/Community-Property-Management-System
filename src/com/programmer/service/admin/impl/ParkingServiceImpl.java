package com.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmer.dao.admin.ParkingDao;
import com.programmer.entity.admin.Parking;
import com.programmer.entity.admin.SaleParking;
import com.programmer.service.admin.ParkingService;

/**
 * 停车位Service类
 * 
 * @author 聂峻民
 *
 */
@Service
public class ParkingServiceImpl implements ParkingService {

	@Autowired
	private ParkingDao parkingDao;

	@Override
	public Parking findParkingById(Long parkingId) {
		// TODO Auto-generated method stub
		return parkingDao.findParkingById(parkingId);
	}

	@Override
	public List<Parking> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return parkingDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return parkingDao.getTotal(queryMap);
	}

	@Override
	public int add(Parking parking) {
		// TODO Auto-generated method stub
		return parkingDao.add(parking);
	}

	@Override
	public int edit(Parking parking) {
		// TODO Auto-generated method stub
		return parkingDao.edit(parking);
	}

	@Override
	public int delete(Long parkingId) {
		// TODO Auto-generated method stub
		return parkingDao.delete(parkingId);
	}

	@Override
	public List<SaleParking> findSaleList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return parkingDao.findSaleList(queryMap);
	}

	@Override
	public int addSale(SaleParking saleParking) {
		// TODO Auto-generated method stub
		return parkingDao.addSale(saleParking);
	}

	@Override
	public int deleteSale(Long saleParkingId) {
		// TODO Auto-generated method stub
		return parkingDao.deleteSale(saleParkingId);
	}

	@Override
	public SaleParking findSaleParkingById(Long saleParkingId) {
		// TODO Auto-generated method stub
		return parkingDao.findSaleParkingById(saleParkingId);
	}

}
