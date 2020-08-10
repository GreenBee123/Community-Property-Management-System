package com.programmer.util;

import java.util.ArrayList;
import java.util.List;

import com.programmer.entity.admin.Menu;

/**
 * 关于菜单操作的一些公用方法
 * 
 * @author 聂峻民
 *
 */
public class MenuUtil {

	/**
	 * 找到所有顶级菜单
	 * 
	 * @param menuList 一个用户的所有菜单
	 * @return 所有找到的顶级菜单
	 */
	public static List<Menu> getAllTopMenu(List<Menu> menuList) {
		List<Menu> ret = new ArrayList<Menu>();
		for (Menu menu : menuList) {
			if (menu.getParentId() == 0) {
				ret.add(menu);
			}
		}
		return ret;
	}

	/**
	 * 获取所有的二级菜单
	 * 
	 * @param menuList 一个用户的所有菜单
	 * @return 返回所有的二级菜单
	 */
	public static List<Menu> getAllSecondMenu(List<Menu> menuList) {
		List<Menu> ret = new ArrayList<Menu>();
		List<Menu> allTopMenu = getAllTopMenu(menuList);
		for (Menu menu : menuList) {
			for (Menu topMenu : allTopMenu) {
				if (menu.getParentId() == topMenu.getId()) {
					ret.add(menu);
					break;
				}
			}
		}
		return ret;
	}

	/**
	 * 获取二级菜单下的菜单，三级菜单
	 * 
	 * @param menuList 一个用户的所有菜单
	 * @param secondMenuId 一个用户的二级菜单
	 * @return 三级菜单
	 */
	public static List<Menu> getAllThridMenu(List<Menu> menuList, Long secondMenuId) {
		List<Menu> ret = new ArrayList<Menu>();
		for (Menu menu : menuList) {
			if (menu.getParentId() == secondMenuId) {
				ret.add(menu);
			}
		}
		return ret;
	}
}
