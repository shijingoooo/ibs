<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<style type="text/css">
    .td_50 {
        width: 50px;
        text-align: center;
    }

    .td_160 {
        width: 160px;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        var companyIds = "${companyIds}";
        var ids = companyIds.split(",");
        $("input[name=platformId]").each(function (i, n) {
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
        $("input[name=platformId]").each(function (i, n) {
            if (n.value != "" && n.checked) {
                ids += n.value + ",";
                names += $("input[name=company" + n.value + "]").val() + ",";
            }
        });
        $.bringBack({
            "companyIds": ids.substring(0, ids.length - 1),
            "companyNames": names.substring(0, names.length - 1)
        });
    }
</script>
<div id="companySelectBox" layoutH="40">
    <div class="pageHeader">
        <form id="pagerForm" onsubmit="return divSearch(this, 'companySelectBox');"
              action="${ctx}/monitoringCompany/selectplatformlist.action" method="post">
            <input type="hidden" name="companyIds" value="${companyIds}"/>
            <input type="hidden" name="companyNames" value="${companyNames}"/>

            <div class="searchBar">
                <table class="searchContent">
                    <tr>
                        <td>平台名称：<input type="text" name="companyNameForLike"
                                        value="<c:out value='${companyQueryBean.companyNameForLike }'/>"/></td>
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
                <th class="td_160">平台名称</th>
                <th class="td_160">平台编号</th>
                <th class="td_160">平台地址</th>
                <th class="td_160">联系人</th>
                <th class="td_160">联系方式</th>
            </tr>
            </thead>
            <tbody id="paginationContent">
            <c:forEach var="obj" items="${companys}" varStatus="index">
                <tr target="tr_form" rel="${obj.id}">
                    <td width="30" align="center">
                        <div>
                            <div>
                                <input name="platformId" type="checkbox" value="${obj.id}"/>
                            </div>
                        </div>
                    </td>
                    <td width="120">${obj.companyName}<input name="company${obj.id}" type="hidden"
                                                             value="${obj.companyName}"/></td>
                    <td width="80">${obj.companyCode}</td>
                    <td width="60">${obj.city}</td>
                    <td width="120">${obj.contacts}</td>
                    <td width="100">${obj.telephone}</td>
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