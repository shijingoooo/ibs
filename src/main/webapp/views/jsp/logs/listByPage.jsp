<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		
	});
</script>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);"
		action="${ctx}/logs/listByPage.action" method="post">
		<input type="hidden" name="pageNum" value="1" /> <input type="hidden"
			name="numPerPage" value="<c:out value="${numPerPage}"></c:out>" />

		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>操作人员：<input type="text" name="userNameForLike"
						value="<c:out value='${logsQueryBean.userNameForLike }'/>" /></td>
					<td>
						<button type="submit">检索</button></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="85" rel="logs_page">
		<thead>
			<tr>
				<th width="120">用户名</th>
				<th width="120">操作对象</th>
				<th width="120">操作</th>
				<th width="120">操作时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="obj" items="${page.result}" varStatus="index">
				<tr target="tr_form" rel="${obj.id}">
					<td>
						<c:choose>
							<c:when test="${obj.user eq null or obj.user.userName eq ''}">
								接口调用
							</c:when>
							<c:otherwise>
								${obj.user.userName}
							</c:otherwise>
						</c:choose>
					</td>
					<td>${obj.object}【${obj.objectCode}】</td>
					<td>${obj.operate }</td>
					<td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<self:pager page="${page}"></self:pager>
</div>