package com.capinfo.framework.web.mapper;

import java.util.List;
import java.util.Map;

import com.capinfo.framework.web.pojo.RoleMenuRele;
import com.capinfo.framework.web.vo.RoleMenuReleQueryBean;

public interface RoleMenuReleMapper {

	public void saveRoleMenuRele(RoleMenuRele roleMenuRele) throws Exception;

	public void deleteRoleMenuRele(Integer id) throws Exception;
	
	public void updateRoleMenuRele(RoleMenuRele roleMenuRele) throws Exception;

	public void saveRoleMenuReleBatch(List<RoleMenuRele> roleMenuReles) throws Exception;
	
	public void deleteRoleMenuReleBatch(List<Integer> ids) throws Exception;
	
	public RoleMenuRele findRoleMenuReleById(Integer id) throws Exception;
	
	public List<RoleMenuRele> findRoleMenuReleList(RoleMenuReleQueryBean roleMenuReleQueryBean) throws Exception;
	
	public List<RoleMenuRele> findRoleMenuRelePage(Map<String, Object> map) throws Exception;
	
	public Integer findRoleMenuReleCount(RoleMenuReleQueryBean roleMenuReleQueryBean) throws Exception;
	
	public List<RoleMenuRele> findRoleMenuReleByRoleId(Integer roleId) throws Exception;
	
	public List<RoleMenuRele> findRoleMenuReleByMenuId(Integer menuId) throws Exception;

	public void deleteRoleMenuReleByRoleId(Integer roleId);

	public List<RoleMenuRele> findRoleMenuReleListByRoleId(Integer roleId) throws Exception;
	
}
