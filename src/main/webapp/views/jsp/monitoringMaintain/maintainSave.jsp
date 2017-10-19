<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(function () {
        $("#troubleReason").children('option').each(function () {
            if ($(this).val() == "${maintain.troubleType}") {
                $(this).attr("selected", true);
                if("${maintain.troubleType}" == "其他"){
                    $("#troubleDescription").parent().show();
                }
            }
        });
        $("#solveWay").children('option').each(function () {
            if ($(this).val() == "${maintain.solveWay}")
                $(this).attr("selected",true);
        });
        $("#solveMethod").children('option').each(function () {
            if ($(this).val() == "${maintain.solveMethod}") {
                $(this).attr("selected", true);
                if("${maintain.solveMethod}" == "其他"){
                    $("#methodDescription").parent().show();
                }
            }
        });
        $("#troubleReason").change(function () {
            var value = $(this).children('option:selected').val();
            if( value == "其他" ){
                $("#troubleDescription").parent().show();
            }
            else {
                $("#troubleDescription").parent().hide();
                $("#troubleDescription").text("");
            }
        });
        $("#solveMethod").change(function () {
            var value = $(this).children('option:selected').val();
            if( value == "其他" ){
                $("#methodDescription").parent().show();
            }else {
                $("#methodDescription").parent().hide();
                $("#methodDescription").text("");
            }
        });

    });
    function saveProject() {
        var $startTime = $("input[name='troubleTime']");
        var $endTime = $("input[name='solveTime']");
        if($endTime.val() < $startTime.val()){
            alert("开始时间必须大于结束时间");
            return;
        }
        $("#maintainRecordForm").submit();
    }
</script>
<style type="text/css">

</style>
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
            <form id="maintainRecordForm"
                  action="${ctx}/monitoringMaintain/save.action?rel=ibs_device_maintain_page&callbackType=closeCurrent"
                  method="post" class='required-validate pageForm'
                  onsubmit="return validateCallback(this, dialogAjaxDone)">

                <input type="hidden" value="${maintain.id}" name="id"/>
                <input type="hidden" value="" name="version"/>

                <div class="pageFormContent" layoutH="100">
                    <p class="nowrap">
                        <label style="width: 60px;">选择设备：</label>
                        <input name="devName" type="text" size="30" readonly="readonly"
                               class="required" value="${maintain.deviceName}" style="margin-left: 25px;">
                        <input name="devId" type="hidden" value="${maintain.deviceId}"/>
                        <input name="devCode" type="hidden" value="${maintain.deviceCode}"/>
                        <a class="btnLook"
                           href="${ctx}/monitoringMaintain/devSelectListInMaintain.action"
                           lookupGroup="" mask="true" width="800" height="500">选择设备</a>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">故障时间：</label>
                        <input type="text" name="troubleTime" class="date required" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" style="margin-left: 25px;"
                               value="<fmt:formatDate value="${maintain.troubleTime}" pattern="yyyy-MM-dd HH:mm:ss" />"/>
                        <a class="inputDateButton" href="javascript:;" style="display: none">选择</a>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">故障原因</label>
                        <select class="required" id="troubleReason" name="troubleReason" style="margin-left: 25px;">
                            <option value="请选择" selected="selected">请选择</option>
                            <option value="数据异常">数据异常</option>
                            <option value="粉尘仪故障">粉尘仪故障</option>
                            <option value="通信异常">通信异常</option>
                            <option value="数据采集终端故障">数据采集终端故障</option>
                            <option value="采集板故障">采集板故障</option>
                            <option value="变压器故障">变压器故障</option>
                            <option value="LED故障">LED故障</option>
                            <option value="其他">其他</option>
                        </select>
                    </p>
                    <p class="nowrap" style="display: none">
                        <label class="required" for="troubleDescription" style="width: 60px;">原因描述：</label>
                        <textarea id="troubleDescription" name="troubleDescription" maxlength="512" style="margin-left: 25px;width: 324px;height: 100px;">${maintain.troubleDescription}</textarea>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">责任人：</label>
                        <input name="responsible" type="text" size="30" minlength="1" maxlength="32"
                               class="required" value="${maintain.responsible}" style="margin-left: 25px;">
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">解决时间：</label>
                        <input type="text" name="solveTime" class="date required" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" style="margin-left: 25px;"
                               value="<fmt:formatDate value="${maintain.solveTime}" pattern="yyyy-MM-dd HH:mm:ss" />"/>
                        <a class="inputDateButton" href="javascript:;" style="display: none;">选择</a>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">解决途径：</label>
                        <select id="solveWay" name="solveWay" style="margin-left: 25px;">
                            <option value="现场" selected="selected">现场</option>
                            <option value="远程">远程</option>
                        </select>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">解决方法：</label>
                        <select class="required" id="solveMethod" name="solveMethod" style="margin-left: 25px;">
                            <option value="" selected="selected">请选择</option>
                            <option value="重启设备">重启设备</option>
                            <option value="替换SIM卡">替换SIM卡</option>
                            <option value="替换粉尘仪">替换粉尘仪</option>
                            <option value="替换数据采集终端">替换数据采集终端</option>
                            <option value="替换LED">替换LED</option>
                            <option value="远程校时">远程校时</option>
                            <option value="远程校准">远程校准</option>
                            <option value="其他">其他</option>
                        </select>
                    </p>
                    <p class="nowrap" style="display: none">
                        <label for="methodDescription" style="width: 60px;">方法描述：</label>
                        <textarea id="methodDescription" name="methodDescription" maxlength="512" style="margin-left: 25px;width: 324px;height: 100px;">${maintain.methodDescription}</textarea>
                    </p>
                    <p class="nowrap">
                        <label for="remark" style="width: 60px;">备注：</label>
                        <textarea id="remark" name="remark" maxlength="512" style="margin-left: 25px;width: 324px;height: 100px;">${maintain.remark}</textarea>
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
