package com.capinfo.framework.web.mapper;

import java.util.List;
import java.util.Map;

import com.capinfo.framework.web.pojo.Menu;
import com.capinfo.framework.web.vo.MenuQueryBean;

public interface MenuMapper {

	public void saveMenu(Menu menu) throws Exception;

	public void deleteMenu(Integer id) throws Exception;
	
	public void updateMenu(Menu menu) throws Exception;

	public void saveMenuBatch(List<Menu> menus) throws Exception;
	
	public void deleteMenuBatch(List<Integer> ids) throws Exception;
	
	public Menu findMenuById(Integer id) throws Exception;
	
	public Menu findMenuUnique(MenuQueryBean menuQueryBean) throws Exception;
	
	public List<Menu> findMenuList(MenuQueryBean menuQueryBean) throws Exception;
	
	public List<Menu> findMenuPage(Map<String, Object> map) throws Exception;
	
	public Integer findMenuCount(MenuQueryBean menuQueryBean) throws Exception;
	
}
