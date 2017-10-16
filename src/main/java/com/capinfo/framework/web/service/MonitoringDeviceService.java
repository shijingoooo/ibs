package com.capinfo.framework.web.service;

import com.capinfo.framework.web.mapper.MonitoringCompanyMapper;
import com.capinfo.framework.web.mapper.MonitoringDeviceGroupMapper;
import com.capinfo.framework.web.mapper.MonitoringDeviceMapper;
import com.capinfo.framework.web.mapper.UserMapper;
import com.capinfo.framework.web.pojo.*;
import com.capinfo.framework.web.vo.*;
import com.capinfo.modules.orm.Page;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryOperators;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	//GPRS远程控制开关
	public void finddevicepower(Page<MonitoringPower> page, MonitoringPowerQueryBean powerQueryBean)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo()-1);
		map.put("pageSize", page.getPageSize());
		map.put("query", powerQueryBean);
		map.put("pageNum", (page.getPageNo()-1)*page.getPageSize());
		List<MonitoringPower> datas = deviceMapper.finddevicepowerPage(map);
		page.setResult(datas);
		page.setTotalCount(deviceMapper.findMonitoringpowerCount(powerQueryBean));

	}

	//根据Ip获取一个开关信息
	public MonitoringLEDQueryBean findledidDev(Integer idDev) throws Exception{
		MonitoringLEDQueryBean ledQueryBean = new MonitoringLEDQueryBean();
		if(idDev!=null && idDev!=0){
			MonitoringLED led = deviceMapper.findledidDev(idDev);
			ledQueryBean.setId(led.getId());
			ledQueryBean.setIdDev(led.getIdDev());
			ledQueryBean.setIdDevice(led.getIdDevice());
			ledQueryBean.setIpSvr(led.getIpSvr());
			ledQueryBean.setPortSvr(led.getPortSvr());
			return ledQueryBean;
		}
		return null;

	}
	//根据Ip获取一个开关信息
	public MonitoringPowerQueryBean findPowerIP(String ip) throws Exception{
		MonitoringPowerQueryBean powerBean = new MonitoringPowerQueryBean();
		if(ip!=null && ip.length()!=0){
			MonitoringPower power = deviceMapper.findPowerIP(ip);
			powerBean.setIp(power.getIp());
			powerBean.setId(power.getId());
			powerBean.setAddr(power.getAddr());
			powerBean.setDevId(power.getDevId());
			powerBean.setPort(power.getPort());
			powerBean.setStatus(power.getStatus());
			powerBean.setDeviceId(power.getDeviceId());
			return powerBean;
		}
		return null;

	}

	//LED验证id_dev名称唯一
	public MonitoringLEDQueryBean findcheckledidDev(MonitoringLEDQueryBean monitoringLEDQueryBean) throws Exception{
		MonitoringLED monitoringLED = deviceMapper.findcheckledidDev(monitoringLEDQueryBean);
		MonitoringPowerQueryBean monitoringPowerQueryBean = new MonitoringPowerQueryBean();
		if (monitoringLED != null)
			monitoringPowerQueryBean.setId(monitoringLED.getId());
		else
			monitoringPowerQueryBean.setId(null);
		return monitoringLEDQueryBean;
	}
	//验证ip名称唯一
	public MonitoringPowerQueryBean findDevicePowerIP(MonitoringPowerQueryBean powerQueryBean) throws Exception{
		MonitoringPower monitoringPower = deviceMapper.findDevicePowerIP(powerQueryBean);
		MonitoringPowerQueryBean monitoringPowerQueryBean = new MonitoringPowerQueryBean();
		if (monitoringPower != null)
			monitoringPowerQueryBean.setId(monitoringPower.getId());
		else
			monitoringPowerQueryBean.setId(null);
		return monitoringPowerQueryBean;
	}
	//添加GPRS控制开关
	public void devicepowerAdd(MonitoringPowerQueryBean powerQueryBean) throws Exception{
		deviceMapper.devicepowerAdd(powerQueryBean);
	}
	//更新开关信息
	public void updateMonitoringDevicePower(Integer id,MonitoringPowerQueryBean powerQueryBean) throws Exception{
		powerQueryBean.setId(id);
		deviceMapper.updateMonitoringDevicePower(powerQueryBean);
	}
	public void updatePower(Integer id,MonitoringPowerQueryBean powerQueryBean) throws Exception{
		powerQueryBean.setId(id);
		deviceMapper.updatePower(powerQueryBean);
	}
	//删除开关信息
	public void deleteDevicePower(List<String> ids) throws Exception{
		deviceMapper.deleteDevicePower(ids);
	}
	//添加LED信息
	public void deviceLedAdd(MonitoringLEDQueryBean monitoringLEDQueryBean) throws Exception{
		deviceMapper.deviceLedAdd(monitoringLEDQueryBean);
	}
	//更新LED信息
	public void updateMonitoringDeviceLED(Integer id,MonitoringLEDQueryBean monitoringLEDQueryBean) throws Exception{
		monitoringLEDQueryBean.setId(id);
		deviceMapper.updateMonitoringDeviceLED(monitoringLEDQueryBean);
	}
	//删除LED信息
	public void deleteDeviceLed(List<String> ids) throws Exception{
		deviceMapper.deleteDeviceLed(ids);
	}
	//LED屏
	public void finddeviceLED(Page<MonitoringLED> page, MonitoringLEDQueryBean ledQueryBean)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo()-1);
		map.put("pageSize", page.getPageSize());
		map.put("query", ledQueryBean);
		map.put("pageNum", (page.getPageNo()-1)*page.getPageSize());
		List<MonitoringLED> datas = deviceMapper.finddeviceLEDPage(map);
		page.setResult(datas);
		page.setTotalCount(deviceMapper.findMonitoringLEDCount(ledQueryBean));
	}


	//统计实时数据分页
	public void findStatisticalDataPage(Page<MonitoringData> page, MonitoringDataQueryBean dataQueryBean)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo()-1);
		map.put("pageSize", page.getPageSize());
		map.put("query", dataQueryBean);
		map.put("pageNum", (page.getPageNo()-1)*page.getPageSize());
		List<MonitoringData> datas = deviceMapper.findStatisticalDataPage(map);
		page.setResult(datas);
		page.setTotalCount(deviceMapper.findMonitoringDataCount(dataQueryBean));
	}
	//统计小时数据分页
	public void findMonitoringHourDatePage(Page<MonitoringHourData> page, MonitoringDataQueryBean hourQueryBean)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo()-1);
		map.put("pageSize", page.getPageSize());
		map.put("query", hourQueryBean);
		map.put("pageNum", (page.getPageNo()-1)*page.getPageSize());
		List<MonitoringHourData> datas = deviceMapper.findMonitoringHourDatePage(map);
		page.setResult(datas);
		page.setTotalCount(deviceMapper.findMonitoringHourCount(hourQueryBean));
	}
	//统计天数据分页
	public void findMonitoringDayDatePage(Page<MonitoringDayData> page, MonitoringDataQueryBean dayQueryBean)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo()-1);
		map.put("pageSize", page.getPageSize());
		map.put("query", dayQueryBean);
		map.put("pageNum", (page.getPageNo()-1)*page.getPageSize());
		List<MonitoringDayData> datas = deviceMapper.findMonitoringDayDatePage(map);
		page.setResult(datas);
		page.setTotalCount(deviceMapper.findMonitoringDayCount(dayQueryBean));
	}

