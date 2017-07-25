package com.capinfo.framework.web.controller.user;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.capinfo.framework.common.controller.BaseController;
import com.capinfo.framework.web.pojo.User;
import com.capinfo.framework.web.service.UserService;
import com.capinfo.framework.web.vo.UserQueryBean;
import com.capinfo.modules.orm.Page;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Resource
	private UserService userService;

	
	@RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
	public String list(Model model, UserQueryBean userQueryBean) throws Exception {
		
		List<User> users = userService.findUserList(userQueryBean);
		model.addAttribute("users", users);
		
		return "user/list";
	}
	
	@RequestMapping(value="/listByPage",method={RequestMethod.POST,RequestMethod.GET})
	public String listByPage(Model model, UserQueryBean userQueryBean, Page<User> page, Integer pageNum) throws Exception {
		
		if(page!=null && pageNum!=null){
			page.setPageNo(pageNum);
		}
		userService.findUserPage(page, userQueryBean);
		model.addAttribute("page", page);
		model.addAttribute("userQueryBean", userQueryBean);
		
		return "user/listByPage";
	}
	
	@RequestMapping(value="/savePage",method={RequestMethod.GET})
	public String savePage(Model model, Integer userId) throws Exception {
		
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		/*if(userId!=null){
			ProjectUserReleQueryBean projectUserReleQueryBean = new ProjectUserReleQueryBean();
			projectUserReleQueryBean.setUserId(userId);
			List<ProjectUserRele> projectUsers = projectUserReleService.findProjectUserReleList(projectUserReleQueryBean);
			StringBuffer proNames = new StringBuffer();
			StringBuffer projectIds = new StringBuffer();
			for(ProjectUserRele projectUser : projectUsers){
				proNames.append(projectUser.getMonitoringProject().getProName()).append(",");
				projectIds.append(projectUser.getMonitoringProject().getId()).append(",");
			}
			if(!projectIds.toString().equals("")){
				model.addAttribute("proNames", proNames.substring(0, proNames.length()-1));
				model.addAttribute("projectIds", projectIds.substring(0, projectIds.length()-1));
			}
		}*/
		
		return "user/save";
	}
	
	@RequestMapping(value="/save",method={RequestMethod.POST})
	public void save(Model model, HttpServletResponse response, HttpSession session, User user, String projectIds, String navTabId, String callbackType, String rel) throws Exception {
		
		Integer userId = (Integer)session.getAttribute("userid");
        if (user.getUserName() != null && user.getUserName() != "") {
            boolean flag = userService.checkUserNameIsExist(user.getUserName(), user.getId());
            if (!flag) {
                JSONObject jo = new JSONObject();
                jo.put("navTabId", rel);
                jo.put("callbackType", callbackType);
                jo.put("rel", rel);
                jo.put("statusCode", "300");
                jo.put("message", "用户名已存在");
                super.rendText(response, jo.toString());
                return;
            }
        }
        if (user.getProvince() != null && user.getProvince().equals("请选择")) {
            user.setProvince("");
		}
		if(user.getCity()!=null && user.getCity().equals("请选择")){
			user.setCity("");
		}
		if(user.getDistrict()!=null && user.getDistrict().equals("请选择")){
			user.setDistrict("");
		}
		if(user.getId()!=null && user.getId()!=0){
			user.setUpdater(userId);
			userService.updateUser(user.getId(), user, projectIds);
		}else{
			user.setCreater(userId);
			userService.saveUser(user, projectIds);
		}
		
		JSONObject jo = new JSONObject();
		jo.put("navTabId", rel);
		jo.put("callbackType", callbackType);
		jo.put("rel", rel);
		jo.put("statusCode", "200");
		jo.put("message", "操作成功");
		super.rendText(response, jo.toString());
	}
	
	@RequestMapping(value="/updatePwdPage",method={RequestMethod.GET})
	public String updatePwdPage(Model model, Integer userId) throws Exception {
		
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);

		return "user/updatePwd";
	}
	
	@RequestMapping(value="/updatePwd",method={RequestMethod.POST})
	public void updatePwd(Model model, HttpServletResponse response, HttpSession session, String userPassword, String projectIds, String navTabId, String callbackType, String rel) throws Exception {
		
		Integer userId = (Integer)session.getAttribute("userid");
		User user = userService.findUserById(userId);
		user.setUserPassword(userPassword);
		userService.updatePwd(user);
		
		JSONObject jo = new JSONObject();
		jo.put("navTabId", rel);
		jo.put("callbackType", callbackType);
		jo.put("rel", rel);
		jo.put("statusCode", "200");
		jo.put("message", "操作成功");
		super.rendText(response, jo.toString());
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET, RequestMethod.POST})
	public void delete(Model model, HttpServletResponse response, String userIds, String navTabId, String callbackType, String rel) throws Exception {
		
		if(StringUtils.isEmpty(userIds)){
			JSONObject jo = new JSONObject();
			jo.put("navTabId", rel);
			jo.put("callbackType", callbackType);
			jo.put("rel", rel);
			jo.put("statusCode", "300");
			jo.put("message", "请选择一条信息");
			super.rendText(response, jo.toString());
			return;
		}
		List<String> ids = new ArrayList<String>();
		ids.addAll(Arrays.asList(userIds.split(",")));
		userService.deleteUserBatch(ids);
		
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


}
