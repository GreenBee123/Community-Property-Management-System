package com.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.programmer.entity.admin.Decorate;

/**
 * 装修信息Service
 * 
 * @author 聂峻民
 *
 */
@Service
public interface DecorateService {
	public Decorate findDecorateById(Long decorateId);
	public List<Decorate>findList(Map<String, Object>queryMap);
	public int getTotal(Map<String, Object>queryMap);
	public int add(Decorate decorate);
	public int edit(Decorate decorate);
	public int delete(Long decorateId);
	public int approval(Decorate decorate);
}
