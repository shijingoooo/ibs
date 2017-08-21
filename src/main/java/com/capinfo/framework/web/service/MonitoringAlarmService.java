package com.capinfo.framework.web.service;

import com.capinfo.framework.web.mapper.AlarmDevpMapper;
import com.capinfo.framework.web.pojo.AlarmDevp;
import com.capinfo.framework.web.vo.MonitoringAlarmQueryBean;
import com.capinfo.modules.orm.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value="monitoringAlarmService")
public class MonitoringAlarmService {

	@Autowired
	private AlarmDevpMapper alarmDevpMapper;

	public enum type {设备断电,设备下线,数据恒值,数据0值,温湿度异常}

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
		page.setTotalCount(alarmDevpMapper.findMonitoringAlarmCount(alarmQueryBean));

	}

	public List<AlarmDevp> findMonitoringAlarmList(MonitoringAlarmQueryBean alarmQueryBean) throws Exception{
		return alarmDevpMapper.findMonitoringAlarmList(alarmQueryBean);
	}

	public List<AlarmDevp> findConstValueAlarmList(MonitoringAlarmQueryBean alarmQueryBean) throws Exception{
		return alarmDevpMapper.findConstValueAlarmList(alarmQueryBean);
	}

	public void saveMonitoringAlarmRecord(MonitoringAlarmQueryBean alarmQueryBean) throws Exception{
		AlarmDevp alarmDevp = new AlarmDevp();
		//id自增
		alarmDevp.setDeviceId(alarmQueryBean.getDevId());
		alarmDevp.setAlarmCause(0);//默认
		alarmDevp.setAlarmStatus(0);//默认
		alarmDevp.setAlarmType(alarmQueryBean.getAlarmType());
		alarmDevp.setAlarmTime(new Date());
		alarmDevpMapper.insert(alarmDevp);
	}

	public void updateMonitoringAlarmRecord(MonitoringAlarmQueryBean alarmQueryBean) throws Exception{
		AlarmDevp alarmDevp = new AlarmDevp();
		alarmDevp.setId(alarmQueryBean.getId());
		alarmDevp.setAlarmType(alarmQueryBean.getAlarmType());
		alarmDevp.setAlarmTime(new Date());
		alarmDevpMapper.updateByPrimaryKeySelective(alarmDevp);
	}
	//根据告警类型获取每天的告警数量
	public Map<String,List<AlarmDevp>> findDayCountByType() throws Exception{
		Map<String,List<AlarmDevp>> result = new HashMap<String,List<AlarmDevp>>();
		for (type item: type.values()) {
			List<AlarmDevp> alarmDevpList = alarmDevpMapper.findDayCountByType(item.ordinal());
			result.put(item.toString(),alarmDevpList);
		}
		return result;
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
