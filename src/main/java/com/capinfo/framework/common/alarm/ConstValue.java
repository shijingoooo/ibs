package com.capinfo.framework.common.alarm;

import com.capinfo.framework.web.pojo.AlarmDevp;
import com.capinfo.framework.web.pojo.DeviceRecentData;
import com.capinfo.framework.web.pojo.MonitoringData;
import com.capinfo.framework.web.service.MonitoringAlarmService;
import com.capinfo.framework.web.service.MonitoringDataService;
import com.capinfo.framework.web.service.MonitoringDeviceService;
import com.capinfo.framework.web.vo.MonitoringAlarmQueryBean;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by shijing on 2017/8/7.
 */
public class ConstValue extends BaseMethod {
    @Resource
    private CheckData checkData;

    @Resource
    private MonitoringDeviceService deviceService;

    @Resource
    private MonitoringDataService dataService;

    @Resource
    private MonitoringAlarmService alarmService;

    public ConstValue(CheckData checkData){
        super(checkData);
        this.checkData = checkData;
    }
    @Override
    public void run() throws Exception{
        System.out.println("ConstValue!!!");
        //先判断0值再判断恒值
        //获取设备最新十条数据
        List<DeviceRecentData> dataList = dataService.findDeviceListWithData();
        //判断0值
        checkZeroValue(dataList);
        //判断恒值
        checkConstValue(dataList);
    }
    public void insertAlarmRecord(boolean flag,DeviceRecentData data,Integer type) throws Exception{
        if(flag&&data.getDataList().size()>0){
            MonitoringAlarmQueryBean alarmQueryBean = new MonitoringAlarmQueryBean();
            alarmQueryBean.setDevId(data.getId());
            alarmQueryBean.setAlarmType(type);
            List<AlarmDevp> alarmList = alarmService.findConstValueAlarmList(alarmQueryBean);
            if (alarmList.size() == 0)
                alarmService.saveMonitoringAlarmRecord(alarmQueryBean);
        }
    }
    public void checkZeroValue(List<DeviceRecentData> dataList) throws Exception{
        for (DeviceRecentData data:dataList) {
            if(data.getDevType() == 7){
                //扬尘噪声：PM10、PM2.5、TSP
                boolean flag = true;//false:不告警;true:告警
                for (MonitoringData item:data.getDataList()) {
                    if(item.getActualTenPm().doubleValue()==0
                        &&item.getActualTwoPm().doubleValue()==0
                        &&item.getActualTsp().doubleValue()==0){
                    }else {
                        flag = false;
                        break;
                    }
                }
                //告警
                insertAlarmRecord(flag,data,3);
            }else if(data.getDevType() == 5){
                //AQI：PM10、PM2.5、SO2、NO2、O3、CO
                boolean flag = true;//false:不告警;true:告警
                for (MonitoringData item:data.getDataList()) {
                    if(item.getActualTenPm().doubleValue()==0
                            &&item.getActualTwoPm().doubleValue()==0
                            &&item.getActual02().doubleValue()==0
                            &&item.getActualNo2().doubleValue()==0
                            &&item.getActual03().doubleValue()==0
                            &&item.getActual04().doubleValue()==0){
                    }else {
                        flag = false;
                        break;
                    }
                }
                //告警
                insertAlarmRecord(flag,data,3);
            }
            //System.out.println(data.getDevType());
        }
    }
    public void checkConstValue(List<DeviceRecentData> dataList) throws Exception{
        for (DeviceRecentData data:dataList) {
            if(data.getDevType() == 7){
                //扬尘噪声：PM10、PM2.5、TSP
                boolean flag = true;//false:不告警;true:告警
                BigDecimal pm10;
                BigDecimal pm25;
                BigDecimal tsp;
                if (data.getDataList().size() > 0) {
                    pm10 = (data.getDataList()).get(0).getActualTenPm();
                    pm25 = (data.getDataList()).get(0).getActualTwoPm();
                    tsp = (data.getDataList()).get(0).getActualTsp();
                    for (MonitoringData item : data.getDataList()) {
                        if (item.getActualTenPm().compareTo(pm10) == 0
                                && item.getActualTwoPm().compareTo(pm25) == 0
                                && item.getActualTsp().compareTo(tsp) == 0) {
                        } else {
                            flag = false;
                            break;
                        }
                    }
                    //告警
                    insertAlarmRecord(flag, data, 2);
                }
            }else if(data.getDevType() == 5){
                //AQI：PM10、PM2.5、SO2、NO2、O3、CO
                if (data.getDataList().size() > 0) {
                    BigDecimal pm10 = (data.getDataList()).get(0).getActualTenPm();
                    BigDecimal pm25 = (data.getDataList()).get(0).getActualTwoPm();
                    BigDecimal so2 = (data.getDataList()).get(0).getActual02();
                    BigDecimal no2 = (data.getDataList()).get(0).getActualNo2();
                    BigDecimal o3 = (data.getDataList()).get(0).getActual03();
                    BigDecimal co = (data.getDataList()).get(0).getActual04();

                    boolean flag = true;//false:不告警;true:告警
                    for (MonitoringData item : data.getDataList()) {
                        if (item.getActualTenPm().compareTo(pm10) == 0
                                && item.getActualTwoPm().compareTo(pm25) == 0
                                && item.getActual02().compareTo(so2) == 0
                                && item.getActualNo2().compareTo(no2) == 0
                                && item.getActual03().compareTo(o3) == 0
                                && item.getActual04().compareTo(co) == 0) {
                        } else {
                            flag = false;
                            break;
                        }
                    }
                    //告警
                    insertAlarmRecord(flag, data, 2);
                }
            }
            //System.out.println(data.getDevType());
        }
    }


}
