<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.form.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        //${obj.id}
        $(".delete").click(function () {
            var dateIds = "";
            $("input[name='ips']").each(function (index, item) {
                if ($(item).prop("checked")) {
                    dateIds += $(item).val() + ",";
                }
            });
            var hrefVal = $(this).attr("href");
            if (hrefVal.indexOf("?") > -1) {
                hrefVal = hrefVal.substring(0, hrefVal.indexOf("?"));
            }
            $(this).attr("href", hrefVal + "?ips=" + dateIds.substring(0, dateIds.length - 1) + "&rel=ibs_device_application");
        });
        $(".deleteLED").click(function () {
            var dateIds = "";
            $("input[name='devIds']").each(function (index, item) {
                if ($(item).prop("checked")) {
                    dateIds += $(item).val() + ",";
                }
            });
            var hrefVal = $(this).attr("href");
            if (hrefVal.indexOf("?") > -1) {
                hrefVal = hrefVal.substring(0, hrefVal.indexOf("?"));
            }
            $(this).attr("href", hrefVal + "?devIds=" + dateIds.substring(0, dateIds.length - 1) + "&rel=ibs_device_application");
        });
        $(".tabs").attr("currentindex",${currentIndex});
    });
function powerSwitch(status,id){
    var status1 = $("#status").val();
    var id1 = $("#id").val();
    $.ajax({
            type:"post",
            async:false,
            data:{"status":status1,"id":id1},
            url:"${ctx}/monitoringDevice/devPower.action",
            success:function(msg){
             var st =  msg.substring(0,1);
             var msgStr = msg.substring(2);
            if(msgStr="更改成功") {
                if(st==1){
                    $("#"+id+"").val("开")
                    $("#status").val(1);
                }
                if(st==0){
                    $("#"+id+"").val("关")
                    $("#status").val(0);
                }

            }
        }
    })
}
    function changeTab(index) {
        var $tabs = $(".tabs");
        $tabs.attr("currentindex",index);
        $("input[name='currentIndex']").attr("value",index);

        var $form = $(".pageForm");
        $form.submit();

    }
</script>

<style type="text/css">
    .devId a{
        color: #00f;
        text-decoration: underline;
    }
</style>

<div class="pageContent">
    <div class="tabs" currentIndex="0" eventType="click">
        <form id="pageForm" class="pageForm" onsubmit="return navTabSearch(this);"
              action="${ctx}/monitoringDevice/devapplication.action?" method="post">
            <input type="hidden" name="pageNum" value="1" />
            <input type="hidden" name="numPerPage" value="<c:out value="${numPerPage}"></c:out>" />
            <input type="hidden" id="currentIndex" name="currentIndex" value="${currentIndex}" />
        </form>


        <div class="tabsHeader">
