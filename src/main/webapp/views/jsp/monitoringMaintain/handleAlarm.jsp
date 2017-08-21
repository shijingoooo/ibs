<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(function () {

        $(".alarmCause").each(function () {
            if ($(this).val() == "${alarmRecord.alarmCause}")
                $(this).attr("checked",true);
        });
        $(".alarmStatus").each(function () {
            if ($(this).val() == "${alarmRecord.alarmStatus}")
                $(this).attr("checked",true);
        });

    });
    function save() {
        $("#alarmHandleForm").submit();
    }
</script>
<style type="text/css">

</style>
<div class="tabs">
    <div class="tabsHeader">
        <div class="tabsHeaderContent">
            <ul>
                <li class="selected">
                    <a href="javascript:;"><span>处理告警</span></a>
                </li>
            </ul>
        </div>
    </div>
    <div class="tabsContent">
        <div id="base" style="display: block;">
            <form id="alarmHandleForm"
                  action="${ctx}/monitoringAlarm/handle.action?rel=ibs_alarm&callbackType=closeCurrent"
                  method="post" class='required-validate pageForm'
                  onsubmit="return validateCallback(this, dialogAjaxDone)">

                <input type="hidden" value="${alarmRecord.id}" name="id"/>
                <input type="hidden" value="" name="version"/>

                <div class="pageFormContent" layoutH="100">
                    <p class="nowrap">
                        <label style="width: 60px;">告警设备：</label>
                        <label>${alarmRecord.deviceCode}</label>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">告警类型：</label>
                        <c:choose>
                            <c:when test="${alarmRecord.alarmType == 0}">
                                <label>设备断电</label>
                            </c:when>
                            <c:when test="${alarmRecord.alarmType == 1}">
                                <label>设备下线</label>
                            </c:when>
                            <c:when test="${alarmRecord.alarmType == 2}">
                                <label>数据恒值</label>
                            </c:when>
                            <c:when test="${alarmRecord.alarmType == 3}">
                                <label>数据0值</label>
                            </c:when>
                            <c:when test="${alarmRecord.alarmType == 4}">
                                <label>温湿度异常</label>
                            </c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">告警原因</label>
                        <label style="width: 70px;"><input class="alarmCause" name="alarmCause" type="radio" value="0" />设备故障 </label>
                        <label style="width: 60px;"><input class="alarmCause" name="alarmCause" type="radio" value="1" />误报 </label>
                        <%--<input name="alarmCause" type="hidden" value="">--%>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">运维记录：</label>
                        <c:choose>
                            <c:when test="${alarmRecord.maintainRecord.size() == 0}">
                                <label>未填写</label>
                            </c:when>
                            <c:otherwise>
                                <label>已填写</label>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">处理状态：</label>
                        <label style="width: 70px;"><input class="alarmStatus" name="alarmStatus" type="radio" value="0" />未处理 </label>
                        <label style="width: 60px;"><input class="alarmStatus" name="alarmStatus" type="radio" value="1" />处理中</label>
                        <label style="width: 60px;"><input class="alarmStatus" name="alarmStatus" type="radio" value="2" />已完成</label>
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
            <button id="save" type="button" onclick="save()">保存</button>
        </div>
    </div>
</div>
