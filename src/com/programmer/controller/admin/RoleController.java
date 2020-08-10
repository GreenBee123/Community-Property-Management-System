package com.programmer.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.programmer.entity.admin.Authority;
import com.programmer.entity.admin.Menu;
import com.programmer.entity.admin.Role;
import com.programmer.page.admin.Page;
import com.programmer.service.admin.AuthorityService;
import com.programmer.service.admin.MenuService;
import com.programmer.service.admin.RoleService;

/**
 * 角色控制器
 * 
 * @author 聂峻民
 *
 */
@Controller
@RequestMapping(value="/admin/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private AuthorityService authorityService;
	
	/**
	 * 进入角色页面
	 * 
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list() {
		return "/role/list";
	}
	
	/**
	 * 获取角色数据
	 * @param page 分页信息
	 * @param name 搜索按钮提供的name，以进行模糊查询
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getRoleList(Page page,
			@RequestParam(name="name",required=false,defaultValue="") String name) {
		Map<String, Object>ret =new HashMap<String, Object>();
		Map<String, Object>queryMap=new HashMap<String,Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pagesize", page.getRows());
		queryMap.put("name", name);
		List<Role>roleList=roleService.findRoleList(queryMap);
		ret.put("rows", roleList);
		ret.put("total", roleService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addRole(Role role) {
		Map<String, String>ret =new HashMap<String, String>();
		if(role==null||StringUtils.isEmpty(role.getName())) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的角色信息。");
			return ret;
		}
		if(roleService.add(role)<=0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员。");
			return ret;
		}		
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}
	
	/**
	 * 修改角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String>editRole(Role role){
		Map<String, String>ret =new HashMap<String, String>();
		if(role==null||StringUtils.isEmpty(role.getName())) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的角色信息。");
			return ret;
		}
		if(roleService.edit(role)<=0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员。");
			return ret;
		}		
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}
	
	/**
	 * 删除权限
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteRole(Long id) {
		Map<String, String>ret =new HashMap<String, String>();
		if(id==null) {
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的信息。");
			return ret;
		}
		try {
			if(roleService.delete(id)<=0) {
				ret.put("type", "error");
				ret.put("msg", "删除失败，请联系管理员。");
				return ret;
			}		
		}catch (Exception e) {
			ret.put("type", "error");
			ret.put("msg", "该角色存在着权限或者用户信息，无法删除。");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}
	
	/**
	 * 查找数据库中所有的菜单信息
	 * @return
	 */
	@RequestMapping(value="/get_all_menu",method=RequestMethod.POST)
	@ResponseBody
	public List<Menu>getAllMenu(){
		Map<String, Object>queryMap =new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pagesize", 9999);
		return menuService.findList(queryMap);
	}
	
	/**
	 * 添加权限
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value="add_authority",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String>addAuthority(
			@RequestParam(name="roleId",required=true)Long roleId,
			@RequestParam(name="ids",required=true)String ids){
		Map<String, String>ret =new HashMap<String, String>();
		Map<String, String>queryMap =new HashMap<String, String>();
		if(roleId==null) {
			ret.put("type", "error");
			ret.put("msg", "请输入正确的用户信息。");
			return ret;
		}
		if(ids==null) {
			ret.put("type", "error");
			ret.put("msg", "请输入正确的菜单信息。");
			return ret;
		}
		//截取最后一个逗号
		if (ids.contains(",")) {
			ids.substring(0, ids.length() - 1);
		}
		//分离逗号将id存到String数组中
		String[] idArr = ids.split(",");
		if(idArr.length>0) {
			authorityService.delete(roleId);
		}
		for(String id:idArr) {
			Authority authority=new Authority();
			authority.setRoleId(roleId);
			authority.setMenuId(Long.valueOf(id));
			authorityService.add(authority);
		}
		ret.put("type", "success");
		ret.put("msg", "添加权限成功！");
		return ret;
	}
	
	/**
	 * 获取角色的权限
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="get_role_authority",method=RequestMethod.POST)
	@ResponseBody
	public List<Authority>getAllAuthority(
			@RequestParam(name="roleId",required=true)Long roleId){
		return authorityService.findAuthorityByRoleId(roleId);
	}
}

