<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    var uniqueDevCode = true;
    var uniqueDevName = true;
    function checkDevCode() {
        if ($("#devCode").val().length > 0) {
            $.getJSON("${ctx}/monitoringDevice/checkDevCode.action?devCode=" + $("#devCode").val(), function (data) {
                if (data.deviceId != "" && data.deviceId != "${device.id}") {
                    $("#devCodeMsg").html("设备编号已使用");
                    uniqueDevCode = false;
                } else {
                    $("#devCodeMsg").html("");
                    uniqueDevCode = true;
                }
            });
        }
    }
    function checkDevName() {
        if ($("#devName").val().length > 0) {
            $.getJSON("${ctx}/monitoringDevice/checkDevName.action?devName=" + $("#devName").val(), function (data) {
                if (data.deviceId != "" && data.deviceId != "${device.id}") {
                    $("#devNameMsg").html("设备名称已使用");
                    uniqueDevName = false;
                } else {
                    $("#devNameMsg").html("");
                    uniqueDevName = true;
                }
            });
        }
    }
    function checkPhoneCard(){
        var val = $("input[name='phoneCard']").val();
        var re = /(^1\d{12})|(^1\d{10})$/g;
        var myArray = re.exec(val);
        if( val.length == 11 || val.length == 13){
            if (!myArray){
                $("#phoneCardMsg").text("请以数字“1”开头的纯数字串，重新填写手机卡号！");
            }
            else{
                $("#phoneCardMsg").text("");
            }
        }
        else{
            $("#phoneCardMsg").text("手机卡号位数要求11位或者13位！");
        }
    }
    //初始化转发因子
    function initCheckBox() {
        //根据设备类型切换显示不同的转发因子，使用jQuery show()、hide()方法。
        var devType = parseInt($("select[name='devType'] > option:selected").val());
        switch (devType){
            case 4:
                $("#dustyNoise").show();
                $("#AQI").hide();
                $("#VOC").hide();
                break;
            case 5:
                $("#dustyNoise").hide();
                $("#AQI").show();
                $("#VOC").hide();
                break;
            case 6:
                $("#common").hide();
                $("#dustyNoise").hide();
                $("#AQI").hide();
                $("#VOC").show();
                break;
            case 7:
                $("#dustyNoise").show();
                $("#AQI").hide();
                $("#VOC").hide();
                break;
        }
        //设置显示的checkbox标签状态，隐藏的不管
        var forwardType = new Array();
        forwardType = "${device.forwardType}".split(';');
        if("${device.forwardType}") {
            for (var i = 0; i < forwardType.length; i++) {
                $(".forwardType:visible").each(function () {
                    if ($(this).val() == forwardType[i]) {
                        $(this).attr("checked", true);
                    }
                });
            }
        }
        else{
            $(".forwardType:visible").each(function () {
                $(this).attr("checked", true);
            });
        }
    }
    //初始化转发频率
    function initRadio(){
        var forwardFrequency = "${device.forwardFrequency}";
        if(forwardFrequency) {
            if (forwardFrequency == "0") {
                $(".forwardFrequency:visible").each(function () {
                    if ($(this).val() == "0") {
                        $(this).attr("checked", true);
                    }
                });
            }
            else if (forwardFrequency == "30") {
                $(".forwardFrequency:visible").each(function () {
                    if ($(this).val() == "30") {
                        $(this).attr("checked", true);
                    }
                });
            }
            else if (forwardFrequency == "300") {
                $(".forwardFrequency:visible").each(function () {
                    if ($(this).val() == "300") {
                        $(this).attr("checked", true);
                    }
                });
            }
            else {
                $(".forwardFrequency:visible").each(function () {
                    if ($(this).val() == "yourself") {
                        $(this).attr("checked", true);
                    }
                });
                $("input[name='yourself']").attr("value", forwardFrequency/60);
                $("input[name='yourself']").removeAttr("disabled");
            }
        }
        else{
            $(".forwardFrequency:visible").each(function () {
                if ($(this).val() == "0") {
                    $(this).attr("checked", true);
                }
            });
        }
    }
    //新增时设置默认状态为在线
    function initStatus(){
        //新增时decCode为空
        var devCode = $("input[name='devCode']").val();
        if(devCode){
            $(".online").attr("checked",true);
            $(".outline").removeAttr("checked");
        }
    }
    //提交表单时设置转发因子的值
    //页面中插入隐藏input用来存放所选转发因子组成的字符串
    function setCheckBoxValue() {
        var forwardTypes = "";
        $(".forwardType:visible").each(function () {
            if($(this).attr("checked")) {
                forwardTypes = forwardTypes+";" + $(this).val();
            }
        });
        var index = forwardTypes.indexOf(';');
        forwardTypes = forwardTypes.substring(index+1);
        $("input[name='forwardType']").attr("value",forwardTypes);
    }
    //提交表单时设置转发频率的值
    function setRadioValue(){
        var $node = $(".forwardFrequency:visible:checked");
        if($node.val() == "yourself"){
            var value = $("input[name='yourself']").val();
            $("input[name='forwardFrequency']").attr("value",value*60);
        }
        else{
            var value = $node.val();
            $("input[name='forwardFrequency']").attr("value",value);
        }
    }
    function saveProject() {
        if (uniqueDevCode) {
            $("input[name='longitude']").val($("#lng").val());
            $("input[name='latitude']").val($("#lat").val());
            setCheckBoxValue();
            setRadioValue();
            $("#deviceForm").submit();
        } else {
            $("#devCode")[0].focus();
        }
    }

    $(function () {
        initCheckBox();
        initRadio();
        //initStatus();
        //根据不同设备显示不同下拉列表
        $("#devType").change(function () {
            var t = parseInt($(this).children('option:selected').val());
            $("#video").hide();
            if (t == 3) {
                $("#degreeData").hide();
                $("#forwardData").hide();
                $("#dustyNoise").hide();
                $("#AQI").hide();
                $("#VOC").hide();
                $("#videoData").show();
            } else {
                $("#degreeData").show();
                $("#forwardData").show();
                $("#videoData").hide();
                switch(t){
                    case 4:
                        $("#dustyNoise").show();
                        $("#AQI").hide();
                        $("#VOC").hide();
                        break;
                    case 5:
                        $("#dustyNoise").hide();
                        $("#AQI").show();
                        $("#VOC").hide();
                        break;
                    case 6:
                        $("#common").hide();
                        $("#dustyNoise").hide();
                        $("#AQI").hide();
                        $("#VOC").show();
                        break;
                    case 7:
                        $("#dustyNoise").show();
                        $("#AQI").hide();
                        $("#VOC").hide();
                        break;
                }
            }
            initCheckBox();
        });

        $("#getVideoUrl").click(function () {
            $.getJSON("${ctx}/monitoringDevice/videoUrl.action?serial=" + $("#serial").val(), function (data) {
                $("#videoUrl").val(data.videoUrl);
            });
            return false;
        });

        //alert($("input[name='forwardFrequency']").size());
        //切换转发频率时改变radio状态
        $(".forwardFrequency").each(function(){
            $(this).click(function(){
                var val = $(this).val();
                if(val!="yourself"){
                    $("input[name='yourself']").attr("disabled",true);
                }
                else
                    $("input[name='yourself']").removeAttr("disabled");
                    $("input[name='yourself']").attr("value",null);
            });
        });


    });
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
                  action="${ctx}/monitoringDevice/save.action?rel=ibs_device_page&callbackType=closeCurrent"
                  method="post" class='required-validate pageForm'
                  onsubmit="return validateCallback(this, dialogAjaxDone)">

                <input type="hidden" value="<c:out value='${device.id}'/>" name="id"/>
                <input type="hidden" value="<c:out value='${device.version}'/>" name="version"/>

                <div class="pageFormContent" layoutH="100">
                    <p class="nowrap">
                        <label style="width: 60px;">设备编号</label>
                        <input id="devCode" name="devCode" value="${device.devCode}" type="text" onblur="checkDevCode()"
                               size="30" class="required alphanumeric" minlength="1" maxlength="32"
                               style="margin-left: 25px;"/>
                        <span id="devCodeMsg"></span>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">设备名称</label>
                        <input id="devName" name="devName" value="${device.devName}" type="text" onblur="checkDevName()"
                               size="30" class="required" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                        <span id="devNameMsg"></span>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">设备类型</label>
                        <select id="devType" name="devType" style="margin-left: 25px;">
                            <c:choose>
                                <c:when test="${device.devType==3 }">
                                    <option value="3" selected="selected">视频</option>
                                    <option value="4">扬尘噪声</option>
                                    <option value="5">AQI</option>
                                    <option value="6">VOC</option>
                                    <option value="7">扬尘噪声（基础）</option>
                                </c:when>
                                <c:when test="${device.devType==4 }">
                                    <option value="3">视频</option>
                                    <option value="4" selected="selected">扬尘噪声</option>
                                    <option value="5">AQI</option>
                                    <option value="6">VOC</option>
                                    <option value="7">扬尘噪声（基础）</option>
                                </c:when>
                                <c:when test="${device.devType==5 }">
                                    <option value="3">视频</option>
                                    <option value="4">扬尘噪声</option>
                                    <option value="5" selected="selected">AQI</option>
                                    <option value="6">VOC</option>
                                    <option value="7">扬尘噪声（基础）</option>
                                </c:when>
                                <c:when test="${device.devType==6 }">
                                    <option value="3">视频</option>
                                    <option value="4">扬尘噪声</option>
                                    <option value="5">AQI</option>
                                    <option value="6" selected="selected">VOC</option>
                                    <option value="7">扬尘噪声（基础）</option>
                                </c:when>
                                <c:when test="${device.devType==7 }">
                                    <option value="3">视频</option>
                                    <option value="4">扬尘噪声</option>
                                    <option value="5">AQI</option>
                                    <option value="6">VOC</option>
                                    <option value="7" selected="selected">扬尘噪声（基础）</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="3">视频</option>
                                    <option value="4" selected>扬尘噪声</option>
                                    <option value="5">AQI</option>
                                    <option value="6">VOC</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </p>

                    <%--<c:choose>
                        <c:when test="${device.proCode!=null && device.proCode!='' }">
                            <p class="nowrap">
                                <label style="width: 60px;">报建号</label>
                                <input id="proCode" value="${device.proCode }" type="text"
                                       size="30" class="" minlength="1" maxlength="32" style="margin-left: 25px;"
                                       disabled="disabled"/>
                            </p>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${device.vendorCode!=null && device.vendorCode!='' }">
                            <p class="nowrap">
                                <label style="width: 60px;">厂商编号</label>
                                <input id="vendorCode" value="${device.vendorCode }" type="text"
                                       size="30" class="" minlength="1" maxlength="32" style="margin-left: 25px;"
                                       disabled="disabled"/>
                            </p>
                        </c:when>
                    </c:choose>--%>
                    <%--<p class="nowrap">
                        <label style="width: 60px;">所属站点</label>
                        <input name="monitoringProject.proName" type="text" size="50" readonly="readonly"
                               value="<c:out value='${device.monitoringProject.proName}'/>" style="margin-left: 25px;">
                        <input name="monitoringProject.id" type="hidden"
                               value="<c:out value='${device.monitoringProject.id}'/>"/>
                        <a class="btnLook"
                           href="${ctx}/monitoringProject/selectlist.action?proId=${device.monitoringProject.id}"
                           lookupGroup="monitoringProject" mask="true" width="800" height="500">查找带回</a>
                    </p>--%>

                    <div id="forwardData"
                        <c:choose>
                            <c:when test="${device.devType!=3}"></c:when>
                            <c:otherwise>style="display: none"</c:otherwise>
                        </c:choose>>
                        <p class="nowrap">
                            <label style="width: 60px;">手机卡号</label>
                            <input id="phoneCard" name="phoneCard" value="<c:out value='${device.phoneCard}'/>" type="text"
                                   size="30" maxlength="13" style="margin-left: 25px;" onblur="checkPhoneCard()"/>
                            <span id="phoneCardMsg"></span>
                        </p>
                        <p class="nowrap">
                            <label style="width: 60px;">所属厂商</label>
                            <input name="monitoringCompany.companyName" type="text" size="30" readonly="readonly"
                                   class="required"
                                   value="<c:out value='${device.monitoringCompany.companyName}'/>"
                                   style="margin-left: 25px;">
                            <input name="monitoringCompany.id" type="hidden"
                                   value="<c:out value='${device.monitoringCompany.id}'/>"/>
                            <a class="btnLook"
                               href="${ctx}/monitoringCompany/selectlist.action?comId=${device.monitoringCompany.id}"
                               lookupGroup="monitoringCompany" mask="true" width="800" height="415">查找带回</a>
                        </p>
                        <p class="nowrap">
                            <label style="width: 60px;">转发平台</label>
                            <input name="companyNames" type="text" size="50" readonly="readonly"
                                   value="<c:out value='${companyNames}'/>" style="margin-left: 25px;">
                            <input name="companyIds" type="hidden"
                                   value="<c:out value='${companyIds}'/>"/>
                            <%--<c:if test="${device.platformId==0}">--%>
                            <%--<input name="platform.companyName" type="text" size="50" readonly="readonly"--%>
                            <%--style="margin-left: 25px;"/>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${device.platformId!=0}">--%>
                            <%--<input name="platform.companyName" type="text" size="50" readonly="readonly"--%>
                            <%--value="<c:out value='${device.platformId}'/>" style="margin-left: 25px;"/>--%>
                            <%--</c:if>--%>
                            <input name="platform.id" type="hidden"
                                   value="<c:out value='${companyIds}'/>"/>
                            <a class="btnLook"
                               href="${ctx}/monitoringCompany/selectplatformlist.action?deviceId=${device.id}&companyIds=${companyIds}&companyNames=${companyNames}"
                               lookupGroup="" mask="true" width="800" height="415">查找带回</a>
                        </p>
                        <p class="nowrap">
                            <label style="width: 60px;">转发MN号</label>
                            <input id="platformMN" name="platformMN" value="${device.platformMN}" type="text"
                                   size="30" class="" maxlength="32" style="margin-left: 25px;"/>
                        </p>
                        <p class="nowrap">
                            <label style="width: 60px;">转发因子</label>
                            <input name="forwardType" type="hidden" value="${device.forwardType}">

                            <span class="forward" id="dustyNoise" style="display: none;">
                                <%--<input name="forwardType" type="hidden" value="${device.forwardType}">--%>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="calibration_two_pm" />PM2.5 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="calibration_ten_pm" />PM10 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="calibration_tsp" />TSP </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="calibration_noise" />噪声 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="actual_wind_speed" />风速 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="actual_wind_direction" />风向 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="actual_temperature" />温度 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="humidity" />湿度 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="pressure" />气压 </label>
                            </span>
                            <span class="forward" id="AQI" style="display: none">
                                <%--<input name="forwardType" type="hidden" value="${device.forwardType}">--%>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="calibration_ten_pm" />PM10 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="calibration_two_pm" />PM2.5 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="calibration_02" />SO2 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="calibration_NO2" />NO2 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="calibration_03" />O3 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="calibration_04" />CO </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="calibration_noise" />噪声 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="actual_wind_speed" />风速 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="actual_wind_direction" />风向 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="actual_temperature" />温度 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="humidity" />湿度 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="pressure" />气压 </label>

                            </span>
                            <span class="forward" id="VOC" style="display: none">
                                <%--<input name="forwardType" type="hidden" value="${device.forwardType}">--%>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="H2S" />H2S </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="NH3" />NH3 </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="C2H6S" />C2H6S </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="CH4S" />CH4S </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="TVOC" />TVOC </label>
                                <label style="width: 30px;"><input class="forwardType" type="checkbox" value="HCL" />HCL </label>
                            </span>
                        </p>
                        <p class="nowrap">
                            <label style="width: 60px;">转发频率</label>
                            <label style="width: 70px;"><input class="forwardFrequency" name="frequency" type="radio" value="0" />即收即发 </label>
                            <label style="width: 60px;"><input class="forwardFrequency" name="frequency" type="radio" value="30" />30s </label>
                            <label style="width: 60px;"><input class="forwardFrequency" name="frequency" type="radio" value="300" />5分钟 </label>
                            <label style="width: 60px;"><input class="forwardFrequency" name="frequency" type="radio" value="yourself" />自定义 </label>
                            <input name="yourself" type="text" value="" style="width: 30px;" disabled/><label>分钟</label>
                            <input name="forwardFrequency" type="hidden" value="">
                        </p>
                        <p class="nowrap">
                            <label style="width: 60px;">经度</label>
                            <input id="longitude" name="longitude" value="${device.longitude }" type="text"
                                   size="30" class="" maxlength="11" style="margin-left: 25px;"/>
                        </p>
                        <p class="nowrap">
                            <label style="width: 60px;">纬度</label>
                            <input id="latitude" name="latitude" value="${device.latitude }" type="text"
                                   size="30" class="" maxlength="11" style="margin-left: 25px;"/>
                        </p>
                    </div>

                    <%--<div id="video" style="display: none">
                        <p class="nowrap">
                            <label style="width: 60px;">序列号</label>
                            <input id="serial" name="serial" value="${device.serial }" type="text"
                                   size="30" class="" maxlength="32" style="margin-left: 25px;"/>
                            <button id="getVideoUrl">获取视频地址</button>
                            (仅针对海康摄像头)
                        </p>

                        <p class="nowrap">
                            <label style="width: 60px;">IP地址</label>
                            <input id="ipAddr" name="ipAddr" value="${device.ipAddr }" type="text"
                                   size="30" class="" maxlength="32" style="margin-left: 25px;"/>
                        </p>

                        <p class="nowrap">
                            <label style="width: 60px;">MAC地址</label>
                            <input id="macAddr" name="macAddr" value="${device.macAddr }" type="text"
                                   size="30" class="" maxlength="32" style="margin-left: 25px;"/>
                        </p>

                        <p class="nowrap">
                            <label style="width: 60px;">端口</label>
                            <input id="devPort" name="devPort" value="${device.devPort }" type="text"
                                   size="30" class="number" maxlength="11" style="margin-left: 25px;"/>
                        </p>

                        <p class="nowrap">
                            <label style="width: 60px;">视频地址</label>
                            <input id="videoUrl" name="videoUrl" value="${device.videoUrl }" type="text"
                                   size="60" class="" maxlength="255" style="margin-left: 25px;"/>
                        </p>
                    </div>--%>
                    <%-- 2017-04-27 chenshuxiao--%>
                    <div id="videoData"
                            <c:choose>
                                <c:when test="${device.devType==3}"></c:when>
                                <c:otherwise>style="display: none"</c:otherwise>
                            </c:choose>>
                        <p class="nowrap">
                            <label style="width: 60px;">视频ID</label>
                            <input id="cameraId " name="cameraId" value="${device.cameraId }" type="text"
                                   size="30" class="" maxlength="32" style="margin-left: 25px;"/>
                        </p>

                        <p class="nowrap">
                            <label style="width: 60px;">视频名称</label>
                            <input id="videoUser " name="videoUser" value="${device.videoUser }" type="text"
                                   size="30" class="" maxlength="255" style="margin-left: 25px;"/>
                        </p>
                        <p class="nowrap">
                            <label style="width: 60px;">视频密码</label>
                            <input id="password" name="password" value="${device.password}" type="text"
                                   size="30" class="" maxlength="255" style="margin-left: 25px;"/>
                        </p>
                        <p class="nowrap">
                            <label style="width: 60px;">视频编码</label>
                            <input id="cameraIndexCode " name="cameraIndexCode" value="${device.cameraIndexCode}"
                                   type="text"
                                   size="30" class="" maxlength="255" style="margin-left: 25px;"/>
                        </p>
                    </div>

                    <p class="nowrap">
                        <label style="width: 60px;">传感器版本 </label>
                        <input id="devVersion" name="devVersion" value="${device.devVersion }" type="text"
                               size="30" class="alphanumeric" maxlength="11" style="margin-left: 25px;"/>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">传感器状态</label>
                        <c:choose>
                            <c:when test="${device.devStatus == 0 }">
                                <input class="online" name="devStatus" type="radio"
                                       value="1" style="margin-left: 25px;"/>在线
                                <input class="outline" name="devStatus" type="radio"
                                       value="0" checked="checked" style="margin-left: 25px;"/>下线
                            </c:when>
                            <c:when test="${device.devStatus == 1 }">
                                <input class="online" name="devStatus" type="radio"
                                       value="1" checked="checked" style="margin-left: 25px;"/>在线
                                <input class="outline" name="devStatus" type="radio"
                                       value="0" style="margin-left: 25px;"/>下线
                            </c:when>
                            <c:otherwise>
                                <input class="online" name="devStatus" type="radio"
                                       value="1" checked="checked" style="margin-left: 25px;"/>在线
                                <input class="outline" name="devStatus" type="radio"
                                       value="0" style="margin-left: 25px;"/>下线
                            </c:otherwise>
                        </c:choose>
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
            <button id="save" type="button" onclick="saveProject()">保存</button>
        </div>
    </div>
</div>
