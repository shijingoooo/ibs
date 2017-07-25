/**
 * 萤石云操作工具类
 * Created by Green Live on 2016/8/31.
 */

var accessToken = null;
var appKey = "7932c510fca247a98ddb4110b92eb940";
var appSecret = "0b6233f3859df9c4a704182c7f299f60";
var serverUrl = "https://open.ys7.com";
var tryTime = 3;
var retry = 0;
var runningSN = null;
var runningCN = 1;

//登录萤石云
function login() {
    if (accessToken == null) {
        //准备登录数据
        var params = {"appKey": appKey, "appSecret": appSecret};
        result = postData("/api/lapp/token/get", params);
        if (result != null) {
            accessToken = result.accessToken;
        }
    }
}

function moveToPreset(serial, channel, index) {
    if (accessToken != null) {
        runningSN = serial;
        runningCN = channel;
        var params = {"accessToken": accessToken, "deviceSerial": runningSN, "channelNo": runningCN, "index": index};
        postData("/api/lapp/device/preset/move", params);
        delayStop();
    } else {
        if (retry < tryTime) {
            retry++;
            login();
            moveToPreset(serial, channel, index);
        } else {
            retry = 0;
        }
    }
}

//云台控制
function control(serial, channel, direction, speed) {
    if (accessToken != null) {
        runningSN = serial;
        runningCN = channel;
        var params = {
            "accessToken": accessToken,
            "deviceSerial": serial,
            "channelNo": channel,
            "direction": direction,
            "speed": speed
        };
        postData("/api/lapp/device/ptz/start", params);
        delayStop();
    } else {
        if (retry < tryTime) {
            retry++;
            login();
            control(serial, channel, direction, speed);
        } else {
            retry = 0;
        }
    }
}

//停止云台控制
function stop(serial, channel) {
    if (accessToken != null) {
        var params = {"accessToken": accessToken, "deviceSerial": serial, "channelNo": channel};
        var result = postData("/api/lapp/device/ptz/stop", params);
        if (result != null) {
            var r = parseBoolean(result.toString());
            if (r) {
                runningSN = null;
                return true;
            }
        }
    } else {
        if (retry < tryTime) {
            retry++;
            login();
            stop(serial, channel);
        } else {
            retry = 0;
        }
    }
    return false;
}

function getChannelFromUrl(videoUrl) {
    if (videoUrl != null) {
        try {
            var channelStr = videoUrl.substring(videoUrl.lastIndexOf("_") + 1, videoUrl.indexOf("."));
            return parseInt(channelStr);
        } catch (e) {
        }
    }
    return 1;
}

function getSerialFromUrl(videoUrl) {
    if (videoUrl != null) {
        try {
            return videoUrl.substring(videoUrl.lastIndexOf("/") + 1, videoUrl.indexOf("_"));
        } catch (e) {
        }
    }
    return null;
}

//获取有效的视频地址
function getVideoUrl(serial) {
    if (accessToken != null) {
        var params = {"accessToken": accessToken, "deviceSerial": serial, "channelNo": "1", "expireTime": "2592000"};
        var result = postData("/api/lapp/live/address/limited", params);
        if (result != null) {
            return result.getString("liveAddress");
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

function postData(action, params) {

}

function postData1(action, params) {
    $.ajax({
        type: 'POST',
        crossDomain: true,
        global: false,
        url: serverUrl + action,
        data: params,
        dataType: 'json',
        success: function (result) {
            var json = $.parseJSON(result);
            return json.data;
        },
        xhrFields: {
            withCredentials: true
        },
        error: function () {
            accessToken = null;
        }
    });
    return null;
}

function delayStop() {
    setTimeout('stop(runningSN,runningCN);', 1000);
}