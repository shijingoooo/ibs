package com.capinfo.framework.web.service;

import com.capinfo.framework.web.mapper.MonitoringDataMapper;
import com.capinfo.framework.web.pojo.MonitoringData;
//import com.capinfo.framework.web.vo.MonitoringDataQueryBean;
import com.capinfo.modules.orm.Page;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service(value = "monitoringDataService")
public class MonitoringDataService{

    @Autowired
    private MonitoringDataMapper dataMapper;

    public List<MonitoringData> findMonitoringDataList() throws Exception {
        return dataMapper.findMonitoringDataList();
    }
    /*
    @Override
    public List<MonitoringData> findMonitoringDataList(MonitoringDataQueryBean dataQueryBean) throws Exception {
        return dataMapper.findMonitoringDataList(dataQueryBean);
    }

    @Override
    public void findMonitoringDataPage(Page<MonitoringData> page, MonitoringDataQueryBean dataQueryBean)
            throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageNo", page.getPageNo() - 1);
        map.put("pageSize", page.getPageSize());
        map.put("query", dataQueryBean);
        map.put("pageNum", (page.getPageNo() - 1) * page.getPageSize());
        List<MonitoringData> datas = dataMapper.findMonitoringDataPage(map);
        page.setResult(datas);
        page.setTotalCount(dataMapper.findMonitoringDataCount(dataQueryBean));
    }

    *//*2017/1/11 新思 彭 开始*//*
    @Override
    public void findMonitoringDataPageFormMongo(MongoCollection collection, Page<MonitoringData> page, MonitoringDataQueryBean dataQueryBean)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageNo", page.getPageNo() - 1);
        map.put("pageSize", page.getPageSize());
        map.put("query", dataQueryBean);
        map.put("pageNum", (page.getPageNo() - 1) * page.getPageSize());
        Document doc = new Document();
        if (dataQueryBean.getDeviceId() != null && dataQueryBean.getDeviceId() != 0) {
            doc = doc.append("device_id", dataQueryBean.getDeviceId());
        }
        if (dataQueryBean.getShSign() != null) {
            doc = doc.append("sh_sign", dataQueryBean.getShSign());
        }

        if (dataQueryBean.getStartTime() != null && dataQueryBean.getStartTime() != "") {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date star = format.parse(dataQueryBean.getStartTime() + " 00:00:00");
            Date end = null;
            if (dataQueryBean.getEndTime() != null && dataQueryBean.getEndTime() != "") {
                end = format.parse(dataQueryBean.getEndTime() + " 23:59:59");
            } else {
                end = new Date();
            }
            doc = doc.append("col_time", new Document("$gte", star).append("$lte", end));
        }

        //db.getCollection('ibs_monitoring_data').find({"device_id":1701}).skip(0).sort({create_time:-1}).limit(20)
        FindIterable findIterable = collection.find(doc).skip((page.getPageNo() - 1) * page.getPageSize()).sort(new BasicDBObject("create_time", -1)).limit(page.getPageSize());
        MongoCursor<MonitoringData> mongoCursor = findIterable.iterator();
        List<MonitoringData> mdList = new ArrayList<MonitoringData>();
        while (mongoCursor.hasNext()) {
            mdList.add(mongoCursor.next());
        }
        page.setResult(mdList);
        long count = collection.count(doc);
        page.setTotalCount(count);
    }
    *//*2017/1/11 新思 彭 结束*//*
    @Override
    public Document getEarlistData(MongoCollection collection,Integer deviceId){
        Document doc = new Document();
        doc.append("device_id",deviceId);
        FindIterable findIterable = collection.find(doc).sort(new BasicDBObject("create_time", 1)).limit(1);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        Document earlistData = new Document();
        while (mongoCursor.hasNext()) {
            earlistData = mongoCursor.next();
        }
        return earlistData;
    }
    @Override
    public List<Document> exportMonitoringData( MongoCollection collection,Integer deviceId,String startTimeStr, String endTimeStr){
        Document doc = new Document();
        doc = doc.append("device_id", deviceId);

        if(startTimeStr!=null&&!"".equals(startTimeStr)){
            try {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date star = format.parse(startTimeStr);
                Date end = null;
                if (endTimeStr!= null && endTimeStr != "") {
                    end = format.parse(endTimeStr);
                } else {
                    end = new Date();
                }
                doc = doc.append("col_time", new Document("$gte", star).append("$lt", end));
            }catch (Exception e){
                e.getStackTrace();
            }

        }
//        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" + doc);
        long count = collection.count(doc);
        FindIterable findIterable = collection.find(doc).sort(new BasicDBObject("create_time", 1));
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<Document> mdList = new ArrayList<Document>();
        while (mongoCursor.hasNext()) {
            mdList.add(mongoCursor.next());
        }
            return mdList;
    }

    public MonitoringData findMonitoringDataById(Integer id) throws Exception {
        if (id != null && id != 0) {
            MonitoringData data = dataMapper.findMonitoringDataById(id);
            return data;
        }
        return null;
    }

    @Override
    public Integer findMonitoringDataCount(MonitoringDataQueryBean dataQueryBean) throws Exception {
        return dataMapper.findMonitoringDataCount(dataQueryBean);
    }


    public MonitoringData findMonitoringDataByLastest(Integer deviceId) throws Exception {
        return dataMapper.findMonitoringDataByDeviceIdForLastest(deviceId);
    }

    @Override
    public MonitoringData saveMonitoringData(MonitoringData data) throws Exception {

        data.setCreateTime(new Date());
        dataMapper.saveMonitoringData(data);
        return data;
    }

    @Override
    public void saveMonitoringDataBatch(List<MonitoringData> dataList) throws Exception {
        for (MonitoringData data : dataList) {
            data.setCreateTime(new Date());
        }
        dataMapper.saveMonitoringDataBatch(dataList);
    }

    @Override
    public MonitoringData findMonitoringDataAvgFiveMinByDeviceId(Integer deviceId) throws Exception {
        return dataMapper.findMonitoringDataAvgFiveMinByDeviceId(deviceId);
    }

    @Override
    public void updateMonitoringData(Integer id, MonitoringData data) throws Exception {

        data.setId(id);
        dataMapper.updateMonitoringDataSign(data);
    }

    @Override
    public void deleteMonitoringData(Integer id) throws Exception {

        dataMapper.deleteMonitoringData(id);
    }

    *//*2017/1/18 新思 彭 开始*//*
    @Override
    public List<MonitoringDataPojo> findAllMonitoringData() throws Exception {
        return dataMapper.findAllMonitoringDate();
    }
    *//*2017/1/18 新思 彭 结束*//*

    public MonitoringDataMapper getDataMapper() {
        return dataMapper;
    }

    public void setDataMapper(MonitoringDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    @Override
    public Integer findMonitoringDataCopyCount(MonitoringDataQueryBean dataQueryBean) throws Exception {
        return dataMapper.findMonitoringDataCopyCount(dataQueryBean);
    }
*/
}
