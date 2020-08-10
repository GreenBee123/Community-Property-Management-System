package com.programmer.controller.admin;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.programmer.entity.admin.Fee;
import com.programmer.entity.admin.House;
import com.programmer.entity.admin.Menu;
import com.programmer.entity.admin.User;
import com.programmer.page.admin.Page;
import com.programmer.service.admin.FeeService;
import com.programmer.service.admin.HouseService;
import com.programmer.service.admin.UserService;

/**
 * 
 * 住房Controller
 * 
 * @author 聂峻民
 *
 */
@Controller
@RequestMapping(value="/admin/house")
public class HouseController {

	@Autowired
	private HouseService houserService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FeeService feeService;
	
	/**
	 * 进入住房界面
	 * 
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list() {
		return "/house/list";
	}
	
	/**
	 * 获取住房列表
	 * 
	 * @param page
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getHouseList(Page page,
			@RequestParam(name = "name", required = false, defaultValue = "") String name) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("name", name);
		List<House> findHouseList = houserService.findList(queryMap);
		for(House house:findHouseList) {
			if(house.getUser()!=null&&house.getUser().getId()!=null&&house.getUser().getId()!=0) {
				house.setUser(userService.findById(house.getUser().getId()));
			}
		}
		ret.put("rows", findHouseList);
		ret.put("total", houserService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加住房的信息
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String>addHouse(House house){
		Map<String, String>ret =new HashMap<String,String>();
		if(house==null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的住房信息。");
			return ret;
		}
		if(house.getHouseId()!=null) {
			house.setName(String.valueOf(house.getHouseId()/1000)+"栋"+String.valueOf(house.getHouseId()%1000)+"室");
		}
		if(house.getUser()==null||house.getUser().getId()==0) {
			house.setStatu(0);
		}else {
			house.setStatu(1);
			if(userService.findById(house.getUser().getId())==null) {
				ret.put("type", "error");
				ret.put("msg", "添加失败，没有该用户存在。");
				return ret;
			}
		}
		if(houserService.add(house)<=0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功。");
		House house2=new House();
		if(houserService.findHouseByUserId(house.getUser().getId())!=null) {
			house2=houserService.findHouseByUserId(house.getUser().getId());
			Fee fee=new Fee();
			fee.setHouse(house2);
			fee.setDate(new Date());
			feeService.add(fee);
		}
		return ret;
	}
	
	/**
	 * 修改住房信息
	 * 
	 * @param menu 传过去的指定住房
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String>editHouse(House house){
		Map<String, String>ret =new HashMap<String,String>();
		if(house==null||house.getId()==null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的住房信息。");
			return ret;
		}
		if(house.getId()!=null) {
			house.setName(String.valueOf(house.getHouseId()/1000)+"栋"+String.valueOf(house.getHouseId()%1000)+"室");
		}
		if(house.getUser()==null||house.getUser().getId()==0) {
			house.setStatu(0);
		}else {
			house.setStatu(1);
			if(userService.findById(house.getUser().getId())==null) {
				ret.put("type", "error");
				ret.put("msg", "添加失败，没有该用户存在。");
				return ret;
			}
		}
		if(houserService.edit(house)<=0) {
			ret.put("type", "error");
			ret.put("msg", "修改失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功。");
		return ret;
	}
	
	/**
	 * 删除住房信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String>deleteMenu(@RequestParam(name="id" ,required=true)Long id){
		Map<String, String>ret =new HashMap<String,String>();
		if(id==null) {
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的信息。");
			return ret;
		}
		if(houserService.delete(id)<=0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功。");
		return ret;
	}
	
}
