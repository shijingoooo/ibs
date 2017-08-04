<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $(".delete").click(function () {
            var dateIds = "";
            $("input[name='recordIds']").each(function (index, item) {
                if ($(item).prop("checked")) {
                    dateIds += $(item).val() + ",";
                }
            });
            var hrefVal = $(this).attr("href");
            if (hrefVal.indexOf("?") > -1) {
                hrefVal = hrefVal.substring(0, hrefVal.indexOf("?"));
            }
            $(this).attr("href", hrefVal + "?recordIds=" + dateIds.substring(0, dateIds.length - 1) + "&rel=ibs_device_maintain_page");
        });
    });

</script>
<style type="text/css">
    .devCode a{
        color: #00f;
        text-decoration: underline;
    }
</style>
<div class="pageHeader">
    告警情况描述
</div>
<div class="pageContent">
    <table class="table" width="100%" rel="maintain_list">
        <thead>
        <tr>
            <th width="30" align="center">
                <div title="" class="gridCol">
                    <input class="checkboxCtrl" type="checkbox" group="recordIds">
                </div>
            </th>
            <th width="80">告警类型</th>
            <th width="80">告警数量</th>
            <th width="80">告警类型</th>
            <th width="80">发生时间</th>
            <th width="80">处理状态</th>
            <th width="50">告警原因</th>
            <th width="50">操作</th>
        </tr>
        </thead>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td>6</td>
        <td>
        <td>
        </td>
        <%--<tbody>
        <c:forEach var="obj" items="${page.result}" varStatus="index">
            <tr target="tr_form" rel="${obj.id}">
                <td>
                    <div>
                        <input name="recordIds" type="checkbox" value="${obj.id}"/>
                    </div>
                </td>
                <td class="devCode">
                    <self:a code="ibs_device_maintain_update" name="${obj.deviceCode}"
                            parameter="?recordId=${obj.id}" style="icon" target="dialog"
                            mask="true" rel="updateRecord" width="620" height="600"></self:a>
                </td>
                <td><fmt:formatDate value="${obj.troubleTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                <td>
                    <c:choose>
                        <c:when test="${obj.troubleType =='请选择'}">
                        </c:when>
                        <c:otherwise>
                            ${obj.troubleType}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${obj.solveWay}</td>
                <td>${obj.solveMethod}</td>
                <td>${obj.responsible}</td>
                <td>${obj.remark}</td>
                <td>操作</td>
            </tr>
        </c:forEach>
        </tbody>--%>
    </table>
</div>
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);"
          action="${ctx}/monitoringAlarm/listByPage.action" method="post">
        <input type="hidden" name="pageNum" value="1"/>
        <input type="hidden" name="numPerPage" value=""/>
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>设备编号：<input type="text" name="devCodeForLike" value=""/></td>
                    <td>站点：<input type="text" name="proNameForLike" value=""></td>
                    <td>类型：
                        <select id="type" name="alarmType" >
                            <option value="" selected="selected">全部</option>
                            <option value="0">设备断电</option>
                            <option value="1">设备下线</option>
                            <option value="2">数据恒值</option>
                            <option value="3">数据0值</option>
                            <option value="4">温湿度异常</option>
                        </select>
                    </td>
                    <td>状态：
                        <select id="status" name="alarmStatus" >
                            <option value="" selected="selected">全部</option>
                            <option value="0">未处理</option>
                            <option value="1">处理中</option>
                            <option value="2">已完成</option>
                        </select>
                    </td>
                    <td>
                        开始时间：
                        <input type="text" name="startTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" style="margin-left: 25px;"/>
                        <a class="inputDateButton" href="javascript:;" style="float: right">选择</a>
                    </td>
                    <td>到
                        <input type="text" name="endTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" style="margin-left: 25px;"/>
                        <a class="inputDateButton" href="javascript:;" style="float: right">选择</a></td>
                    </td>

                    <td>
                        <button type="submit">查询</button>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<div class="pageContent">
    <%--<div class="panelBar">
        <ul class="toolBar">
            <li>
                <a id="maintainAdd" class="add" target="dialog" mask="true" width="620" height="600" title="新增记录"
                   rel="newMaintain" href="${ctx}/monitoringAlarm/savePage.action">
                    <span>新增</span>
                </a>
            </li>
            <li>
                <a id="companyDelete" class="delete" target="ajaxTodo" title="您确定要删除所选记录吗?"
                   href="${ctx}/monitoringAlarm/delete.action">
                    <span>删除</span>
                </a>
            </li>
        </ul>
    </div>--%>
    <table class="table" width="100%" layoutH="112" rel="maintain_list">
        <thead>
        <tr>
            <%--<th width="30" align="center">
                <div title="" class="gridCol">
                    <input class="checkboxCtrl" type="checkbox" group="recordIds">
                </div>
            </th>--%>
            <th width="50">设备编号</th>
            <th width="80">站点</th>
            <th width="80">告警类型</th>
            <th width="80">发生时间</th>
            <th width="80">处理状态</th>
            <th width="80">告警原因</th>
            <th width="50">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="obj" items="${page.result}" varStatus="index">
            <tr target="tr_form" rel="${obj.id}">
                <%--<td>--%>
                    <%--<div>--%>
                        <%--<input name="recordIds" type="checkbox" value="${obj.id}"/>--%>
                    <%--</div>--%>
                <%--</td>--%>
                <input name="deviceId" type="hidden" value="${obj.deviceId}">
                <td>
                    ${obj.deviceCode}
                </td>
                <td>
                    ${obj.monitoringProject.proName}
                </td>
                <td>
                    <c:choose>
                        <c:when test="${obj.alarmType =='0'}">
                            设备断电
                        </c:when>
                        <c:when test="${obj.alarmType =='1'}">
                            设备下线
                        </c:when>
                        <c:when test="${obj.alarmType =='2'}">
                            设备恒值
                        </c:when>
                        <c:when test="${obj.alarmType =='3'}">
                            设备0值
                        </c:when>
                        <c:when test="${obj.alarmType =='4'}">
                            温湿度异常
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>

                </td>
                <td><fmt:formatDate value="${obj.alarmTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                <td>
                    <c:choose>
                        <c:when test="${obj.alarmStatus =='0'}">
                            未处理
                        </c:when>
                        <c:when test="${obj.alarmStatus =='1'}">
                            处理中
                        </c:when>
                        <c:when test="${obj.alarmStatus =='2'}">
                            已完成
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${obj.alarmCause =='0'}">
                            设备故障
                        </c:when>
                        <c:when test="${obj.alarmCause =='1'}">
                            误报
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <self:a code="ibs_device_maintain_add" name="添加运维记录"
                            parameter="?deviceId=${obj.deviceId}&deviceCode=${obj.deviceCode}&alarmTime=${obj.alarmTime}" style="icon" target="dialog"
                            mask="true" rel="addRecord" width="620" height="600"></self:a></td>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <self:pager page="${page}"></self:pager>
</div>
