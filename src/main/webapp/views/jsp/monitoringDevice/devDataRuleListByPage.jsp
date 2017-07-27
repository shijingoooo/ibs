<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".add").click(function () {
            var deviceId = $("input[name='deviceId']").val();
            var hrefVal = $(this).attr("href");
            if (hrefVal.indexOf("?") > -1) {
                hrefVal = hrefVal.substring(0, hrefVal.indexOf("?"));
            }
            $(this).attr("href",hrefVal+"?deviceId="+deviceId);
        });
        $(".delete").click(function () {
            var dateIds = "";
            $("input[name='ruleIds']").each(function (index, item) {
                if ($(item).prop("checked")) {
                    dateIds += $(item).val() + ",";
                }
            });
            var hrefVal = $(this).attr("href");
            if (hrefVal.indexOf("?") > -1) {
                hrefVal = hrefVal.substring(0, hrefVal.indexOf("?"));
            }
            $(this).attr("href", hrefVal + "?ruleIds=" + dateIds.substring(0, dateIds.length - 1) + "&rel=ibs_data_calibration_page");
        });

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
    function firm(node) {
        //利用对话框返回的值 （true 或者 false）
        var msg = node.innerHTML;
        if (confirm("你确定"+msg+"吗？")) {
            var $node = $(node);
            //获取所选规则id
            var id = $node.parent().parent().parent().find("input").val();
            if(msg == '启用')
                var ruleStatus = '1';
            else
                var ruleStatus = '0';
            $.ajax({
                type : "post",
                url : "${ctx}/monitoringDevice/changeStatus.action",
                data: {
                    "id":id,
                    "status":ruleStatus
                },
                success:function(str){
                    if(str == 'success')
                        //使用id的选择器找不到该页面表单元素
                        $(".Form").submit();
                    else {
                        alert("操作失败！");
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown)
                {
                    //alert(XMLHttpRequest.readyState);
                    alert(XMLHttpRequest.status);
                    //alert(XMLHttpRequest.responseText);
                }
            });
        }
        else {
        }

    }
    function valiStr(obj){
        var str = obj.value;
        if (str.indexOf("%") > -1) {
            str = str.replaceAll("%", "");
        }
        obj.value = str;
    }

    function FileUpload(data) {
        alert(data.message);
        if (data.isSuccess)
            $("#modelSearchDeviceGroup").click();
    }
</script>
<style type="text/css">
    .devCode a{
        color: #00f;
        text-decoration: underline;
    }
</style>
<div class="pageHeader">
    <table>
        <tr>
            <td>
                <form class="Form" id="pagerForm" onsubmit="return navTabSearch(this);"
                      action="${ctx}/monitoringDevice/devDataCalibrationListByPage.action?devId=${dataCalibrationQueryBean.deviceId}" method="post">
                    <input type="hidden" name="pageNum" value="1"/>
                    <input type="hidden" name="numPerPage" value="<c:out value="${numPerPage}"></c:out>"/>
                    <input name="deviceId" type="hidden" value="${dataCalibrationQueryBean.deviceId}"/>
                        <table class="searchContent">
                            <tr>
                                <%--<td>设备编号：<input type="text" name="deviceCodeForLike"
                                                 value="<c:out value='${dataCalibrationQueryBean.deviceCodeForLike }'/>"
                                                 onblur="valiStr(this)"/></td>
                                <td>
                                    <button type="submit" id="modelSearchDeviceGroup">检索</button>
                                </td>--%>
                                <%--暂时注释--%>
                                <%--<c:if test="${ sessionScope.usertype==4 }">
                                    <td><input type="button" value="模板导出" style="cursor: pointer;"
                                               id="modelExportDeviceGroup"></td>
                                </c:if>--%>
                            </tr>
                        </table>
                </form>
            </td>
            <%--暂时注释--%>
            <%-- <c:if test="${ sessionScope.usertype==4 }">
                 <td>
                     <form onsubmit="return iframeCallback(this, FileUpload);"
                           action="${ctx}/monitoringDevice/importDeviceGroup.action" method="post"
                           enctype="multipart/form-data" id="ImportDeviceGroupForm">
                         <input type="file" name="file" id="file" style="width: 150px;cursor: pointer;">
                         <input type="button" value="数据导入" style="cursor: pointer;" id="modelImportDeviceGroup"></form>
                 </td>
             </c:if>--%>
        </tr>
    </table>

</div>
</div>
<div class="pageContent">
    <div class="panelBar">
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
    </div>
    <table class="table" width="100%" layoutH="112" rel="device_group_list">
        <thead>
        <tr>
            <th width="30" align="center">
                <div title="" class="gridCol">
                    <input class="checkboxCtrl" type="checkbox" group="ruleIds">
                </div>
            </th>
            <th width="120">设备编号</th>
            <th width="120">指标</th>
            <th width="120">单位</th>
            <th width="120">下限</th>
            <th width="120">上限</th>
            <th width="120">校正系数</th>
            <th width="120">操作</th>
        </tr>
        </thead>
        <tbody>
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
                <td>
                    <c:choose>
                        <c:when test="${obj.type == 'calibration_tsp'}">
                            TSP
                        </c:when>
                        <c:when test="${obj.type == 'calibration_two_pm'}">
                            PM2.5
                        </c:when>
                        <c:when test="${obj.type == 'calibration_ten_pm'}">
                            PM10
                        </c:when>
                        <c:when test="${obj.type == 'calibration_02'}">
                            SO₂
                        </c:when>
                        <c:when test="${obj.type == 'calibration_NO2'}">
                            NO₂
                        </c:when>
                        <c:when test="${obj.type == 'calibration_03'}">
                            O₃
                        </c:when>
                        <c:when test="${obj.type == 'calibration_04'}">
                            CO
                        </c:when>
                        <c:otherwise>
                            ${obj.type}
                        </c:otherwise>
                    </c:choose>
                </td>
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
        </tbody>
    </table>
    <self:pager page="${page}"></self:pager>
</div>