<%--            <div class="searchBar">
                <table class="searchContent">
                    <tr>
                        <td>分组名称：<input type="text" name="devGroupNameForLike"
                                        value="<c:out value='${deviceGroupQueryBean.devGroupNameForLike }'/>"
                                        onblur="valiStr(this)"/></td>
                        <td>
                            <button type="submit" id="modelSearchDeviceGroup">查询</button>
                        </td>
                    </tr>
                </table>
            </div>--%>
    <div class="tabsHeaderContent">
        <ul>
            <li><a href="javascript:;"onclick="changeTab('0');"><span>GPRS开关应用</span></a></li>
            <li><a href="javascript:;"onclick="changeTab('1');"><span>LED屏应用</span></a></li>
        </ul>
    </div>
        </div>
        <div class="tabsContent" style="height:795px;">
            <div class="GPRS">
                <div class="panelBar">
                    <ul class="toolBar">
                        <li>
                            <a id="devicePowerAdd" class="add" target="dialog" mask="true" width="620" height="400" title="新增GPRS开关"
                               rel="newdevicePower" href="${ctx}/monitoringDevice/devSavePowerPage.action">
                                <span>新增</span>
                            </a>
                        </li>
                        <li>
                            <a id="devicePowerDelete" class="delete" target="ajaxTodo" title="您确定要删除设备信息吗?" href="${ctx}/monitoringDevice/devDeletePower.action">
                                <span>删除</span>
                            </a>
                        </li>
                       <%-- <select id="status" name="status"style="margin-left: 25px;">
                            <c:choose>
                                <c:when test="${obj.mode == 0}">
                                    <option value="1">手动</option>
                                    <option value="0"selected>自动</option>
                                </c:when>
                                <c:when test="${obj.mode == 1}" >
                                    <option value="1" selected>手动</option>
                                    <option value="0">自动</option>
                                </c:when>
                            </c:choose>
                        </select>--%>

                        <li>
                            <label><input name="devStatus" type="checkbox" value="1" />手动 </label>
                        </li>
                        <li>
                            <label><input name="devStatus" type="checkbox" value="0" />自动 </label>
                        </li>
                    </ul>
                </div>
                <table class="table" width="100%" layoutH="112">
                    <thead>
                    <tr>

                            <th width="30" align="center">
                                <div title="" class="gridCol">
                                    <input class="checkboxCtrl" type="checkbox" group="ips">
                                </div>
                            </th>
                            <th width="80">GPRS开关ip/域名</th>
                            <th width="80">GPRS开关地址</th>
                            <th width="80">GPRS开关编号</th>
                            <th width="80">GPRS开关端口</th>
                            <th width="80">设备编号</th>
                            <th width="80">GPRS开关状态</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="obj" items="${page.result}" varStatus="index">
                        <tr target="tr_form" rel="${obj.id}">
                            <td width="30" align="center">
                                <div>
                                    <input name="ips" type="checkbox" value="${obj.id}"/>
                                </div>
                            </td>
                            <td width="80" class="ip">
                                <self:a code="ibs_device_power_update" name="${obj.ip}"
                                        parameter="?ip=${obj.ip}" style="icon" target="dialog"
                                        mask="true" rel="newdevicePower" width="560" height="380"></self:a>

                            </td>
                            </td>
                            <td width="80">${obj.addr}</td>
                            <td width="80">${obj.devId}</td>
                            <td width="80">${obj.port}</td>
                            <td width="80">${obj.deviceId}</td>

                            <td width="80">
                                <input type="hidden" value="${obj.status}" id="status"/>
                                <input type="hidden" value="${obj.id}" id="id"/>
                                <input type="button" id="${obj.id}" onclick=powerSwitch("javascript:${obj.status}","${obj.id}")
                                        <c:if test="${obj.status == 0}">value="关"</c:if>
                                        <c:if test="${obj.status == 1}">value="开"</c:if>
                                />
<%--
                                <input type="text" id="powerButton" class="powerButton"/>
--%>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <self:pager page="${page}"></self:pager>
                    <%--<     >
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
            </div>
            <div class="LED">
                        <div class="panelBar">
                            <ul class="toolBar">
                                <li>
                                    <a id="deviceLEDAdd" class="add" target="dialog" mask="true" width="620" height="400" title="新增LED数据"
                                       rel="newdeviceLED" href="${ctx}/monitoringDevice/devSaveLedPage.action">
                                        <span>新增</span>
                                    </a>
                                </li>
                                <li>
                                    <a id="deviceLedDelete" class="deleteLED" target="ajaxTodo" title="您确定要删除设备信息吗?"
                                       href="${ctx}/monitoringDevice/devDeleteLed.action">
                                        <span>删除</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                <table class="table" width="100%" layoutH="112">
                    <thead>
                    <tr>
                        <th width="30" align="center">
                            <div title="" class="gridCol">
                                <input class="checkboxCtrl" type="checkbox" group="devIds">
                            </div>
                        </th>
                        <th width="80">LED编号</th>
                        <th width="80">设备编号</th>
                        <th width="80">LED域名/ip</th>
                        <th width="80">LED端口</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="obj" items="${ledpage.result}" varStatus="index">
                        <tr target="tr_form" rel="${obj.id}">
                            <td>
                                <input name="devIds" type="checkbox" value="${obj.id}"/>
                            </td>
                           <td width="80" class="idDev">
                                <self:a code="ibs_device_led_update" name="${obj.idDev}"
                                        parameter="?id_dev=${obj.idDev}" style="icon" target="dialog"
                                        mask="true" rel="newdeviceLed" width="560" height="380"></self:a>
                            </td>
                            <td>${obj.idDevice}</td>
                            <td>${obj.ipSvr}</td>
                            <td>${obj.portSvr}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <self:pager page="${ledpage}"></self:pager>

            </div>
        </div>
        <div class="tabsFooter">
            <div class="tabsFooterContent"></div>
        </div>
    </div>



</div>