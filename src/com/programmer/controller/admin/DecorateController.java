package com.programmer.controller.admin;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.programmer.entity.admin.Decorate;
import com.programmer.entity.admin.House;
import com.programmer.entity.admin.Role;
import com.programmer.entity.admin.User;
import com.programmer.page.admin.Page;
import com.programmer.service.admin.DecorateService;
import com.programmer.service.admin.HouseService;
import com.programmer.service.admin.RoleService;
import com.programmer.service.admin.UserService;

/**
 * 装修Controller类
 * 
 * @author 聂峻民
 *
 */
@Controller
@RequestMapping(value="/admin/decorate")
public class DecorateController {
	
	@Autowired
	private DecorateService decorateService;
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 进入装修界面
	 * 
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list() {
		return "/decorate/list";
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
		List<Decorate> findDecorateList = decorateService.findList(queryMap);
		for(Decorate decorate:findDecorateList) {
			if(decorate.getHouse().getId()!=null&&decorate.getHouse().getId()!=0) {
				decorate.setHouse(houseService.findHouseById(decorate.getHouse().getId()));
				decorate.setUser(userService.findById(decorate.getHouse().getUser().getId()));
			}
			if(decorate.getExaminer()!=null&&decorate.getExaminer().getId()!=null&&decorate.getExaminer().getId()!=0) {
				decorate.setExaminer(roleService.findRoleById(decorate.getExaminer().getId()));
			}
		}
		ret.put("rows", findDecorateList);
		ret.put("total", decorateService.getTotal(queryMap));
		return ret;
	}
	
	
	/**
	 * 添加装修的信息
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String>addDecoration(Decorate decorate,HttpServletRequest request){
		Map<String, String>ret =new HashMap<String,String>();
		if(decorate.getBail()==0) {
			ret.put("type", "error");
			ret.put("msg", "请填写保证金！。");
			return ret;
		}
		if(decorate.getDecorationContent()==null) {
			ret.put("type", "error");
			ret.put("msg", "请填写装修内容。");
			return ret;
		}
		User user=(User)request.getSession().getAttribute("admin");
		decorate.setUser(user);
		decorate.setStatu(1);
		decorate.setApplicationDate(new Date());
		House house=houseService.findHouseByUserId(user.getId());
		decorate.setRemark((roleService.findRoleById(user.getId()).getName()+"录入"));
		if(house==null){
			ret.put("type", "error");
			ret.put("msg", "该用户没有住房信息，不能申请装修。");
			return ret;
		}
		decorate.setHouse(house);
		if(decorateService.add(decorate)<=0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功。");
		return ret;
	}
	
	/**
	 * 修改装修信息
	 * 
	 * @param menu 传过去的指定住房
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String>editDecoration(Decorate decorate,HttpServletRequest request){
		Map<String, String>ret =new HashMap<String,String>();
		if(decorate.getBail()==0) {
			ret.put("type", "error");
			ret.put("msg", "请填写保证金！。");
			return ret;
		}
		if(decorate.getDecorationContent()==null) {
			ret.put("type", "error");
			ret.put("msg", "请填写装修内容。");
			return ret;
		}
		User user=(User)request.getSession().getAttribute("admin");
		decorate.setUser(user);
		decorate.setStatu(1);
		decorate.setApplicationDate(new Date());
		House house=houseService.findHouseByUserId(user.getId());
		decorate.setRemark((roleService.findRoleById(user.getId()).getName()+"录入"));
		if(house==null){
			ret.put("type", "error");
			ret.put("msg", "该用户没有住房信息，不能申请装修。");
			return ret;
		}
		decorate.setHouse(house);
		decorate.setApplicationDate(new Date());
		if(decorateService.edit(decorate)<=0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功。");
		return ret;
	}
	
	/**
	 * 删除装修信息
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
		if(decorateService.delete(id)<=0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功。");
		return ret;
	}
	
	/**
	 * 审批装修信息
	 * 
	 * @param menu 传过去的指定住房
	 * @return
	 */
	@RequestMapping(value="/approval",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String>apprDecoration(Decorate decorate,HttpServletRequest request){
		Map<String, String>ret =new HashMap<String,String>();

		User user=(User)request.getSession().getAttribute("admin");
		Role examiner=(roleService.findRoleById(user.getRoleId()));
		if(examiner==null){
			ret.put("type", "error");
			ret.put("msg", "该用户没有没有权限信息，不能审批");
			return ret;
		}
		decorate.setExaminer(examiner);
		decorate.setApprovalTime(new Date());
		if(decorateService.approval(decorate)<=0) {
			ret.put("type", "error");
			ret.put("msg", "审批失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "审批成功。");
		return ret;
	}
}
