package com.programmer.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.programmer.entity.admin.House;
import com.programmer.entity.admin.Menu;
import com.programmer.entity.admin.Parking;
import com.programmer.entity.admin.SaleParking;
import com.programmer.page.admin.Page;
import com.programmer.service.admin.ParkingService;
import com.programmer.service.admin.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;

/**
 * 停车位Controller类
 * 
 * @author 聂峻民
 *
 */
@Controller
@RequestMapping(value="/admin/parking")
public class ParkingController {
	
	@Autowired
	private ParkingService parkingService;
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 进入停车位信息页面
	 * 
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list() {
		return "/parking/list";
	}
	
	/**
	 * 获取停车位信息列表
	 * 
	 * @param page
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getParkingList(Page page,
			@RequestParam(name = "name", required = false, defaultValue = "") String name) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("name", name);
		ret.put("rows", parkingService.findList(queryMap));
		ret.put("total", parkingService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加停车位的信息
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String>addParking(Parking parking,Model model){
		Map<String, String>ret =new HashMap<String,String>();
		if(parkingService.add(parking)<=0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功。");
		
		return ret;
	}
	
	/**
	 * 修改停车位信息
	 * 
	 * @param menu 
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String>editParking(Parking parking){
		Map<String, String>ret =new HashMap<String,String>();
		if(parkingService.edit(parking)<=0) {
			ret.put("type", "error");
			ret.put("msg", "修改失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功。");
		
		return ret;
	}
	
	/**
	 * 删除菜单信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String>deleteParking(@RequestParam(name="id" ,required=true)Long id){
		Map<String, String>ret =new HashMap<String,String>();
		if(id==null) {
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的信息。");
			return ret;
		}
		try {
			if(parkingService.delete(id)<=0) {
				ret.put("type", "error");
				ret.put("msg", "删除失败，请联系管理员。");
				return ret;
			}
		}
		catch (Exception e) {
			ret.put("type", "error");
			ret.put("msg", "该车位有绑定的用户信息，无法删除");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功。");
		return ret;
	}
	
	/**
	 * 进入停车位销售信息页面
	 * 
	 * @return
	 */
	@RequestMapping(value="/salelist",method=RequestMethod.GET)
	public String salelist(Model model) {
		Map<String, Object>queryMap=new HashMap<String, Object>();
		model.addAttribute("Parking", parkingService.findList(queryMap));
		return "/parking/salelist";
	}
	
	/**
	 * 获取停车位销售信息列表
	 * 
	 * @param page
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/salelist",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSaleList(Page page,
			@RequestParam(name = "name", required = false, defaultValue = "") String name) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("name", name);
		
		List<SaleParking>saleParkings=parkingService.findSaleList(queryMap);
		for(SaleParking sParking:saleParkings) {
			if(sParking.getUser()!=null&&sParking.getUser().getId()!=null) {
				sParking.setUser(userService.findById(sParking.getUser().getId()));
			}
			if(sParking.getParking()!=null&&sParking.getParking().getId()!=null) {
				sParking.setParking(parkingService.findParkingById(sParking.getParking().getId()));
			}
		}
		ret.put("rows", saleParkings);
		ret.put("total", parkingService.getTotal(queryMap));
		return ret;
	}
	
	
	/**
	 * 添加停车位的信息
	 * 
	 * @param menu
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/addsale",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String>addSaleParking(SaleParking saleParking,Model model) throws IOException{
		Map<String, String>ret =new HashMap<String,String>();
		if(!checkPlateNumberFormat(saleParking.getLicense_number())) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的车牌号");
			return ret;
		}
		if(!isMobileNO(saleParking.getContact())) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的手机号");
			return ret;
		}
		saleParking.setSaleId("No."+new Date());
		saleParking.setSale_date(new Date());
		if(parkingService.addSale(saleParking)<=0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员。");
			return ret;
		}
		Parking parking=new Parking();
		parking.setStatu(saleParking.getStatu());
		parking.setId(saleParking.getParking().getId());
		parkingService.edit(parking);
		ret.put("type", "success");
		ret.put("msg", "添加成功。");
		
		return ret;
	}
	
	/**
	 * 删除菜单信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deletesale",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String>deleteSaleParking(@RequestParam(name="id" ,required=true)Long id){
		Map<String, String>ret =new HashMap<String,String>();
		Parking parking=new Parking();
		parking.setStatu(0);
		parking.setId(parkingService.findSaleParkingById(id).getParking().getId());
		if(id==null) {
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的信息。");
			return ret;
		}
		try {
			if(parkingService.deleteSale(id)<=0) {
				ret.put("type", "error");
				ret.put("msg", "删除失败，请联系管理员。");
				return ret;
			}
		}
		catch (Exception e) {
			ret.put("type", "error");
			ret.put("msg", "该车位有绑定的用户信息，无法删除");
			return ret;
		}
		
		parkingService.edit(parking);
		ret.put("type", "success");
		ret.put("msg", "删除成功。");
		return ret;
	}
	
	public static boolean checkPlateNumberFormat(String content)throws IOException {
        String pattern = "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{1}(([A-HJ-Z]{1}[A-HJ-NP-Z0-9]{5})|([A-HJ-Z]{1}(([DF]{1}[A-HJ-NP-Z0-9]{1}[0-9]{4})|([0-9]{5}[DF]{1})))|([A-HJ-Z]{1}[A-D0-9]{1}[0-9]{3}警)))|([0-9]{6}使)|((([沪粤川云桂鄂陕蒙藏黑辽渝]{1}A)|鲁B|闽D|蒙E|蒙H)[0-9]{4}领)|(WJ[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼·•]{1}[0-9]{4}[TDSHBXJ0-9]{1})|([VKHBSLJNGCE]{1}[A-DJ-PR-TVY]{1}[0-9]{5})";
        return Pattern.matches(pattern, content);
	}

	public static boolean isMobileNO(String mobiles)throws IOException{
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
}
	
}
