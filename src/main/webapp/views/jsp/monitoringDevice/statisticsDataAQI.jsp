<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
//        $("#AQI_dwz_export_a").hide();
        $("#modelExportDevice0").click(function () {
            expAQI();
        });
        $("#modelExportDevice1").click(function () {
            expAQI();
        });
        $("#modelExportDevice2").click(function () {
            expAQI();
        });

        $(".tabs").attr("currentindex",${currentIndex});

        $("#modelImportDeviceGroup").click(function () {
            $("#ImportDeviceGroupForm").submit();
        });
    });

    function expAQI() {
        var currentIndex = $("#currentIndex").val();
        var deviceType = $("#deviceType").val();
            $("#AQI_dwz_export_a").attr("href","${ctx}/monitoringDevice/goStatisticsExport.action?deviceType="+deviceType+"&currentIndex="+currentIndex);
            $("#AQI_dwz_export_a").click();
    }
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
    .heading{
        margin-left: 20px;
        font-size: 14px;
    }
    #AQI_dwz_export_a{
        display: none;
    }
</style>
<div class="pageContent">
    <div class="tabs" currentIndex="0" eventType="click">
        <%--导出时，button点击该超链接--%>
        <a id="AQI_dwz_export_a" class="button" href="" target="dialog" rel="dlg_page8" max="false" title="${device.devCode}" width="455" height="420"></a>
        <form id="pagerForm" class="pagerForm" onsubmit="return navTabSearch(this);"
              action="${ctx}/monitoringDevice/devStatisticsDataListByPage.action?devId=${device.id}" method="post">
            <input type="hidden" name="pageNum" value="1" />
            <input type="hidden" name="numPerPage" value="<c:out value="${numPerPage}"></c:out>" />
            <input type="hidden" name="currentIndex" id="currentIndex" value="${currentIndex}">
            <input type="hidden" id="deviceType" name="deviceType" value="${device.devType}"/>
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
                <button type="button" id="modelExportDevice0" class="modelExportDevice" style="margin-left: 100px">导出数据</button>
                <table class="table" width="100%" layoutH="112" rel="statistics_data_list">
                    <thead>
                    <tr>
                        <th width="30" align="center">
                            <div title="" class="gridCol">
                                <input class="checkboxCtrl" type="checkbox" group="ruleIds">
                            </div>
                        </th>
                        <th width="120">时间</th>
                        <th width="70">PM10（ug/m³）</th>
                        <th width="70">PM2.5（ug/m³）</th>
                        <th width="70">SO₂（ug/m³）</th>
                        <th width="70">NO₂（ug/m³）</th>
                        <th width="70">O₃（ug/m³）</th>
                        <th width="70">CO（ug/m³）</th>
                        <th width="70">噪声（dB(A)）</th>
                        <th width="60">风速（m/s）</th>
                        <th width="40">风向</th>
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
                                <td>${obj.calibrationTenPm}</td>
                                <td>${obj.calibrationTwoPm}</td>
                                <td>${obj.calibration02}</td>
                                <td>${obj.calibrationNo2}</td>
                                <td>${obj.calibration03}</td>
                                <td>${obj.calibration04}</td>
                                <td>${obj.calibrationNoise}</td>
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
                </c:choose></span>
                <span class="heading">设备分组：${device.belongGroups}</span>
                <span class="heading">设备厂商：${device.monitoringCompany.companyName}</span>
                <button type="button" id="modelExportDevice1" class="modelExportDevice" style="margin-left: 100px">导出数据</button>
                <table class="table" width="100%" layoutH="112" rel="statistics_data_list">
                    <thead>
                    <tr>
                        <th width="30" align="center">
                            <div title="" class="gridCol">
                                <input class="checkboxCtrl" type="checkbox" group="ruleIds">
                            </div>
                        </th>
                        <th width="120">时间</th>
                        <th width="70">PM10（ug/m³）</th>
                        <th width="70">PM2.5（ug/m³）</th>
                        <th width="70">SO₂（ug/m³）</th>
                        <th width="70">NO₂（ug/m³）</th>
                        <th width="70">O₃（ug/m³）</th>
                        <th width="70">CO（ug/m³）</th>
                        <th width="70">噪声（dB(A)）</th>
                        <th width="60">风速（m/s）</th>
                        <th width="40">风向</th>
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
                            <td>${obj.actual02}</td>
                            <td>${obj.actualNO2}</td>
                            <td>${obj.actual03}</td>
                            <td>${obj.actual04}</td>
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
                </c:choose></span>
                <span class="heading">设备分组：${device.belongGroups}</span>
                <span class="heading">设备厂商：${device.monitoringCompany.companyName}</span>
                <button type="button" id="modelExportDevice2" class="modelExportDevice" style="margin-left: 100px">导出数据</button>
                <table class="table" width="100%" layoutH="112" rel="statistics_data_list">
                    <thead>
                    <tr>
                        <th width="30" align="center">
                            <div title="" class="gridCol">
                                <input class="checkboxCtrl" type="checkbox" group="ruleIds">
                            </div>
                        </th>
                        <th width="120">时间</th>
                        <th width="70">PM10（ug/m³）</th>
                        <th width="70">PM2.5（ug/m³）</th>
                        <th width="70">SO₂（ug/m³）</th>
                        <th width="70">NO₂（ug/m³）</th>
                        <th width="70">O₃（ug/m³）</th>
                        <th width="70">CO（ug/m³）</th>
                        <th width="70">噪声（dB(A)）</th>
                        <th width="60">风速（m/s）</th>
                        <th width="40">风向</th>
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
                            <td>${obj.actual02}</td>
                            <td>${obj.actualNO2}</td>
                            <td>${obj.actual03}</td>
                            <td>${obj.actual04}</td>
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
    <%--<div class="panelBar">
        <ul class="toolBar">
            <li>
                <a id="deviceGroupAdd" class="add" target="dialog" mask="true" width="620" height="400" title="新增规则"
                   rel="newdeviceGroup" href="${ctx}/monitoringDevice/devDataCalibrationSave.action">
                    <span>新增</span>
                </a>
            </li>
            <li>
                <a id="DataCalibrationRuleDelete" class="delete" target="ajaxTodo" title="您确定要删除设备信息吗?"
                   href="${ctx}/monitoringDevice/devDataCalibrationDelete.action">
                    <span>删除</span>
                </a>
            </li>
        </ul>
    </div>--%>


</div>