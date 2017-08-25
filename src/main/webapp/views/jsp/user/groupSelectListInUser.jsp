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
        var groupIds = "${groupIds}";
        var ids = groupIds.split(",");
        $("input[name='groupId']").each(function (i, n) {
            for (var i = 0; i < ids.length; i++) {
                if (n.value == ids[i]) {
                    $(this).attr("checked", true);
                }
            }
        });
    });

    function backVal(clickCount) {
        var ids = "";
        var names = "";
        $("input[name='groupId']").each(function (i, n) {
            if (n.value != "" && n.checked) {
                ids += n.value+",";
                names += $("input[name=groupName" + n.value + "]").val()+",";
            }
            /*alert(ids);
            alert(names);*/
        });
        if (clickCount == "1"){
            //保存设备组
            $.ajax({
                type : "post",
                url : "${ctx}/user/saveGroupInUserList.action",
                data: {
                    "userId":"${userId}",
                    "groupIds":ids
                },
                success:function(str){
                    var $form = $(".userManagement", window.parent.document);
                    $form.submit();
                },
                error:function(XMLHttpRequest, textStatus, errorThrown)
                {
                    //alert(XMLHttpRequest.readyState);
                    alert(XMLHttpRequest.status);
                    //alert(XMLHttpRequest.responseText);
                }
            });
            $.bringBack();
        }else{
            $.bringBack({
                "groupIds": ids.substring(0, ids.length - 1),
                "groupNames": names.substring(0, names.length - 1),
            });
        }

    }
</script>
<div id="deviceSelectBox" layoutH="40">
    <div class="pageHeader">
        <form id="pagerForm" onsubmit="return divSearch(this, 'deviceSelectBox');"
              action="${ctx}/user/groupSelectListInUser.action" method="post">
            <div class="searchBar">
                <table class="searchContent">
                    <tr>
                        <td>组名称：<input type="text" name="devGroupNameForLike"
                                        value=""/>
                        </td>
                        <td>组类型：<input type="text" name="devGroupTypeForLike"
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
                    <a class="add" href="javascript:backVal(${clickCount});"><span>确定</span></a>
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
            <c:forEach var="obj" items="${groups}" varStatus="index">
                <tr target="tr_form" rel="${obj.id}">
                    <td class="td_50">
                        <div>
                            <input name="groupId" type="checkbox" value="${obj.id}"/>
                        </div>
                    </td>
                    <%--不显示分组名--%>
                    <input name="groupName${obj.id}" type="hidden" value="${obj.groupDevName}"/>
                    <td>${obj.groupDevName}</td>
                    <td>${obj.groupDevType}</td>
                    <td>${obj.devCount}</td>
                    <td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td>${obj.groupDevDescription}</td>
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