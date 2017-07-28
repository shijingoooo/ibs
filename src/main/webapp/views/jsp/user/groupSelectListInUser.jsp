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
        var deviceId = "${deviceId}";
        $("input[name=deviceId]").each(function (i, n) {
            if (n.value == deviceId) {
                $(this).attr("checked", true);
            }
        });
    });

    function backVal() {
        var id = "";
        var name = "";
        var code = "";
        $("input[name=deviceId]").each(function (i, n) {
            if (n.value != "" && n.checked) {
                id = n.value;
                name = $("input[name=devName" + n.value + "]").val();
                code = $("input[name=devCode" + n.value + "]").val();
            }
        });
        $.bringBack({
            "devId": id,
            "devName": name,
            "devCode": code
        });
    }
</script>
<div id="deviceSelectBox" layoutH="40">
    <div class="pageHeader">
        <form id="pagerForm" onsubmit="return divSearch(this, 'deviceSelectBox');"
              action="${ctx}/monitoringMaintain/devSelectListInMaintain.action" method="post">
            <input type="hidden" name="deviceId" value="${deviceId}"/>
            <input type="hidden" name="deviceName" value="${deviceName}"/>
            <div class="searchBar">
                <table class="searchContent">
                    <tr>
                        <td>组名称：<input type="text" name="devIdForLike"
                                        value=""/>
                        </td>
                        <td>组类型：<input type="text" name="devNameForLike"
                                        value=""/>
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
                <th class="td_130">组名称</th>
                <th class="td_130">组类型</th>
                <th class="td_130">设备数量</th>
                <th class="td_130">创建时间</th>
                <th class="td_130">备注</th>
            </tr>
            </thead>
            <tbody id="paginationContent" style="display:none;">
            <c:forEach var="obj" items="${devices}" varStatus="index">
                <tr target="tr_form" rel="${obj.id}">
                    <td class="td_50">
                        <div>
                            <input name="deviceId" type="radio" value="${obj.id}"/>
                        </div>
                    </td>
                    <td class="td_130">${obj.id}</td>
                    <td class="td_130">${obj.devName}</td>
                    <td class="td_130">
                        <c:choose>
                            <c:when test="${obj.devStatus==0 }">
                                否
                            </c:when>
                            <c:when test="${obj.devStatus==1 }">
                                是
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <%--不显示input--%>
                    <input name="devName${obj.id}" type="hidden" value="${obj.devName}"/>
                    <input name="devCode${obj.id}" type="hidden" value="${obj.devCode}"/>
                    <td class="td_130">
                        <c:choose>
                            <c:when test="${obj.devType==1 }">
                                扬尘
                            </c:when>
                            <c:when test="${obj.devType==2 }">
                                噪声
                            </c:when>
                            <c:when test="${obj.devType==3 }">
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
                            <c:otherwise>
                                该设备未指定类型
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