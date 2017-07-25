<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/MD5Utils.js"></script>
<script type="text/javascript">
	function saveProject(){
		
		if(hex_md5($("#old_pass_input").val())!=$("#userPassword").val()){
			alertMsg.error("原密码不正确");
			return false;
		}
		
		if($("#pass_input").val()!=""){
			if($("#re_pass_input").val()!=$("#pass_input").val()){
				alertMsg.error("新密码不一致");
				return false;
			}
			$("#userPassword").val(hex_md5($("#pass_input").val()));
		}
				
		$("#userForm").submit();
	}
	
</script>
<div class="tabs">
	<div class="tabsHeader">
		<div class="tabsHeaderContent">
			<ul>
				<li class="selected">
					<a href="javascript:;"><span>修改密码</span></a>
				</li>
			</ul>
		</div>
	</div>
	<div class="tabsContent">
		<div id="base" style="display: block;">
			<form id="userForm"
				action="${ctx}/user/updatePwd.action?rel=ibs_user_page&callbackType=closeCurrent"
				method="post" class='required-validate pageForm'
				onsubmit="return validateCallback(this, dialogAjaxDone)">

				<input type="hidden" value="<c:out value='${user.id}'/>" name="id" />
				<input type="hidden" value="<c:out value='${user.version}'/>" name="version" />
				
				<div class="pageFormContent" layoutH="100">
					<p class="nowrap">
						<label style="width: 60px;">用户名称</label>
						<input id="userName" name="userName" value="<c:out value='${user.userName}'/>" type="text"
							size="30" class="required alphanumeric" maxlength="32" style="margin-left: 25px;" disabled="disabled"/>
					</p>
					<p class="nowrap">
						<label style="width: 60px;">原密码</label>
						<input id="old_pass_input" value="" type="password" 
							size="30" class="required" maxlength="32" style="margin-left: 25px;" />
					</p>
					<p class="nowrap">
						<label style="width: 60px;">密码</label>
						<input id="pass_input" value="" type="password" 
							size="30" class="required" maxlength="32" style="margin-left: 25px;" />
						<input type="hidden" id="userPassword" name="userPassword" value="${user.userPassword }"/>
					</p>
					<p class="nowrap">
						<label style="width: 60px;">确认密码</label>
						<input id="re_pass_input" value="" type="password" 
							size="30" class="required" maxlength="32" style="margin-left: 25px;" />
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
