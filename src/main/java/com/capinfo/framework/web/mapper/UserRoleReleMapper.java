package com.capinfo.framework.web.mapper;

import java.util.List;
import java.util.Map;

import com.capinfo.framework.web.pojo.UserRoleRele;
import com.capinfo.framework.web.vo.UserRoleReleQueryBean;

public interface UserRoleReleMapper {

	public void saveUserRoleRele(UserRoleRele userRoleRele) throws Exception;

	public void deleteUserRoleRele(Integer id) throws Exception;
	
	public void updateUserRoleRele(UserRoleRele userRoleRele) throws Exception;

	public void saveUserRoleReleBatch(List<UserRoleRele> userRoleReles) throws Exception;
	
	public void deleteUserRoleReleBatch(List<String> ids) throws Exception;
	
	public UserRoleRele findUserRoleReleById(Integer id) throws Exception;
	
	public List<UserRoleRele> findUserRoleReleList(UserRoleReleQueryBean userRoleReleQueryBean) throws Exception;
	
	public List<UserRoleRele> findUserRoleRelePage(Map<String, Object> map) throws Exception;
	
	public Integer findUserRoleReleCount(UserRoleReleQueryBean userRoleReleQueryBean) throws Exception;
	
	public List<UserRoleRele> findUserRoleReleByRoleId(Integer roleId) throws Exception;
	
	public List<UserRoleRele> findUserRoleReleByUserId(Integer userId) throws Exception;
	
}
