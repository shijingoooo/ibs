<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#modelExportDeviceGroup").click(function () {
            var dateIds = "";
            $("input[name='deviceGroupIds']").each(function (index, item) {
                if ($(item).prop("checked")) {
                    dateIds += $(item).val() + ",";
                }
            });
            if (dateIds == "") {
                alert("请选择要导出的数据！");
            } else {
                var hrefVal = "${ctx}/monitoringDevice/downloadDeviceGroup.action";
                location.href = hrefVal + "?deviceGroupIds=" + dateIds.substring(0, dateIds.length - 1) + "&rel=ibs_device_group2_page";
            }
        });

        $("#modelImportDeviceGroup").click(function () {
            $("#ImportDeviceGroupForm").submit();
        });


    });
</script>
<style type="text/css">
    .devCode a{
        color: #00f;
        text-decoration: underline;
    }
</style>
<div class="pageContent">
    该设备未指定类型，无法获取数据
</div>