package com.programmer.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.programmer.entity.admin.Advice;
import com.programmer.entity.admin.Decorate;
import com.programmer.entity.admin.House;
import com.programmer.entity.admin.Role;
import com.programmer.entity.admin.User;
import com.programmer.page.admin.Page;

@Controller
@RequestMapping(value="/admin/advice")
public class AdviceController {
	
	
	/**
	 * 进入装修界面
	 * 
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list() {
		return "/advice/list";
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
		List<Advice>advices=new ArrayList<Advice>();
		Advice advice=new Advice(1l, "小明", new Date(), "这是投诉内容", 1, null, null, null);
		Advice advice1=new Advice(2l, "admin", new Date(), "这是投诉内容2", 1, new Date(), "admin", "物业管理员录入");
		advices.add(advice);
		advices.add(advice1);
		ret.put("rows", advices);
		ret.put("total", 2);
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

		ret.put("type", "success");
		ret.put("msg", "审批成功。");
		return ret;
	}
}
