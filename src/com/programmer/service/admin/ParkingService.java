package com.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.programmer.entity.admin.Parking;
import com.programmer.entity.admin.SaleParking;

/**
 * 停车位Service类
 * 
 * @author 聂峻民
 *
 */
@Service
public interface ParkingService {
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
