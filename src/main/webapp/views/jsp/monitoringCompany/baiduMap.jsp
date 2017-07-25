<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>地图地位</title>
<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=97b313acf63434af32cbc33d877ee1c6"></script>
<body>
	<div align="center" class="margint10">
		标注地址：<input type="text" id="address" name="address" value="" size="50" maxlength="50" />&nbsp;&nbsp;
		<input type="button" class="button_org" value="查 询" onclick="theAddress()" />
		&nbsp;<input type="button" value="保存" onclick="dosubmit()" class="button_org" />
		<input id="longitude" type="hidden" value=""/>
		<input id="latitude" type="hidden" value=""/>
		<input id="province" type="hidden" value=""/>
		<input id="city" type="hidden" value=""/>
		<input id="district" type="hidden" value=""/>
		<input id="street" type="hidden" value=""/>
	</div>
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	var address = window.dialogArguments;
	 var paramArr = address.split("-");
	if(paramArr.length==3){
		document.getElementById("longitude").value = paramArr[0];
		document.getElementById("latitude").value = paramArr[1];
		document.getElementById("address").value = paramArr[2];
	}else if(paramArr.length==2){
		document.getElementById("longitude").value = paramArr[0];
		document.getElementById("latitude").value = paramArr[1];
	}

//	document.getElementById("address").value = address;
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
// 	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	//单击获取点击的经纬度
	map.addEventListener("click",function(e){
		document.getElementById("longitude").value = e.point.lng;
		document.getElementById("latitude").value = e.point.lat;
		theLocation();
		// 创建地址解析器实例
		var geoc = new BMap.Geocoder();
		geoc.getLocation(e.point, function(rs){
			var addComp = rs.addressComponents;
			if(addComp.province==addComp.city){
				document.getElementById("address").value = addComp.city+addComp.district+addComp.street+addComp.streetNumber;
			}else{
				document.getElementById("address").value = addComp.province+addComp.city+addComp.district+addComp.street+addComp.streetNumber;
			}
			document.getElementById("province").value = addComp.province;
			document.getElementById("city").value = addComp.city;
			document.getElementById("district").value = addComp.district;
			document.getElementById("street").value = addComp.street+addComp.streetNumber;
		});

	});
	theLocation();

	function theAddress(){
		var address = document.getElementById("address").value;
		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		// 将地址解析结果显示在地图上,并调整地图视野
		myGeo.getPoint(address, function(point){
			if (point) {
				map.clearOverlays();
				map.centerAndZoom(point, 16);
				map.addOverlay(new BMap.Marker(point));
				document.getElementById("longitude").value = point.lng;
				document.getElementById("latitude").value = point.lat;
				// 创建地址解析器实例
				var geoc = new BMap.Geocoder();
				geoc.getLocation(point, function(rs){
					var addComp = rs.addressComponents;
					if(addComp.province==addComp.city){
						document.getElementById("address").value = addComp.city+addComp.district+addComp.street+addComp.streetNumber;
					}else{
						document.getElementById("address").value = addComp.province+addComp.city+addComp.district+addComp.street+addComp.streetNumber;
					}
					document.getElementById("province").value = addComp.province;
					document.getElementById("city").value = addComp.city;
					document.getElementById("district").value = addComp.district;
					document.getElementById("street").value = addComp.street+addComp.streetNumber;
				});
			}else{
				alert("您选择地址没有解析到结果!");
			}
		}, "北京市");
	}

	// 用经纬度设置地图中心点
	function theLocation(){
		if(document.getElementById("longitude").value != "" && document.getElementById("latitude").value != ""){
			map.clearOverlays();
			var new_point = new BMap.Point(document.getElementById("longitude").value,document.getElementById("latitude").value);
			var marker = new BMap.Marker(new_point);  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
			map.panTo(new_point);
		}
	}

	function dosubmit(){
		var lng = document.getElementById("longitude").value;
		var lat = document.getElementById("latitude").value;
		var province = document.getElementById("province").value;
		var city = document.getElementById("city").value;
		var district = document.getElementById("district").value;
		var street = document.getElementById("street").value;

		if(lng == "" || lat == "") {
			alert("查询失败,请联系管理员");
		return false;
		}
		//检查当前只能有一个标注点
	    var center= lng+','+lat;
		var markers = lng + ',' + lat;
		var staticmapstr= 'http://api.map.baidu.com/staticimage?center='+center+'&markers='+markers+'&zoom=15&width=200&height=200';
		if(confirm("你确定要生成缩略图吗?")){
			window.returnValue = lat +"^" + lng + "^" + staticmapstr + "^" + province + "^" + city + "^" + district + "^" + street;
		window.close();
		}
	}
</script>