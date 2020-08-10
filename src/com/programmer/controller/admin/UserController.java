package com.programmer.controller.admin;

import java.io.File;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;

import com.programmer.entity.admin.User;
import com.programmer.entity.admin.Role;
import com.programmer.page.admin.Page;
import com.programmer.service.admin.RoleService;
import com.programmer.service.admin.UserService;

@Controller
@RequestMapping(value="/admin/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	/**
	 * 进入用户页面
	 * 
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		Map<String, Object>queryMap=new HashMap<String,Object>();
		model.addAttribute(roleService.findRoleList(queryMap));
		return "/user/list";
	}
	
	/**
	 * 获取用户数据
	 * @param page 分页信息
	 * @param name 搜索按钮提供的name，以进行模糊查询
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMenuList(Page page,
			@RequestParam(name = "name", required = false, defaultValue = "") String name) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("name", name);
		List<User> findList = userService.findList(queryMap);
		ret.put("rows", findList);
		ret.put("total", userService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(User user) {
		Map<String, String> ret = new HashMap<String, String>();
		if (user == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的用户信息！");
			return ret;
		}
		if (StringUtils.isEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户名！");
			return ret;
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "请填写密码！");
			return ret;
		}
		if (user.getRoleId() == null) {
			ret.put("type", "error");
			ret.put("msg", "请选择所属角色！");
			return ret;
		}
		if (isExist(user.getUsername(), 0l)) {
			ret.put("type", "error");
			ret.put("msg", "该用户名已经存在，请重新输入！");
			return ret;
		}
		if (userService.add(user) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "用户添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "角色添加成功！");
		return ret;
	}

	/**
	 * 编辑用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(User user) {
		Map<String, String> ret = new HashMap<String, String>();
		if (user == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的用户信息！");
			return ret;
		}
		if (StringUtils.isEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户名！");
			return ret;
		}
		
		if (user.getRoleId() == null) {
			ret.put("type", "error");
			ret.put("msg", "请选择所属角色！");
			return ret;
		}
		if (isExist(user.getUsername(), user.getId())) {
			ret.put("type", "error");
			ret.put("msg", "该用户名已经存在，请重新输入！");
			return ret;
		}
		if (userService.edit(user) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "用户添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "角色添加成功！");
		return ret;
	}

	/**
	 * 批量删除用户
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String ids) {
		Map<String, String> ret = new HashMap<String, String>();
		if (StringUtils.isEmpty(ids)) {
			ret.put("type", "error");
			ret.put("msg", "选择要删除的数据！");
			return ret;
		}
		if (ids.contains(",")) {
			ids = ids.substring(0, ids.length() - 1);
		}
		if (userService.delete(ids) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "用户删除失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "用户删除成功！");
		return ret;
	}

	/**
	 * 判断该用户名是否存在
	 * 
	 * @param username
	 * @param id
	 * @return
	 */
	private boolean isExist(String username, Long id) {
		User user = userService.findByUsername(username);
		if (user == null)
			return false;
		if (user.getId().longValue() == id.longValue())
			return false;
		return true;
	}
	
	/**
	 * 上传图片
	 * 
	 * getOriginalFilename 方法是得到原来的文件名在客户机的文件系统名称
	 * 
	 * @param photo  MultipartFile 这个类一般是用来接受前台传过来的文件
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/upload_photo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> uploadPhoto(MultipartFile photo,HttpServletRequest request){
		MultipartFile photo1=photo;
		Map<String, String> ret = new HashMap<String, String>();
		if(photo == null){
			ret.put("type", "error");
			ret.put("msg", "请选择图像");
			return ret;
		}
		if(photo.getSize() > 1024*1024*1024){
			ret.put("type", "error");
			ret.put("msg", "图片文件过大，请重新选择");
			return ret;
		}
		//截取后缀名
		String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1,photo.getOriginalFilename().length());
		if(!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())){
			ret.put("type", "error");
			ret.put("msg", "请选择jpg,jpeg,gif,png文件");
			return ret;
		}
		
		String savePath1 = request.getServletContext().getRealPath("/")+ "resources/upload/";
		
		String savePath = (request.getServletContext().getRealPath("/")).replace('\\', '/')+ "resources/upload/";
		int num=savePath.indexOf(".metadata");
		savePath=savePath.substring(0,num).replace('/', '\\')+"Uptown-Facility-Management-System\\WebContent\\resources\\upload\\";
		
		
		File savePathFile = new File(savePath);
		if(!savePathFile.exists()){
			//如果没有该目录，则创建目录
			savePathFile.mkdir();
		}
		String filename = new Date().getTime()+"."+suffix;
		try {
			//将文件传到项目路径中
			System.out.println(savePath+filename);
			//photo.transferTo(new File(savePath+filename));
			photo.transferTo(new File(savePath1+filename));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			ret.put("type", "error");
			ret.put("msg", "上传失败，请联系管理员");
			e.printStackTrace();
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "上传图片成功");
		ret.put("filepath",request.getServletContext().getContextPath() + "/resources/upload/" + filename );
		return ret;
	}
}
