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
<div class="pageContent sortDrag" selector="h1" layoutH="42">
    <div class="panel" defH="150">
        <h1>告警情况描述</h1>
        <div>
            <table class="list" width="98%">
                <thead>
                <tr>
                    <th width="50">告警类型</th>
                    <th width="50">告警数量</th>
                    <th width="50">${week[0]}</th>
                    <th width="50">${week[1]}</th>
                    <th width="50">${week[2]}</th>
                    <th width="50">${week[3]}</th>
                    <th width="50">${week[4]}</th>
                    <th width="50">${week[5]}</th>
                    <th width="50">${week[6]}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${countMap}">
                    <tr>
                        <td>${item.key}</td>
                        <td>${item.value[7]}</td>
                        <td>${item.value[0]}</td>
                        <td>${item.value[1]}</td>
                        <td>${item.value[2]}</td>
                        <td>${item.value[3]}</td>
                        <td>${item.value[4]}</td>
                        <td>${item.value[5]}</td>
                        <td>${item.value[6]}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="panel" defH="540">
        <h1>告警情况</h1>
        <div>
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
            <table class="list" width="98%">
                <thead>
                <tr>
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
                                    mask="true" rel="addRecord" width="620" height="600"></self:a>
                            |<self:a code="ibs_alarm_handle" name="处理"
                                     parameter="?id=${obj.id}" style="icon" target="dialog"
                                     mask="true" rel="handleRecord" width="400" height="350"></self:a>

                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${fn:length(page.result)<20}">
                    <c:forEach begin="${fn:length(page.result)}" end="19"
                               step="1">
                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <self:pager page="${page}"></self:pager>
        </div>
    </div>
</div>



