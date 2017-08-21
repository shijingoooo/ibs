package com.capinfo.framework.web.mapper;

//import com.capinfo.framework.web.pojo.DeviceRecentData;
import com.capinfo.framework.web.pojo.DeviceRecentData;
import com.capinfo.framework.web.pojo.GroupDevice;
import com.capinfo.framework.web.pojo.MonitoringDevice;
//import com.capinfo.framework.web.pojo.MonitoringDeviceXsl;
import com.capinfo.framework.web.pojo.MonitoringProject;
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

    List<MonitoringDevice> findMonitoringDeviceListByGID(GroupDevice groupDevice);
}
