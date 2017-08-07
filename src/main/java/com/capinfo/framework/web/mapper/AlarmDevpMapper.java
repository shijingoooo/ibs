package com.capinfo.framework.web.mapper;

import com.capinfo.framework.web.pojo.AlarmDevp;
import com.capinfo.framework.web.vo.MonitoringAlarmQueryBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AlarmDevpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlarmDevp record);

    int insertSelective(AlarmDevp record);

    AlarmDevp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlarmDevp record);

    int updateByPrimaryKey(AlarmDevp record);
    //查询列表
    List<AlarmDevp> findMonitoringAlarmPage(Map<String,Object> map);

    int findMonitoringAlarmCount(MonitoringAlarmQueryBean alarmQueryBean);
}