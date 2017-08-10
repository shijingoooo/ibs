package com.capinfo.framework.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capinfo.framework.web.mapper.MenuMapper;
import com.capinfo.framework.web.pojo.Menu;
import com.capinfo.framework.web.vo.MenuQueryBean;
import com.capinfo.modules.orm.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="menuService")
public class MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Menu> findMenuList(MenuQueryBean menuQueryBean) throws Exception {
		return menuMapper.findMenuList(menuQueryBean);
	}


	public void findMenuPage(Page<Menu> page, MenuQueryBean menuQueryBean)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo()-1);
		map.put("pageSize", page.getPageSize());
		map.put("query", menuQueryBean);
		map.put("pageNum", (page.getPageNo()-1)*page.getPageSize());
		List<Menu> menus = menuMapper.findMenuPage(map);
		page.setResult(menus);
		page.setTotalCount(menuMapper.findMenuCount(menuQueryBean));
	}


	public Menu findMenuById(Integer id) throws Exception {

		if(id!=null && id!=0){
			Menu menu = menuMapper.findMenuById(id);
			return menu;
		}
		return null;
	}


	public Menu findMenuUnique(MenuQueryBean menuQueryBean) throws Exception {

		return menuMapper.findMenuUnique(menuQueryBean);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Menu saveMenu(Menu menu) throws Exception {

		menu.setCreateTime(new Date());
		menuMapper.saveMenu(menu);
		return menu;
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateMenu(Integer id, Menu menu) throws Exception {

		menu.setId(id);
		menuMapper.updateMenu(menu);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteMenu(Integer id) throws Exception {

		menuMapper.deleteMenu(id);
	}


	public void doInfo() throws Exception {


	}

	public MenuMapper getMenuMapper() {
		return menuMapper;
	}

	public void setMenuMapper(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}

}
