package com.capinfo.framework.web.service;

import com.capinfo.framework.web.mapper.GroupDeviceMapper;
import com.capinfo.framework.web.pojo.GroupDevice;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.QueryOperators;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service(value="groupDeviceService")
public class GroupDeviceService {
    @Autowired
    private GroupDeviceMapper groupDeviceMapper;

    /**
     * 根据设备组id获取设备组信息
     */
    public GroupDevice findGroupDevice(Integer deviceGroupId) throws Exception {
        GroupDevice groupDevice = groupDeviceMapper.findGroupDevice(deviceGroupId);
        return groupDevice;
    }

    public List<Integer> findDevices(GroupDevice groupDevice) {
        return groupDeviceMapper.findDevices(groupDevice);

    }

    public List<Map<String, Object>> exportMonitoringDataMongo(MongoCollection collection, Integer[] deviceIds, String startDate, String endDate) throws ParseException {
        BasicDBObject queryObject = new BasicDBObject();
        queryObject.put("device_id", new BasicDBObject(QueryOperators.IN,deviceIds));
        if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
            //格式化时间
            Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
            Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
            //添加时间条件
            queryObject.put("create_time",new BasicDBObject(new BasicDBObject(QueryOperators.GTE,startTime).append(QueryOperators.LTE,endTime)));

        }
        FindIterable findIterable = collection.find(queryObject);
        MongoCursor<Document> mongoCursor = findIterable.iterator();

        //存储最终返回的结果
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        //FindIterable转换成Document
        List<Document> mdList = new ArrayList<Document>();
        while (mongoCursor.hasNext()) {
            mdList.add(mongoCursor.next());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (Document doc : mdList) {
            map = new HashMap<String, Object>();

            map.put("_id", doc.get("_id"));
            map.put("actual_tsp", doc.get("actual_tsp"));
            map.put("actual_two_pm", doc.get("actual_two_pm"));
            map.put("actual_ten_pm", doc.get("actual_ten_pm"));
            map.put("actual_noise", doc.get("actual_noise"));
            map.put("actual_temperature", doc.get("actual_temperature"));
            map.put("actual_humidity", doc.get("actual_humidity"));
            map.put("actual_pressure", doc.get("actual_pressure"));
            map.put("actual_wind_speed", doc.get("actual_wind_speed"));
            map.put("actual_wind_direction", doc.get("actual_wind_direction"));
            map.put("calibration_tsp", doc.get("calibration_tsp"));
            map.put("calibration_two_pm", doc.get("calibration_two_pm"));
            map.put("calibration_ten_pm", doc.get("calibration_ten_pm"));
            map.put("calibration_noise", doc.get("calibration_noise"));
            map.put("col_time", sdf.format(doc.get("col_time")));
            map.put("create_time", sdf.format(doc.get("create_time")));
            map.put("version", doc.get("version"));
            map.put("device_id", doc.get("device_id"));

            lists.add(map);
        }
        return lists;
    }
}