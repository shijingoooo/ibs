<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<style type="text/css">
    .td_50 {
        width: 50px;
        text-align: center;
    }

    .td_130 {
        width: 130px;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        var deviceIds = "${deviceIds}";
        var ids = deviceIds.split(",");
        $("input[name=deviceId]").each(function (i, n) {
            for (var i = 0; i < ids.length; i++) {
                if (n.value == ids[i]) {
                    $(this).attr("checked", true);
                }
            }
        });
    });

    function backVal() {
        var ids = "";
        var names = "";
        $("input[name=deviceId]").each(function (i, n) {
            if (n.value != "" && n.checked) {
                ids += n.value + ",";
                names += $("input[name=dev" + n.value + "]").val() + ",";
            }
        });
        $.bringBack({
            "deviceIds": ids.substring(0, ids.length - 1),
            "deviceNames": names.substring(0, names.length - 1)
        });
    }
</script>
<div id="deviceSelectBox" layoutH="40">
    <div class="pageHeader">
        <form id="pagerForm" onsubmit="return divSearch(this, 'deviceSelectBox');"
              action="${ctx}/monitoringDevice/devSelectListInGroup.action" method="post">
            <input type="hidden" name="deviceIds" value="${deviceIds}"/>
            <input type="hidden" name="deviceNames" value="${deviceNames}"/>

            <div class="searchBar">
                <table class="searchContent">
                    <tr>
                        <%--<td>设备名称：<input type="text" name="devNameForLike"
                                        value="<c:out value='${deviceQueryBean.devNameForLike }'/>"/>
                            <input type="hidden" name="deviceGroupIdForOr"
                                   value="${deviceQueryBean.deviceGroupIdForOr}">
                        </td>--%>
                        <td>设备编号：<input type="text" name="devCodeForLike"
                                        value="<c:out value='${deviceQueryBean.devCodeForLike }'/>"/>
                        </td>
                        <td>不属于组类型：<input type="text" name="notBelongTypeForLike"
                                        value="${deviceQueryBean.notBelongTypeForLike}"/>
                        </td>
                        <td>属于组：<input type="text" name="devGroupNameForLike"
                                        value="${deviceQueryBean.devGroupNameForLike}"/>
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
        <div class="panelBar" width="100%">
            <ul class="toolBar">
                <li>
                    <a class="add" href="javascript:backVal();"><span>确定</span></a>
                </li>
            </ul>
        </div>
        <table class="table" width="100%">
            <thead>
            <tr>
                <th class="td_50"></th>
                <th class="td_130">设备编号</th>
                <th class="td_130">所属设备组</th>
                <th class="td_130">设备组类型</th>
                <th class="td_130">是否在线</th>
                <th class="td_130">传感器类型</th>
            </tr>
            </thead>
            <tbody id="paginationContent" style="display:none;">
            <c:forEach var="obj" items="${devices}" varStatus="index">
                <tr target="tr_form" rel="${obj.id}">
                    <td class="td_50">
                        <div>
                            <input name="deviceId" type="checkbox" value="${obj.id}"/>
                        </div>
                    </td>
                    <td class="td_130">${obj.devCode}</td>
                    <%--不显示设备名--%>
                    <input name="dev${obj.id}" type="hidden" value="${obj.devName}"/>

                    <td class="td_130">
                        <c:forEach var="group" items="#{obj.belongGroups}" varStatus="index">
                            ${group}
                        </c:forEach>
                    </td>
                    <td class="td_130">${obj.devGroupType}</td>
                    <td class="td_130">
                        <c:choose>
                            <c:when test="${obj.devStatus=='0' }">
                                下线
                            </c:when>
                            <c:otherwise>
                                在线
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="td_130">
                        <c:choose>
                            <c:when test="${obj.devType == 2}">
                                噪声
                            </c:when>
                            <c:when test="${obj.devType == 3}">
                                视频
                            </c:when>
                            <c:otherwise>
                                扬尘
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div id="pagination" style="float:right;">
        <script src="${ctx}/js/pagination.js"></script>
    </div>
</div>
</div>