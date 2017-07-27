package com.capinfo.framework.web.mapper;

import com.capinfo.framework.web.pojo.MonitoringMaintain;
import com.capinfo.framework.web.vo.MonitoringMaintainQueryBean;

import java.util.List;
import java.util.Map;

public interface MonitoringMaintainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MonitoringMaintain record);

    int insertSelective(MonitoringMaintain record);

    MonitoringMaintain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MonitoringMaintain record);

    int updateByPrimaryKey(MonitoringMaintain record);

    List<MonitoringMaintain> findMonitoringMaintainPage(Map<String,Object> map);

    int findMonitoringMaintainCount(MonitoringMaintainQueryBean maintainQueryBean);

    MonitoringMaintain findMonitoringMaintainById(Integer recordId);

    void updateMonitoringMaintianRecord(MonitoringMaintainQueryBean maintainQueryBean);
}