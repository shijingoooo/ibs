package com.capinfo.framework.web.mapper;

import com.capinfo.framework.web.pojo.AlarmDevp;
import com.capinfo.framework.web.pojo.MonitoringData;
import com.capinfo.framework.web.vo.MonitoringAlarmQueryBean;
import com.capinfo.framework.web.vo.MonitoringDeviceQueryBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AlarmDevpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlarmDevp record);

    int insertSelective(AlarmDevp record);

    AlarmDevp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlarmDevp record);

    //查询列表
    List<AlarmDevp> findMonitoringAlarmPage(Map<String,Object> map);
    //获取查询记录数量
    int findMonitoringAlarmCount(MonitoringAlarmQueryBean alarmQueryBean);
    //获取记录列表
    List<AlarmDevp> findMonitoringAlarmList(MonitoringAlarmQueryBean alarmQueryBean);
    //获取数据恒值与数据0值记录
    List<AlarmDevp> findConstValueAlarmList(MonitoringAlarmQueryBean alarmQueryBean);
    //获取每天的告警数量
    List<AlarmDevp> findDayCountByType(Integer type);
    //获取每种类型告警数量总数
    List<AlarmDevp> findAlarmCountByType(Integer type);

}