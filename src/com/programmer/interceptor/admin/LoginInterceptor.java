package com.programmer.interceptor.admin;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.programmer.entity.admin.Menu;
import com.programmer.util.MenuUtil;

import net.sf.json.JSONObject;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String requestURL=request.getRequestURI();
		System.out.println("链接"+requestURL+"进入拦截器");
		Object admin=request.getSession().getAttribute("admin");
		if(admin==null) {
			//表示未登录或者登录已经失效
			String header=request.getHeader("X-Requested-With");
			//判断是否为Ajax请求
			if("XMLHttpRequest".equals(header)) {
				Map<String, String>ret=new HashMap<String, String>();
				ret.put("type", "error");
				ret.put("msg", "登录会话超时或者未登录，请重启登录");
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			//如果不是Ajax请求
			response.sendRedirect(request.getServletContext().getContextPath()+"/system/login");
			return false;
		}
		//获取二级菜单的id
		String mid=request.getParameter("_mid");
		if(!StringUtils.isEmpty(mid)) {
			List<Menu>allThridMenu = MenuUtil.getAllThridMenu((List<Menu>)request.getSession().getAttribute("userMenus"), Long.valueOf(mid));
			request.setAttribute("allThridMenuList", allThridMenu);
		}
		return true;
	}

}
