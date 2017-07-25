package com.capinfo.framework.web.mapper;

import com.capinfo.framework.web.pojo.MonitoringCompany;
import com.capinfo.framework.web.vo.MonitoringCompanyQueryBean;

import java.util.List;
import java.util.Map;

public interface MonitoringCompanyMapper {

	public void saveMonitoringCompany(MonitoringCompany monitoringCompany) throws Exception;

	public void deleteMonitoringCompany(Integer id) throws Exception;
	
	public void updateMonitoringCompany(MonitoringCompany monitoringCompany) throws Exception;
	
	public void saveMonitoringCompanyBatch(List<MonitoringCompany> monitoringCompanys) throws Exception;
	
	public void deleteMonitoringCompanyBatch(List<String> ids) throws Exception;
	
	public MonitoringCompany findMonitoringCompanyById(Integer id) throws Exception;
	
	public MonitoringCompany findMonitoringCompanyUnique(MonitoringCompanyQueryBean monitoringCompanyQueryBean) throws Exception;
	
	public List<MonitoringCompany> findMonitoringCompanyList(MonitoringCompanyQueryBean monitoringCompanyQueryBean) throws Exception;
	
	public List<MonitoringCompany> findMonitoringCompanyPage(Map<String, Object> map) throws Exception;
	
	public Integer findMonitoringCompanyCount(MonitoringCompanyQueryBean monitoringCompanyQueryBean) throws Exception;

    public List<MonitoringCompany> findCompanyListByIds(String[] ids) throws Exception;

}
