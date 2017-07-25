<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $(".delete").click(function () {
            var dateIds = "";
            $("input[name='companyIds']").each(function (index, item) {
                if ($(item).prop("checked")) {
                    dateIds += $(item).val() + ",";
                }
            });
            var hrefVal = $(this).attr("href");
            if (hrefVal.indexOf("?") > -1) {
                hrefVal = hrefVal.substring(0, hrefVal.indexOf("?"));
            }
            $(this).attr("href", hrefVal + "?companyIds=" + dateIds.substring(0, dateIds.length - 1) + "&rel=ibs_company_page");
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
          action="${ctx}/monitoringCompany/listByPage.action" method="post">
        <input type="hidden" name="pageNum" value="1"/>
        <input type="hidden" name="numPerPage" value="<c:out value="${numPerPage}"></c:out>"/>

        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>厂商名称：<input type="text" name="companyNameForLike"
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
    <div class="panelBar">
        <ul class="toolBar">
            <li>
                <%--<self:a code="ibs_company_page_add" width="620" height="450"--%>
                <%--name="新增" title="新增厂商" style="add" target="dialog" mask="true"--%>
                <%--rel="newcompany" parameter=""></self:a>--%>
                <a id="companyAdd" class="add" target="dialog" mask="true" width="620" height="450" title="新增厂商"
                   rel="newcompany" href="${ctx}/monitoringCompany/savePage.action">
                    <span>新增</span>
                </a>
            </li>
            <li>
                <c:choose>
                    <c:when test="${sessionScope.usertype==3 or sessionScope.usertype==4 or sessionScope.usertype==12}">
                        <a id="companyDelete" class="delete" target="ajaxTodo" title="您确定要删除厂商信息吗?"
                           href="${ctx}/monitoringCompany/delete.action">
                            <span>删除</span>
                        </a>
                    </c:when>
                </c:choose>
            </li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="112" rel="company_list">
        <thead>
        <tr>
            <th width="30" align="center">
                <div title="" class="gridCol">
                    <input class="checkboxCtrl" type="checkbox" group="companyIds">
                </div>
            </th>
            <th width="120">厂商名称</th>
            <th width="80">厂商编号</th>
            <th width="80">厂商类型</th>
            <th width="60">所在城市</th>
            <th width="120">联系人</th>
            <th width="100">联系电话</th>
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
                <td class="companyName">
                    <self:a code="ibs_company_page_update" name="${obj.companyName}"
                            parameter="?companyId=${obj.id}" style="icon" target="dialog"
                            mask="true" rel="newcompany" width="560" height="380"></self:a>
                </td>
                <td>${obj.companyCode}</td>
                <td>
                    <c:choose>
                        <c:when test="${obj.type==0}">
                            设备供应商
                        </c:when>
                        <c:when test="${obj.type==1}">
                            平台服务商
                        </c:when>
                        <c:when test="${obj.type==2}">
                            APP服务商
                        </c:when>
                        <c:otherwise>
                            为指定
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${obj.city}</td>
                <td>${obj.contacts}</td>
                <td>${obj.telephone}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <self:pager page="${page}"></self:pager>
</div>