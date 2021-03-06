﻿/**
 * jQuery :  城市联动插件
 * @author   XiaoDong <cssrain@gmail.com>
 *			 http://www.cssrain.cn
 * @example  $("#test").ProvinceCity();
 * @params   暂无
 */
$.fn.ProvinceCity = function(province_,city1_,city2_){
	var _self = this;
	//定义3个默认值
	if(province_)
		_self.data("province",[province_, province_]);
	else
		_self.data("province",["请选择", "请选择"]);
	if(city1_)
		_self.data("city1",[city1_, city1_]);
    else
		_self.data("city1",["请选择", "请选择"]);
	if(city2_)
	    _self.data("city2",[city2_, city2_]);
	else
		_self.data("city2",["请选择", "请选择"]);
	//插入3个空的下拉框
	_self.append('<select name="entity.province" id="province" style="margin-left: 25px;"></select>&nbsp;');
	_self.append('&nbsp;<select name="entity.city" id="city" style="margin-left: 10px;"></select>');
	//_self.append('<select name="county" id="county"></select> 区');
	_self.append('<select name="county" id="county" style="display:none;"></select>');
	//分别获取3个下拉框
	var $sel1 = _self.find("select").eq(0);
	var $sel2 = _self.find("select").eq(1);
	var $sel3 = _self.find("select").eq(2);
	//默认省级下拉
	if(_self.data("province")){
		 $sel1.append("<option value='请选择'>请选择</option>");
	}
	$.each( GP , function(index,data){
		if(data == province_){
			$sel1.append("<option value='"+data+"' selected='selected'>"+data+"</option>");
		}else{
			$sel1.append("<option value='"+data+"'>"+data+"</option>");
		}
		   
	});
	
	//默认的1级城市下拉
	if(_self.data("city1")){
		$sel2.append("<option value='"+_self.data("city1")[1]+"'>"+_self.data("city1")[0]+"</option>");
	}
	//默认的2级城市下拉
	if(_self.data("city2")){
		$sel3.append("<option value='"+_self.data("city2")[1]+"'>"+_self.data("city2")[0]+"</option>");
	}
	//省级联动 控制
	
	var index1 = "" ;
	$sel1.change(function(){
		//清空其它2个下拉框
		$sel2[0].options.length=0;
		$sel3[0].options.length=0;
		index1 = this.selectedIndex;
		var index_ = 0;
		if(index1==0&&$("#province").val() == '请选择'){	//当选择的为 “请选择” 时
				$sel2.append("<option value='请选择'>请选择</option>");
				$sel3.append("<option value='请选择'>请选择</option>");
		}else{
			$.each( GT[index1-1] , function(index,data){
				if(data == city1_){
				   $sel2.append("<option value='"+data+"' selected='selected'>"+data+"</option>");
				   index_ = index;
				}
				else{
					 $sel2.append("<option value='"+data+"'>"+data+"</option>");
				}
				  
			});
			$.each( GC[index1-1][index_] , function(index,data){
				if(data == city2_)
				   $sel3.append("<option value='"+data+"' selected='selected'>"+data+"</option>");
				else
				   $sel3.append("<option value='"+data+"' >"+data+"</option>");
			})
		}
	}).change();
	//1级城市联动 控制
	var index2 = "" ;
	$sel2.change(function(){
		if(index1==0)
			return;
		$sel3[0].options.length = 0;
		index2 = this.selectedIndex;
		$.each( GC[index1-1][index2] , function(index,data){
			$sel3.append("<option value='"+data+"'>"+data+"</option>");
		})
	});
	return _self;
};