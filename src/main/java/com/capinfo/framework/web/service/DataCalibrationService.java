package com.capinfo.framework.web.service;

import com.capinfo.framework.web.mapper.DataCalibrationRuleMapper;
import com.capinfo.framework.web.mapper.MonitoringDeviceMapper;
import com.capinfo.framework.web.pojo.DataCalibrationRule;
import com.capinfo.framework.web.pojo.MonitoringDevice;
import com.capinfo.framework.web.pojo.MonitoringDeviceGroup;
import com.capinfo.framework.web.pojo.MonitoringDeviceGroupRele;
import com.capinfo.framework.web.vo.DataCalibrationQueryBean;
import com.capinfo.framework.web.vo.MonitoringDeviceGroupQueryBean;
import com.capinfo.modules.orm.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value="dataCalibrationService")
public class DataCalibrationService {

	@Autowired
	private DataCalibrationRuleMapper dataCalibrationRuleMapper;
	@Autowired
	private MonitoringDeviceMapper monitoringDeviceMapper;
	//根据id查找某一条规则
	public DataCalibrationQueryBean findDataCalibrationRuleById(Integer id) throws Exception {
		DataCalibrationQueryBean deviceGroupQueryBean = new DataCalibrationQueryBean();
		DataCalibrationRule rule = dataCalibrationRuleMapper.findDataCalibrationRuleById(id);
		deviceGroupQueryBean.setId(rule.getId());
		deviceGroupQueryBean.setDeviceName(rule.getDeviceName());
		deviceGroupQueryBean.setDeviceCode(rule.getDeviceCode());
		deviceGroupQueryBean.setIndicator(rule.getType());
		deviceGroupQueryBean.setMaxs(rule.getMax().toString());
		deviceGroupQueryBean.setMins(rule.getMin().toString());
		deviceGroupQueryBean.setCalibrationFactors(rule.getCalibrationFactor().toString());
		if(rule.getDeviceType() == null)
			deviceGroupQueryBean.setDeviceType("");
		else
			deviceGroupQueryBean.setDeviceType(rule.getDeviceType().toString());

		return deviceGroupQueryBean;
	}
	//返回一个页面的规则
	public void findDataCalibrationPage(Page<DataCalibrationRule> page, DataCalibrationQueryBean dataCalibrationQueryBean) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo()-1);
		map.put("pageSize", page.getPageSize());
		map.put("query", dataCalibrationQueryBean);
		map.put("pageNum", (page.getPageNo()-1)*page.getPageSize());
		List<DataCalibrationRule> datas = dataCalibrationRuleMapper.findDataCalibrationPage(map);
		page.setResult(datas);
		page.setTotalCount(dataCalibrationRuleMapper.findDataCalibrationRuleCount(dataCalibrationQueryBean));
		int a = 0;
	}
	//校验规则
	public boolean checkRule(DataCalibrationQueryBean dataCalibrationQueryBean){
		//分解将要添加的规则
		String[] maxs = (dataCalibrationQueryBean.getMaxs()).split(",");
		String[] mins = (dataCalibrationQueryBean.getMins()).split(",");
		int newCount = maxs.length;
		//获取该设备已有的规则
		int deviceId = dataCalibrationQueryBean.getDeviceId();
		String indicator = dataCalibrationQueryBean.getIndicator();
		int ruleId = (dataCalibrationQueryBean.getId()!=null)?dataCalibrationQueryBean.getId():0;
		List<DataCalibrationRule> ruleList = dataCalibrationRuleMapper.findDataCalibrationRuleList(deviceId,indicator);
		int oldCount = ruleList.size();
		if (oldCount > 0) {
			for (int i = 0; i < newCount; i++) {
				for (int j = 0; j < oldCount; j++) {
					//不与自己判断
					if (ruleId == ruleList.get(j).getId()){
						continue;
					}else{
						if (Float.parseFloat(maxs[i]) <= ruleList.get(j).getMin() || Float.parseFloat(mins[i]) >= ruleList.get(j).getMax())
							continue;
						else {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	//保存规则
	public void saveDataCalibrationRule(DataCalibrationQueryBean dataCalibrationQueryBean) throws Exception {
		DataCalibrationRule obj = new DataCalibrationRule();
		obj.setDeviceId(dataCalibrationQueryBean.getDeviceId());
		obj.setCreateTime(new Date());
		obj.setType(dataCalibrationQueryBean.getIndicator());
		//分别添加rule
		String[] maxs = (dataCalibrationQueryBean.getMaxs()).split(",");
		String[] mins = (dataCalibrationQueryBean.getMins()).split(",");
		int count = maxs.length;
		String[] calibrationFactors = dataCalibrationQueryBean.getCalibrationFactors().split(",");

		for(int i = 0; i<count; i++)
		{
			obj.setMax(Float.parseFloat(maxs[i]));
			obj.setMin(Float.parseFloat(mins[i]));
			obj.setCalibrationFactor(Float.parseFloat(calibrationFactors[i]));
			dataCalibrationRuleMapper.saveDataCalibrationRule(obj);
		}
	}

	//更新规则
	public void updateDataCalibrationRule(DataCalibrationQueryBean dataCalibrationQueryBean) throws Exception{
		DataCalibrationRule obj = new DataCalibrationRule();

		obj.setId(dataCalibrationQueryBean.getId());
		obj.setType(dataCalibrationQueryBean.getIndicator());
		obj.setMax(Float.parseFloat(dataCalibrationQueryBean.getMaxs()));
		obj.setMin(Float.parseFloat(dataCalibrationQueryBean.getMins()));
		obj.setCalibrationFactor(Float.parseFloat(dataCalibrationQueryBean.getCalibrationFactors()));

		dataCalibrationRuleMapper.updateDataCalibrationRule(obj);
	}
	//返回设备列表
	public List<MonitoringDevice> findMonitoringDeviceList(DataCalibrationQueryBean dataCalibrationQueryBean) throws Exception{
		List result = new ArrayList();
		result = dataCalibrationRuleMapper.findMonitoringDeviceList(dataCalibrationQueryBean);
		return result;
	}
	//批量删除规则
	public void deleteDataCalibrationRuleBatch(List<String> ids, Integer userId) throws Exception {
		for(int i = 0; i < ids.size(); i++) {
			dataCalibrationRuleMapper.deleteDataCalibrationRuleById(Integer.parseInt(ids.get(i)));
		}
	}
	//修改规则状态
	public String updateDataCalibrationRuleStatus(String id,String status) throws Exception{
		int count = dataCalibrationRuleMapper.updateDataCalibrationRuleStatus(Integer.parseInt(id),Integer.parseInt(status));
		if(count > 0){
			return "success";
		}
		else
			return "failure";
	}
	//获取设备信息
	public DataCalibrationQueryBean findMonitoringDeviceById(Integer id) throws Exception{
		MonitoringDevice monitoringDevice = monitoringDeviceMapper.findMonitoringDeviceById(id);
		DataCalibrationQueryBean dataCalibrationQueryBean = new DataCalibrationQueryBean();
		dataCalibrationQueryBean.setDeviceId(monitoringDevice.getId());
		dataCalibrationQueryBean.setDeviceCode(monitoringDevice.getDevCode());
		dataCalibrationQueryBean.setDeviceName(monitoringDevice.getDevName());
		dataCalibrationQueryBean.setDeviceType(monitoringDevice.getDevType().toString());
		return dataCalibrationQueryBean;
	}
}
