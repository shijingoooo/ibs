<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		
	});
	
	function backVal(){
		var id = "";
		$("input[name=companyId]").each(function(i, n){
    		if(n.value!=""&&n.checked){
    			id = n.value;
    		}
    	});
		$.bringBack({"id":id,"companyName":$("input[name=company"+id+"]").val()});
	}
	
</script>
<div id="companySelectBox" layoutH="40">
	<div class="pageHeader">
		<form id="pagerForm" onsubmit="return divSearch(this, 'companySelectBox');"
			action="${ctx}/monitoringCompany/selectlist.action" method="post">
			<input type="hidden" name="comId" value="${id }">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>厂商名称：<input type="text" name="companyNameForLike"
							value="<c:out value='${companyQueryBean.companyNameForLike }'/>" /></td>
						<td>
							<button type="submit">查询</button></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar" width="100%">
			<ul class="toolBar">
				<li>
					<a class="add" href="javascript:backVal();"><span>确定</span></a>
				</li>
			</ul>
		</div>
		<table class="table" width="100%">
			<thead>
				<tr>
					<th width="30" align="center"></th>
					<th width="120">厂商名称</th>
					<th width="80">厂商编号</th>
					<th width="60">所在城市</th>
					<th width="120">联系人</th>
					<th width="100">联系电话</th>
				</tr>
			</thead>
			<tbody id="paginationContent" style="display:none;">
				<c:forEach var="obj" items="${companys}" varStatus="index">
					<tr target="tr_form" rel="${obj.id}">
						<td width="30" align="center">
							<div>
								<c:choose>
									<c:when test="${obj.id==id}">
										<input name="companyId" type="radio" value="${obj.id}" checked="checked" />
									</c:when>
									<c:otherwise>
										<input name="companyId" type="radio" value="${obj.id}" />
									</c:otherwise>
								</c:choose>
							</div>
						</td>
						<td width="120">${obj.companyName}<input name="company${obj.id}" type="hidden"
																 value="${obj.companyName}"/></td>
						<td width="80">${obj.companyCode}</td>
						<td width="60">${obj.city}</td>
						<td width="120">${obj.contacts}</td>
						<td width="100">${obj.telephone}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="pagination"  style="float:right;">
		<script src="${ctx}/js/pagination.js"></script>
	</div>
</div>