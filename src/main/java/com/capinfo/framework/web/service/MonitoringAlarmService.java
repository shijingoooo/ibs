package com.capinfo.framework.web.service;

import com.capinfo.framework.web.mapper.AlarmDevpMapper;
import com.capinfo.framework.web.pojo.AlarmDevp;
import com.capinfo.framework.web.pojo.MonitoringProject;
import com.capinfo.framework.web.vo.MonitoringAlarmQueryBean;
import com.capinfo.modules.orm.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value="monitoringAlarmService")
public class MonitoringAlarmService {

	@Autowired
	private AlarmDevpMapper alarmDevpMapper;

	public void findMonitoringAlarmPage(Page<AlarmDevp> page, MonitoringAlarmQueryBean alarmQueryBean)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo()-1);
		map.put("pageSize", page.getPageSize());
		map.put("query", alarmQueryBean);
		map.put("pageNum", (page.getPageNo()-1)*page.getPageSize());
		List<AlarmDevp> datas = alarmDevpMapper.findMonitoringAlarmPage(map);
		//根据站点查询
		String proNameForLike = alarmQueryBean.getProNameForLike();
		if( proNameForLike != null && proNameForLike !=""){

			Iterator<AlarmDevp> it = datas.iterator();
			while(it.hasNext())
			{
				AlarmDevp item = it.next();
				if(item.getMonitoringProject()!=null) {
					if ((item.getMonitoringProject().getProName().indexOf(proNameForLike)) == -1) {
						it.remove();
					}
				}else{
					it.remove();
				}
			}
			page.setResult(datas);
		}else {
			page.setResult(datas);
		}
		//page.setTotalCount(alarmDevpMapper.findMonitoringMaintainCount(alarmQueryBean));

	}

	/*public MonitoringMaintain findMonitoringMaintainById(Integer recordId) throws Exception {

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
	public void deleteMaintainRecordBatch(List<String> ids)
			throws Exception {
		maintainMapper.deleteMaintainRecordBatch(ids);
	}*/
}
