<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.form.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".delete").click(function(){
            var dateIds = "";
            $("input[name='deviceIds']").each(function(index, item) {
                if ($(item).prop("checked")) {
                    dateIds += $(item).val()+",";
                }
            });
            var hrefVal = $(this).attr("href");
            if(hrefVal.indexOf("?")>-1){
                hrefVal = hrefVal.substring(0,hrefVal.indexOf("?"));
            }
            $(this).attr("href",hrefVal+"?deviceIds="+dateIds.substring(0,dateIds.length-1)+"&rel=ibs_device_page");
        });


        $("#modelExportDevice").click(function () {
            var dateIds = "";
            $("input[name='deviceIds']").each(function(index, item) {
                if ($(item).prop("checked")) {
                    dateIds += $(item).val()+",";
                }
            });
            if (dateIds == ""){
                alert("请选择要导出的数据！");
            } else{
                var hrefVal = "${ctx}/monitoringDevice/downloadDevice.action";
                location.href = hrefVal+"?deviceIds="+dateIds.substring(0,dateIds.length-1)+"&rel=ibs_device_page";
            }
        });

        $("#modelImportDevice").click(function () {
            $("#ImportDeviceForm").submit();
        });
    });
    function clickA(node) {
        var $node = $(node);
        $node.parent().children("#maintain").click();

    }
    function valiStr(obj){
        var str = obj.value;
        if(str.indexOf("%")>-1){
            str = str.replaceAll("%","");
        }
        obj.value = str;
    }

    function FileUpload(data) {
        alert(data.message);
        if (data.isSuccess)
            $("#modelSearchDevice").click();
    }
</script>
<style type="text/css">
    .devCode a{
        color: #00f;
        text-decoration: underline;
    }
    .maintainCount a{
        color: #00f;
        text-decoration: underline;
    }
    td{
        position: relative;
    }
    .count{
        color: #00f;
        text-decoration: underline;
        cursor: pointer;
        position: absolute;
        top:25%;
    }
