package com.capinfo.framework.web.service;

import com.capinfo.framework.web.mapper.GroupDeviceMapper;
import com.capinfo.framework.web.pojo.GroupDevice;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryOperators;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(value="groupDeviceService")
public class GroupDeviceService {
    @Autowired
    private GroupDeviceMapper groupDeviceMapper;

    /**根据设备组id获取设备组信息*/
    public GroupDevice findGroupDevice(Integer deviceGroupId) throws Exception {
        GroupDevice groupDevice = groupDeviceMapper.findGroupDevice(deviceGroupId);
        return groupDevice;
    }

    public List<Integer> findDevices(GroupDevice groupDevice) {
        return groupDeviceMapper.findDevices(groupDevice);

    }

    public List<Map<String,Object>> exportMonitoringData(MongoCollection collection,Integer[] deviceIds,String startDate,String endDate) {
        /*Document doc = new Document();
        doc = doc.append("device_id", new Document("$in",deviceIds));
        if(startDate != null || !"".equals(startDate) || endDate != null || !"".equals(endDate)){
            doc = doc.append("col_time", new Document("$gte", startDate).append("$lte", endDate));
        }*/
        BasicDBObject queryObject = new BasicDBObject();
        queryObject.append("device_id",new BasicDBObject(QueryOperators.IN,deviceIds));
        FindIterable findIterable = collection.find(queryObject);

        MongoCursor<Document> mongoCursor = findIterable.iterator();
        //FindIterable转换成Document
        List<Document> mdList = new ArrayList<Document>();
        while (mongoCursor.hasNext()) {
            mdList.add(mongoCursor.next());
        }
        //


        /* List<List<Object>> targetList = new ArrayList<List<Object>>();
        for (Document doc : mdList) {
            List<Object> dataList = new ArrayList<Object>();
            dataList.add(doc.get("calibration_tsp"));
            dataList.add(doc.get("calibration_noise"));
            dataList.add(doc.get("calibration_two_pm"));
            dataList.add(doc.get("calibration_ten_pm"));
            targetList.add(dataList);
        }*/
        return null;
    }
}
