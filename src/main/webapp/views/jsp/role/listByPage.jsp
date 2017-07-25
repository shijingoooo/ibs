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
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx}/user/listByPage.action" method="post">
		<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
			name="numPerPage" value="<c:out value="${numPerPage}"></c:out>" />

		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>用户名称：<input type="text" name="userNameForLike"
						value="<c:out value='${userQueryBean.proNameForLike }'/>" /></td>
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
				<self:a code="ibs_user_page_add" width="620" height="450"
					name="新增" title="新增用户" style="add" target="dialog" mask="true"
					rel="newuser" parameter=""></self:a>
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
				<th width="120">用户名称</th>
				<th width="120">用户编号</th>
				<th width="120">所在城市</th>
				<th width="120">用户状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="obj" items="${page.result}" varStatus="index">
				<tr target="tr_form" rel="${obj.id}">
					<td><div>
							<input name="userIds" type="checkbox" value="${obj.id}" />
						</div>
					</td>
					<td>
						<self:a code="ibs_user_page_update" name="${obj.proName}"
							parameter="?userId=${obj.id}" style="icon" target="dialog"
							mask="true" rel="newuser" width="560" height="380"></self:a>
					</td>
					<td>${obj.proCode}</td>
					<td>${obj.city}</td>
					<td>
						<c:choose>
							<c:when test="${obj.isCompleted==0 }">
								下线
							</c:when>
							<c:otherwise>
								在线
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<self:pager page="${page}"></self:pager>
</div>