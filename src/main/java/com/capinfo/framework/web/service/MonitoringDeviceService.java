package com.capinfo.framework.web.service;

import com.capinfo.framework.common.util.LogUtil;
import com.capinfo.framework.web.mapper.MonitoringCompanyMapper;
import com.capinfo.framework.web.mapper.MonitoringDeviceGroupMapper;
import com.capinfo.framework.web.mapper.MonitoringDeviceMapper;
import com.capinfo.framework.web.mapper.UserMapper;
import com.capinfo.framework.web.pojo.*;
import com.capinfo.framework.web.vo.*;
import com.capinfo.modules.orm.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value="monitoringDeviceService")
public class MonitoringDeviceService {

	@Autowired
	private MonitoringDeviceMapper deviceMapper;
	@Autowired
	private MonitoringDeviceGroupMapper deviceGroupMapper;
	@Autowired
	private MonitoringCompanyMapper monitoringCompanyMapper;
	@Autowired
	private UserMapper userMapper;
	//获取设备列表
	public List<MonitoringDevice> findMonitoringDeviceList() throws Exception {
		return deviceMapper.findMonitoringDeviceList();
	}
	public List<MonitoringDevice> findMonitoringDeviceShList(MonitoringDeviceQueryBean deviceQueryBean) throws Exception {
		return deviceMapper.findMonitoringDeviceShList(deviceQueryBean);
	}

	public void findMonitoringDevicePage(Page<MonitoringDevice> page, MonitoringDeviceQueryBean deviceQueryBean)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo()-1);
		map.put("pageSize", page.getPageSize());
		map.put("query", deviceQueryBean);
		map.put("pageNum", (page.getPageNo()-1)*page.getPageSize());
		List<MonitoringDevice> datas = deviceMapper.findMonitoringDevicePage(map);
		for (int i = 0; i < datas.size(); i++) {
			String platformNames = "";
			if (datas.get(i).getPlatformId() != null){
				String[] ids = datas.get(i).getPlatformId().split(",");
				List<MonitoringCompany> monitoringCompanyList= monitoringCompanyMapper.findCompanyListByIds(ids);
				for (int j = 0; j < monitoringCompanyList.size(); j++) {
					platformNames = platformNames+","+monitoringCompanyList.get(j).getCompanyName();
				}
				int index = platformNames.indexOf(',');
				platformNames = platformNames.substring(index+1);
			}
			datas.get(i).setPlatformName(platformNames);
		}
		page.setResult(datas);
		page.setTotalCount(deviceMapper.findMonitoringDeviceCount(deviceQueryBean));
	}

	public MonitoringDevice findMonitoringDeviceById(Integer id) throws Exception {

		if(id!=null && id!=0){
			MonitoringDevice data = deviceMapper.findMonitoringDeviceById(id);
			return data;
		}
		return null;
	}

	public MonitoringDevice findMonitoringDeviceUnique(MonitoringDeviceQueryBean deviceQueryBean) throws Exception {

		return deviceMapper.findMonitoringDeviceUnique(deviceQueryBean);
	}

	public Integer findMonitoringDeviceCount(MonitoringDeviceQueryBean deviceQueryBean) throws Exception {

		return deviceMapper.findMonitoringDeviceCount(deviceQueryBean);
	}

	public MonitoringDeviceGroup findMonitoringDeviceGroupById(Integer id) throws Exception {

		if(id!=null && id!=0){
			MonitoringDeviceGroup data = deviceGroupMapper.findMonitoringDeviceGroupById(id);
			return data;
		}
		return null;
	}

	public MonitoringDeviceQueryBean saveMonitoringDevice(MonitoringDeviceQueryBean deviceQueryBean) throws Exception {

		deviceQueryBean.setCreateTime(new Date());
		deviceMapper.saveMonitoringDevice(deviceQueryBean);

		return deviceQueryBean;
	}

	public void updateMonitoringDevice(Integer id, MonitoringDeviceQueryBean deviceQueryBean) throws Exception {

		deviceQueryBean.setId(id);
		deviceQueryBean.setUpdateTime(new Date());
		deviceMapper.updateMonitoringDevice(deviceQueryBean);

	}

	public void deleteMonitoringDevice(Integer id, Integer userId) throws Exception {

		MonitoringDevice device = deviceMapper.findMonitoringDeviceById(Integer.valueOf(id));
		deviceMapper.deleteMonitoringDevice(id);

		/*try{
			Logs logs = new Logs();
			logs.setCreateTime(new Date());
			logs.setObject("传感器");
			logs.setObjectId(id);
			logs.setObjectCode(device.getDevCode());
			logs.setOperate("删除");
			User user = userMapper.findUserById(userId);
			logs.setUser(user);
			logsMapper.saveLogs(logs);
		} catch(Exception e) {
			LogUtil.printLog("ERROR", "传感器删除日志记录失败!");
		}*/
	}

	public void deleteMonitoringDeviceBatch(List<String> ids, Integer userId) throws Exception {

		deviceMapper.deleteMonitoringDeviceBatch(ids);

		//User user = userMapper.findUserById(userId);
		/*for(String id : ids){
			DeviceCommandQueryBean deviceCommandQueryBean = new DeviceCommandQueryBean();
			deviceCommandQueryBean.setDeviceId(Integer.valueOf(id));
			List<DeviceCommand> commands = commandMapper.findDeviceCommandList(deviceCommandQueryBean);
			List<String> cIds = new ArrayList<String>();
			for(DeviceCommand command : commands){
				cIds.add(String.valueOf(command.getId()));
			}
			if(cIds.size()>0){
				commandMapper.deleteDeviceCommandBatch(cIds);
			}

			MonitoringDevice device = deviceMapper.findMonitoringDeviceById(Integer.valueOf(id));
			try{
				Logs logs = new Logs();
				logs.setCreateTime(new Date());
				logs.setObject("传感器");
				logs.setObjectId(Integer.valueOf(id));
				logs.setObjectCode(device.getDevCode());
				logs.setOperate("删除");
				logs.setUser(user);
				logsMapper.saveLogs(logs);
			} catch(Exception e) {
				LogUtil.printLog("ERROR", "传感器删除日志记录失败!");
			}
		}*/
	}

	public MonitoringDeviceMapper getDeviceMapper() {
		return deviceMapper;
	}

	public void setDeviceMapper(MonitoringDeviceMapper deviceMapper) {
		this.deviceMapper = deviceMapper;
	}

	public MonitoringDeviceGroupMapper getDeviceGroupMapper() {
		return deviceGroupMapper;
	}

	public void setDeviceGroupMapper(MonitoringDeviceGroupMapper deviceGroupMapper) {
		this.deviceGroupMapper = deviceGroupMapper;
	}

	/**
	 * 下載传感器数据
	 * @param ids
	 * @throws Exception
	 */
	public List<Map<String, Object>> downloadMonitoringDeviceList(List<String> ids) throws Exception{
		return deviceMapper.downloadMonitoringDeviceList(ids);
	}

	public List<DeviceRecentData> findMonitoringDeviceRecentDataList() throws Exception {
		return deviceMapper.findMonitoringDeviceRecentDataList();
	}

	/**
	 * 导入传感器数据
	 * @param ids
	 * @throws Exception
	 */
	/*public void insertMonitoringDeviceList(List<MonitoringDeviceXsl> ids) throws Exception{
		deviceMapper.insertMonitoringDeviceList(ids);
	}*/
}
