<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">

    $(document).ready(function () {
        $(".addRule").click(function(){
            $(".nowrap:last").append($(".nowrap:last").clone(true));
        });
        $(".deleteRule").click(function(){
            //默认一条规则，无法删除
            if( $(".deleteRule").length > 1)
                $(this).parent().remove();
        })

        $.each($(".indicator > option"),function (i,n) {
            if(n.value == '${rule.indicator}')
            {
                $(this).attr("selected", true);
            }
        });


    });

    function saveRule() {

        //添加表单验证
        if(checkRule())
            $("#deviceRuleForm").submit();
    }
    function checkRule() {
        var $mins = $("input[name='mins']");
        var $maxs = $("input[name='maxs']");
        var $calibrationFactors = $("input[name='calibrationFactors']");
        var $msg = $(".ruleMsg");
        for(var i = 0; i < $mins.size(); i++){
            if($mins[i].value == ""){
                $msg.text("字段不允许为空！");
                return false;
            }
        }
        for(var j = 0; j < $maxs.size(); j++){
            if($maxs[j].value == ""){
                $msg.text("字段不允许为空！");
                return false;
            }
        }
        for(var k = 0; k < $calibrationFactors.size(); k++){
            if($calibrationFactors[k].value == ""){
                $msg.text("字段不允许为空！");
                return false;
            }
        }
        if(parseFloat($mins.val()) >= parseFloat($maxs.val())){
            $msg.text("规则错误！");
            return false
        }
        return true;
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
            <form id="deviceRuleForm"
                  action="${ctx}/monitoringDevice/devUpdateRule.action?rel=ibs_data_calibration_page&callbackType=closeCurrent"
                  method="post" class='required-validate pageForm'
                  onsubmit="return validateCallback(this, dialogAjaxDone)">
                <input name="id" type="hidden" value="${rule.id}"/>
                <input name="deviceId" type="hidden" value="${rule.deviceId}">
                <div class="pageFormContent" layoutH="100">
                    <p class="nowrap">
                        <label style="width: 60px;">设备编号:</label>
                        <input name="deviceCode" type="text" size="50" readonly="readonly"
                               value="${rule.deviceCode}" style="margin-left: 25px;">
                        <%--<a class="btnLook" href="${ctx}/monitoringDevice/devSelectListInRule.action?deviceId=${rule.deviceId}"
                           lookupGroup="" mask="true" width="1000" height="500">选择传感器</a>--%>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">设备类型:</label>
                        <c:choose>
                            <c:when test="${rule.deviceType == '1' }">
                                <input name="deviceTypeName" type="text" size="50" readonly="readonly"
                                       value="扬尘" style="margin-left: 25px;" >
                            </c:when>
                            <c:when test="${rule.deviceType == '2' }">
                                <input name="deviceTypeName" type="text" size="50" readonly="readonly"
                                       value="噪声" style="margin-left: 25px;" >
                            </c:when>
                            <c:when test="${rule.deviceType == '3' }">
                                <input name="deviceTypeName" type="text" size="50" readonly="readonly"
                                       value="视频" style="margin-left: 25px;" >
                            </c:when>
                            <c:when test="${rule.deviceType == '4' }">
                                <input name="deviceTypeName" type="text" size="50" readonly="readonly"
                                       value="扬尘噪声" style="margin-left: 25px;" >
                            </c:when>
                            <c:when test="${rule.deviceType == '5' }">
                                <input name="deviceTypeName" type="text" size="50" readonly="readonly"
                                       value="AQI" style="margin-left: 25px;" >
                            </c:when>
                            <c:when test="${rule.deviceType == '6' }">
                                <input name="deviceTypeName" type="text" size="50" readonly="readonly"
                                       value="VOC" style="margin-left: 25px;" >
                            </c:when>
                            <c:otherwise>
                                <input name="deviceTypeName" type="text" size="50" readonly="readonly"
                                       value="该设备未指定类型" style="margin-left: 25px;" >
                            </c:otherwise>
                        </c:choose>
                    </p>

                    <p class="nowrap">
                        <label class="indicator" style="width: 60px;">指标:</label>
                        <c:choose>
                            <%--<c:when test="${rule.deviceType == '1' }">
                                <select class='indicator' name='indicator' style='margin-left: 25px;'>
                                    <option value='PM2.5'>PM2.5</option>
                                    <option value='PM10'>PM10</option>
                                    <option value='TSP'>TSP</option>
                                </select>
                                <label class='unit'>单位：ug/m3</label>
                            </c:when>
                            <c:when test="${rule.deviceType == '2' }">
                                <select class='indicator' name='indicator' style='margin-left: 25px;'>
                                    <option value='PM2.5'>PM2.5</option>
                                    <option value='PM10'>PM10</option>
                                    <option value='TSP'>TSP</option>
                                </select>
                                <label class='unit'>单位：ug/m3</label>
                            </c:when>
                            <c:when test="${rule.deviceType == '3' }">
                                <select class='indicator' name='indicator' style='margin-left: 25px;'>
                                    <option value='PM2.5'>PM2.5</option>
                                    <option value='PM10'>PM10</option>
                                    <option value='SO2'>SO2</option>
                                    <option value='NO3'>NO3</option>
                                    <option value='O3'>O3</option>
                                    <option value='CO'>CO</option>
                                </select>
                                <label class='unit'>单位：ug/m3</label>
                            </c:when>--%>
                            <c:when test="${rule.deviceType == '4' }">
                                <select class='indicator' name='indicator' style='margin-left: 25px;'>
                                    <option value='calibration_two_pm' selected>PM2.5</option>
                                    <option value='calibration_ten_pm'>PM10</option>
                                    <option value='calibration_tsp'>TSP</option>
                                </select>
                                <label class='unit'>单位：ug/m3</label>
                            </c:when>
                            <c:when test="${rule.deviceType == '5' }">
                                <select class='indicator' name='indicator' style='margin-left: 25px;'>
                                    <option value='calibration_ten_pm'>PM10</option>
                                    <option value='calibration_two_pm' selected>PM2.5</option>
                                    <option value='calibration_02'>SO₂</option>
                                    <option value='calibration_NO2'>NO₂</option>
                                    <option value='calibration_03'>O₃</option>
                                    <option value='calibration_04'>CO</option>
                                </select>
                                <label class='unit'>单位：ug/m3</label>
                            </c:when>
                            <%--<c:when test="${rule.deviceType == '6' }">
                                <select class='indicator' name='indicator' style='margin-left: 25px;'>
                                    <option value='H2S'>H2S</option>
                                    <option value='NH3'>NH3</option>
                                    <option value='C2H6S'>C2H6S</option>
                                    <option value='CH4S'>CH4S</option>
                                    <option value='TVOC'>TVOC</option>
                                    <option value='HCL'>HCL</option>
                                </select>
                                <label class='unit'>单位：ug/m3</label>
                            </c:when>--%>
                            <c:otherwise>

                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p class="nowrap">
                        <label style="width: 80px;">指标校准范围:</label>
                        &nbsp;&nbsp;<span class="ruleMsg"></span>
                    </p>
                    <p class="nowrap">
                        <label style="width: 40px;">下限>=</label>
                        <input name="mins" type="text" value="${rule.mins}" style="width: 50px;">
                        <label style="width: 40px;">上限<</label>
                        <input name="maxs" type="text" value="${rule.maxs}" style="width: 50px;">
                        <label style="width: 50px;">校准系数</label>
                        <input name="calibrationFactors" type="text" value="${rule.calibrationFactors}" style="width: 50px;">
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
            <button type="button" onclick="saveRule()">更新</button>
        </div>
    </div>
</div>