</style>
<div class="pageHeader">
    <table>
        <tr>
            <td>
                <form id="pagerForm" onsubmit="return navTabSearch(this);"
                      action="${ctx}/monitoringDevice/listByPage.action" method="post">
                    <input type="hidden" name="pageNum" value="1" />
                    <input type="hidden" name="numPerPage" value="<c:out value="${numPerPage}"></c:out>" />

                    <div class="searchBar">
                        <table class="searchContent">
                            <tr>
                                <td>设备编号：<input type="text" name="devCodeForLike"
                                                 value="<c:out value='${deviceQueryBean.devCodeForLike }'/>" onblur="valiStr(this)"/></td>
                                <td>站点名称：<input type="text" name="devProNameForLike"
                                                 value="<c:out value='${deviceQueryBean.devProNameForLike}'/>" onblur="valiStr(this)"/></td>
                                <td>设备组：<input type="text" name="devGroupNameForLike"
                                                 value="<c:out value='${deviceQueryBean.devGroupNameForLike }'/>" onblur="valiStr(this)"/></td>
                                <td>
                                    <label><input name="devStatus" type="checkbox" value="1" />在线 </label>
                                    <label><input name="devStatus" type="checkbox" value="0" />离线 </label>
                                </td>
                                <td><button type="submit" id="modelSearchDevice">查询</button></td>
                                <td><button type="button" id="">导出数据</button></td>
                            </tr>
                        </table>
                    </div>
                </form>
            </td>
            <%--暂时注释--%>
            <%-- <c:if test="${ sessionScope.usertype==4 }">
                 <td>
                     <form onsubmit="return iframeCallback(this, FileUpload);"
                           action="${ctx}/monitoringDevice/importDevice.action" method="post"
                           enctype="multipart/form-data" id="ImportDeviceForm" >
                         <input type="file" name="file" id="file" style="width: 150px;cursor: pointer;">
                         <input type="button" value="数据导入" style="cursor: pointer;" id="modelImportDevice"></form>
                 </td>
             </c:if>--%>
        </tr>
    </table>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <c:if test="${sessionScope.usertype == 1}">
                <li>
                    <a id="deviceAdd" class="add" target="dialog" mask="true" width="620" height="600" title="新增传感器" rel="newdevice" href="${ctx}/monitoringDevice/savePage.action">
                        <span>新增</span>
                    </a>
                </li>
            </c:if>
            <li>
                <a id="deviceDelete" class="delete" target="ajaxTodo" title="您确定要删除传感器信息吗?" href="${ctx}/monitoringDevice/delete.action">
                    <span>删除</span>
                </a>
            </li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="112" rel="device_list">
        <thead>
        <tr>
            <th width="30" align="center">
                <div title="" class="gridCol">
                    <input class="checkboxCtrl" type="checkbox" group="deviceIds">
                </div></th>
            <th width="100">设备编号</th>
            <th width="80">维护次数</th>
            <th width="100">是否在线</th>
            <th width="120">设备组</th>
            <th width="100">传感器类型</th>
            <th width="140">厂商</th>
            <th width="180">站点</th>
            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="obj" items="${page.result}" varStatus="index">
            <tr target="tr_form" rel="${obj.id}">
                <td><div>
                    <input name="deviceIds" type="checkbox" value="${obj.id}" />
                </div>
                </td>
                <td class="devCode">
                    <c:if test="${obj.devCode != null and obj.devCode !=''}">
                        <self:a code="ibs_device_page_update" name="${obj.devCode}"
                                parameter="?deviceId=${obj.id}" style="icon" target="dialog"
                                mask="true" rel="newdevice" width="620" height="600"></self:a>
                    </c:if>
                </td>
                <td class="maintainCount">
                    <a id="maintain" href="<c:out value='${ctx}/monitoringMaintain/listByPage.action?devId=${obj.id}'/>"
                       target="navTab" rel="<c:out value='ibs_device_maintain_page'/>" style="display: none">运维记录</a>
                    <span class="count" onclick="clickA(this)">${obj.maintainCount}</span>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${obj.devStatus=='0' }">
                            下线
                        </c:when>
                        <c:otherwise>
                            在线
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:if test="${obj.belongGroups.size() > 0}">
                        ${obj.belongGroups}
                    </c:if>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${obj.devType == 1}">
                            扬尘
                        </c:when>
                        <c:when test="${obj.devType == 2}">
                            噪声
                        </c:when>
                        <c:when test="${obj.devType == 3}">
                            视频
                        </c:when>
                        <c:when test="${obj.devType == 4}">
                            扬尘噪声
                        </c:when>
                        <c:when test="${obj.devType == 5}">
                            AQI
                        </c:when>
                        <c:when test="${obj.devType == 6}">
                            VOC
                        </c:when>
                        <c:when test="${obj.devType == 7}">
                            扬尘噪声（基础）
                        </c:when>
                        <c:otherwise>
                            该设备未指定类型
                        </c:otherwise>
                    </c:choose>

                </td>
                <td>
                    ${obj.monitoringCompany.companyName}
                </td>
                <td>
                    ${obj.monitoringProject.proName}
                </td>
                <td>
                    <%--<a id="statistics" href="<c:out value='${ctx}/monitoringDevice/devStatisticsDataListByPage.action?devId=${obj.id}'/>"
                       target="navTab" rel="<c:out value='ibs_statistics_data_page'/>">统计数据</a>
                    |--%>
                    <a id="checkRule" href="<c:out value='${ctx}/monitoringDevice/devDataCalibrationListByPage.action?devId=${obj.id}'/>"
                       target="navTab" rel="<c:out value='ibs_data_calibration_page'/>">校准</a>

                    <%--|控制--%>
                </td>

                    <%--<td>
                    <self:a code="ibs_device_oper_restart"
                            name="重启" title="您确定将传感器重启吗?" target="ajaxTodo" parameter="?deviceId=${obj.id }&commandId=1&rel=ibs_device_page"></self:a>
                    | <self:a code="ibs_data_page"
                              name="查看历史数据" target="navTab" parameter="?deviceId=${obj.id }&rel=ibs_device_data_page"></self:a>
                </td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <self:pager page="${page}"></self:pager>
</div>