package com.capinfo.framework.web.controller.role;

import com.capinfo.framework.common.controller.BaseController;
import com.capinfo.framework.web.pojo.Menu;
import com.capinfo.framework.web.pojo.Role;
import com.capinfo.framework.web.pojo.RoleMenuRele;
import com.capinfo.framework.web.service.MenuService;
import com.capinfo.framework.web.service.RoleMenuReleService;
import com.capinfo.framework.web.service.RoleService;
import com.capinfo.framework.web.vo.RoleQueryBean;
import com.capinfo.modules.orm.Page;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	
	@Resource
	private RoleService roleService;

	@Resource
	private MenuService menuService;

	@Resource
	private RoleMenuReleService roleMenuReleService;
	
	@RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
	public String list(Model model, RoleQueryBean roleQueryBean) throws Exception {
		
		List<Role> roles = roleService.findRoleList(roleQueryBean);
		model.addAttribute("roles", roles);
		
		return "role/list";
	}
	
	@RequestMapping(value="/listByPage",method={RequestMethod.POST,RequestMethod.GET})
	public String listByPage(Model model, RoleQueryBean roleQueryBean, Page<Role> page, Integer pageNum) throws Exception {
		
		if(page!=null && pageNum!=null){
			page.setPageNo(pageNum);
		}
		roleService.findRolePage(page, roleQueryBean);
		model.addAttribute("page", page);
		
		return "role/listByPage";
	}

	@RequestMapping(value="/listByPageForAuthority",method={RequestMethod.POST,RequestMethod.GET})
	public String listByPageForAuthority(Model model, RoleQueryBean roleQueryBean, Page<Role> page, Integer pageNum) throws Exception {

		if(page!=null && pageNum!=null){
			page.setPageNo(pageNum);
		}
		roleService.findRolePage(page, roleQueryBean);
		model.addAttribute("page", page);
		model.addAttribute("roleQueryBean",roleQueryBean);
		return "role/listByPageForAuthority";
	}
	
	@RequestMapping(value="/detail",method={RequestMethod.GET})
	public String detail(Model model, Integer id) throws Exception {
		
		Role role = roleService.findRoleById(id);
		model.addAttribute("role", role);
		
		return "role/detail";
	}
	
	@RequestMapping(value="/savePage",method={RequestMethod.GET})
	public String savePage(Model model, Integer roleId) throws Exception {
		
		Role role = roleService.findRoleById(roleId);
		model.addAttribute("role", role);
		
		return "role/save";
	}
	
	@RequestMapping(value="/save",method={RequestMethod.POST})
	public void save(Model model, HttpServletResponse response, HttpSession session, Role role, String navTabId, String callbackType, String rel) throws Exception {
		
		Integer userId = (Integer)session.getAttribute("userid");
		if(role.getId()!=null && role.getId()!=0){
			role.setUpdater(userId);
			roleService.updateRole(role.getId(), role);
		}else{
			role.setCreater(userId);
			roleService.saveRole(role);
		}
		
		JSONObject jo = new JSONObject();
		jo.put("navTabId", rel);
		jo.put("callbackType", callbackType);
		jo.put("rel", rel);
		jo.put("statusCode", "200");
		jo.put("message", "操作成功");
		super.rendText(response, jo.toString());
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


	@RequestMapping(value = "/initMenuList", method = {RequestMethod.GET})
	public String initMenuList(Model model, Integer roleId) throws Exception {
		List<Menu> menuList = menuService.findMenuList( null);
        //查询初始化信息
        List<RoleMenuRele> menuByRoleList = roleMenuReleService.findRoleMenuReleListByRoleId(roleId);
        List<Menu> menuListResult = new ArrayList<Menu>();
        for(Menu menu:menuList) {
            for (RoleMenuRele rm : menuByRoleList) {
                if(menu.getId()==rm.getMenu().getId()){
                    menu.setUpdater(1);
                    menuListResult.add(menu);
                }
            }
        }

        for (Menu menu : menuList) {
            if (!menuListResult.contains(menu))
                menuListResult.add(menu);
        }

        model.addAttribute("menuList", menuListResult);
        model.addAttribute("roleId", roleId);


		return "role/save";
	}


    @RequestMapping(value = "/saveRoleAndMenu", method = {RequestMethod.POST})
    public void saveRoleAndMenu(Model model, HttpServletResponse response, HttpSession session,String menuIdStr, Integer roleId, String callbackType, String rel) throws Exception {
        List<Menu> menuList = menuService.findMenuList( null);
        //删除所有有关的菜单关联；
        roleMenuReleService.deleteRoleMenuReleByRoleId(roleId);
        //新增菜单关联
        String[] arr = menuIdStr.split(",");
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=null && arr[i]!=""){
                RoleMenuRele roleMenuRele = new RoleMenuRele();
                Role r = roleService.findRoleById(roleId);
                Menu m = menuService.findMenuById(Integer.parseInt(arr[i]));
                roleMenuRele.setRole(r);
                roleMenuRele.setMenu(m);
                roleMenuRele.setCreateTime(new Date());
                roleMenuReleService.saveRoleMenuRele(roleMenuRele);
            }
        }
        try{
            JSONObject jo = new JSONObject();
            jo.put("callbackType", "closeCurrent");
            jo.put("rel", "ibs_user_page");
            jo.put("statusCode", "200");
            jo.put("message", "操作成功");
            super.rendText(response, jo.toString());
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
