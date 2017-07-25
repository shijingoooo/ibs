package com.capinfo.framework.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PathKit;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 萤石云操作工具类
 * Created by Green Live on 2016/8/31.
 */
public class YS7Util {
    private static String accessToken = null;
    private static String appKey = "7932c510fca247a98ddb4110b92eb940";
    private static String appSecret = "0b6233f3859df9c4a704182c7f299f60";
    private static String serverUrl = "https://open.ys7.com";
    private static SSLContext sslcontext = null;
    private static int tryTime = 3;
    private static int tryStopTime=3;
    private static int retry = 0;
    private static ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(1);
    private static String runningSN=null;
    private static int runningCN=1;

    //登录萤石云
    public static void login() {
        if (accessToken == null) {
            //准备登录数据
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("appKey", appKey));
            params.add(new BasicNameValuePair("appSecret", appSecret));
            Object result = postData("/api/lapp/token/get", params);
            if (result != null) {
                JSONObject r = (JSONObject) result;
                accessToken = r.getString("accessToken");
            }
        }
    }

    public static void moveToPreset(String serial,int channel,int index){
        if (accessToken != null) {
            runningSN=serial;
            runningCN=channel;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("accessToken", accessToken));
            params.add(new BasicNameValuePair("deviceSerial", runningSN));
            params.add(new BasicNameValuePair("channelNo", runningCN+""));
            params.add(new BasicNameValuePair("index", index + ""));
            postData("/api/lapp/device/preset/move", params);
            delayStop();
        } else {
            if (retry < tryTime) {
                retry++;
                login();
                moveToPreset(serial,channel, index);
            } else {
                retry = 0;
            }
        }
    }

    //云台控制
    public static void control(String serial,int channel, int direction, int speed) {
        if (accessToken != null) {
            runningSN=serial;
            runningCN=channel;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("accessToken", accessToken));
            params.add(new BasicNameValuePair("deviceSerial", serial));
            params.add(new BasicNameValuePair("channelNo", channel+""));
            params.add(new BasicNameValuePair("direction", direction + ""));
            params.add(new BasicNameValuePair("speed", speed + ""));
            postData("/api/lapp/device/ptz/start", params);
            delayStop();
        } else {
            if (retry < tryTime) {
                retry++;
                login();
                control(serial,channel, direction, speed);
            } else {
                retry = 0;
            }
        }
    }

    //停止云台控制
    public static boolean stop(String serial,int channel){
        if (accessToken != null) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("accessToken", accessToken));
            params.add(new BasicNameValuePair("deviceSerial", serial));
            params.add(new BasicNameValuePair("channelNo", channel+""));
            Object result=postData("/api/lapp/device/ptz/stop", params);
            if(result!=null){
                boolean r=Boolean.parseBoolean(result.toString());
                if(r){
                    runningSN=null;
                    return true;
                }
            }
        } else {
            if (retry < tryTime) {
                retry++;
                login();
                stop(serial,channel);
            } else {
                retry = 0;
            }
        }
        return false;
    }

    public static int getChannelFromUrl(String videoUrl){
        if (videoUrl != null) {
            try{
                String channelStr = videoUrl.substring(videoUrl.lastIndexOf("_") + 1, videoUrl.indexOf("."));
                return Integer.parseInt(channelStr);
            }catch (Exception e){

            }
        }
        return 1;
    }

    public static String getSerialFromUrl(String videoUrl) {
        if (videoUrl != null) {
            try{
                return videoUrl.substring(videoUrl.lastIndexOf("/") + 1, videoUrl.indexOf("_"));
            }catch (Exception e){

            }
        }
        return null;
    }

    //获取有效的视频地址
    public static String getVideoUrl(String serial){
        if (accessToken != null) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("accessToken", accessToken));
            params.add(new BasicNameValuePair("deviceSerial", serial));
            params.add(new BasicNameValuePair("channelNo", "1"));
            params.add(new BasicNameValuePair("expireTime", "2592000"));
            Object result = postData("/api/lapp/live/address/limited", params);
            if (result != null) {
                JSONObject r = (JSONObject) result;
                return r.getString("liveAddress");
            }
        } else {
            if (retry < tryTime) {
                retry++;
                login();
                return getVideoUrl(serial);
            } else {
                retry = 0;
            }
        }
        return null;
    }

    //获取设备播放地址
    public static String getVideoUrlFromList(String serial) {
        List videoUrls = getVideoList();
        if (videoUrls.size() > 0) {
            for (Object v : videoUrls) {
                JSONObject json = (JSONObject) v;
                if (serial.equals(json.getString("deviceSerial"))) {
                    return json.getString("liveAddress");
                }
            }
        }
        return null;
    }

    //获取播放列表
    public static List getVideoList() {
        if (accessToken != null) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("accessToken", accessToken));
            params.add(new BasicNameValuePair("pageSize", "50"));
            Object result = postData("/api/lapp/live/video/list", params);
            if (result != null) {
                JSONArray r = (JSONArray) result;
                List list = r.subList(0, r.size());
                return list;
            }
        } else {
            if (retry < tryTime) {
                retry++;
                login();
                return getVideoList();
            } else {
                retry = 0;
            }
        }
        return null;
    }

    //加载证书(可选)
    private void loadTrust() {
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream ksIn = new FileInputStream(new File(PathKit.getRootClassPath() + "/glcp.p12"));
            keyStore.load(ksIn, "glcp123".toCharArray());
            sslcontext = SSLContexts.custom()
                    .loadTrustMaterial(new TrustStrategy() {
                        @Override
                        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            return true;
                        }
                    })
                    .loadKeyMaterial(keyStore, "glcp123".toCharArray())
                    //.loadTrustMaterial(new File(PathKit.getRootClassPath() + "/glcp.p12"), "glcp123".toCharArray(), new TrustSelfSignedStrategy())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object postData(String action, List<NameValuePair> params) {
        return postData(action, params, 0);
    }

    private static Object postData(String action, List<NameValuePair> params, int type) {
        CloseableHttpClient httpclient = null;
        if (type > 0) {
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } else {
            httpclient = HttpClients.custom().build();
        }
        String result = "";
        try {
            HttpPost httppost = new HttpPost(serverUrl + action);
            httppost.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity rentity = response.getEntity();
                //System.out.println("----------------------------------------");
                //System.out.println(response.getStatusLine());
                //EntityUtils.consume(rentity);
                result = EntityUtils.toString(rentity);
                System.out.println("["+action+"]返回:"+result);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        if (result.length() > 0) {
            JSONObject returnJson = JSON.parseObject(result);
            int status = returnJson.getIntValue("code");
            if (status == 200) {
                if(returnJson.get("data")!=null){
                    return returnJson.get("data");
                }else{
                    return true;
                }
            }else if(status==10002){
                accessToken=null;
            }
        }
        return null;
    }

    private static void delayStop(){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                if(runningSN!=null){
                    System.out.println("5秒钟未收到指令，停止云台");
                    //确保设备停下来
                    if(tryStopTime>0){
                        if(!stop(runningSN,runningCN)){
                            tryStopTime--;
                            delayStop();
                        }else{
                            tryStopTime=3;
                        }
                    }
                }
            }
        };
        scheduExec.schedule(task,1000,TimeUnit.MILLISECONDS);
    }
}
