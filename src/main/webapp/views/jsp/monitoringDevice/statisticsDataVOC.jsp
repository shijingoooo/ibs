<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
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

        $("#modelImportDeviceGroup").click(function () {
            $("#ImportDeviceGroupForm").submit();
        });


    });
</script>
<style type="text/css">
    .devCode a{
        color: #00f;
        text-decoration: underline;
    }
</style>
<%--<div class="pageHeader">
    <table>
        <tr>
            <td>
                <form class="Form" id="pagerForm" onsubmit="return navTabSearch(this);"
                      action="${ctx}/monitoringDevice/devDataCalibrationListByPage.action?devId=${dataCalibrationQueryBean.deviceId}" method="post">
                    <input type="hidden" name="pageNum" value="1"/>
                    <input type="hidden" name="numPerPage" value=""/>
                    <input name="deviceId" type="hidden" value=""/>

                        <table class="searchContent">
                            <tr>
                                &lt;%&ndash;<td>设备编号：<input type="text" name="deviceCodeForLike"
                                                 value="<c:out value='${dataCalibrationQueryBean.deviceCodeForLike }'/>"
                                                 onblur="valiStr(this)"/></td>
                                <td>
                                    <button type="submit" id="modelSearchDeviceGroup">检索</button>
                                </td>&ndash;%&gt;
                                &lt;%&ndash;暂时注释&ndash;%&gt;
                                &lt;%&ndash;<c:if test="${ sessionScope.usertype==4 }">
                                    <td><input type="button" value="模板导出" style="cursor: pointer;"
                                               id="modelExportDeviceGroup"></td>
                                </c:if>&ndash;%&gt;
                            </tr>
                        </table>
                </form>
            </td>
            &lt;%&ndash;暂时注释&ndash;%&gt;
            &lt;%&ndash; <c:if test="${ sessionScope.usertype==4 }">
                 <td>
                     <form onsubmit="return iframeCallback(this, FileUpload);"
                           action="${ctx}/monitoringDevice/importDeviceGroup.action" method="post"
                           enctype="multipart/form-data" id="ImportDeviceGroupForm">
                         <input type="file" name="file" id="file" style="width: 150px;cursor: pointer;">
                         <input type="button" value="数据导入" style="cursor: pointer;" id="modelImportDeviceGroup"></form>
                 </td>
             </c:if>&ndash;%&gt;
        </tr>
    </table>
