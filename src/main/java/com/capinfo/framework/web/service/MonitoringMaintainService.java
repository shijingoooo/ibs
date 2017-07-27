package com.capinfo.framework.web.service;

import com.capinfo.framework.web.mapper.MonitoringCompanyMapper;
import com.capinfo.framework.web.mapper.MonitoringMaintainMapper;
import com.capinfo.framework.web.pojo.MonitoringCompany;
import com.capinfo.framework.web.pojo.MonitoringMaintain;
import com.capinfo.framework.web.vo.MonitoringCompanyQueryBean;
import com.capinfo.framework.web.vo.MonitoringMaintainQueryBean;
import com.capinfo.modules.orm.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value="monitoringMaintainService")
public class MonitoringMaintainService {

	@Autowired
	private MonitoringMaintainMapper maintainMapper;

	public void findMonitoringMaintainPage(Page<MonitoringMaintain> page, MonitoringMaintainQueryBean maintainQueryBean)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo()-1);
		map.put("pageSize", page.getPageSize());
		map.put("query", maintainQueryBean);
		map.put("pageNum", (page.getPageNo()-1)*page.getPageSize());
		List<MonitoringMaintain> datas = maintainMapper.findMonitoringMaintainPage(map);
		page.setResult(datas);
		page.setTotalCount(maintainMapper.findMonitoringMaintainCount(maintainQueryBean));
		int a;
	}

	public MonitoringMaintain findMonitoringMaintainById(Integer recordId) throws Exception {

		if(recordId!=null && recordId!=0){
			MonitoringMaintain data = maintainMapper.findMonitoringMaintainById(recordId);
			return data;
		}
		return null;
	}

	public void updateMonitoringMaintianRecord(MonitoringMaintainQueryBean maintainQueryBean) throws Exception{
		maintainQueryBean.setCreateTime(new Date());
		maintainMapper.updateMonitoringMaintianRecord(maintainQueryBean);
	}
/*

	public MonitoringCompany findMonitoringCompanyUnique(MonitoringCompanyQueryBean companyQueryBean) throws Exception {

		return companyMapper.findMonitoringCompanyUnique(companyQueryBean);
	}


	public Integer findMonitoringCompanyCount(MonitoringCompanyQueryBean companyQueryBean) throws Exception {

		return companyMapper.findMonitoringCompanyCount(companyQueryBean);
	}


	public MonitoringCompany saveMonitoringCompany(MonitoringCompany company) throws Exception {

//		PushResourceServiceService service = new PushResourceServiceService();
//		PushResourceService s = service.getPushResourceServicePort();
//		String code = s.registerVendor(company.getCompanyName());
//		if(StringUtils.isNotEmpty(code)){
//			company.setCompanyCode(code);
		company.setCreateTime(new Date());
		companyMapper.saveMonitoringCompany(company);
		return company;
//		}
//		return null;
	}



	public void deleteMonitoringCompany(Integer id) throws Exception {

		companyMapper.deleteMonitoringCompany(id);
	}


	public void deleteMonitoringCompanyBatch(List<String> ids)
			throws Exception {

		companyMapper.deleteMonitoringCompanyBatch(ids);
	}


	public List<MonitoringCompany> findCompanyListByIds(String[] ids) throws Exception {
		return companyMapper.findCompanyListByIds(ids);
	}

	public MonitoringCompanyMapper getCompanyMapper() {
		return companyMapper;
	}

	public void setCompanyMapper(MonitoringCompanyMapper companyMapper) {
		this.companyMapper = companyMapper;
	}
*/

}
