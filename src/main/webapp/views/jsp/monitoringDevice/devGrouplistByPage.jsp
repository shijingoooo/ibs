<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.form.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".delete").click(function () {
            var dateIds = "";
            $("input[name='deviceGroupIds']").each(function (index, item) {
                if ($(item).prop("checked")) {
                    dateIds += $(item).val() + ",";
                }
            });
            var hrefVal = $(this).attr("href");
            if (hrefVal.indexOf("?") > -1) {
                hrefVal = hrefVal.substring(0, hrefVal.indexOf("?"));
            }
            $(this).attr("href", hrefVal + "?deviceGroupIds=" + dateIds.substring(0, dateIds.length - 1) + "&rel=ibs_device_group2_page");
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
    .groupName a{
        color: #00f;
        text-decoration: underline;
    }
</style>
<div class="pageHeader">
    <table>
        <tr>
            <td>
                <form id="pagerForm" onsubmit="return navTabSearch(this);"
                      action="${ctx}/monitoringDevice/devGrouplistByPage.action" method="post">
                    <input type="hidden" name="pageNum" value="1"/> <input type="hidden"
                                                                           name="numPerPage"
                                                                           value="<c:out value="${numPerPage}"></c:out>"/>

                    <div class="searchBar">
                        <table class="searchContent">
                            <tr>
                                <td>分组名称：<input type="text" name="devGroupNameForLike"
                                                 value="<c:out value='${deviceGroupQueryBean.devGroupNameForLike }'/>"
                                                 onblur="valiStr(this)"/></td>
                                <td>
                                    <button type="submit" id="modelSearchDeviceGroup">查询</button>
                                </td>
                                <%--暂时注释--%>
                                <%--<c:if test="${ sessionScope.usertype==4 }">
                                    <td><input type="button" value="模板导出" style="cursor: pointer;"
                                               id="modelExportDeviceGroup"></td>
                                </c:if>--%>
                            </tr>
                        </table>
                    </div>
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
                <%--<self:a code="ibs_device_group_page_add" width="620" height="450"--%>
                <%--name="新增" title="新增设备" style="add" target="dialog" mask="true"--%>
                <%--rel="newdeviceGroup" parameter=""></self:a>--%>
                <a id="deviceGroupAdd" class="add" target="dialog" mask="true" width="620" height="400" title="新增设备组"
                   rel="newdeviceGroup" href="${ctx}/monitoringDevice/devSaveGroupPage.action">
                    <span>新增</span>
                </a>
            </li>
            <li>
                <a id="deviceGroupDelete" class="delete" target="ajaxTodo" title="您确定要删除设备信息吗?"
                   href="${ctx}/monitoringDevice/devDeleteGroup.action">
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
                    <input class="checkboxCtrl" type="checkbox" group="deviceGroupIds">
                </div>
            </th>
            <th width="120">分组名称</th>
            <th width="120">分组类别</th>
            <th width="120">分组说明</th>
            <th width="120">设备数量</th>
            <th width="120">创建时间</th>
            <th width="120">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="obj" items="${page.result}" varStatus="index">
            <tr target="tr_form" rel="${obj.id}">
                <td>
                    <div>
                        <input name="deviceGroupIds" type="checkbox" value="${obj.id}"/>
                    </div>
                </td>
                <td class="groupName">
                    <self:a code="ibs_device_group2_page_update" name="${obj.groupDevName}"
                            parameter="?deviceGroupId=${obj.id}" style="icon" target="dialog"
                            mask="true" rel="newdeviceGroup" width="560" height="380"></self:a>
                </td>
                <td>${obj.groupDevType}</td>
                <td>${obj.groupDevDescription}</td>
                <td>${obj.devCount}</td>
                <td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                <td>导出数据</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <self:pager page="${page}"></self:pager>
</div>