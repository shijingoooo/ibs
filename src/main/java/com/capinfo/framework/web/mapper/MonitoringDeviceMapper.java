package com.capinfo.framework.web.mapper;

//import com.capinfo.framework.web.pojo.DeviceRecentData;
import com.capinfo.framework.web.pojo.*;
import com.capinfo.framework.web.pojo.DeviceRecentData;
import com.capinfo.framework.web.pojo.GroupDevice;
import com.capinfo.framework.web.pojo.MonitoringDevice;
//import com.capinfo.framework.web.pojo.MonitoringDeviceXsl;
import com.capinfo.framework.web.vo.MonitoringDataQueryBean;
import com.capinfo.framework.web.vo.MonitoringDeviceQueryBean;
import com.capinfo.framework.web.vo.MonitoringLEDQueryBean;
import com.capinfo.framework.web.vo.MonitoringPowerQueryBean;

import java.util.List;
import java.util.Map;

public interface MonitoringDeviceMapper {

	public void saveMonitoringDevice(MonitoringDeviceQueryBean deviceQueryBean) throws Exception;

	public void deleteMonitoringDevice(Integer id) throws Exception;
	
	public void updateMonitoringDevice(MonitoringDeviceQueryBean deviceQueryBean) throws Exception;
	
	public void saveMonitoringDeviceBatch(List<MonitoringDevice> monitoringDevices) throws Exception;
	
	public void deleteMonitoringDeviceBatch(List<String> ids) throws Exception;
	
	public MonitoringDevice findMonitoringDeviceById(Integer id) throws Exception;
	//public MonitoringPower findPowerId(String ip) throws Exception;

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

	//GPRS开关
	public List<MonitoringPower> finddevicepowerPage(Map<String, Object> map) throws Exception;
	public Integer findMonitoringpowerCount(MonitoringPowerQueryBean powerQueryBean) throws Exception;
	public void devicepowerAdd(MonitoringPowerQueryBean powerQueryBean) throws Exception;
	public void updateMonitoringDevicePower(MonitoringPowerQueryBean powerQueryBean) throws Exception;
	public void updatePower(MonitoringPowerQueryBean powerQueryBean) throws Exception;
	//批量删除
	public void deleteDevicePower(List<String> ids) throws Exception;
	public MonitoringPower findDevicePowerIP(MonitoringPowerQueryBean powerQueryBean) throws Exception;
	//根据Ip获取某一GPRS信息
	public MonitoringPower findPowerIP(String ip) throws Exception;
	public MonitoringPower findPowerId(int id) throws Exception;
	//LED屏
	public void updateMonitoringDeviceLED(MonitoringLEDQueryBean monitoringLEDQueryBean) throws Exception;
	public void deviceLedAdd(MonitoringLEDQueryBean monitoringLEDQueryBean) throws Exception;
	public MonitoringLED findledidDev(Integer idDev) throws Exception;
	public List<MonitoringLED> finddeviceLEDPage(Map<String, Object> map) throws Exception;

	public Integer findMonitoringLEDCount(MonitoringLEDQueryBean ledQueryBean) throws Exception;
	public MonitoringLED findcheckledidDev(MonitoringLEDQueryBean monitoringLEDQueryBean) throws Exception;
	//删除LED屏
	public void deleteDeviceLed(List<String> ids) throws Exception;

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

    /**
     * @Author: Zhang Chuanjia
     * @Date: 2017/8/30 18:38
     * @Description: 导出实时数据
     */
	List<Map<String,Object>> findStatisticalData(Map<String,Object> params);
	/**
	 * @Author: Zhang Chuanjia
	 * @Date: 2017/8/30 18:57
	 * @Description: 导出小时数据
	 */
	List<Map<String,Object>> findMonitoringHourDate(Map<String,Object> params);

	/**
	 * @Author: Zhang Chuanjia
	 * @Date: 2017/8/30 18:58
	 * @Description: 导出填数据
	 */
	List<Map<String,Object>> findMonitoringDayDate(Map<String,Object> params);
}
