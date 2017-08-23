package com.capinfo.framework.web.mapper;

//import com.capinfo.framework.web.pojo.DeviceRecentData;
import com.capinfo.framework.web.pojo.*;
import com.capinfo.framework.web.pojo.DeviceRecentData;
import com.capinfo.framework.web.pojo.GroupDevice;
import com.capinfo.framework.web.pojo.MonitoringDevice;
//import com.capinfo.framework.web.pojo.MonitoringDeviceXsl;
import com.capinfo.framework.web.vo.MonitoringDataQueryBean;
import com.capinfo.framework.web.vo.MonitoringDeviceQueryBean;

import java.util.List;
import java.util.Map;

public interface MonitoringDeviceMapper {

	public void saveMonitoringDevice(MonitoringDeviceQueryBean deviceQueryBean) throws Exception;

	public void deleteMonitoringDevice(Integer id) throws Exception;
	
	public void updateMonitoringDevice(MonitoringDeviceQueryBean deviceQueryBean) throws Exception;
	
	public void saveMonitoringDeviceBatch(List<MonitoringDevice> monitoringDevices) throws Exception;
	
	public void deleteMonitoringDeviceBatch(List<String> ids) throws Exception;
	
	public MonitoringDevice findMonitoringDeviceById(Integer id) throws Exception;

	public MonitoringDevice findMonitoringDeviceUnique(MonitoringDeviceQueryBean deviceQueryBean) throws Exception;

	public List<MonitoringDevice> findMonitoringDeviceList() throws Exception;

    public List<DeviceRecentData> findMonitoringDeviceRecentDataList() throws Exception;

	public List<DeviceRecentData> findDevicePowerType() throws Exception;

	public List<MonitoringDevice> findMonitoringDeviceShList(MonitoringDeviceQueryBean deviceQueryBean) throws Exception;
	
	public List<MonitoringDevice> findMonitoringDevicePage(Map<String, Object> map) throws Exception;
	
	public Integer findMonitoringDeviceCount(MonitoringDeviceQueryBean deviceQueryBean) throws Exception;
	
	public List<MonitoringDevice> findMonitoringDeviceByProjectId(Integer projectId) throws Exception;

	public List<Map<String, Object>> downloadMonitoringDeviceList(List<String> ids) throws Exception;

	//public void insertMonitoringDeviceList(List<MonitoringDeviceXsl> ids) throws Exception;
	public MonitoringProject findMonitoringProjectById(Integer id) throws Exception;



	/*	//统计数据--实时数据
        public List<MonitoringData> findStatisticalData(Integer devId)throws Exception;*/
	//统计数据--小时数据
	public List<MonitoringHourData> findHourData(Integer devId)throws Exception;
	//统计数据--天数据
	public List<MonitoringDayData> findDayData(Integer devId)throws Exception;

	//统计数据--实时数据
	public Integer findMonitoringDataCount(MonitoringDataQueryBean dataQueryBean) throws Exception;
	//统计数据--实时数据分页
	public List<MonitoringData> findStatisticalDataPage(Map<String, Object> map) throws Exception;

	//统计数据--小时数据
	public Integer findMonitoringHourCount(MonitoringDataQueryBean hourQueryBean) throws Exception;
	//统计数据--小时数据分页
	public List<MonitoringHourData> findMonitoringHourDatePage(Map<String, Object> map) throws Exception;

	//统计数据--天数据
	public Integer findMonitoringDayCount(MonitoringDataQueryBean dayQueryBean) throws Exception;
	//统计数据--天数据分页
	public List<MonitoringDayData> findMonitoringDayDatePage(Map<String, Object> map) throws Exception;
    List<MonitoringDevice> findMonitoringDeviceListByGID(GroupDevice groupDevice);
}
