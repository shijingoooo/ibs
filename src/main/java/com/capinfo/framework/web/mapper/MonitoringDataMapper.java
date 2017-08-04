package com.capinfo.framework.web.mapper;

import com.capinfo.framework.web.pojo.MonitoringData;
import com.capinfo.framework.web.pojo.MonitoringData;
/*import com.capinfo.framework.web.vo.MonitoringDataQueryBean;
import com.capinfo.framework.web.vo.MonitoringDataResult;
import com.capinfo.framework.web.vo.MonitoringProjectQueryBean;
import com.capinfo.framework.web.vo.StatisticsQueryBean;*/
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MonitoringDataMapper {

/*
	public void saveMonitoringData(MonitoringData data) throws Exception;

	public void deleteMonitoringData(Integer id) throws Exception;

	public void updateMonitoringDataSign(MonitoringData data) throws Exception;

	public void updateMonitoringDataShSign(MonitoringDataQueryBean dataQueryBean) throws Exception;

	public void saveMonitoringDataBatch(List<MonitoringData> monitoringDatas) throws Exception;

	public void deleteMonitoringDataBatch(List<Integer> ids) throws Exception;


	public MonitoringData findMonitoringDataById(Integer id) throws Exception;
*/
	public List<MonitoringData> findMonitoringDataByDeviceId(Integer deviceId) throws Exception;
/*
	public List<MonitoringData> findMonitoringDataList(MonitoringDataQueryBean dataQueryBean) throws Exception;

	public List<MonitoringData> findMonitoringDataPage(Map<String, Object> map) throws Exception;

	public Integer findMonitoringDataCount(MonitoringDataQueryBean dataQueryBean) throws Exception;

	public MonitoringData findMonitoringDataByDeviceIdForLastest(Integer deviceId) throws Exception;

	public List<MonitoringDataResult> findMonitoringDeviceGroupDataByToday(Integer deviceGroupId) throws Exception;

	public List<MonitoringDataResult> findMonitoringProjectDataOrderForLastest(StatisticsQueryBean statisticsQueryBean) throws Exception;

	public MonitoringDataResult findMonitoringProjectDataForLastest(StatisticsQueryBean statisticsQueryBean) throws Exception;

	public List<MonitoringDataResult> findMonitoringProjectDataByProCodeAndHour(StatisticsQueryBean statisticsQueryBean) throws Exception;

	public List<MonitoringDataResult> findMonitoringProjectDataByProCodeAndDay(StatisticsQueryBean statisticsQueryBean) throws Exception;

	public List<MonitoringDataResult> findMonitoringProjectDataByProCodeAndMonth(StatisticsQueryBean statisticsQueryBean) throws Exception;

	public Integer findMonitoringDataCountByProject(StatisticsQueryBean statisticsQueryBean) throws Exception;

	public Integer findOverproofLogCountByProject(StatisticsQueryBean statisticsQueryBean) throws Exception;

	public MonitoringData findMonitoringDataAvgByProjectId(Integer projectId) throws Exception;

	public MonitoringData findLastMonitoringDataByProjectId(Integer projectId) throws Exception;

	public MonitoringData findMonitoringDataAvgFiveMinByDeviceId(Integer deviceId) throws Exception;

	public List<MonitoringDataResult> findMonitoringProjectDataOrder(StatisticsQueryBean statisticsQueryBean) throws Exception;

	public List<MonitoringDataResult> findMonitoringProjectSiteTypeDataOrder(StatisticsQueryBean statisticsQueryBean) throws Exception;

	public List<MonitoringDataResult> findMonitoringProjectStageDataOrder(StatisticsQueryBean statisticsQueryBean) throws Exception;

	*//*2017/1/22 新思 彭 开始*//*
	public MonitoringData findLastMonitoringDataByProjectIdFromDataCopy(Integer projectId) throws Exception;
	*//*2017/1/22 新思 彭 结束*//*
	*//*2017/1/11 新思 彭 开始*//*
	public List<MonitoringDataResult> findDeviceByProCode(StatisticsQueryBean statisticsQueryBeans) throws Exception;
	*//*2017/1/11 新思 彭 结束*//*
	public List<MonitoringData> selectMonitorDataLimit(@Param("start") int start, @Param("end") int end);

	*//*2017/1/18 新思 彭 开始*//*
	public List<MonitoringDataPojo> findAllMonitoringDate() throws Exception;
	*//*2017/1/18 新思 彭 结束*//*

	*//*2017/2/10 新思 彭 开始*//*
	public List<MonitoringDataResult> findMonitoringProjectDataOrderForMonth(StatisticsQueryBean statisticsQueryBean) throws Exception;
	*//*2017/2/10 新思 彭 结束*//*

    public Integer findMonitoringDataCopyCount(MonitoringDataQueryBean dataQueryBean) throws Exception;

    *//*2017/3/10 新思 彭 开始*//*
    public List<MonitoringDataResult> findOrderDataByHour(MonitoringProjectQueryBean projectQueryBean) throws Exception;

    public List<MonitoringDataResult> findOrderDataByDay(MonitoringProjectQueryBean projectQueryBean) throws Exception;

    public List<MonitoringDataResult> findOrderDataByMonth(MonitoringProjectQueryBean projectQueryBean) throws Exception;
    *//*2017/3/10 新思 彭 结束
    *
    * */
}
