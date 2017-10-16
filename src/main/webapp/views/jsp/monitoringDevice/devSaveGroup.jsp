<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var uniqueDevGroupName = true;
    $(document).ready(function () {

    });

    function saveProject() {
        if(uniqueDevGroupName){
            $("#deviceGroupForm").submit();
        }
    }
    function checkDevGroupName(){
        if ($("#devGroupName").val().length > 0) {
            $.getJSON("${ctx}/monitoringDevice/checkDevGroupName.action?devGroupName=" + $("#devGroupName").val(), function (data) {
                if (data.gourpId != undefined) {
                    $("#devGroupNameMsg").html("分组名称重复");
                    uniqueDevGroupName = false;
                } else {
                    $("#devGroupNameMsg").html("");
                    uniqueDevGroupName = true;
                }
            });
        }
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
            <form id="deviceGroupForm"
                  action="${ctx}/monitoringDevice/devSaveGroup.action?rel=ibs_device_group2_page&callbackType=closeCurrent"
                  method="post" class='required-validate pageForm'
                  onsubmit="return validateCallback(this, dialogAjaxDone)">

                <input type="hidden" value="<c:out value='${deviceGroup.id}'/>" name="id"/>

                <div class="pageFormContent" layoutH="100">
                    <p class="nowrap">
                        <label style="width: 60px;">分组名称：</label>
                        <input id="devGroupName" name="devGroupName" value="<c:out value='${deviceGroup.devGroupName}'/>"
                               type="text" onblur="checkDevGroupName()" size="30" class="required" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                        <span id="devGroupNameMsg"></span>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">分组类型：</label>
                        <input id="type" name="devGroupType" value="<c:out value='${deviceGroup.devGroupType}'/>"
                               type="text" size="30" class="required" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">分组说明：</label>
                        <textarea id="proDescription" name="devDescription" size="30" maxlength="512" style="margin-left: 25px;width: 324px;height: 100px;">${deviceGroup.devDescription}</textarea>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">归属设备：</label>
                        <input name="deviceNames" type="text" size="50" readonly="readonly"
                               value="<c:out value='${deviceNames}'/>" style="margin-left: 25px;">
                        <input name="deviceIds" type="hidden"
                               value="<c:out value='${deviceIds}'/>"/>
                        <a class="btnLook"
                           href="${ctx}/monitoringDevice/devSelectListInGroup.action?deviceGroupIdForOr=${deviceGroup.id}&deviceIds=${deviceIds}&deviceNames=${deviceNames}"
                           lookupGroup="" mask="true" width="1000" height="500">添加归属</a>
                    </p>
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
            <button type="button" onclick="saveProject()">保存</button>
        </div>
    </div>
</div>
