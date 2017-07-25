<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>智慧工地扬尘噪声在建监控系统</title>
    <link href="${ctx}/css/ys.css" rel="stylesheet" type="text/css">
    <script src="${ctx}/js/echarts.common.min.js"></script>
</head>
<body>
<div class="top">
    <!--logo位置,用户名退出-->
    <div class="logo">
        <span class="tuichu"><a href="${ctx}/login/logout.action">退出</a></span>
        <span class="xingming"> ${sessionScope.username}</span>
        <span class="yonghuming"> 用户名：</span>
    </div>
</div>
<!--广告条-->
<div class="bannerbig">
    <div class="bannerlittle"></div>
</div>
<!--中间-->
<div class="maxmid">
    <!--小中间-->
    <div class="minmid">
        <div class="dbxxlan">
            <div style="float:left; height:26px; line-height:26px;">${city}环保监测站实时数据</div>
            <div class="hhhtbj">
                <span class="aqi">AQI</span><span class="aqis">${air.get('t1')}</span>
            </div>
            <div class="hhhtbj">
                <span class="aqi">PM2.5</span><span class="aqis">${air.get('t3')}</span>
            </div>
            <div class="hhhtbj">
                <span class="aqi">PM10</span><span class="aqis">${air.get('t4')}</span>
            </div>
            <div class="hhhtbj">
                <span class="aqi">SO2</span><span class="aqis">${air.get('t7')}</span>
            </div>
            <div class="hhhtbj">
                <span class="aqi">NO2</span><span class="aqis">${air.get('t6')}</span>
            </div>
            <div class="hhhtbj">
                <span class="aqi">CO</span><span class="aqis">${air.get('t5')}</span>
            </div>
            <div class="hhhtbj">
                <span class="aqi">O3</span><span class="aqis">${air.get('t10')}</span>
            </div>
        </div>
        <div class="partxm">

            <div class="xm1">
                <a class="xm1shang" href="#">${province}</a>
                <a class="xm1xia" href="#">${city}${district}</a>
            </div>
            <div class="xm2"><a href="#">${projectNums}</a></div>
            <div class="xm3"><a href="#">${deviceNums}</a></div>
            <div class="xm4">
                <a class="xm4shang" href="#">${deviceOnlineNums}</a>
                <a class="xm4xia" href="#">${deviceOfflineNums}</a>
            </div>
            <div class="xm5">
                <a class="xm5shang" href="#">${weather.weatherinfo.temp}/${weather.weatherinfo.tempn}</a>
                <a class="xm5xia" href="#" title="${weather.weatherinfo.weather}">
                    <c:if test="${fn:length(weather.weatherinfo.weather)>10 }">${fn:substring(weather.weatherinfo.weather, 0, 9)}...</c:if>
                    <c:if test="${fn:length(weather.weatherinfo.weather)<=10 }">${weather.weatherinfo.weather}</c:if>
                </a>
            </div>
            <div class="xm6"><a href="#">${projectNums}</a></div>
            <div class="xm7"><a href="#">${deviceAlarmNums}</a></div>
            <a href="${ctx}/login/home.action">
                <div class="xm8"></div>
            </a>
        </div>

    </div>
    <!--10个对比位置-->
    <div class="duibibiao">
        <h4>站点排名</h4>

        <div class="charts" style="height: 390px;">
            <div style="width: 49%;float: left">
                <div id="pm25" style="width: 80%;margin: 8px auto;height: 360px;border: 1px solid gray;"></div>
            </div>
            <div style="width: 49%;float: left">
                <div id="pm10" style="width: 80%;margin: 8px auto;height: 360px;border: 1px solid gray;"></div>
            </div>
        </div>
        <div class="charts" style="height: 390px;">
            <div style="width: 49%;float: left">
                <div id="dust" style="width: 80%;margin: 8px auto;height: 360px;border: 1px solid gray;"></div>
            </div>
            <div style="width: 49%;float: left">
                <div id="noise" style="width: 80%;margin: 8px auto;height: 360px;border: 1px solid gray;"></div>
            </div>
        </div>
    </div>

</div>

<!--底部-->
<div class="foot">
    <div class="foottxt">Copyright&nbsp;2015 &nbsp;&nbsp;中国联合网络通信有限公司</div>
</div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var pm25Chart = echarts.init(document.getElementById('pm25'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'PM2.5 TOP10'
        },
        tooltip: {},
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value'
        },
        yAxis: {
            type: 'category',
            axisLabel: {
                formatter: function (val) {
                    if (val.length > 5) {
                        val = val.substr(0, 4) + "..";
                    }
                    return val;
                }
            },
            data: ${pm25Category}
        },
        series: [{
            name: 'pm2.5',
            type: 'bar',
            barWidth: 10,
            data: ${pm25Data}
        }]
    };
    pm25Chart.setOption(option);

    var pm10Chart = echarts.init(document.getElementById('pm10'));
    var option = {
        title: {
            text: 'PM10 TOP10'
        },
        tooltip: {},
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value'
        },
        yAxis: {
            type: 'category',
            axisLabel: {
                formatter: function (val) {
                    if (val.length > 5) {
                        val = val.substr(0, 4) + "..";
                    }
                    return val;
                }
            },
            data: ${pm10Category}
        },
        series: [{
            name: 'pm10',
            type: 'bar',
            barWidth: 10,
            data: ${pm10Data}
        }]
    };
    pm10Chart.setOption(option);

    var dustChart = echarts.init(document.getElementById('dust'));
    var option = {
        title: {
            text: '扬尘 TOP10'
        },
        tooltip: {},
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value'
        },
        yAxis: {
            type: 'category',
            axisLabel: {
                formatter: function (val) {
                    if (val.length > 5) {
                        val = val.substr(0, 4) + "..";
                    }
                    return val;
                }
            },
            data: ${dustCategory}
        },
        series: [{
            name: 'dust',
            type: 'bar',
            barWidth: 10,
            data: ${dustData}
        }]
    };
    dustChart.setOption(option);

    var noiseChart = echarts.init(document.getElementById('noise'));
    var option = {
        title: {
            text: '噪声 TOP10'
        },
        tooltip: {},
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value'
        },
        yAxis: {
            type: 'category',
            axisLabel: {
                formatter: function (val) {
                    if (val.length > 5) {
                        val = val.substr(0, 4) + "..";
                    }
                    return val;
                }
            },
            data: ${noiseCategory}
        },
        series: [{
            name: 'noise',
            type: 'bar',
            barWidth: 10,
            data: ${noiseData}
        }]
    };
    noiseChart.setOption(option);
</script>
</body>
</html>