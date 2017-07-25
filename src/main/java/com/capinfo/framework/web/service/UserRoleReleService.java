package com.capinfo.framework.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capinfo.framework.web.mapper.UserRoleReleMapper;
import com.capinfo.framework.web.pojo.UserRoleRele;
import com.capinfo.framework.web.vo.UserRoleReleQueryBean;
import com.capinfo.modules.orm.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="userRoleReleService")
public class UserRoleReleService {

	@Autowired
	private UserRoleReleMapper userRoleReleMapper;
//	@Autowired
//	private UserRoleReleRoleReleMongoDao userRoleReleMongoDao;


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<UserRoleRele> findUserRoleReleList(UserRoleReleQueryBean userRoleReleQueryBean) throws Exception {
		return userRoleReleMapper.findUserRoleReleList(userRoleReleQueryBean);
	}


	public void findUserRoleRelePage(Page<UserRoleRele> page, UserRoleReleQueryBean userRoleReleQueryBean)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo()-1);
		map.put("pageSize", page.getPageSize());
		map.put("query", userRoleReleQueryBean);
		map.put("pageNum", (page.getPageNo()-1)*page.getPageSize());
		List<UserRoleRele> userRoleReles = userRoleReleMapper.findUserRoleRelePage(map);
		page.setResult(userRoleReles);
		page.setTotalCount(userRoleReleMapper.findUserRoleReleCount(userRoleReleQueryBean));
	}


	public UserRoleRele findUserRoleReleById(Integer id) throws Exception {

		if(id!=null && id!=0){
			UserRoleRele userRoleRele = userRoleReleMapper.findUserRoleReleById(id);
			return userRoleRele;
		}
		return null;
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public UserRoleRele saveUserRoleRele(UserRoleRele userRoleRele) throws Exception {

		userRoleRele.setCreateTime(new Date());
		userRoleReleMapper.saveUserRoleRele(userRoleRele);
		return userRoleRele;
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateUserRoleRele(Integer id, UserRoleRele userRoleRele) throws Exception {

		userRoleRele.setId(id);
		userRoleReleMapper.updateUserRoleRele(userRoleRele);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteUserRoleRele(Integer id) throws Exception {

		userRoleReleMapper.deleteUserRoleRele(id);
	}


	public void doInfo() throws Exception {


	}

	public UserRoleReleMapper getUserRoleReleMapper() {
		return userRoleReleMapper;
	}

	public void setUserRoleReleMapper(UserRoleReleMapper userRoleReleMapper) {
		this.userRoleReleMapper = userRoleReleMapper;
	}

}
