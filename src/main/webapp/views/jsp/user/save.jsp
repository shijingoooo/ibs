<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/js/MD5Utils.js"></script>
<script type="text/javascript" src="${ctx}/js/area/jquery.provincesCity-user.js"></script>

<style type="text/css">
    .showPassword{
        position: absolute;
        margin:-3px 0 0 -30px;
        cursor: pointer;
    }
</style>
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
            <form id="userForm"
                  action="${ctx}/user/save.action?rel=ibs_user_page&callbackType=closeCurrent"
                  method="post" class='required-validate pageForm'
                  onsubmit="return validateCallback(this, dialogAjaxDone)">

                <input type="hidden" value="<c:out value='${user.id}'/>" name="id"/>
                <input type="hidden" value="<c:out value='${user.version}'/>" name="version"/>

                <div class="pageFormContent" layoutH="100">
                    <p class="nowrap">
                        <label style="width: 60px;">用户名：</label>
                        <input id="userName" name="userName" value="<c:out value='${user.userName}'/>" type="text"
                               size="11" class="required alphanumeric" maxlength="11" style="margin-left: 25px;"/>
                    </p>

                    <p class="nowrap">
                        <label style="width: 60px;">用户类型：</label>
                        <select class="userType" id="userType" name="userType" style="margin-left: 25px;"
                                onchange="changeUserType(this.value)">
                            <option value="1">超级管理员</option>
                            <option value="2">普通管理员</option>
                        </select>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">密码：</label>
                        <c:if test="${user.id !=null}">
                            <input id="pass_input" value="" type="password"
                                   size="30" maxlength="32" style="margin-left: 25px;"/>
                        </c:if>
                        <c:if test="${user.id ==null}">
                            <input id="pass_input" value="" type="password"
                                   size="30" class="required" maxlength="32" style="margin-left: 25px;"/></c:if>

                        <input type="hidden" id="userPassword" name="userPassword" value="${user.userPassword }"/>
                        <img class="showPassword" src="${ctx}/images/password.png">
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">单位：</label>
                        <input id="companyName" name="companyName" value="${user.companyName }" type="text"
                               size="30" class="" maxlength="32" style="margin-left: 25px;"/>
                    </p>

                    <p class="nowrap">
                        <label style="width: 60px;">手机号：</label>
                        <input id="telephone" name="telephone" value="${user.telephone }" type="text"
                               size="30" class="required" maxlength="11" style="margin-left: 25px;"/>
                    </p>

                    <p class="nowrap">
                        <label style="width: 60px;">邮箱：</label>
                        <input id="email" name="email" value="${user.email }" type="text"
                               size="30" class="required" maxlength="32" style="margin-left: 25px;"/>
                    </p>
                    <p class="nowrap">
                        <label style="width: 60px;">管理设备组：</label>
                        <input name="proNames" type="text" size="50" readonly="readonly"
                               value="<c:out value='${proNames}'/>" style="margin-left: 25px;"/>
                        <input name="projectIds" type="hidden"
                               value="<c:out value='${projectIds}'/>"/>
                        <a class="btnLook" href="${ctx}/monitoringProject/mulselectlist.action?userId=${user.id}"
                           lookupGroup="" mask="true" width="800" height="500">查找带回</a>
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
<script type="text/javascript">
    var province = "${user.province}";
    var city = "${user.city}";
    var district = "${user.district}";
    $(document).ready(function () {
        var userType = "${user.userType}";
        if (userType == "" || userType == null) {
            userType = "9";
        }
        $("#userType option").each(function () {
            if ($(this).val() == userType) {
                $(this).attr("selected", "selected");
            }
        });
        changeUserType(userType);
        $(".showPassword").click(function () {
            var $node = $("#pass_input");
            var type = $node.attr("type");
            if(type == "password"){
                $node[0].setAttribute("type","text");
            }
            else if(type == "text") {
                $node[0].setAttribute("type","password");
            }
        })
    });

    function saveProject() {
        var mobileVal = "";
        var userName = $("input[name='userName']").val()
        if (userName == "") {
            alertMsg.error("请输入用户名称");
            return false;
        }
        var telephone = $("input[name='telephone']").val();
        if (telephone != "") {
            mobileVal = telephone.match(/^(13|14|15|17|18)[0-9]{9}$/);
            if (mobileVal == null) {
                alertMsg.error("请输入正确的手机号码");
                return false;
            }
        } else {
            alertMsg.error("请输入手机号码");
            return false;
        }
        var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
        var email = $("input[name='email']").val();
        if (email == "") {
            alertMsg.error("请输入邮箱");
            return false;
        } else if (!reg.test(email)) {
            alertMsg.error("请输入正确的邮箱");
            return false;
        }
        var id = $("#id").val();
        if (id != null) {
            if ($("#pass_input").val() == "") {
                alertMsg.error("请输入密码");
                return false;
            }
        }

        var userType = $(".userType").val(userType);
        if (userType == 1 || userType == 5 || userType == 8) {
            var province = $("#userProvince").val();
            var city = $("#userCity").val();
            if (province == "" || city == "" || province == "请选择" || city == "请选择") {
                alertMsg.error("市级住建委用户必须选择管辖范围");
                return false;
            }
            $("input[name='projectIds']").val("");
        } else if (userType == 2 || userType == 6 || userType == 7) {
            var province = $("#userProvince").val();
            var city = $("#userCity").val();
            var district = $("#userDistrict").val();
            if (province == "" || city == "" || district == "" || province == "请选择" || city == "请选择" || district == "请选择") {
                alertMsg.error("区县住建委用户必须选择管辖范围");
                return false;
            }
            $("input[name='projectIds']").val("");
        } else if (userType == 9 || userType == 10 || userType == 11) {
            var province = $("#userProvince").val();
            if (province == "" || province == "请选择") {
                alertMsg.error("省级用户必须选择管辖范围");
                return false;
            }
            $("input[name='projectIds']").val("");
        } else if (userType == 3) {
            var projectIds = $("input[name='projectIds']").val();
            if (projectIds == "") {
                alertMsg.error("站点管理员必须选择管辖工地");
                return false;
            }
        }
        if ($("#pass_input").val() != "") {
            $("#userPassword").val(hex_md5($("#pass_input").val()));
        }

        $("#userForm").submit();
    }

    function changeUserType(value) {
        var userType = value;
        $("#proPtag").hide();
        $("#areaSelect").empty();
        $("#cityPtag").show();

        if (userType == "9" || userType == "10" || userType == "11") {
            $("#areaSelect").ProvinceCity(province, city, district);
            $("#userCity").remove();
            $("#userDistrict").remove();
        } else if (userType == "1" || userType == "5" || userType == "8") {
            $("#areaSelect").ProvinceCity(province, city, district);
            $("#userDistrict").remove();
        } else if (userType == "2" || userType == "6" || userType == "7") {
            $("#areaSelect").ProvinceCity(province, city, district);
        } else if (userType == "3") {
            $("#proPtag").show();
            $("#cityPtag").hide();
        } else if (userType == "4") {
            $("#cityPtag").hide();
        }

    }
</script>