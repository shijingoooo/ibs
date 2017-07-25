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
        var typeName = "";
        var typeId = "";
        var code = "";
        $("input[name=deviceId]").each(function (i, n) {
            if (n.value != "" && n.checked) {
                id = n.value;
                name = $("input[name=devName" + n.value + "]").val();
                typeId = $("input[name=devType" + n.value + "]").val();
                code = $("input[name=devCode" + n.value + "]").val();
                switch (typeId)
                {
                    case "1":
                        typeName = "扬尘";
                        break;
                    case "2":
                        typeName = "噪声";
                        break;
                    case "3":
                        typeName = "视频";
                        break;
                    default:
                        typeName = "";
                }
            }
        });
        $.bringBack({
            "deviceId": id,
            "deviceName": name,
            "deviceType": typeId,
            "deviceTypeName": typeName,
            "deviceCode": code
        });
        if(typeId == "1" || typeId == "2")
        {
            if( $("select",window.parent.document).length == 0) {
                $(".indicator", window.parent.document).after("<select class='indicator' name='indicator' style='margin-left: 25px;'><option value='PM2.5'>PM2.5</option><option value='PM10'>PM10</option><option value='TSP'>TSP</option></select><label class='unit'>单位：ug/m3</label>");
            }
            else{
                $("select",window.parent.document).next().remove();
                $("select",window.parent.document).remove();
                $(".indicator", window.parent.document).after("<select class='indicator' name='indicator' style='margin-left: 25px;'><option value='PM2.5'>PM2.5</option><option value='PM10'>PM10</option><option value='TSP'>TSP</option></select><label class='unit'>单位：ug/m3</label>");
            }
        }
        if(typeId == "3")
        {
            if( $("select",window.parent.document).length == 0) {
                $(".indicator", window.parent.document).after("<select class='indicator' name='indicator' style='margin-left: 25px;'><option value='PM2.5'>PM2.5</option><option value='PM10'>PM10</option><option value='SO2'>SO2</option><option value='NO3'>NO3</option><option value='O3'>O3</option><option value='CO'>CO</option></select><label class='unit'>单位：ug/m3</label>");
            }
            else{
                $("select",window.parent.document).next().remove();
                $("select",window.parent.document).remove();
                $(".indicator", window.parent.document).after("<select class='indicator' name='indicator' style='margin-left: 25px;'><option value='PM2.5'>PM2.5</option><option value='PM10'>PM10</option><option value='SO2'>SO2</option><option value='NO3'>NO3</option><option value='O3'>O3</option><option value='CO'>CO</option></select><label class='unit'>单位：ug/m3</label>");
            }
        }
    }
</script>
<div id="deviceSelectBox" layoutH="40">
    <div class="pageHeader">
        <form id="pagerForm" onsubmit="return divSearch(this, 'deviceSelectBox');"
              action="${ctx}/monitoringDevice/devSelectListInRule.action" method="post">
            <input type="hidden" name="deviceId" value="${deviceId}"/>
            <input type="hidden" name="deviceName" value="${deviceName}"/>

            <div class="searchBar">
                <table class="searchContent">
                    <tr>
                        <td>设备编号：<input type="text" name="deviceCodeForLike"
                                        value="<c:out value='${dataCalibrationQueryBean.deviceCodeForLike}'/>"/>
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
                <th class="td_130">设备名称</th>
                <th class="td_130">设备类型</th>
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
                    <td class="td_130">${obj.devCode}</td>
                    <td class="td_130">${obj.devName}</td>
                    <%--不显示input--%>
                    <input name="devName${obj.id}" type="hidden" value="${obj.devName}"/>
                    <input name="devType${obj.id}" type="hidden" value="${obj.devType}"/>
                    <input name="devCode${obj.id}" type="hidden" value="${obj.devCode}"/>
                    <td class="td_130">
                        <c:choose>
                            <c:when test="${obj.devType==2 }">
                                噪声
                            </c:when>
                            <c:when test="${obj.devType==3 }">
                                视频
                            </c:when>
                            <c:when test="${obj.devType==1 }">
                                扬尘
                            </c:when>
                            <c:otherwise>
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