package com.capinfo.framework.web.service;

import com.capinfo.framework.web.mapper.MonitoringCompanyMapper;
import com.capinfo.framework.web.mapper.MonitoringMaintainMapper;
import com.capinfo.framework.web.pojo.MonitoringCompany;
import com.capinfo.framework.web.pojo.MonitoringDevice;
import com.capinfo.framework.web.pojo.MonitoringMaintain;
import com.capinfo.framework.web.vo.MonitoringCompanyQueryBean;
import com.capinfo.framework.web.vo.MonitoringMaintainQueryBean;
import com.capinfo.modules.orm.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
		//maintainQueryBean.setCreateTime(new Date());
		maintainMapper.updateMonitoringMaintianRecord(maintainQueryBean);
	}

	public void saveMonitoringMaintainRecord(MonitoringMaintainQueryBean maintainQueryBean) throws Exception{
		maintainQueryBean.setCreateTime(new Date());
		maintainMapper.saveMonitoringMaintainRecord(maintainQueryBean);
	}

	public List<MonitoringDevice> findMonitoringDeviceList(MonitoringMaintainQueryBean maintainQueryBean) throws Exception{
		List result = new ArrayList();
		result = maintainMapper.findMonitoringDeviceList(maintainQueryBean);
		return  result;
	}

	public void deleteMaintainRecordBatch(List<String> ids) throws Exception {
		maintainMapper.deleteMaintainRecordBatch(ids);
	}
}
