package com.capinfo.framework.web.mapper;

import com.capinfo.framework.web.pojo.User;
import com.capinfo.framework.web.vo.UserQueryBean;

import java.util.List;
import java.util.Map;

public interface UserMapper {

	public void saveUser(User user) throws Exception;

	public void deleteUser(Integer id) throws Exception;
	
	public void updateUser(User user) throws Exception;
	
	public void saveUserBatch(List<User> users) throws Exception;
	
	public void deleteUserBatch(List<String> ids) throws Exception;
	
	public User findUserById(Integer id) throws Exception;
	
	public User findUserByName(String userName) throws Exception; 
	
	public List<User> findUserList(UserQueryBean userQueryBean) throws Exception;
	
	public List<User> findUserPage(Map<String, Object> map) throws Exception;
	
	public Integer findUserCount(UserQueryBean userQueryBean) throws Exception;
	
}
