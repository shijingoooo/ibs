<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="pageContent">
	<form method="post" id="devGroupExportFormId" action="${ctx}/monitoringDevice/exportExcel.action?rel=ibs_device_group2_page&callbackType=closeCurrent" class="pageForm required-validate">
		<div class="pageFormContent" layoutH="56">
			<h2 style="text-align: center">导出数据选项</h2>
			<p>
				<label>开始时间：</label>
				<input type="text" name="startDate" id="startD" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>结束时间：</label>
				<input type="text" name="endDate" id="endD" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<br>
			<p>
				<label>导出指标：</label>
				<input name="PM10" type="checkbox" size="30" value="PM10" checked="checked"/>PM10&nbsp;&nbsp;&nbsp;
				<input name="PM2_5" type="checkbox" size="30" value="PM2_5" checked="checked"/>PM2.5&nbsp;&nbsp;&nbsp;
				<input name="NO2" type="checkbox" size="30" value="NO2" checked="checked"/>NO2
			</p>
			<br>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="O3" type="checkbox" size="30" value="O3" checked="checked"/>O3&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="CO" type="checkbox" size="30" value="CO" checked="checked"/>CO&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="WIND_S" type="checkbox" size="30" value="WIND_S" checked="checked"/>风速
			</p>
			<br>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="NOISE" type="checkbox" size="30" value="NOISE" checked="checked"/>噪声&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="WIND_D" type="checkbox" size="30" value="WIND_D" checked="checked"/>风向&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="PA" type="checkbox" size="30" value="PA" checked="checked"/>气压
			</p>
			<br>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="TSP" type="checkbox" size="30" value="TSP" checked="checked"/>TSP&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="H2S" type="checkbox" size="30" value="H2S" checked="checked"/>H2S&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="T" type="checkbox" size="30" value="T" checked="checked"/>温度
			</p>
			<br>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="HUMIDITY" type="checkbox" size="30" value="HUMIDITY" checked="checked"/>湿度
				<%--<input name="SO2" type="checkbox" size="30" value="SO2" checked="checked"/>SO2&nbsp;&nbsp;&nbsp;&nbsp;--%>
				<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
				<%--&lt;%&ndash;<input name="TVOC" type="checkbox" size="30" value="TVOC" checked="checked"/>TVOC&nbsp;&nbsp;&nbsp;&nbsp;&ndash;%&gt;--%>
				<%--&lt;%&ndash;<input name="HCL" type="checkbox" size="30" value="HCL" checked="checked"/>HCL&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&ndash;%&gt;--%>
				<%--&lt;%&ndash;<input name="CH4S" type="checkbox" size="30" value="CH4S" checked="checked"/>CH4S&ndash;%&gt;--%>
				<%--<input name="NH3" type="checkbox" size="30" value="NH3" checked="checked"/>NH3&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
				<%--<input name="C2H6S" type="checkbox" size="30" value="C2H6S" checked="checked"/>C2H6S--%>
			</p>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="devGroupExportData()">导出</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
			<input type="hidden" name="deviceGroupId" value="${deviceGroupId}"></input>
	</form>
</div>
<script type="text/javascript">
    <%--$(document).ready(function () {--%>
        <%--if(${message!=null&&message!=""}){--%>
            <%--if ('${exist}' == 'true') {--%>
                <%--var url = encodeURI("${ctx}/monitoringData/download.action?originFileName=${originFileName}&folderPath=${folderPath}");--%>
                <%--window.location.href = encodeURI(url);--%>
            <%--}--%>
            <%--alert("${message}");--%>
        <%--}--%>
    <%--});--%>
    function devGroupExportData() {
        var startTimeStr = $("#startD").val();
        var endTimeStr = $("#endD").val();
        if (startTimeStr == null || startTimeStr == "" || endTimeStr == null || endTimeStr == "") {
            alert("请选择导出历史数据的时间范围");
            return false;
        }
        var startTimeStrTemp = startTimeStr;
        var endTimeStrTemp = endTimeStr;
        var pageNum = $("#pageNum").val();
        startTimeStrTemp = startTimeStrTemp.replace(/-/g, "/");
        var startDate = new Date(startTimeStrTemp);
        endTimeStrTemp = endTimeStrTemp.replace(/-/g, "/");
        var endDate = new Date(endTimeStrTemp);
        if (startDate > endDate) {
            alert("您选择的开始时间大于结束时间，请重新选择");
            return false;
        }
        alert($("#devGroupExportFormId"));
		$("#devGroupExportFormId").submit();
    }

</script>
