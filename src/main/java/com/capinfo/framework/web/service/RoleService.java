package com.capinfo.framework.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capinfo.framework.web.mapper.RoleMapper;
import com.capinfo.framework.web.pojo.Role;
import com.capinfo.framework.web.vo.RoleQueryBean;
import com.capinfo.modules.orm.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="roleService")
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;

	public List<Role> findRoleList(RoleQueryBean roleQueryBean) throws Exception {
		return roleMapper.findRoleList(roleQueryBean);
	}

	public void findRolePage(Page<Role> page, RoleQueryBean roleQueryBean) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo()-1);
		map.put("pageSize", page.getPageSize());
		map.put("query", roleQueryBean);
		map.put("pageNum", (page.getPageNo()-1)*page.getPageSize());
		List<Role> roles = roleMapper.findRolePage(map);
		page.setResult(roles);
		page.setTotalCount(roleMapper.findRoleCount(roleQueryBean));
	}

	public Role findRoleById(Integer id) throws Exception {

		if(id!=null && id!=0){
			Role role = roleMapper.findRoleById(id);
			return role;
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Role saveRole(Role role) throws Exception {

		role.setCreateTime(new Date());
		roleMapper.saveRole(role);
		return role;
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateRole(Integer id, Role role) throws Exception {

		role.setId(id);
		roleMapper.updateRole(role);
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteRole(Integer id) throws Exception {

		roleMapper.deleteRole(id);
	}


	public void doInfo() throws Exception {


	}

	public RoleMapper getRoleMapper() {
		return roleMapper;
	}

	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}

}
