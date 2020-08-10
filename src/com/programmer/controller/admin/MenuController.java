package com.programmer.controller.admin;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.programmer.entity.admin.Menu;
import com.programmer.page.admin.Page;
import com.programmer.service.admin.MenuService;

/**
 * 菜单控制器类
 * 
 * @author 聂峻民
 *
 */
@Controller
@RequestMapping(value="/admin/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuservice;
	
	/**
	 * 进入菜单页面
	 * 
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("topList",menuservice.findTopList());
		return "/menu/list";
	}
	
	/**
	 * 获取菜单列表
	 * 
	 * @param page
	 * @param username
	 * @param roleId
	 * @param sex
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>getMenuList(Page page,
			@RequestParam(name="username",required=false,defaultValue="")String username,
			@RequestParam(name="roleId",required=false,defaultValue="")Long roleId,
			@RequestParam(name="sex",required=false,defaultValue="")Integer sex
			){
		Map<String, Object>ret=new HashMap<String, Object>();
		Map<String, Object>queryMap=new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pagesize", page.getRows());
		queryMap.put("username", username);
		queryMap.put("roleId", roleId);
		queryMap.put("sex", sex);
		List<Menu> menusList=menuservice.findList(queryMap);
		ret.put("rows", menusList);
		ret.put("total", menuservice.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加菜单的信息
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String>addMenu(Menu menu,Model model){
		Map<String, String>ret =new HashMap<String,String>();
		if(menu==null||StringUtils.isEmpty(menu.getName())) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的菜单信息。");
			return ret;
		}
		if(StringUtils.isEmpty(menu.getIcon())) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的菜单图标信息。");
			return ret;
		}
		if(menu.get_parentId()==null) {
			menu.set_parentId(0L);
		}else {
			menu.set_parentId(menu.getParentId());
		}
		if(menuservice.add(menu)<=0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功。");
		
		return ret;
	}
	
	/**
	 * 修改菜单
	 * 
	 * @param menu 传过去的指定Menu信息
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String>editMenu(Menu menu){
		Map<String, String>ret =new HashMap<String,String>();
		if(menu==null||StringUtils.isEmpty(menu.getName())) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的菜单信息。");
			return ret;
		}
		if(StringUtils.isEmpty(menu.getIcon())) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的菜单图标信息。");
			return ret;
		}
		if(menu.get_parentId()==null) {
			menu.set_parentId(0L);
		}
		if(menuservice.edit(menu)<=0) {
			ret.put("type", "error");
			ret.put("msg", "修改失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功。");
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
	public Map<String, String>deleteMenu(@RequestParam(name="id" ,required=true)Long id){
		Map<String, String>ret =new HashMap<String,String>();
		if(id==null) {
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的信息。");
			return ret;
		}
		List<Menu>childMune=menuservice.findChildrenList(id);
		if(childMune!=null&&childMune.size()>0) {
			ret.put("type", "error");
			ret.put("msg", "该菜单里面包含子菜单信息，无法删除。");
			return ret;
		}
		if(menuservice.delete(id)<=0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败，请联系管理员。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功。");
		return ret;
	}
	
	
	/**
	 * 获取图标列表
	 * 
	 * 获取所有的文件名，然后对文件名近些截取修改，然后返还给田端页面
	 * 
	 * @param request
	 * @return 返回所有图标的文件名，放在一个list集合中
	 */
	@RequestMapping(value = "/get_icons", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getIconList(HttpServletRequest request) {
		Map<String, Object> ret = new HashMap<String, Object>();
		String realPath = request.getServletContext().getRealPath("/");
		File file = new File(realPath + "\\resources\\admin\\easyui\\css\\icons");
		List<String> icon = new ArrayList<String>();
		if (!file.exists()) {
			ret.put("type", "error");
			ret.put("msg", "文件目录不存在");
		}
		File[] listFiles = file.listFiles();//listFiles()方法是返回某个目录下所有文件和目录的绝对路径，返回的是File数组
		for (File f : listFiles) {
			if (f != null && f.getName().contains("png")) {
				//如果是png文件，截取文字，从第一个字符到.之前，并且将下划线替代为-
				icon.add("icon-" + f.getName().substring(0, f.getName().indexOf(".")).replace("_", "-"));
			}
		}
		ret.put("type", "success");
		ret.put("content",icon);
		return ret;
	}
}
