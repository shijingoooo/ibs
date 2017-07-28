package com.capinfo.framework.web.service;

import com.capinfo.framework.web.mapper.*;
import com.capinfo.framework.web.pojo.*;
import com.capinfo.framework.web.vo.ProjectUserReleQueryBean;
import com.capinfo.framework.web.vo.RoleMenuReleQueryBean;
import com.capinfo.framework.web.vo.UserQueryBean;
import com.capinfo.framework.web.vo.UserRoleReleQueryBean;
import com.capinfo.modules.orm.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(value = "userService")
public class UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleReleMapper userRoleReleMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleMenuReleMapper roleMenuReleMapper;
	@Autowired
	private UserGroupReleMapper userGroupReleMapper;




	public Object login(UserQueryBean userQueryBean) throws Exception {

		User user = userMapper.findUserByName(userQueryBean.getUserName());
		if (user == null) {
			return "该用户不存在";
		} else if (!user.getUserPassword().equals(
				userQueryBean.getUserPassword())) {
			return "密码输入有误";
		} else {
			return user;
		}
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<User> findUserList(UserQueryBean userQueryBean)
			throws Exception {
		return userMapper.findUserList(userQueryBean);
	}


	public void findUserPage(Page<User> page, UserQueryBean userQueryBean)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo() - 1);
		map.put("pageSize", page.getPageSize());
		map.put("query", userQueryBean);
		map.put("pageNum", (page.getPageNo() - 1) * page.getPageSize());
		List<User> users = userMapper.findUserPage(map);
		page.setResult(users);
		page.setTotalCount(userMapper.findUserCount(userQueryBean));
	}


	public User findUserById(Integer id) throws Exception {

		if (id != null && id != 0) {
			User user = userMapper.findUserById(id);
			return user;
		}
		return null;
	}


	public boolean permissionValidate(User user, Menu menu) throws Exception {

		List<UserRoleRele> userRoleReles = userRoleReleMapper.findUserRoleReleByUserId(user.getId());
		if (userRoleReles == null || userRoleReles.size() == 0) {
			return false;
		} else {
			RoleMenuReleQueryBean roleMenuReleQueryBean = new RoleMenuReleQueryBean();
			roleMenuReleQueryBean.setMenuId(menu.getId());
			Integer[] roleIds = new Integer[userRoleReles.size()];
			for (int i = 0, n = userRoleReles.size(); i < n; i++) {
				UserRoleRele userRoleRele = userRoleReles.get(i);
				roleIds[i] = userRoleRele.getRole().getId();
			}
			roleMenuReleQueryBean.setRoleIdForIn(roleIds);
			Integer count = roleMenuReleMapper
					.findRoleMenuReleCount(roleMenuReleQueryBean);

			return count > 0;
		}
	}


	public boolean permissionValidate(User user, String url) throws Exception {
		List<UserRoleRele> userRoleReles = userRoleReleMapper
				.findUserRoleReleByUserId(user.getId());
		if (userRoleReles == null || userRoleReles.size() == 0) {
			return false;
		} else {
			RoleMenuReleQueryBean roleMenuReleQueryBean = new RoleMenuReleQueryBean();
			roleMenuReleQueryBean.setMenuUrl(url);
			Integer[] roleIds = new Integer[userRoleReles.size()];
			for (int i = 0; i < userRoleReles.size(); i++) {
				UserRoleRele userRoleRele = userRoleReles.get(i);
				roleIds[i] = userRoleRele.getRole().getId();
			}
			roleMenuReleQueryBean.setRoleIdForIn(roleIds);
			Integer count = roleMenuReleMapper
					.findRoleMenuReleCount(roleMenuReleQueryBean);

			return count > 0;
		}
	}


	public User saveUser(User user, String projectIds) throws Exception {

		user.setCreateTime(new Date());
		userMapper.saveUser(user);

		Role role = roleMapper.findRoleById(user.getUserType());
		UserRoleRele userRole = new UserRoleRele();
		userRole.setUser(user);
		userRole.setRole(role);
		userRole.setCreater(user.getCreater());
		userRole.setCreateTime(new Date());
		userRoleReleMapper.saveUserRoleRele(userRole);

        /*if (StringUtils.isNotEmpty(projectIds)) {
            ProjectUserRele projectUserRele = null;
            for (String proId : projectIds.split(",")) {
                MonitoringProject project = monitoringProjectMapper.findMonitoringProjectById(Integer.valueOf(proId));
                projectUserRele = new ProjectUserRele();
                projectUserRele.setUser(user);
                projectUserRele.setMonitoringProject(project);
                projectUserRele.setCreater(user.getCreater());
                projectUserRele.setCreateTime(new Date());
                projectUserReleMapper.saveProjectUserRele(projectUserRele);
            }
        }*/

//		RUser ruser = new RUser();
//		ruser.setId(String.valueOf(user.getId()));
//		ruser.setUserName(user.getUserName());
//		userRedisDao.add(user);

		return user;
	}


	public void updateUser(Integer id, User user, String projectIds) throws Exception {

		user.setId(id);
		userMapper.updateUser(user);

		Role role = roleMapper.findRoleById(user.getUserType());
		UserRoleReleQueryBean userRoleReleQueryBean = new UserRoleReleQueryBean();
		userRoleReleQueryBean.setUserId(id);

		// 修改角色
		List<UserRoleRele> userRoles = userRoleReleMapper.findUserRoleReleList(userRoleReleQueryBean);
		UserRoleRele userRoleRele = userRoles.get(0);
		if (userRoleRele.getRole() != null && role.getId() != userRoleRele.getRole().getId()) {
			userRoleRele.setRole(role);
			userRoleReleMapper.updateUserRoleRele(userRoleRele);
		}

		if (StringUtils.isNotEmpty(projectIds)) {
			List<String> ids = new ArrayList<String>();
			ids.addAll(Arrays.asList(projectIds.split(",")));

			ProjectUserReleQueryBean projectUserReleQueryBean = new ProjectUserReleQueryBean();
			projectUserReleQueryBean.setUserId(user.getId());
            /*List<ProjectUserRele> projectUsers = projectUserReleMapper.findProjectUserReleList(projectUserReleQueryBean);
            for (ProjectUserRele projectUser : projectUsers) {
                int index = ids.indexOf(String.valueOf(projectUser.getMonitoringProject().getId()));
                if (index == -1) {
                    // 不存在就删除
                    projectUserReleMapper.deleteProjectUserRele(projectUser.getId());
                } else {
                    // 存在就证明没有变动
                    ids.remove(index);
                }
            }*/
			ProjectUserRele projectUserRele = null;
            /*for (String proId : ids) {
                MonitoringProject project = monitoringProjectMapper.findMonitoringProjectById(Integer.valueOf(proId));
                projectUserRele = new ProjectUserRele();
                projectUserRele.setUser(user);
                projectUserRele.setMonitoringProject(project);
                projectUserRele.setCreater(user.getUpdater());
                projectUserRele.setCreateTime(new Date());
                projectUserReleMapper.saveProjectUserRele(projectUserRele);
            }*/
		} else {
           /* ProjectUserReleQueryBean projectUserReleQueryBean = new ProjectUserReleQueryBean();
            projectUserReleQueryBean.setUserId(user.getId());
            List<ProjectUserRele> projectUsers = projectUserReleMapper.findProjectUserReleList(projectUserReleQueryBean);
            if (projectUsers.size() > 0) {
                for (ProjectUserRele projectUser : projectUsers) {
                    projectUserReleMapper.deleteProjectUserRele(projectUser.getId());
                }
            }*/
		}
	}


	public void updatePwd(User user) throws Exception {

		userMapper.updateUser(user);
	}

	//更新用户分组关系表
	public void updateUserGroupRele(Integer id,String groupIds) throws Exception{
		String[] groupArr = groupIds.split(",");
		//groupIds如果为空字符串则groupArr为[""],包含一个空串的数组
		if(!groupArr[0].equals("")) {
			//先删除
			userGroupReleMapper.deleteByUserId(id);
			//再添加
			for (String groupId : groupArr) {
				UserGroupRele userGroupRele = new UserGroupRele();
				userGroupRele.setGroupId(Integer.parseInt(groupId));
				userGroupRele.setUserId(id);
				userGroupRele.setCreateTime(new Date());
				userGroupReleMapper.insert(userGroupRele);
			}
		}
		else {
			userGroupReleMapper.deleteByUserId(id);
		}
	}

	//根据用户Id获取分组
	public List<UserGroupRele> findUserGroupReleList(Integer id) throws Exception{
		List result = new ArrayList();
		result = userGroupReleMapper.findUserGroupReleList(id);
		return result;
	}

	public void deleteUser(Integer id) throws Exception {

		List<String> ids = new ArrayList<String>();
		ProjectUserReleQueryBean projectUserReleQueryBean = new ProjectUserReleQueryBean();
		projectUserReleQueryBean.setUserId(id);
        /*List<ProjectUserRele> prjectUsers = projectUserReleMapper.findProjectUserReleList(projectUserReleQueryBean);
        for (ProjectUserRele projectUser : prjectUsers) {
            ids.add(String.valueOf(projectUser.getId()));
        }
        projectUserReleMapper.deleteProjectUserReleBatch(ids);
*/
		ids.clear();
		UserRoleReleQueryBean userRoleReleQueryBean = new UserRoleReleQueryBean();
		userRoleReleQueryBean.setUserId(id);
		List<UserRoleRele> userRoles = userRoleReleMapper.findUserRoleReleList(userRoleReleQueryBean);
		for (UserRoleRele userRole : userRoles) {
			ids.add(String.valueOf(userRole.getId()));
		}
		userRoleReleMapper.deleteUserRoleReleBatch(ids);

		userMapper.deleteUser(id);
	}


	public void doInfo() throws Exception {

	}

	public void deleteUserBatch(List<String> ids) throws Exception {
		userMapper.deleteUserBatch(ids);
		for (String id:ids) {
			userGroupReleMapper.deleteByUserId(Integer.parseInt(id));
		}
	}

	public boolean checkUserNameIsExist(String userName, Integer Id) throws Exception {
		User user = userMapper.findUserByName(userName);
		if (user != null) {
			if (Id == null || (user.getId().intValue() != Id.intValue())) {
				return false;
			}
		}
		return true;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public UserRoleReleMapper getUserRoleReleMapper() {
		return userRoleReleMapper;
	}

	public void setUserRoleReleMapper(UserRoleReleMapper userRoleReleMapper) {
		this.userRoleReleMapper = userRoleReleMapper;
	}

	public RoleMenuReleMapper getRoleMenuReleMapper() {
		return roleMenuReleMapper;
	}

	public void setRoleMenuReleMapper(RoleMenuReleMapper roleMenuReleMapper) {
		this.roleMenuReleMapper = roleMenuReleMapper;
	}

	public RoleMapper getRoleMapper() {
		return roleMapper;
	}

	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}




}
