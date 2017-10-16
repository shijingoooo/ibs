<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var uniqueidDev = true;
    $(document).ready(function () {

    });

    function saveProject() {
        if(uniqueidDev){
            $("input[name='longitude']").val($("#lng").val());
            $("input[name='latitude']").val($("#lat").val());
            $("#deviceledForm").submit();
        }

    }
    function checkidDev(){


        if ($("#idDev").val().length > 0) {
            $.getJSON("${ctx}/monitoringDevice/checkidDev.action?idDev=" + $("#idDev").val(), function (data) {
                if (data.idDev != 'undefined') {
                    $("#devIdMsg").html("该设备编号已经被占用");
                    uniqueidDev = false;
                } else {
                    $("#devIdMsg").html("");
                    uniqueidDev = true;
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
            <form id="deviceledForm"
                  action="${ctx}/monitoringDevice/devSaveLed.action?rel=ibs_device_application&callbackType=closeCurrent"
                  method="post" class='required-validate pageForm'
                  onsubmit="return validateCallback(this, dialogAjaxDone)">

                <input type="hidden" value="<c:out value='${ledBean.id}'/>" name="id"/>
                <div class="pageFormContent" layoutH="100">

                    <p class="nowrap">
                        <label style="width: 60px;">编号：</label>
                        <input id="idDev" name="idDev" value="<c:out value='${ledBean.idDev}'/>"
                               type="text" onblur="checkidDev()" size="30" class="required" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                        <span id="devIdMsg"></span>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">设备编号：</label>
                        <input id="idDevice" name="idDevice" value="<c:out value='${ledBean.idDevice}'/>"
                               type="text" size="30" class="required" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                    </p>
                  <%--  <p class="nowrap">
                       <label style="width: 60px;">设备编号：</label>
                       <input name="devId" type="text" size="30" readonly="readonly"
                              class="required" value="${maintain.deviceId}" style="margin-left: 25px;">
                       <input name="devId" type="hidden" value="${maintain.deviceId}"/>
                       <a class="btnLook"
                          href="${ctx}/monitoringMaintain/devSelectListInMaintain.action"
                          lookupGroup="" mask="true" width="800" height="500">选择设备</a>
                   </p>--%>
                    <p class="nowrap">
                        <label style="width: 60px;">域名/ip：</label>
                        <input id="ipSvr" name="ipSvr" value="<c:out value='${ledBean.ipSvr}'/>"
                               type="text"  size="30" class="required" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">端口：</label>
                        <input id="portSvr" name="portSvr" value="<c:out value='${ledBean.portSvr}'/>"
                               type="text" size="30" class="required" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                    </p>


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
