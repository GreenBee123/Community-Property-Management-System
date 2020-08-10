package com.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.programmer.entity.admin.Decorate;
import com.programmer.entity.admin.House;
import com.programmer.entity.admin.User;


/**
 * 住房Dao类
 * 
 * @author 聂峻民
 *
 */
@Repository
public interface HouseDao {
	public House findHouseById(Long houseId);
	public House findHouseByUserId(Long userId);
	public List<House>findList(Map<String, Object>queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int add(House house);
	public int edit(House house);
	public int delete(Long houseId);
}