/*	//统计数据--实时数据
	public List<MonitoringData> findStatisticalData(Integer devId) throws Exception {
		//System.out.println("devId-->"+devId);
		System.out.println("size()----->"+deviceMapper.findStatisticalData(devId).size());
		return deviceMapper.findStatisticalData(devId);
	}*/

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

	public List<DeviceRecentData> findDevicePowerType() throws Exception{
		return deviceMapper.findDevicePowerType();
	}

	public List<MonitoringDevice> findMonitoringDeviceListByGID(GroupDevice groupDevice) {
		return deviceMapper.findMonitoringDeviceListByGID(groupDevice);
	}

	/**
	 * @Author: Zhang Chuanjia
	 * @Date: 2017/8/30 17:48
	 * @Description: 导出设备组的的数据——mongodb
	 */
	public List<Map<String,Object>> exportMonitoringDataMongo(MongoCollection collection, Integer[] devIds, String startDate, String endDate) throws ParseException {
		BasicDBObject queryObject = new BasicDBObject();
		queryObject.put("device_id", new BasicDBObject(QueryOperators.IN,devIds));
		if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
			//格式化时间
			Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
			Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
			//添加时间条件
			queryObject.put("create_time",new BasicDBObject(new BasicDBObject(QueryOperators.GTE,startTime).append(QueryOperators.LTE,endTime)));

		}
		FindIterable findIterable = collection.find(queryObject);
		MongoCursor<Document> mongoCursor = findIterable.iterator();

		//存储最终返回的结果
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		//FindIterable转换成Document
		List<Document> mdList = new ArrayList<Document>();
		while (mongoCursor.hasNext()) {
			mdList.add(mongoCursor.next());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (Document doc : mdList) {
			map = new HashMap<String, Object>();

			map.put("_id", doc.get("_id"));
			map.put("actual_tsp", doc.get("actual_tsp"));
			map.put("actual_two_pm", doc.get("actual_two_pm"));
			map.put("actual_ten_pm", doc.get("actual_ten_pm"));
			map.put("actual_noise", doc.get("actual_noise"));
			map.put("actual_temperature", doc.get("actual_temperature"));
			map.put("actual_humidity", doc.get("actual_humidity"));
			map.put("actual_pressure", doc.get("actual_pressure"));
			map.put("actual_wind_speed", doc.get("actual_wind_speed"));
			map.put("actual_wind_direction", doc.get("actual_wind_direction"));
			map.put("calibration_tsp", doc.get("calibration_tsp"));
			map.put("calibration_two_pm", doc.get("calibration_two_pm"));
			map.put("calibration_ten_pm", doc.get("calibration_ten_pm"));
			map.put("calibration_noise", doc.get("calibration_noise"));
			map.put("col_time", sdf.format(doc.get("col_time")));
			map.put("create_time", sdf.format(doc.get("create_time")));
			map.put("version", doc.get("version"));
			map.put("device_id", doc.get("device_id"));

			lists.add(map);
		}
		return lists;
	}

	/**
	 * @Author: Zhang Chuanjia
	 * @Date: 2017/8/30 17:45
	 * @Description: 统计数据的导出
	 */
    public List<Map<String,Object>> exportStatistics(String currentIndex, String startDate,String endDate){

    	//存储最终的返回结果
    	List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
    	Map<String,Object> params = new HashMap<String, Object>();

    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date startTime = null;
		Date endTime = null;
		try {
			startTime = sdf.parse(startDate);
			endTime = sdf.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		params.put("startTime",startTime);
		params.put("endTime",endTime);
		//params.put("ids",ids);

        if(Integer.parseInt(currentIndex) == 0){
            //实时数据
			return deviceMapper.findStatisticalData(params);
        }else if(Integer.parseInt(currentIndex) == 1){
            //小时数据
			return deviceMapper.findMonitoringHourDate(params);
        }else if(Integer.parseInt(currentIndex) == 2){
            //天数据
			return deviceMapper.findMonitoringDayDate(params);
        }
    	return null;
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
