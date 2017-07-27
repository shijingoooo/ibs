<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $(".delete").click(function () {
            var dateIds = "";
            $("input[name='maintainIds']").each(function (index, item) {
                if ($(item).prop("checked")) {
                    dateIds += $(item).val() + ",";
                }
            });
            var hrefVal = $(this).attr("href");
            if (hrefVal.indexOf("?") > -1) {
                hrefVal = hrefVal.substring(0, hrefVal.indexOf("?"));
            }
            $(this).attr("href", hrefVal + "?maintainIds=" + dateIds.substring(0, dateIds.length - 1) + "&rel=ibs_device_maintain_page");
        });
    });

</script>
<style type="text/css">
    .companyName a{
        color: #00f;
        text-decoration: underline;
    }
</style>
<div class="pageHeader">
    <form id="pagerForm" onsubmit="return navTabSearch(this);"
          action="${ctx}/monitoringMaintain/listByPage.action" method="post">
        <input type="hidden" name="pageNum" value="1"/>
        <input type="hidden" name="numPerPage" value=""/>
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>设备编号：<input type="text" name="devCodeForLike" value=""/></td>

                    <td>
                        开始时间：
                        <input type="text" name="startTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" style="margin-left: 25px;"/>
                        <a class="inputDateButton" href="javascript:;" style="float: right">选择</a>
                    </td>

                    <td>结束时间：
                        <input type="text" name="endTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" style="margin-left: 25px;"/>
                        <a class="inputDateButton" href="javascript:;" style="float: right">选择</a></td>
                    <td>
                        <button type="submit">查询</button>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li>
                <a id="maintainAdd" class="add" target="dialog" mask="true" width="620" height="600" title="新增记录"
                   rel="newMaintain" href="${ctx}/monitoringMaintain/savePage.action">
                    <span>新增</span>
                </a>
            </li>
            <li>
                <c:choose>
                    <c:when test="${sessionScope.usertype==3 or sessionScope.usertype==4 or sessionScope.usertype==12}">
                        <a id="companyDelete" class="delete" target="ajaxTodo" title="您确定要删除所选记录吗?"
                           href="${ctx}/monitoringMaintain/delete.action">
                            <span>删除</span>
                        </a>
                    </c:when>
                </c:choose>
            </li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="112" rel="maintain_list">
        <thead>
        <tr>
            <th width="30" align="center">
                <div title="" class="gridCol">
                    <input class="checkboxCtrl" type="checkbox" group="maintainIds">
                </div>
            </th>
            <th width="100">设备编号</th>
            <th width="80">故障时间</th>
            <th width="80">故障原因</th>
            <th width="60">解决途径</th>
            <th width="120">解决办法</th>
            <th width="50">责任人</th>
            <th width="100">备注</th>
            <th width="50">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="obj" items="${page.result}" varStatus="index">
            <tr target="tr_form" rel="${obj.id}">
                <td>
                    <div>
                        <input name="companyIds" type="checkbox" value="${obj.id}"/>
                    </div>
                </td>
                <td class="devCode">
                    <self:a code="ibs_device_maintain_update" name="${obj.deviceCode}"
                            parameter="?recordId=${obj.id}" style="icon" target="dialog"
                            mask="true" rel="updateRecord" width="620" height="600"></self:a>
                </td>
                <td><fmt:formatDate value="${obj.troubleTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                <td>${obj.troubleType}</td>
                <td>${obj.solveWay}</td>
                <td>${obj.solveMethod}</td>
                <td>${obj.responsible}</td>
                <td>${obj.remark}</td>
                <td>操作</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <self:pager page="${page}"></self:pager>
</div>
