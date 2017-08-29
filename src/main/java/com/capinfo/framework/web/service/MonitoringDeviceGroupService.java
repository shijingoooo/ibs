package com.capinfo.framework.web.service;

import com.capinfo.framework.web.mapper.MonitoringDeviceGroupMapper;
import com.capinfo.framework.web.pojo.*;
import com.capinfo.framework.web.vo.MonitoringDeviceGroupQueryBean;
import com.capinfo.modules.orm.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value="monitoringDeviceGroupService")
public class MonitoringDeviceGroupService {

	@Autowired
	private MonitoringDeviceGroupMapper deviceGroupMapper;
	@Autowired
	private MonitoringDeviceGroupMapper groupMapper;
	//返回同一组中的设备
	public List<MonitoringDeviceGroupQueryBean> findMonitoringDeviceByGroupId(Integer id) throws Exception {

		List<MonitoringDeviceGroupQueryBean> data = new ArrayList();
		List<MonitoringDevice> devices = deviceGroupMapper.findMonitoringDeviceByGroupId(id);
		for (MonitoringDevice device:devices) {
			MonitoringDeviceGroupQueryBean deviceGroupQueryBean = new MonitoringDeviceGroupQueryBean();
			deviceGroupQueryBean.setId(device.getId());
			deviceGroupQueryBean.setDevName(device.getDevName());
			data.add(deviceGroupQueryBean);
		}
		return data;
	}
	//返回一个页面的设备组信息
	public void findMonitoringDeviceGroupPage(Page<MonitoringDeviceGroup> page, MonitoringDeviceGroupQueryBean deviceGroupQueryBean) throws Exception
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", page.getPageNo()-1);
		map.put("pageSize", page.getPageSize());
		map.put("query", deviceGroupQueryBean);
		map.put("pageNum", (page.getPageNo()-1)*page.getPageSize());
		List<MonitoringDeviceGroup> datas = deviceGroupMapper.findMonitoringDeviceGroupPage(map);
		page.setResult(datas);
		page.setTotalCount(deviceGroupMapper.findMonitoringDeviceGroupCount(deviceGroupQueryBean));

	}
	//根据ID获取一个设备组信息
	public MonitoringDeviceGroupQueryBean findMonitoringDeviceGroupById(Integer id) throws Exception {
		MonitoringDeviceGroupQueryBean deviceGroupQueryBean = new MonitoringDeviceGroupQueryBean();
		if(id!=null && id!=0){
			MonitoringDeviceGroup data = deviceGroupMapper.findMonitoringDeviceGroupById(id);
			deviceGroupQueryBean.setId(id);
			deviceGroupQueryBean.setDevGroupType(data.getGroupDevType());
			deviceGroupQueryBean.setDevDescription(data.getGroupDevDescription());
			deviceGroupQueryBean.setDevGroupName(data.getGroupDevName());
			return deviceGroupQueryBean;
		}
		return null;
	}
	//获取设备列表
	public List<MonitoringDeviceGroupRele> findMonitoringDeviceList(MonitoringDeviceGroupQueryBean deviceGroupQueryBean) throws Exception{
		List result = new ArrayList();
		result = deviceGroupMapper.findMonitoringDeviceList(deviceGroupQueryBean);
		return result;
	}
	//更新设备与设备组关联
	public void updateMonitoringDeviceGroupRele(String ids, MonitoringDeviceGroupQueryBean deviceGroupQueryBean) throws Exception{
		String[] idArr = ids.split(",");
		if(deviceGroupMapper.findMonitoringDeviceGroupReleByGroupId(deviceGroupQueryBean.getId()).isEmpty())
		{	//添加传感器
			if( idArr.length > 0){
				for (String id:idArr) {
					try{
						deviceGroupMapper.insertMonitoringDeviceGroupRele(deviceGroupQueryBean.getId(),Integer.parseInt(id));
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		else
		{
			//更新传感器(先删除在添加)
			deviceGroupMapper.deleteMonitoringDeviceGroupReleByGroupId(deviceGroupQueryBean.getId());
			if( idArr.length > 0){
				for (String id:idArr) {
					try{
						deviceGroupMapper.insertMonitoringDeviceGroupRele(deviceGroupQueryBean.getId(),Integer.parseInt(id));
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			}
		}
	}
	//保存设备组
	public void saveMonitoringDeviceGroup(MonitoringDeviceGroupQueryBean deviceGroupQueryBean) throws Exception {
		deviceGroupQueryBean.setCreateTime(new Date());
		deviceGroupMapper.saveMonitoringDeviceGroup(deviceGroupQueryBean);
	}
	//更新设备组
	public void updateMonitoringDeviceGroup(Integer id, MonitoringDeviceGroupQueryBean deviceGroupQueryBean) throws Exception {
		deviceGroupQueryBean.setId(id);
		deviceGroupMapper.updateMonitoringDeviceGroup(deviceGroupQueryBean);
	}
	//批量删除设备组
	public void deleteMonitoringDeviceGroupBatch(List<String> ids, Integer userId) throws Exception {
		deviceGroupMapper.deleteMonitoringDeviceGroupBatch(ids);
		for(int i = 0; i < ids.size(); i++) {
			deviceGroupMapper.deleteMonitoringDeviceGroupReleByGroupId(Integer.parseInt(ids.get(i)));
		}
	}
	//验证分组名称唯一
	public MonitoringDeviceGroupQueryBean findMonitoringDeviceGroupUnique(MonitoringDeviceGroupQueryBean deviceGroupQueryBean) throws Exception{
		MonitoringDeviceGroup monitoringDeviceGroup = deviceGroupMapper.findMonitoringDeviceGroupUnique(deviceGroupQueryBean);
		MonitoringDeviceGroupQueryBean monitoringDeviceGroupQueryBean = new MonitoringDeviceGroupQueryBean();
		if(monitoringDeviceGroup != null)
			monitoringDeviceGroupQueryBean.setId(monitoringDeviceGroup.getId());
		else

			monitoringDeviceGroupQueryBean.setId(null);
		return monitoringDeviceGroupQueryBean;
	}
	//获取分组列表（用户页面添加管理用户组时使用）
	public List<MonitoringDeviceGroup> findMonitoringGroupList(MonitoringDeviceGroupQueryBean groupQueryBean) throws Exception{
		List result = new ArrayList();
		result = groupMapper.findMonitoringGroupList(groupQueryBean);
		return result;
	}
}
