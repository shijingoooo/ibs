package com.capinfo.framework.web.common.job;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */
public class DataBaseJob {

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
    @Resource

    private MongoCollection collection;
    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;

    public void getConnection() {
        mongoClient = new MongoClient(mongoIP, Integer.parseInt(mongoPort));
        mongoDatabase = mongoClient.getDatabase(mongodbname);
        collection = mongoDatabase.getCollection("ibs_monitoring_data");
    }

    public void moveDate() throws Exception {
        /*getConnection();
        JSONArray json = JSONArray.fromObject(monitoringDataPojoList);
        List<Document> docList = null;
        Document doc = Document.parse("{ \"list\":" + json + "}");
        Object list = doc.get("list");
        if (list instanceof List<?>) {
            docList = (List<Document>) doc.get("list");
        }
        collection.insertMany(docList);*/

    }

    public static List<Document> toListOfDocuments(String json) {
        Document doc = Document.parse("{ \"list\":" + json + "}");
        Object list = doc.get("list");
        if (list instanceof List<?>) {
            return (List<Document>) doc.get("list");
        }
        return null;
    }
}
