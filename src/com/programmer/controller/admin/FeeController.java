package com.programmer.controller.admin;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.programmer.entity.admin.Advice;
import com.programmer.entity.admin.Fee;
import com.programmer.page.admin.Page;
import com.programmer.service.admin.FeeService;
import com.programmer.service.admin.HouseService;
import com.programmer.service.admin.UserService;

@Controller
@RequestMapping(value="/admin/fee")
public class FeeController {
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FeeService feeService;
	/**
	 * 进入装修界面
	 * 
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list() {
		return "/fee/list";
	}
	
	/**
	 * 获取装修列表
	 * 
	 * @param page
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDecorateList(Page page,
			@RequestParam(name = "name", required = false, defaultValue = "") String name) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("name", name);
		List<Fee>fees=feeService.findList(queryMap);
		for(Fee fee: fees) {
			if(fee.getHouse()!=null&&fee.getHouse().getId()!=null) {
				fee.setHouse(houseService.findHouseById(fee.getHouse().getId()));
			}
			if(fee.getHouse()!=null&&fee.getHouse().getUser().getId()!=null) {
				fee.getHouse().setUser(userService.findById(fee.getHouse().getUser().getId()));
			}
		}
		ret.put("rows", fees);
		ret.put("total", feeService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加收费烂
	 * 
	 * @param fee
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String>addFee(Fee fee){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("type", "success");
		ret.put("msg", "添加成功。");
		return ret;
	}
}
