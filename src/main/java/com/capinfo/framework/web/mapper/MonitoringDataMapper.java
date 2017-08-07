package com.capinfo.framework.web.mapper;

import com.capinfo.framework.web.pojo.MonitoringData;

import java.util.List;

public interface MonitoringDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MonitoringData record);

    int insertSelective(MonitoringData record);

    MonitoringData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MonitoringData record);

    int updateByPrimaryKey(MonitoringData record);

    List<MonitoringData> findMonitoringDataList();
}