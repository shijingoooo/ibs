<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    $(function () {
        var province = "${company.province}";
        var city = "${company.city}";
        var district = "${company.district}";
        $.getScript("${ctx}/js/area/jquery.provincesCity.js", function () {
            if (province != "" || city != "" || district != "") {
                $("#companyArea").ProvinceCity(province, city, district);
            } else {
                $("#companyArea").ProvinceCity("", "", "");
            }
        });

        $("#type").change(function () {
            var t = parseInt($(this).children('option:selected').val());
            if (t == 1) {
                $("#platform").show();
            } else {
                $("#platform").hide();
            }
        });
    });

    function saveCompany() {
        var myReg = /^[a-zA-Z0-9_-]{0,}$/;
        var companyCode = $("#companyCode").val();
        if (!myReg.test(companyCode)) {
            alertMsg.error("厂商编号不能含有中文或特殊字符");
            return;
        }
        var telephone = $("input[name='telephone']").val();
        var greg = /\d{3}-\d{8}|\d{4}-\{7,8}/;
        var mreg = /^(13|14|15|17|18)[0-9]{9}$/;
        if (telephone != "") {
            var telVal = telephone.match(/\d{3}-\d{8}|\d{4}-\d{7}/);
            var mobileVal = telephone.match(/^(13|14|15|17|18)[0-9]{9}$/);
            if (telVal == null && mobileVal == null) {
                alertMsg.error("请输入正确的电话号码");
                return false;
            }
            if (!greg.test(telephone) && !mreg.test(telephone)) {
                alertMsg.error("请输入正确的电话号码");
                return false;
            }
        }


        $("#companyForm").submit();
    }

    //地图定位
    function showModalMap() {

        var lng = $("#lng").val();
        var lat = $("#lat").val();
        var province = $("#province").val();
        var city = $("#city").val();
        var district = $("#district").val();
        var street = $("#street").val();

//        var address = "";
//        if(province!="" && city!=""){
//            address = address+"-"+province+city+"市"+district+street;
//        }
        if (city != "" || district != "" || street != "") {
            address = city + "市" + district + street;
        } else {
            address = "北京";
        }
        var staticMapUrl = window.showModalDialog("${ctx}/monitoringCompany/showMap.action?Rnd=" + Math.random(), address, "dialogWidth=700px;dialogHeight=500px;dialogLeft=400px;dialogTop=170px;scroll=no;status=no");
        if (staticMapUrl != "" && staticMapUrl != null) {
            var lat = staticMapUrl.split("^")[0];
            var lng = staticMapUrl.split("^")[1];
            var url = staticMapUrl.split("^")[2];
            var province = staticMapUrl.split("^")[3];
            var city = staticMapUrl.split("^")[4];
            var district = staticMapUrl.split("^")[5];
            var street = staticMapUrl.split("^")[6];
// 	        $("#staticMap").attr("src","${ctx}/images/loading.gif");
// 	        $("#staticMap").attr("height","200");
// 	        $("#staticMap").attr("width","200");
// 	        $("#staticMap").attr("src", url);
            $("#staticMapUrl").val(url);
            $("#lng").val(lng);
            $("#lat").val(lat);
            $("#companyArea").empty();
            $("#companyArea").ProvinceCity(province.substring(0, province.length - 1), city.substring(0, city.length - 1), district.substring(0, district.length - 1));
            if (street != "") {
                $("#street").val(street);
            }
            $("#mapTip").text("标注位置正确");
            $("#mapTip").attr("class", "onCorrect");
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
            <form id="companyForm"
                  action="${ctx}/monitoringCompany/save.action?rel=ibs_company_page&callbackType=closeCurrent"
                  method="post" class='required-validate pageForm'
                  onsubmit="return validateCallback(this, dialogAjaxDone)">

                <input type="hidden" value="<c:out value='${company.id}'/>" name="id"/>
                <input type="hidden" value="<c:out value='${company.version}'/>" name="version"/>

                <div class="pageFormContent" layoutH="100">
                    <p class="nowrap">
                        <label style="width: 60px;">厂商名称</label>
                        <input id="companyName" name="companyName" value="<c:out value='${company.companyName}'/>"
                               type="text"
                               size="30" class="required" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                    </p>

                    <p class="nowrap">
                        <label style="width: 60px;">厂商编号</label>
                        <input id="companyCode" name="companyCode" value="<c:out value='${company.companyCode}'/>"
                               type="text"
                               size="30" class="required" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                    </p>

                    <p class="nowrap">
                        <label style="width: 60px;">厂商类型</label>
                        <select id="type" name="type" style="margin-left: 25px;">
                            <c:choose>
                                <c:when test="${company.type==0}">
                                    <option value="0" selected="selected">设备供应商</option>
                                    <option value="1">平台服务商</option>
                                    <option value="2">APP服务商</option>
                                </c:when>
                                <c:when test="${company.type==1}">
                                    <option value="0">设备供应商</option>
                                    <option value="1" selected="selected">平台服务商</option>
                                    <option value="2">APP服务商</option>
                                </c:when>
                                <c:when test="${company.type==2}">
                                    <option value="0">设备供应商</option>
                                    <option value="1">平台服务商</option>
                                    <option value="2" selected="selected">APP服务商</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="0" selected="selected">设备供应商</option>
                                    <option value="1">平台服务商</option>
                                    <option value="2">APP服务商</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </p>
                    <div id="platform"
                            <c:choose>
                                <c:when test="${company.type==1}"></c:when>
                                <c:otherwise>style="display: none"</c:otherwise>
                            </c:choose>>
                        <p class="nowrap">
                            <label style="width: 60px;">平台协议</label>
                            <select id="protocol" name="protocol" style="margin-left: 25px;">
                                <c:choose>
                                    <c:when test="${company.protocol==0}">
                                        <option value="0" selected="selected">自定义</option>
                                        <option value="1">HJ212(标准)</option>
                                        <option value="2">HJ212(同阳)</option>
                                    </c:when>
                                    <c:when test="${company.protocol==1}">
                                        <option value="0">自定义</option>
                                        <option value="1" selected="selected">HJ212(标准)</option>
                                        <option value="2">HJ212(同阳)</option>
                                    </c:when>
                                    <c:when test="${company.protocol==2}">
                                        <option value="0">自定义</option>
                                        <option value="1">HJ212(标准)</option>
                                        <option value="2" selected="selected">HJ212(同阳)</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0">自定义</option>
                                        <option value="1" selected="selected">HJ212(标准)</option>
                                        <option value="2">HJ212(同阳)</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </p>
                    </div>
                    <p class="nowrap">
                        <label style="width: 60px;">联系人</label>
                        <input id="contacts" name="contacts" value="${company.contacts }" type="text"
                               size="30" class="" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                    </p>

                    <p class="nowrap">
                        <label style="width: 60px;">联系电话</label>
                        <input id="telephone" name="telephone" value="${company.telephone }" type="text"
                               size="30" class="" minlength="1" maxlength="32" style="margin-left: 25px;"/>
                    </p>
                    <label style="width: 60px;">所在地</label>

                    <div id="companyArea" style="display:inline;"></div>
                    <p class="nowrap">
                        <label style="width: 60px;">街道</label>
                        <input id="street" name="street" value="${company.street }" type="text"
                               size="30" class="required" minlength="1" maxlength="128" style="margin-left: 25px;"/>
                    </p>

                    <p class="nowrap">
                        <input type="hidden" id="lng" name="longitude" value=""/>
                        <input type="hidden" id="lat" name="latitude" value=""/>
                        <label style="width: 60px;">地图</label>
                        <a href="javascript:void(0);" onclick="showModalMap();" style="margin-left: 25px;">标注厂商位置</a>
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
            <button type="button" onclick="saveCompany()">保存</button>
        </div>
    </div>
</div>
