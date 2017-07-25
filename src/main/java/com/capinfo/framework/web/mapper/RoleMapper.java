package com.capinfo.framework.web.mapper;

import java.util.List;
import java.util.Map;

import com.capinfo.framework.web.pojo.Role;
import com.capinfo.framework.web.vo.RoleQueryBean;

public interface RoleMapper {

	public void saveRole(Role role) throws Exception;

	public void deleteRole(Integer id) throws Exception;
	
	public void updateRole(Role role) throws Exception;
	
	public void saveRoleBatch(List<Role> roles) throws Exception;
	
	public void deleteRoleBatch(List<Integer> ids) throws Exception;
	
	public Role findRoleById(Integer id) throws Exception;
	
	public Role findRoleByName(String roleName) throws Exception; 
	
	public List<Role> findRoleList(RoleQueryBean roleQueryBean) throws Exception;
	
	public List<Role> findRolePage(Map<String, Object> map) throws Exception;
	
	public Integer findRoleCount(RoleQueryBean roleQueryBean) throws Exception;
	
}
