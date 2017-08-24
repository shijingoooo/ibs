<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#modelExportDeviceGroup").click(function () {
            var dateIds = "";
            $("input[name='deviceGroupIds']").each(function (index, item) {
                if ($(item).prop("checked")) {
                    dateIds += $(item).val() + ",";
                }
            });
            if (dateIds == "") {
                alert("请选择要导出的数据！");
            } else {
                var hrefVal = "${ctx}/monitoringDevice/downloadDeviceGroup.action";
                location.href = hrefVal + "?deviceGroupIds=" + dateIds.substring(0, dateIds.length - 1) + "&rel=ibs_device_group2_page";
            }
        });

        $(".tabs").attr("currentindex",${currentIndex});

        $("#modelImportDeviceGroup").click(function () {
            $("#ImportDeviceGroupForm").submit();
        });

    });

    function changeTab(index) {
        var $tabs = $(".tabs");
        $tabs.attr("currentindex",index);
        $("input[name='currentIndex']").attr("value",index);

        var $form = $(".pagerForm");
        $form.submit();
    }
</script>
<style type="text/css">
    .devCode a{
        color: #00f;
        text-decoration: underline;
    }
</style>
<div class="pageContent">

    <div class="tabs" currentIndex="0" eventType="click">
        <form id="pagerForm" class="pagerForm" onsubmit="return navTabSearch(this);"
              action="${ctx}/monitoringDevice/devStatisticsDataListByPage.action?devId=${device.id}" method="post">
            <input type="hidden" name="pageNum" value="1" />
            <input type="hidden" name="numPerPage" value="<c:out value="${numPerPage}"></c:out>" />
            <input type="hidden" name="currentIndex" value="${currentIndex}">
        </form>
        <div class="tabsHeader">
            <div class="tabsHeaderContent">
                <ul>
                    <li class="tab"><a href="javascript:;" onclick="changeTab('0');"><span>实时数据</span></a></li>
                    <li class="tab"><a href="javascript:;" onclick="changeTab('1');"><span>小时数据</span></a></li>
                    <li class="tab"><a href="javascript:;" onclick="changeTab('2');"><span>天数据</span></a></li>
                </ul>
            </div>
        </div>
        <div class="tabsContent" style="height:834px;">
            <div class="now">
                <span class="heading">设备编号：${device.devCode}</span>
                <span class="heading">设备类型：
                    <c:choose>
                        <c:when test="${device.devType == 3}">视频</c:when>
                        <c:when test="${device.devType == 4}">扬尘噪声</c:when>
                        <c:when test="${device.devType == 5}">AQI</c:when>
                        <c:when test="${device.devType == 6}">VOC</c:when>
                        <c:when test="${device.devType == 7}">扬尘噪声（基础）</c:when>
                        <c:otherwise>未指定类型</c:otherwise>
                    </c:choose>
                </span>
                <span class="heading">设备分组：${device.belongGroups}</span>
                <span class="heading">设备厂商：${device.monitoringCompany.companyName}</span>
                <button type="button" class="modelExportDevice" style="margin-left: 100px">导出数据</button>
                <table class="table" width="100%" layoutH="112" rel="statistics_data_list">
                    <thead>
                    <tr>
                        <th width="30" align="center">
                            <div title="" class="gridCol">
                                <input class="checkboxCtrl" type="checkbox" group="ruleIds">
                            </div>
                        </th>
                        <th width="100">时间</th>
                        <th width="80">PM10（ug/m³）</th>
                        <th width="80">PM2.5（ug/m³）</th>
                        <th width="80">TSP（ug/m³）</th>
                        <th width="80">噪声（dB(A)）</th>
                        <th width="60">风速（m/s）</th>
                        <th width="60">风向</th>
                        <th width="60">温度（℃）</th>
                        <th width="60">湿度（%RH）</th>
                        <th width="60">气压（KPa）</th>
                        <th width="40">电源状态（?）</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="obj" items="${page.result}" varStatus="index">
                        <tr target="tr_form" rel="${obj.id}">
                            <td>
                                <input name="ruleIds" type="checkbox" value="${obj.id}"/>
                            </td>
                            <td><fmt:formatDate value="${obj.colTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td>${obj.actualTenPm}</td>
                        <td>${obj.actualTwoPm}</td>
                        <td>${obj.actualTsp}</td>
                        <td>${obj.actualNoise}</td>
                        <td>${obj.actualWindSpeed}</td>
                        <td>${obj.actualWindDirection}</td>
                        <td>${obj.actualTemperature}</td>
                        <td>${obj.actualRainfall}</td>
                        <td>${obj.actualPressure}</td>
                        <td>${obj.electricQuantity}</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <self:pager page="${page}"></self:pager>
            </div>
            <div class="hour">
                <span class="heading">设备编号：${device.devCode}</span>
                <span class="heading">设备类型：
                    <c:choose>
                        <c:when test="${device.devType == 3}">视频</c:when>
                        <c:when test="${device.devType == 4}">扬尘噪声</c:when>
                        <c:when test="${device.devType == 5}">AQI</c:when>
                        <c:when test="${device.devType == 6}">VOC</c:when>
                        <c:when test="${device.devType == 7}">扬尘噪声（基础）</c:when>
                        <c:otherwise>未指定类型</c:otherwise>
                    </c:choose>
                </span>
                <span class="heading">设备分组：${device.belongGroups}</span>
                <span class="heading">设备厂商：${device.monitoringCompany.companyName}</span>
                <button type="button" class="modelExportDevice" style="margin-left: 100px">导出数据</button>
                <table class="table" width="100%" layoutH="112" rel="statistics_data_list">
                    <thead>
                    <tr>
                        <th width="30" align="center">
                            <div title="" class="gridCol">
                                <input class="checkboxCtrl" type="checkbox" group="ruleIds">
                            </div>
                        </th>
                        <th width="100">时间</th>
                        <th width="80">PM10（ug/m³）</th>
                        <th width="80">PM2.5（ug/m³）</th>
                        <th width="80">TSP（ug/m³）</th>
                        <th width="80">噪声（dB(A)）</th>
                        <th width="60">风速（m/s）</th>
                        <th width="60">风向</th>
                        <th width="60">温度（℃）</th>
                        <th width="60">湿度（%RH）</th>
                        <th width="60">气压（KPa）</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="obj" items="${hourDataPage.result}" varStatus="index">
                    <tr target="tr_form" rel="${obj.id}">
                        <td>
                            <input name="ruleIds" type="checkbox" value="${obj.id}"/>
                        </td>
                        <td><fmt:formatDate value="${obj.colTime}" pattern="yyyy-MM-dd HH:00:00" /></td>
                            <td>${obj.actualTenPm}</td>
                            <td>${obj.actualTwoPm}</td>
                            <td>${obj.actualTsp}</td>
                            <td>${obj.actualNoise}</td>
                            <td></td>
                            <td></td>
                            <td>${obj.actualTemperature}</td>
                            <td></td>
                            <td></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <self:pager page="${hourDataPage}"></self:pager>
            </div>
            <div class="day">
                <span class="heading">设备编号：${device.devCode}</span>
                <span class="heading">设备类型：
                    <c:choose>
                        <c:when test="${device.devType == 3}">视频</c:when>
                        <c:when test="${device.devType == 4}">扬尘噪声</c:when>
                        <c:when test="${device.devType == 5}">AQI</c:when>
                        <c:when test="${device.devType == 6}">VOC</c:when>
                        <c:when test="${device.devType == 7}">扬尘噪声（基础）</c:when>
                        <c:otherwise>未指定类型</c:otherwise>
                    </c:choose>
                </span>
                <span class="heading">设备分组：${device.belongGroups}</span>
                <span class="heading">设备厂商：${device.monitoringCompany.companyName}</span>
                <button type="button" class="modelExportDevice" style="margin-left: 100px">导出数据</button>
                <table class="table" width="100%" layoutH="112" rel="statistics_data_list">
                    <thead>
                    <tr>
                        <th width="30" align="center">
                            <div title="" class="gridCol">
                                <input class="checkboxCtrl" type="checkbox" group="ruleIds">
                            </div>
                        </th>
                        <th width="100">时间</th>
                        <th width="80">PM10（ug/m³）</th>
                        <th width="80">PM2.5（ug/m³）</th>
                        <th width="80">TSP（ug/m³）</th>
                        <th width="80">噪声（dB(A)）</th>
                        <th width="60">风速（m/s）</th>
                        <th width="60">风向</th>
                        <th width="60">温度（℃）</th>
                        <th width="60">湿度（%RH）</th>
                        <th width="60">气压（KPa）</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="obj" items="${dayDataPage.result}" varStatus="index">
                        <tr target="tr_form" rel="${obj.id}">
                            <td>
                                <input name="ruleIds" type="checkbox" value="${obj.id}"/>
                            </td>
                            <td><fmt:formatDate value="${obj.colTime}" pattern="yyyy-MM-dd" /></td>
                            <td>${obj.actualTenPm}</td>
                            <td>${obj.actualTwoPm}</td>
                            <td>${obj.actualTsp}</td>
                            <td>${obj.actualNoise}</td>
                            <td></td>
                            <td></td>
                            <td>${obj.actualTemperature}</td>
                            <td></td>
                            <td></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <self:pager page="${dayDataPage}"></self:pager>
            </div>
        </div>
        <div class="tabsFooter">
            <div class="tabsFooterContent"></div>
        </div>
    </div>
</div>