<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var uniqueDevIP = true;
    $(document).ready(function () {

    });

    function saveProject() {
        if(uniqueDevIP){
            $("input[name='longitude']").val($("#lng").val());
            $("input[name='latitude']").val($("#lat").val());
            $("#devicepowerForm").submit();
        }

    }
    function checkDevIP(){


        if ($("#ip").val().length > 0) {
            $.getJSON("${ctx}/monitoringDevice/checkDevIP.action?ip=" + $("#ip").val(), function (data) {

                if (data.ip != 'undefined') {
                    $("#devIPMsg").html("Ip已经被占用");
                    uniqueDevIP = false;
                } else {
                    $("#devIPMsg").html("");
                    uniqueDevIP = true;
                }
            });
        }
    }
</script>
<div class="tabs">
    <div class="tabsHeader">
        <div class="tabsHeaderContent">
            <ul>
                <li class="selected">
                    <a href="javascript:;"><span>基本信息</span></a>
                </li>
            </ul>
        </div>
    </div>
    <div class="tabsContent">
        <div id="base" style="display: block;">
            <form id="devicepowerForm"
                  action="${ctx}/monitoringDevice/devSavePower.action?rel=ibs_device_application&callbackType=closeCurrent"
                  method="post" class='required-validate pageForm'
                  onsubmit="return validateCallback(this, dialogAjaxDone)">


                <input type="hidden" value="<c:out value='${powerBean.id}'/>" name="id"/>
                <div class="pageFormContent" layoutH="100">

                    <p class="nowrap">
                        <label style="width: 60px;">GPRS开关域名：</label>
                        <input id="ip" name="ip" value="<c:out value='${powerBean.ip}'/>"
                               type="text" onblur="checkDevIP()" size="30" class="required" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                        <span id="devIPMsg"></span>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">GPRS开关地址：</label>
                        <input id="addr" name="Addr" value="<c:out value='${powerBean.addr}'/>"
                               type="text" size="30" class="required" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">GPRS开关编号：</label>
                        <input id="devId" name="devId" value="<c:out value='${powerBean.devId}'/>"
                               type="text"  size="30" class="required" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">GPRS开关端口：</label>
                        <input id="port" name="port" value="<c:out value='${powerBean.port}'/>"
                               type="text" size="30" class="required" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">GPRS开关状态：</label>

                        <select id="status" name="status"style="margin-left: 25px;">
                            <c:choose>
                                <c:when test="${powerBean.status == 1}">
                                    <option value="0">关</option>
                                    <option value="1"selected>开</option>
                                </c:when>
                                <c:when test="${powerBean.status == 0}" >
                                    <option value="0" selected>关</option>
                                    <option value="1">开</option>
                                </c:when>
                            </c:choose>
                        </select>

                       <%-- <input id="status" name="status" value="<c:out value='${powerBean.status}'/>"
                               type="text" size="30" class="required" minlength="1" maxlength="32" style="margin-left: 25px;"/>--%>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">设备编号：</label>
                        <input id="deviceId" name="deviceId" value="<c:out value='${powerBean.deviceId}'/>"
                               type="text" size="30" class="required" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                    </p>

                    <%--<p class="nowrap">
                        <label style="width: 60px;">选择设备：</label>
                        <input name="devCode" type="text" size="30" readonly="readonly"
                               class="required" value="${maintain.deviceCode}" style="margin-left: 25px;">
                        <input name="devId" type="hidden" value="${maintain.deviceId}"/>
                        <a class="btnLook"
                           href="${ctx}/monitoringMaintain/devSelectListInMaintain.action"
                           lookupGroup="" mask="true" width="800" height="500">选择设备</a>
                    </p>--%>
                </div>
            </form>
        </div>
    </div>
    <div class="tabsFooter">
        <div class="tabsFooterContent"></div>
    </div>
</div>
<div style="margin-top: 5px;">
    <div class="button">
        <div class="buttonContent">
            <button id="save" type="button" onclick="saveProject()">保存</button>
        </div>
    </div>
</div>
