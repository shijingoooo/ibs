package com.capinfo.framework.web.service;

import com.capinfo.framework.web.mapper.RoleMenuReleMapper;
import com.capinfo.framework.web.pojo.RoleMenuRele;
import com.capinfo.framework.web.vo.RoleMenuReleQueryBean;
import com.capinfo.modules.orm.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value="roleMenuReleService")
public class RoleMenuReleService {

	@Autowired
	private RoleMenuReleMapper roleMenuReleMapper;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<RoleMenuRele> findRoleMenuReleList(RoleMenuReleQueryBean roleMenuReleQueryBean) throws Exception {
		return roleMenuReleMapper.findRoleMenuReleList(roleMenuReleQueryBean);
	}

	public void findRoleMenuRelePage(Page<RoleMenuRele> page, RoleMenuReleQueryBean roleMenuReleQueryBean) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo()-1);
		map.put("pageSize", page.getPageSize());
		map.put("query", roleMenuReleQueryBean);
		map.put("pageNum", (page.getPageNo()-1)*page.getPageSize());
		List<RoleMenuRele> roleMenuReles = roleMenuReleMapper.findRoleMenuRelePage(map);
		page.setResult(roleMenuReles);
		page.setTotalCount(roleMenuReleMapper.findRoleMenuReleCount(roleMenuReleQueryBean));
	}

	public RoleMenuRele findRoleMenuReleById(Integer id) throws Exception {

		if(id!=null && id!=0){
			RoleMenuRele roleMenuRele = roleMenuReleMapper.findRoleMenuReleById(id);
			return roleMenuRele;
		}
		return null;
	}

	public RoleMenuRele saveRoleMenuRele(RoleMenuRele roleMenuRele) throws Exception {
		roleMenuRele.setCreateTime(new Date());
		roleMenuReleMapper.saveRoleMenuRele(roleMenuRele);
		return roleMenuRele;
	}

	public void updateRoleMenuRele(Integer id, RoleMenuRele roleMenuRele) throws Exception {
		roleMenuRele.setId(id);
		roleMenuReleMapper.updateRoleMenuRele(roleMenuRele);
	}

	public void deleteRoleMenuRele(Integer id) throws Exception {

		roleMenuReleMapper.deleteRoleMenuRele(id);
	}

	public void doInfo() throws Exception {


	}

	public void deleteRoleMenuReleByRoleId(Integer roleId) throws Exception	{
		roleMenuReleMapper.deleteRoleMenuReleByRoleId(roleId);
	}

	public List<RoleMenuRele> findRoleMenuReleListByRoleId(Integer roleId) throws Exception{
		return roleMenuReleMapper.findRoleMenuReleListByRoleId(roleId);
	}

	public RoleMenuReleMapper getRoleMenuReleMapper() {
		return roleMenuReleMapper;
	}

	public void setRoleMenuReleMapper(RoleMenuReleMapper roleMenuReleMapper) {
		this.roleMenuReleMapper = roleMenuReleMapper;
	}

}
