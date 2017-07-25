<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${ctx }/css/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${ctx }/css/bootstrap/dashboard.css" rel="stylesheet" type="text/css">
<script src="${ctx }/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${ctx }/js/bootstrap/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
	
	function changeLi(obj){
		$("#menuul li").each(function(){
			$(this).attr("class","");
		});
		$(obj).attr("class","active");
	}
	
</script>
<div class="col-sm-3 col-md-2">
	<ul id="menuul" class="nav nav-sidebar" style="margin: 0px 0 0 0;">
	  <li class="active" onclick="changeLi(this);"><a href="${ctx }/user/listByPage.action" target="mainFrame">用户列表<span class="sr-only">(current)</span></a></li>
	  <li onclick="changeLi(this);"><a href="${ctx }/monitoringData/listByPage.action" target="mainFrame">检测数据</a></li>
	  <li onclick="changeLi(this);"><a href="${ctx }/monitoringDevice/listByPage.action" target="mainFrame">设备管理</a></li>
	</ul>
</div>