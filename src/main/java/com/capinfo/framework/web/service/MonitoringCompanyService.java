package com.capinfo.framework.web.service;

import com.capinfo.framework.web.mapper.MonitoringCompanyMapper;
import com.capinfo.framework.web.pojo.MonitoringCompany;
import com.capinfo.framework.web.vo.MonitoringCompanyQueryBean;
import com.capinfo.modules.orm.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value="monitoringCompanyService")
public class MonitoringCompanyService {

	@Autowired
	private MonitoringCompanyMapper companyMapper;

	public List<MonitoringCompany> findMonitoringCompanyList(MonitoringCompanyQueryBean companyQueryBean) throws Exception {
		return companyMapper.findMonitoringCompanyList(companyQueryBean);
	}

	public void findMonitoringCompanyPage(Page<MonitoringCompany> page, MonitoringCompanyQueryBean companyQueryBean)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo()-1);
		map.put("pageSize", page.getPageSize());
		map.put("query", companyQueryBean);
		map.put("pageNum", (page.getPageNo()-1)*page.getPageSize());
		List<MonitoringCompany> datas = companyMapper.findMonitoringCompanyPage(map);
		page.setResult(datas);
		page.setTotalCount(companyMapper.findMonitoringCompanyCount(companyQueryBean));
	}

	public MonitoringCompany findMonitoringCompanyById(Integer id) throws Exception {

		if(id!=null && id!=0){
			MonitoringCompany data = companyMapper.findMonitoringCompanyById(id);
			return data;
		}
		return null;
	}

	public MonitoringCompany findMonitoringCompanyUnique(MonitoringCompanyQueryBean companyQueryBean) throws Exception {

		return companyMapper.findMonitoringCompanyUnique(companyQueryBean);
	}

	public Integer findMonitoringCompanyCount(MonitoringCompanyQueryBean companyQueryBean) throws Exception {

		return companyMapper.findMonitoringCompanyCount(companyQueryBean);
	}

	public MonitoringCompany saveMonitoringCompany(MonitoringCompany company) throws Exception {
		company.setCreateTime(new Date());
		companyMapper.saveMonitoringCompany(company);
		return company;
	}

	public void updateMonitoringCompany(Integer id, MonitoringCompany company) throws Exception {
		company.setId(id);
		companyMapper.updateMonitoringCompany(company);
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

}
