package com.capinfo.framework.web.mapper;

import com.capinfo.framework.web.pojo.*;
import com.capinfo.framework.web.vo.MonitoringDeviceGroupQueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MonitoringDeviceGroupMapper {
    //获取设备组总数
    public Integer findMonitoringDeviceGroupCount(MonitoringDeviceGroupQueryBean deviceGroupQueryBean) throws Exception;
    //获取单个页面设备组列表
    public List<MonitoringDeviceGroup> findMonitoringDeviceGroupPage(Map<String, Object> map) throws Exception;
    //根据ID获取某一设备组信息
    public MonitoringDeviceGroup findMonitoringDeviceGroupById(Integer id) throws Exception;
    //更新设备组信息
    public void updateMonitoringDeviceGroup(MonitoringDeviceGroupQueryBean deviceGroupQueryBean) throws Exception;
    //保存设备组
    public void saveMonitoringDeviceGroup(MonitoringDeviceGroupQueryBean deviceGroupQueryBean) throws Exception;
    //批量删除设备组
    public void deleteMonitoringDeviceGroupBatch(List<String> ids) throws Exception;
    //通过ID获取某一组中的设备
    public List<MonitoringDevice> findMonitoringDeviceByGroupId(Integer id) throws Exception;
    //保存设备组与设备关联
    public void insertMonitoringDeviceGroupRele(@Param("groupId")Integer groupId, @Param("deviceId")Integer deviceId) throws Exception;
    //通过组ID获取等于该组ID的所有设备组信息
    public List<MonitoringDeviceGroupRele> findMonitoringDeviceGroupReleByGroupId(Integer groupId) throws Exception;
    //删除设备组与设备关联
    public void deleteMonitoringDeviceGroupReleByGroupId(Integer id) throws Exception;
    //获取设备列表
    public List<MonitoringDeviceGroupRele> findMonitoringDeviceList(MonitoringDeviceGroupQueryBean queryBean) throws Exception;
    //验证分组名称唯一
    public MonitoringDeviceGroup findMonitoringDeviceGroupUnique(MonitoringDeviceGroupQueryBean queryBean) throws Exception;

}
