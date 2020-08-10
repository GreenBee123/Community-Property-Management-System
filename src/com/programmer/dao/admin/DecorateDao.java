package com.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.programmer.entity.admin.Decorate;
import com.programmer.entity.admin.House;
import com.programmer.entity.admin.User;

/**
 * 装修信息Dao类
 * 
 * @author 聂峻民
 *
 */
@Repository
public interface DecorateDao {
	public Decorate findDecorateById(Long decorateId);
	public List<Decorate>findList(Map<String, Object>queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int add(Decorate decorate);
	public int edit(Decorate decorate);
	public int delete(Long decorateId);
	public int approval(Decorate decorate);
}
