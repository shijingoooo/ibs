<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<style type="text/css">
    .trCtrl-3 {
        display: none;
    }
</style>
<script type="text/javascript">

    $(document).ready(function () {
    });

    function saveProject() {
        var menuIdStr = '';
        $("input[name='menuIds']").each(function (index, item) {
            if ($(item).prop("checked")) {
                menuIdStr += $(item).val() + ",";
            }
        });
        console.log(menuIdStr);
        $("#menuIdStr").val(menuIdStr);
        $("#deviceForm").submit();

    }


</script>
<div class="tabs">
    <div class="tabsHeader">
        <div class="tabsHeaderContent">
            <ul>
                <li class="selected">
                    <a href="javascript:;"><span>基本信息</span></a>
                </li>
            </ul>
        </div>
    </div>
    <div class="tabsContent">
        <div id="base" style="display: block;">
            <form id="deviceForm"
                  action="${ctx}/role/saveRoleAndMenu.action?rel=ibs_user_page&callbackType=closeCurrent"
                  method="post" class='required-validate pageForm'
                  onsubmit="return validateCallback(this, dialogAjaxDone)"
            >

                <input type="hidden" name="menuIdStr" id="menuIdStr"/>
                <input type="hidden" value="<c:out value='${roleId}'/>" name="roleId" id="roleId"/>
                <div class="pageFormContent" layoutH="100">
                    <table>
                        <c:forEach var="menu" items="${menuList}" varStatus="index">
                            <c:if test="${menu.menuLevel eq 1}">
                                <tr>
                                    <td style="border-top: 10px solid; border-color: transparent;">
                                        <input name="menuIds"
                                          id="${menu.id}"
                                          class="checkboxCtrl"
                                          type="checkbox"
                                          group=""
                                          value="${menu.id}"
                                          onclick="allCancle(this,${menu.id});"
                                          <c:if test='${menu.updater eq 1  or roleId eq 4}'>checked="checked" </c:if>
                                          <c:if test='${roleId eq 4 and menu.id eq 23}'>disabled="disabled"</c:if>>
                                    </td>
                                    <td style="font-weight: bold;border-top: 10px solid; border-color: transparent;">${menu.menuName}</td>
                                </tr>
                                <c:forEach var="menu2" items="${menuList}" varStatus="index">
                                    <c:if test="${menu2.menuLevel eq 2 and menu2.pMenu.id  eq menu.id}">
                                        <tr>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <input name="menuIds"
                                                 id="${menu.id}_${menu2.id}"
                                                 class="checkboxCtrl"
                                                 type="checkbox" group=""
                                                 onclick="checkboxonclick(${menu2.id},this,${menu.id},-1);"
                                                 value="${menu2.id}"
                                                 <c:if test='${menu2.updater eq 1 or roleId eq 4}'>checked="checked" </c:if>
                                                 <c:if test='${roleId eq 4 and menu.id eq 23}'>disabled="disabled"</c:if>>
                                            </td>
                                            <td>${menu2.menuName}</td>
                                        </tr>
                                        <c:forEach var="menu3" items="${menuList}" varStatus="index">
                                            <c:if test="${menu3.menuLevel eq 3 and menu3.pMenu.id  eq menu2.id}">
                                                <tr class="trCtrl-3">
                                                    <td>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <input
                                                            name="menuIds" id="${menu.id}_${menu2.id}_${menu3.id}"
                                                            class="checkboxCtrl checkboxCtrl-3" type="checkbox" group=""
                                                            onclick="checkboxonclick(${menu3.id},this,${menu2.id},${menu.id});"
                                                            value="${menu3.id}"
                                                            <c:if test='${menu3.updater eq 1 or roleId eq 4}'>checked="checked" </c:if>
                                                            <c:if test='${roleId eq 4 and menu.id eq 23}'>disabled="disabled"</c:if>>
                                                    </td>
                                                    <td>${menu3.menuName}</td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </table>
                </div>
            </form>
        </div>
    </div>
    <div class="tabsFooter">
        <div class="tabsFooterContent"></div>
    </div>
</div>
<div style="margin-top: 5px;">
    <div class="button">
        <div class="buttonContent">
            <button id="save" type="button" onclick="saveProject()">保存</button>
        </div>
    </div>
</div>
<script>
    //二级菜单 三级菜单选中、取消事件
    //自己ID，this，上级ID，上上级ID
    function checkboxonclick(myId, this_, parentId, firstParentId) {
        console.log($(this_).attr("checked") + myId + this + parentId + firstParentId);
        if ($(this_).attr("checked")) {//选中

            if (firstParentId > -1) {//选中三级菜单
                console.log("选中三级菜单：" + firstParentId + "_" + parentId);
                document.getElementById(firstParentId + "_" + parentId).checked = true;//2级菜单设置选中
                document.getElementById(firstParentId).checked = true;//1级菜单设置选中
            } else {//选中2级菜单
                document.getElementById(parentId).checked = true;//1级菜单设置选中
                //二级菜单下所有三级菜单都取消
                var list = $("[id^='" + parentId + "_" + myId + "_" + "']");
                console.log(list);
                for (var i = 0; i < list.length; i++) {
                    console.log(parentId + "_" + myId + "_" + i);
                    list[i].checked = true;
                }
            }
        } else {//取消
            if (firstParentId == -1) {//取消二级菜单
                //二级菜单下所有三级菜单都取消
                var list = $("[id^='" + parentId + "_" + myId + "_" + "']");
                console.log(list);
                for (var i = 0; i < list.length; i++) {
                    console.log(parentId + "_" + myId + "_" + i);
                    list[i].checked = false;
                }
            }
        }
    }
    //一级菜单选中 取消事件
    function allCancle(this_, myId) {
        console.log($(this_).attr("checked"));
        if (!$(this_).attr("checked")) {//取消
            var list = $("[id^='" + myId + "_']");
            for (var i = 0; i < list.length; i++) {
                console.log(myId + "_" + i);
                list[i].checked = false;
            }
        } else {
            var list = $("[id^='" + myId + "_']");
            for (var i = 0; i < list.length; i++) {
                console.log(myId + "_" + i);
                list[i].checked = true;
            }
        }
    }


</script>