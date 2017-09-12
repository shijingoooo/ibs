package com.capinfo.framework.common.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author Zhang Chuanjia
 * @Created with IntelliJ IDEA
 * @User: zhang
 * @Date: 2017/8/30 11:52
 */
public class MongoUtil {
    private MongoCollection collection;
    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;

    @Value("#{c['mongo.ip']}")
    private String mongoIP;
    @Value("#{c['mongo.port']}")
    private String mongoPort;
    @Value("#{c['mongo.dbname']}")
    private String mongodbname;
    @Value("#{c['mongo.username']}")
    private String mongoUsername;
    @Value("#{c['mongo.password']}")
    private String mongoPassword;

    public MongoCollection getCollection(){
        mongoClient = new MongoClient(mongoIP, Integer.parseInt(mongoPort));
        mongoDatabase = mongoClient.getDatabase(mongodbname);
        collection = mongoDatabase.getCollection("ibs_monitoring_data");
        return collection;
    }
}
