<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--<html>--%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv=”X-UA-Compatible” content=”IE=edge,chrome=1″ />
		<title>扬尘噪声监控</title>
		<%--<link type="text/css" rel="stylesheet" href="${ctx}/css/login_htc.css"/>--%>
<!-- 		<link type="image/x-icon" rel="icon" href="${ctx}/images/favicon.ico"/> -->
		<link type="text/css" rel="stylesheet" href="${ctx}/css/login_new.css">
		<script type="text/javascript" src="${ctx}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/MD5Utils.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#button1").click(function(){
					$("#password").val(hex_md5($("#password_input").val())) ;
					$("#mainForm").submit();		
			    });
			});
            /*$(window).resize(function() {
                /!*var width = $(this).width();
                var height = $(this).height();
                var scale = 1;
                var scaleW = width/1920;
                var scaleH = height/1080;
                scale = scaleW <= scaleH ? scaleW : scaleH;
                //console.log(scale);
                var $body = $("body");
                $body.css("font-size",100*scale);
                console.log($body.css("font-size"));*!/
                var width = $(this).width();
                var $a = $(".forgetPassword");
                var fontSize = 15;
                if(width>800 && width<1500)
                {
                    $a.css("font-size",(fontSize-(1500-width)/40));
                }else if(width >1500)
                    $a.css("font-size",15);
            });*/
			/*document.onkeydown = function(e){
			    var ev = document.all ? window.event : e;
			    if(ev.keyCode==13) {
			    	$("#password").val(hex_md5($("#password_input").val())) ;
			    	$("#mainForm").submit();
			     }
			}*/
			
			/////////////鼠标滑过文本框变色、、、、、、、、、、、开始
			window.onload=function change(){
				var Arrayinput = document.getElementsByTagName("input");
				for(var i=0;i<Arrayinput.length;i++){
				       Arrayinput[i].onmouseover=inputOver;
					   Arrayinput[i].onmouseout=inputOut;
				}
			}
			function inputOver(event){
				var event=event || window.event;
				var source = event.srcElement || event.target;
			///////////////////////////鼠标 onmouseover 时文本框里焦点在此文本框信息后
			    if  (this.createTextRange)  {  
			       var  r  =  this.createTextRange(); 
			       r.moveStart('character',  this.value.length);  
			       r.collapse();
				   if(r.onclick){}else{r.select();}
				}
			///////////////////////////鼠标 onmouseover 时文本框里焦点在此文本框信息后
			 if(this.name=="user"){source.className="user_text2";}
			 if(this.name=="pass"){source.className="pass_text2";}
			 if(this.name=="btn"){source.className="btn2";}
			}
			function inputOut(event){
			 var event=event || window.event;
			 var source = event.srcElement || event.target;
			 if(this.name=="user"){source.className="user_text";}
			 if(this.name=="pass"){source.className="pass_text";}
			 if(this.name=="btn"){source.className="btn1"}
			}
			/////////////鼠标滑过文本框变色、、、、、、、、、、、结束
			
		</script>
		<style type="text/css">
			body,td,th {
				font-family: 宋体;
			}
			.captchas {
			    color: #8d8d8d;
			    float: right;
			    font-family: simsun;
			    font-size: 12px;
			    line-height: 40px;
			    text-align: left;
			    text-decoration: underline;
			    width: 198px;
			}
		</style>
	</head>
	<body id="userlogin_body">
		<br />
		<%--<form id="mainForm" action="${ctx}/login/home.action" method="post">
			<div class="container">
		    	<div class="wrap">
		        	<div class="formDiv clearfix">
		                   <div class="formWrap clearfix">
		                        <div class="conDiv">
		                            <label>用户名：</label>
		                            <input class="user_text" id="user.loginName" type="text" name="userName" tabindex="1" value="">
		                        </div>
		                        <div class="conDiv">
		                            <label>密码：</label>
		                            <input type="password" class="pass_text" id="password_input" tabindex="2" value=""> 
									<input type="hidden" id="password" name="userPassword" />
		                        </div>
		                        <!-- div class="conDiv">
		                            <label>验证码：</label>
		                            <input class="user_text" id="valiCode" type="text" name="valiCode" tabindex="3" value="" style="width:170px;">
		                            <p class="captchas" onclick="document.getElementById('imgCode').src='${ctx}/validationCode?r='+Math.random()">
										<img src="${ctx}/validationCode" id="imgCode" width="85" height="30">
									</p>
		                        </div -->
							   <font color="red">${msg} &nbsp;</font>
		                        <div class="logDiv">
		                            <button type="button" id="button1"></button>
		                        </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</form>--%>
		<div class="login">
			<!-- 标题 -->
			<%--<div class="title">
				<h1>设备云管理系统</h1>
				<p>Equipment cloud management system</p>
			</div>--%>
			<!-- 表单区域 -->
			<form class="login-form" id="mainForm" action="${ctx}/login/home.action" method="post">
				<input type="text" class="user" placeholder="User" id="user.loginName" name="userName" tabindex="1" value="">
				<input type="password" name="password" class="password" placeholder="Password" id="password_input" tabindex="2" value="">
				<input type="hidden" id="password" name="userPassword" />
				<input type="text" name="checked" class="checked" placeholder="请输入验证码">
				<img src="${ctx}/css/img/verify.png">
				<input type="submit" name="submit" class="submit" value="登录" id="button1">
				<input type="button" class="forgetPassword" value="忘记密码？" id="button">
				<%--<a class="forgetPassword" href="">忘记密码?</a>--%>
			</form>
		</div>
	</body>
</html>