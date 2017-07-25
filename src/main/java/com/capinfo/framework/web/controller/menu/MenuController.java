package com.capinfo.framework.web.controller.menu;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.capinfo.framework.common.controller.BaseController;
import com.capinfo.framework.web.pojo.Menu;
import com.capinfo.framework.web.service.MenuService;
import com.capinfo.framework.web.vo.MenuQueryBean;
import com.capinfo.modules.orm.Page;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
	
	@Resource
	private MenuService menuService;
	
	@RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
	public String list(Model model, MenuQueryBean menuQueryBean) throws Exception {
		
		List<Menu> menus = menuService.findMenuList(menuQueryBean);
		model.addAttribute("menus", menus);
		
		return "menu/list";
	}
	
	@RequestMapping(value="/listByPage",method={RequestMethod.POST,RequestMethod.GET})
	public String listByPage(Model model, MenuQueryBean menuQueryBean, Page<Menu> page) throws Exception {
		
		menuService.findMenuPage(page, menuQueryBean);
		model.addAttribute("page", page);
		
		return "menu/listByPage";
	}
	
	@RequestMapping(value="/detail",method={RequestMethod.GET})
	public String detail(Model model, Integer id) throws Exception {
		
		Menu menu = menuService.findMenuById(id);
		model.addAttribute("menu", menu);
		
		return "menu/detail";
	}
	
	@RequestMapping(value="/savePage",method={RequestMethod.GET})
	public String savePage(Model model, Integer menuId) throws Exception {
		
		Menu menu = menuService.findMenuById(menuId);
		model.addAttribute("menu", menu);
		
		return "menu/save";
	}
	
	@RequestMapping(value="/save",method={RequestMethod.POST})
	public void save(Model model, HttpServletResponse response, Menu menu) throws Exception {
		
		menuService.saveMenu(menu);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write("success");
	}
	
	@RequestMapping(value="/upFile",method={RequestMethod.POST})
	public void uploadFile(Model model, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws Exception {
		
		String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();  
         
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName); 
	}
}
