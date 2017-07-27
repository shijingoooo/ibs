<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta name="renderer" content="ie-stand"/>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		<title>扬尘噪声监控</title>
		<link href="${ctx}/dwz_jui/themes/silver/style.css" rel="stylesheet" type="text/css" media="screen" />
		<link href="${ctx}/dwz_jui/themes/css/core.css" rel="stylesheet" type="text/css" media="screen" />
		<link href="${ctx}/dwz_jui/themes/css/print.css" rel="stylesheet" type="text/css" media="print" />
		<link href="${ctx}/dwz_jui/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen" />
	</head>
	<script src="${ctx}/dwz_jui/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/js/jquery.cookie.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/js/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/js/jquery.bgiframe.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/bin/dwz.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/bin/dwz.regional.zh.js" type="text/javascript"></script>
	<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
	<script type="text/javascript" src="${ctx}/dwz_jui/chart/raphael.js"></script>
	<script type="text/javascript" src="${ctx}/dwz_jui/chart/g.raphael.js"></script>
	<script type="text/javascript" src="${ctx}/dwz_jui/chart/g.bar.js"></script>
	<script type="text/javascript" src="${ctx}/dwz_jui/chart/g.line.js"></script>
	<script type="text/javascript" src="${ctx}/dwz_jui/chart/g.pie.js"></script>
	<script type="text/javascript" src="${ctx}/dwz_jui/chart/g.dot.js"></script>
	<script type="text/javascript" src="${ctx}/js/calendar/WdatePicker.js"></script>
	<script type="text/javascript">
		function checkBrowser()
		{
			var needNotice=false;
			var explorer = window.navigator.userAgent ;
			if (explorer.indexOf("MSIE") >= 0) {
				needNotice=true;
				var b_name=navigator.appName;
				var b_version=navigator.appVersion;
				var version=b_version.split(";");
				version=version[1].replace(/[ ]/g,"");
				version=version.split('MSIE')[1];
				browser='IE:'+version;
				if(version>7){
					needNotice=false;
				}
			}else if (explorer.indexOf("Firefox") >= 0){
				browser='Firefox';
				needNotice=true;
			}else if (explorer.indexOf("Chrome") >= 0){
				browser='Chrome';
				needNotice=true;
			}else if (explorer.indexOf("Opera") >= 0){
				browser='Opera';
				needNotice=true;
			}else if (explorer.indexOf("Safari") >= 0){
				browser='Safari';
				needNotice=true;
			}else if (explorer.indexOf("Trident/7.0") >= 0){
				browser='IE:10.0以上';
				needNotice=false;
			}
			return needNotice;
		}
		
		function changeLogo(style){
			$("#logo").attr("src","${ctx}/dwz_jui/themes/"+style+"/images/logo.png");
			if(style=='purple'){
				$("#treeDiv").attr("style","background:#FFCCFF");
			}
			if(style=='default'){
				$("#treeDiv").attr("style","background:#F8F8FF");
			}
			if(style=='azure'){
				$("#treeDiv").attr("style","background:#E6E6FA");
			}
			if(style=='green'){
				$("#treeDiv").attr("style","background:#ADFF2F");
			}
			if(style=='silver'){
				$("#treeDiv").attr("style","background:#F5F5F5");
			}
		}
	</script>
	<body scroll="no">
		<div id="layout">
			<div id="header">
				<div class="headerNav">
					<img src="${ctx}/dwz_jui/themes/default/images/logo.png" id="logo" />
					<ul class="nav" style="top:38px;">
						<c:choose>
							<c:when test="${sessionScope.usertype==3 }">
								<li><a href="${ctx }/alarm/listByPage.action" target="navTab" rel="ibs_alarm_page">待告警处理（<font color="red">${alarmCount }</font>）</a></li>
							</c:when>
						</c:choose>
						<li>您好！ ${sessionScope.username } 用户</li>
						<li><a href="${ctx }/user/updatePwdPage.action?userId=${sessionScope.userid }" target="dialog" 
								mask="true" rel="newuser" width="560" height="380">修改密码</a></li>
						<li><a href="${ctx}/login/logout.action">退出</a></li>
					</ul>
				</div>
			</div>
			<div id="leftside">
				<div id="sidebar_s">
					<div class="collapse">
						<div class="toggleCollapse">
							<div>123</div>
						</div>
					</div>
				</div>
				<div id="sidebar">
					<div class="toggleCollapse">
						<h2>主菜单</h2>
						<div>收缩</div>
					</div>
					<div class="accordion" fillSpace="sidebar" id="treeDiv"
						style="background:#F8F8FF">
						<c:forEach var="obj" items="${userMenus}">
							<div class="accordionHeader">
								<h2>
									<span>Folder</span>
									<c:out value='${obj.key.menuName}' />
								</h2>
							</div>
							<div class="accordionContent">
								<ul class="tree treeFolder">
									<c:forEach var="subMenu" items="${obj.value}">
										<li><a id="menu_${subMenu.id}" href="<c:out value='${ctx}${subMenu.menuUrl}'/>"
											   target="navTab" rel="<c:out value='${subMenu.menuCode}'/>"><c:out
												value='${subMenu.menuName}' />
										</a>
										</li>

										<%--<c:choose>
											<c:when test="${subMenu.menuCode=='ibs_map_page' and sessionScope.usertype!=4 }">
											
											</c:when>
											<c:otherwise>

											</c:otherwise>
										</c:choose>--%>
									</c:forEach>
								</ul>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div id="container">
				<div id="navTab" class="tabsPage">
					<div class="tabsPageHeader">
						<div class="tabsPageHeaderContent">
							<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
							<ul class="navTab-tab">
								<li tabid="main" class="main"><a href="javascript:;"><span><span
											class="home_icon">首页</span>
									</span>
								</a>
								</li>
							</ul>
						</div>
						<div class="tabsLeft">left</div>
						<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
						<div class="tabsRight">right</div>
						<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
						<div class="tabsMore">more</div>
					</div>
					<ul class="tabsMoreList">
						<li><a href="javascript:;">我的主页</a>
						</li>
					</ul>
					<div class="navTab-panel tabsPageContent layoutBox">
						<div class="page unitBox">
							<c:choose>
								<c:when test="${sessionScope.usertype!=4 }">
									<div id="ibs_map_page">
									</div>
								</c:when>
								<c:otherwise>
									<div class="pageFormContent" layoutH="20" style="background: url('${ctx}/images/home_bg.jpg') no-repeat left top;background-size:cover;filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='${ctx}/images/home_bg.jpg',sizingMethod='scale');">
								
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="footer">Copyright 2015 中国联合网络通信有限公司 <!--a href="${ctx}/soft/ScoPlugin20150909-(V5-1-2).exe" target="_blank">下载控件</a--></div>
	<script type="text/javascript">
		$(function(){
			DWZ.init("${ctx}/dwz_jui/dwz.frag.xml", {
				loginUrl:"/ibs/login/index.action",	// 跳到登录页面
				statusCode:{ok:200, error:300, timeout:301}, //【可选】
				pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
				debug:false,	// 调试模式 【true|false】
				callback:function(){
					initEnv();
					if(checkBrowser()) {
						alertMsg.warn("“欢迎您使用本平台！\n为保证您的正常使用，请确认您使用的浏览器为IE8及以上版本，并安装了<a href='${ctx}/soft/ScoPlugin20150909-(V5-1-2).exe'>视频播放控件</a>！”");
					}else{
						//console.info("浏览器版本为:"+browser);
					}

					<%--<c:if test="${sessionScope.usertype!=4 }">
					$("#ibs_map_page").loadUrl("${ctx}/monitoringProject/listByMap.action?target=navTab&rel=ibs_map_page", "", function () {
						if (navTab.getCurrentPanel()) {
							$("#ibs_map_page", navTab.getCurrentPanel()).find("[layoutH]").layoutH();
						}
					});
					</c:if>--%>
				}
			});

		});
	</script>
	</body>
</html>