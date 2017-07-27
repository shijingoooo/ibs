<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$(".delete").click(function(){
		    var dateIds = "";
			$("input[name='userIds']").each(function(index, item) {
				if ($(item).prop("checked")) {
					dateIds += $(item).val()+",";
                }
			});
			var hrefVal = $(this).attr("href");
			if(hrefVal.indexOf("?")>-1){
				hrefVal = hrefVal.substring(0,hrefVal.indexOf("?"));
			}
			$(this).attr("href",hrefVal+"?userIds="+dateIds.substring(0,dateIds.length-1)+"&rel=ibs_user_page");
		});
	});
</script>
<style type="text/css">
	.userName a{
		color: #00f;
		text-decoration: underline;
	}
</style>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx}/user/listByPage.action" method="post">
		<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
			name="numPerPage" value="<c:out value="${numPerPage}"></c:out>" />

		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>用户名称：<input type="text" name="userNameForLike"
						value="<c:out value='${userQueryBean.userNameForLike }'/>" /></td>
					<td>
						<button type="submit">查询</button></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<%--<self:a code="ibs_user_page_add" width="620" height="450"--%>
					<%--name="新增" title="新增用户" style="add" target="dialog" mask="true"--%>
					<%--rel="newuser" parameter=""></self:a>--%>
				<a id="userAdd" class="add" target="dialog" mask="true" width="620" height="450" title="新增用户" rel="newuser" href="${ctx}/user/savePage.action">
					<span>新增</span>
				</a>
			</li>
			<li>
				<a id="userDelete" class="delete" target="ajaxTodo" title="您确定要删除用户信息吗?" href="${ctx}/user/delete.action">
					<span>删除</span>
				</a>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112" rel="user_list">
		<thead>
			<tr>
				<th width="30" align="center">
					<div title="" class="gridCol">
						<input class="checkboxCtrl" type="checkbox" group="userIds">
					</div></th>
				<th width="100">用户名称</th>
				<th width="100">管理设备组数量</th>
				<th width="100">用户类型</th>
				<th width="100">单位</th>
				<th width="100">手机号</th>
				<th width="100">邮箱</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="obj" items="${page.result}" varStatus="index">
				<tr target="tr_form" rel="${obj.id}">
					<td><div>
							<input name="userIds" type="checkbox" value="${obj.id}" />
						</div>
					</td>
					<td class="userName">
						<self:a code="ibs_user_page_update" name="${obj.userName}"
							parameter="?userId=${obj.id}" style="icon" target="dialog"
							mask="true" rel="newuser" width="560" height="380"></self:a>
					</td>
					<td>${obj.companyName}</td>
					<td>
						<c:choose>
							<c:when test="${obj.userType==1}">
								超级管理员
							</c:when>
							<c:when test="${obj.userType==2}">
								普通管理员
							</c:when>
						</c:choose>
					</td>
					<td>${obj.companyName}</td>
					<td>${obj.telephone}</td>
					<td>${obj.email}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<self:pager page="${page}"></self:pager>
</div>