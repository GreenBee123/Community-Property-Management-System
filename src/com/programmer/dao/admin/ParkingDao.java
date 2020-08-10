package com.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.programmer.entity.admin.House;
import com.programmer.entity.admin.Parking;
import com.programmer.entity.admin.SaleParking;

/**
 * 
 * 停车位信息Dao类
 * 
 * @author 聂峻民
 *
 */
@Repository
public interface ParkingDao {
	public Parking findParkingById(Long parkingId);
	public List<Parking>findList(Map<String, Object>queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int add(Parking parking);
	public int edit(Parking parking);
	public int delete(Long parkingId);
	
	public List<SaleParking>findSaleList(Map<String, Object>queryMap);
	public SaleParking findSaleParkingById(Long saleParkingId);
	public int addSale(SaleParking saleParking);
	public int deleteSale(Long saleParkingId);
}
