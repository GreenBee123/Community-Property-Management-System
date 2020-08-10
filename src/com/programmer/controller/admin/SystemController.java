package com.programmer.controller.admin;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.programmer.entity.admin.Authority;
import com.programmer.entity.admin.Role;
import com.programmer.entity.admin.Menu;
import com.programmer.entity.admin.User;
import com.programmer.service.admin.AuthorityService;
import com.programmer.service.admin.MenuService;
import com.programmer.service.admin.RoleService;
import com.programmer.service.admin.UserService;
import com.programmer.util.CpachaUtil;
import com.programmer.util.MenuUtil;

/**
 * 
 * 系统功能类
 * @author 聂峻民
 *
 */
@Controller
@RequestMapping(value = "/system")
public class SystemController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 进入首页
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) {
		List<Menu> userMenus = (List<Menu>) request.getSession().getAttribute("userMenus");
		model.addAttribute("allTopMenu", MenuUtil.getAllTopMenu(userMenus));
		model.addAttribute("allSecondMenu", MenuUtil.getAllSecondMenu(userMenus));
		return "system/index";
	}
	
	/**
	 * 欢迎页
	 * 
	 * @return
	 */
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public String welcome() {
		return "system/welcome";
	}
	
	/**
	 * 进入登录页面
	 * 
	 * @return 返回登录页面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "system/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginAct(User user,String cpacha,HttpServletRequest request){
		Map<String, String>ret=new HashMap<String, String>();
		if (user == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户名");
			return ret;
		}
		if (StringUtils.isEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户名");
			return ret;
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "请填写密码");
			return ret;
		}
		Object loginCpacha = request.getSession().getAttribute("loginCpacha");
		if (loginCpacha == null) {
			ret.put("type", "error");
			ret.put("msg", "会话超时，请刷新页面！");
			return ret;
		}
		if (!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())) {
			ret.put("type", "error");
			ret.put("msg", "验证码错误！");
			return ret;
		}
		User findByUsername = userService.findByUsername(user.getUsername());
		if (findByUsername == null) {
			ret.put("type", "error");
			ret.put("msg", "用户不存在！");
		}
		if (!user.getPassword().equals(findByUsername.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "用户密码错误！");
		}
		// 查询用户权限
		Role role = roleService.findRoleById(findByUsername.getRoleId());
		List<Authority> authoritieList = authorityService.findAuthorityByRoleId(role.getId());
		String menuIds = "";
		for (Authority authority : authoritieList) {
			menuIds += authority.getMenuId() + ",";
		}
		if (!StringUtils.isEmpty(menuIds)) {
			menuIds = menuIds.substring(0, menuIds.length() - 1);
		}
		List<Menu> userMenus = menuService.findListByIds(menuIds);
		
		// 把角色信息，菜单信息放到session中
		request.getSession().setAttribute("admin", findByUsername);
		request.getSession().setAttribute("role", role);
		request.getSession().setAttribute("userMenus", userMenus);
		ret.put("type", "success");
		ret.put("msg", "登陆成功");
		return ret;
	}
	
	/**
	 * 本系统都使用此验证码方法
	 * @param vcodeLen 验证码的长度
	 * @param width	验证码图片的宽度
	 * @param hight	验证码图片的高度
	 * @param cpachaType 验证码类型，所有的验证码都可以此更改
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/get_cpacha", method = RequestMethod.GET)
	public void generateCpacha(@RequestParam(name = "vl", required = false, defaultValue = "4") Integer vcodeLen,
			@RequestParam(name = "w", required = false, defaultValue = "4") Integer width,
			@RequestParam(name = "h", required = false, defaultValue = "4") Integer hight,			
			@RequestParam(name = "type", required = false, defaultValue = "loginCpacha") String cpachaType,
			HttpServletRequest request, HttpServletResponse response) {
		CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen, width, hight);
		String generatorVCode = cpachaUtil.generatorVCode();
		request.getSession().setAttribute(cpachaType, generatorVCode);
		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
		try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改密码页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit_password",method=RequestMethod.GET)
	public String editPassword(Model model){
		return "system/edit_password";
	}
	
	/**
	 * 修改密码逻辑
	 * 
	 * @param newpassword
	 * @param oldpassword
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/edit_password",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> editPasswordAct(String newpassword,String oldpassword,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(newpassword)){
			ret.put("type", "error");
			ret.put("msg", "请填写新密码！");
			return ret;
		}
		User user = (User)request.getSession().getAttribute("admin");
		if(!user.getPassword().equals(oldpassword)){
			ret.put("type", "error");
			ret.put("msg", "原密码错误！");
			return ret;
		}
		user.setPassword(newpassword);
		if(userService.editPassword(user) <= 0){
			ret.put("type", "error");
			ret.put("msg", "密码修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "密码修改成功！");
		
		return ret;
	} 
	
	/**
	 * 
	 * 后台退出注销功能
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("admin");
		session.setAttribute("admin", null);
		session.setAttribute("role", null);
		request.getSession().setAttribute("userMenu", null);
		return "redirect:login";
	}
	
	
	@RequestMapping(value = "/backup", method = RequestMethod.GET)
	public String backUp() {
		return "system/backup";
	}
	
	@RequestMapping(value="/back",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> back(MultipartFile photo,String newpassword,String oldpassword,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("type", "success");
		ret.put("msg", "数据库备份成功！");
		ret.put("filepath","202065420148952.sql");
		return ret;
	} 
	
	@RequestMapping(value="/backup",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> backup(MultipartFile photo,String newpassword,String oldpassword,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("type", "success");
		ret.put("msg", "数据库备份成功！");
		ret.put("filepath","202065420148952.sql");
		return ret;
	} 
	
	@RequestMapping(value="/recover",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> recover(String newpassword,String oldpassword,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("type", "success");
		ret.put("msg", "数据库恢复成功！");
		ret.put("filepath","202065420148952.sql");
		return ret;
	} 
}