</div>--%>
<div class="pageContent">
    <div class="tabs" currentIndex="0" eventType="click">
        <div class="tabsHeader">
            <div class="tabsHeaderContent">
                <ul>
                    <li><a href="javascript:;"><span>实时数据</span></a></li>
                    <li><a href="javascript:;"><span>小时数据</span></a></li>
                    <li><a href="javascript:;"><span>天数据</span></a></li>
                </ul>
            </div>
        </div>
        <div class="tabsContent" style="height:795px;">
            <div class="now">
                <label>设备编号：20161027000016</label>
                <label>设备类型：VOC</label>
                <label>设备分组：A组</label>
                <label>设备厂商：山东金叶</label>
                <button type="button" class="modelExportDevice" style="margin-left: 100px">导出数据</button>
                <table class="table" width="100%" layoutH="112" rel="statistics_data_list">
                    <thead>
                    <tr>
                        <th width="30" align="center">
                            <div title="" class="gridCol">
                                <input class="checkboxCtrl" type="checkbox" group="ruleIds">
                            </div>
                        </th>
                        <th width="80">时间</th>
                        <th width="80">H₂S（ug/m³）</th>
                        <th width="80">NH₃（ug/m³）</th>
                        <th width="80">C₂H₆S（ug/m³）</th>
                        <th width="80">CH₄S（ug/m³）</th>
                        <th width="80">TVOC（ug/m³）</th>
                        <th width="80">HCL（ug/m³）</th>
                        <th width="80">电源状态（?）</th>
                    </tr>
                    </thead>
                    <%--<tbody>
                    <c:forEach var="obj" items="${page.result}" varStatus="index">
                        <tr target="tr_form" rel="${obj.id}">
                            <td>
                                <input class="ruleIds" name="ruleIds" type="checkbox" value="${obj.id}"/>
                            </td>
                            <td class="devCode">
                                <self:a code="ibs_data_calibration_page_update" name="${obj.deviceCode}"
                                        parameter="?ruleId=${obj.id}&deviceId=${obj.deviceId}" style="icon" target="dialog"
                                        mask="true" rel="newdeviceRule" width="560" height="380"></self:a>
                            </td>
                            <td>${obj.type}</td>
                            <td>单位：ug/m3</td>
                            <td>${obj.min}</td>
                            <td>${obj.max}</td>
                            <td>${obj.calibrationFactor}</td>
                            <td>
                                <c:if test="${obj.status eq 0}">
                                    <a class="status" href="javascript:void(0);" onclick="firm(this);">启用</a>
                                </c:if>
                                <c:if test="${obj.status eq 1 }">
                                    <a class="status" href="javascript:void(0);" onclick="firm(this);">停用</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>--%>
                </table>
            </div>
            <div class="hour">
                <label>设备编号：20161027000016</label>
                <label>设备类型：VOC</label>
                <label>设备分组：A组</label>
                <label>设备厂商：山东金叶</label>
                <button type="button" class="modelExportDevice" style="margin-left: 100px">导出数据</button>
                <table class="table" width="100%" layoutH="112" rel="statistics_data_list">
                    <thead>
                    <tr>
                        <th width="30" align="center">
                            <div title="" class="gridCol">
                                <input class="checkboxCtrl" type="checkbox" group="ruleIds">
                            </div>
                        </th>
                        <th width="80">时间</th>
                        <th width="80">H₂S（ug/m³）</th>
                        <th width="80">NH₃（ug/m³）</th>
                        <th width="80">C₂H₆S（ug/m³）</th>
                        <th width="80">CH₄S（ug/m³）</th>
                        <th width="80">TVOC（ug/m³）</th>
                        <th width="80">HCL（ug/m³）</th>
                        <th width="80">电源状态（?）</th>
                    </tr>
                    </thead>
                    <%--<tbody>
                    <c:forEach var="obj" items="${page.result}" varStatus="index">
                        <tr target="tr_form" rel="${obj.id}">
                            <td>
                                <input class="ruleIds" name="ruleIds" type="checkbox" value="${obj.id}"/>
                            </td>
                            <td class="devCode">
                                <self:a code="ibs_data_calibration_page_update" name="${obj.deviceCode}"
                                        parameter="?ruleId=${obj.id}&deviceId=${obj.deviceId}" style="icon" target="dialog"
                                        mask="true" rel="newdeviceRule" width="560" height="380"></self:a>
                            </td>
                            <td>${obj.type}</td>
                            <td>单位：ug/m3</td>
                            <td>${obj.min}</td>
                            <td>${obj.max}</td>
                            <td>${obj.calibrationFactor}</td>
                            <td>
                                <c:if test="${obj.status eq 0}">
                                    <a class="status" href="javascript:void(0);" onclick="firm(this);">启用</a>
                                </c:if>
                                <c:if test="${obj.status eq 1 }">
                                    <a class="status" href="javascript:void(0);" onclick="firm(this);">停用</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>--%>
                </table>
            </div>
            <div class="day">
                <label>设备编号：20161027000016</label>
                <label>设备类型：VOC</label>
                <label>设备分组：A组</label>
                <label>设备厂商：山东金叶</label>
                <button type="button" class="modelExportDevice" style="margin-left: 100px">导出数据</button>
                <table class="table" width="100%" layoutH="112" rel="statistics_data_list">
                    <thead>
                    <tr>
                        <th width="30" align="center">
                            <div title="" class="gridCol">
                                <input class="checkboxCtrl" type="checkbox" group="ruleIds">
                            </div>
                        </th>
                        <th width="80">时间</th>
                        <th width="80">H₂S（ug/m³）</th>
                        <th width="80">NH₃（ug/m³）</th>
                        <th width="80">C₂H₆S（ug/m³）</th>
                        <th width="80">CH₄S（ug/m³）</th>
                        <th width="80">TVOC（ug/m³）</th>
                        <th width="80">HCL（ug/m³）</th>
                        <th width="80">电源状态（?）</th>
                    </tr>
                    </thead>
                    <%--<tbody>
                    <c:forEach var="obj" items="${page.result}" varStatus="index">
                        <tr target="tr_form" rel="${obj.id}">
                            <td>
                                <input class="ruleIds" name="ruleIds" type="checkbox" value="${obj.id}"/>
                            </td>
                            <td class="devCode">
                                <self:a code="ibs_data_calibration_page_update" name="${obj.deviceCode}"
                                        parameter="?ruleId=${obj.id}&deviceId=${obj.deviceId}" style="icon" target="dialog"
                                        mask="true" rel="newdeviceRule" width="560" height="380"></self:a>
                            </td>
                            <td>${obj.type}</td>
                            <td>单位：ug/m3</td>
                            <td>${obj.min}</td>
                            <td>${obj.max}</td>
                            <td>${obj.calibrationFactor}</td>
                            <td>
                                <c:if test="${obj.status eq 0}">
                                    <a class="status" href="javascript:void(0);" onclick="firm(this);">启用</a>
                                </c:if>
                                <c:if test="${obj.status eq 1 }">
                                    <a class="status" href="javascript:void(0);" onclick="firm(this);">停用</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>--%>
                </table>
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

    <self:pager page="${page}"></self:pager>
</div>