<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
//	$(document).ready(function() {
//		$(".delete").click(function(){
//		    var dateIds = "";
//			$("input[name='userIds']").each(function(index, item) {
//				if ($(item).prop("checked")) {
//					dateIds += $(item).val()+",";
//				}
//			});
//			var hrefVal = $(this).attr("href");
//			if(hrefVal.indexOf("?")>-1){
//				hrefVal = hrefVal.substring(0,hrefVal.indexOf("?"));
//			}
//			$(this).attr("href",hrefVal+"?userIds="+dateIds.substring(0,dateIds.length-1)+"&rel=ibs_user_role_authority_page");
//		});
//	});
</script>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx}/role/listByPageForAuthority.action" method="post">
		<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
			name="numPerPage" value="<c:out value="${numPerPage}"></c:out>" />

		<%--<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>角色名称：<input type="text" name="roleNameForLike"
						value="<c:out value='${roleQueryBean.roleNameForLike }'/>" /></td>
					<td>
						<button type="submit">检索</button></td>
				</tr>
			</table>
		</div>--%>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<%--<li>--%>
				<%--<self:a code="ibs_user_page_add" width="620" height="450"--%>
					<%--name="新增" title="新增用户" style="add" target="dialog" mask="true"--%>
					<%--rel="newuser" parameter=""></self:a>--%>
			<%--</li>--%>
			<%--<li>--%>
				<%--<a id="userDelete" class="delete" target="ajaxTodo" title="您确定要删除用户信息吗?" href="${ctx}/user/delete.action">--%>
					<%--<span>删除</span>--%>
				<%--</a>--%>
			<%--</li>--%>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112" >
		<thead>
			<tr>
                <th width="120">角色名称</th>
				<th width="120">角色描述</th>
				<th width="120">创建时间</th>
				<th width="120">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="obj" items="${page.result}" varStatus="index">
				<tr target="tr_form" rel="${obj.id}">
					<td>${obj.roleName}</td>
					<td>${obj.roleDesc}</td>
					<td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td><a target="dialog" mask="true" title="设置角色权限"
                           href="${ctx}/role/initMenuList.action?roleId=${obj.id}" width="620" height="500">
                        设置角色权限
                    </a>
                        <%--<self:a code="ibs_data_page"
                                name="设置角色权限" target="navTab" parameter="?deviceId=${obj.id }&rel=ibs_device_data_page"></self:a>
--%>
                    </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<self:pager page="${page}"></self:pager>
</div>