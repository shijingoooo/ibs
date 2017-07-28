package com.capinfo.framework.web.mapper;


import com.capinfo.framework.web.pojo.DataCalibrationRule;
import com.capinfo.framework.web.pojo.MonitoringDevice;
import com.capinfo.framework.web.vo.DataCalibrationQueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DataCalibrationRuleMapper {
    //规则列表
    List<DataCalibrationRule> findDataCalibrationPage(Map<String, Object> map);
    //规则总数
    int findDataCalibrationRuleCount(DataCalibrationQueryBean dataCalibrationQueryBean);
    //新建规则
    void saveDataCalibrationRule(DataCalibrationRule dataCalibrationRule);
    //获得设备列表信息
    List<MonitoringDevice> findMonitoringDeviceList(DataCalibrationQueryBean dataCalibrationQueryBean);
    //根据ID查询某一条规则
    DataCalibrationRule findDataCalibrationRuleById(Integer id);
    //更新规则
    void updateDataCalibrationRule(DataCalibrationRule dataCalibrationRule);
    //删除规则
    void deleteDataCalibrationRuleById(Integer id);
    //更新规则状态
    int updateDataCalibrationRuleStatus(@Param("id") Integer id, @Param("status") Integer status);
    //根据设备ID获取该设备下的规则
    List<DataCalibrationRule> findDataCalibrationRuleList(@Param("id") Integer id,@Param("type") String indicator);
}