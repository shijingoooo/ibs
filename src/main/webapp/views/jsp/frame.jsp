<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
		<title>智能工地</title>
		<link href="${ctx}/dwz_jui/themes/silver/style.css" rel="stylesheet" type="text/css" media="screen" />
		<link href="${ctx}/dwz_jui/themes/css/core.css" rel="stylesheet" type="text/css" media="screen" />
		<link href="${ctx}/dwz_jui/themes/css/print.css" rel="stylesheet" type="text/css" media="print" />
		<link href="${ctx}/dwz_jui/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen" />
	</head>
	<script src="${ctx}/dwz_jui/js/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/js/jquery.cookie.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/js/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/js/jquery.bgiframe.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/bin/dwz.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz_jui/bin/dwz.regional.zh.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			DWZ.init("${ctx}/dwz_jui/dwz.frag.xml", {
				loginUrl:"/ibs/login/index.action",	// 跳到登录页面
				statusCode:{ok:200, error:300, timeout:301}, //【可选】
				pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
				debug:false,	// 调试模式 【true|false】
				callback:function(){
					initEnv();
					$("#themeList").theme({themeBase:"${ctx}/dwz_jui/themes"}); // themeBase 相对于index页面的主题base路径
				}
			});
		});
		
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
					<ul class="nav">
						<li><a href="${ctx}/user/updatePwdPage.action"
							mask="true" target="dialog" width="600">修改密码</a>
						</li>
						<li><a href="${ctx}/user/logout.action">退出</a>
						</li>
					</ul>
					<ul class="themeList" id="themeList">
						<li theme="default" onclick="changeLogo('default')"><div
								class="selected">蓝色</div>
						</li>
						<li theme="green" onclick="changeLogo('green')"><div>绿色</div>
						</li>
						<li theme="purple" onclick="changeLogo('purple')"><div>紫色</div>
						</li>
						<li theme="silver" onclick="changeLogo('silver')"><div>银色</div>
						</li>
						<li theme="azure" onclick="changeLogo('azure')"><div>天蓝</div>
						</li>
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
						<c:forEach var="menu" items="${userMenus}">
							<div class="accordionHeader">
								<h2>
									<span>Folder</span>
									<c:out value='${menu.key.menuName}' />
								</h2>
							</div>
							<div class="accordionContent">
								<ul class="tree treeFolder">
									<c:forEach var="subMenu" items="${menu.value}">
										<li><a href="<c:out value='${subMenu.menuAddr}'/>"
											target="navTab" rel="<c:out value='${subMenu.menuCode}'/>"><c:out
													value='${subMenu.menuName}' />
										</a>
										</li>
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
							<div class="pageFormContent" layoutH="20">
								<div class="panel" style="width:33%;float:left" minh="171">
									<h1>系统通知</h1>
									<div>
										<p>内容内容内容</p>
										<p>内容</p>
										<p>内容</p>
										<p>内容</p>
										<p>内容</p>
									</div>
								</div>
								<div class="panel" style="width:33%;float:left" minh="171">
									<h1>监控日志</h1>
									<div>
										
									</div>
								</div>
								<div class="panel" style="width:33%;float:left" minh="171">
									<h1>待审核事项</h1>
									<div>
										
									</div>
								</div>
								<div class="panel" style="width:33%;float:left" minh="171">
									<h1>待处理抓取</h1>
									<div>
										
									</div>
								</div>
								<div class="panel" style="width:33%;float:left" minh="171">
									<h1>待处理同步</h1>
									<div>
									
									</div>
								</div>
								<div class="panel" style="width:33%;float:left" minh="171">
									<h1>待处理表格</h1>
									<div>
										
									</div>
								</div>
								<div class="panel" style="width:99%;float:left" minh="140">
									<h1>事项日志</h1>
									<div>
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="footer">技术支持：</div>
	</body>
</html>
