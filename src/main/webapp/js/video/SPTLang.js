/**
 * jz song
 */
var SPT = {
	Ready : function(args) {
		var Element = this.D(window,args.e);
		var fn = this.D(function(){return false;},args.fn);
		var func = (function() {return fn;})();
		this.AddEvent(Element,"load",func);
	},
	E:function(id){
		return document.getElementById(id);
	},
	D:function(defVar,newVar) {
		defVar = (typeof newVar == "undefined")?defVar:newVar;
		return defVar;
	},
	IsBrowerType:function(browerName) {
		return navigator.userAgent.toLowerCase().indexOf(browerName.toLowerCase())>-1;
	},
	ScrollValue:function(way) {
		var value = 0;
		switch(way.toLowerCase()) {
			case "top" :
				value = document.body.scrollTop | document.documentElement.scrollTop;
				break;
			case "left":
				value = document.body.scrollLeft | document.documentElement.scrollLeft;
				break;
			case "bottom":
				value = document.body.scrollBottom | document.documentElement.scrollBottom;
				break;
			case "right":
				value = document.body.scrollRight | document.documentElement.scrollRight;
			default :
				value = 0;
		}
		return value;
	},
	ParseInt:function(str) {
		var value = parseInt(str);
		return isNaN(value) ? 0 : value;
	},
	ParseFloat:function(str) {
		var value = parseFloat(str);
		return isNaN(value) ? 0.0 : value;
	},
	DivRealHeight:function(div) {
		var style = div.style;
		var realHeight = this.ParseInt(style.height)+
				this.ParseInt(style.paddingBottom)+this.ParseInt(style.paddingTop)+
				this.ParseInt(style.marginBottom)+this.ParseInt(style.marginTop)+
				this.ParseInt(style.borderTopWidth)+this.ParseInt(style.borderBottomWidth);
		return realHeight;
	},
	DivRealWidth:function(div) {
		var style = div.style;
		var realWidth = this.ParseInt(style.width)+
				this.ParseInt(style.paddingLeft)+this.ParseInt(style.paddingRight)+
				this.ParseInt(style.marginLeft)+this.ParseInt(style.marginRight)+
				this.ParseInt(style.borderLeftWidth)+this.ParseInt(style.borderRightWidth);
		return realWidth;
	},
	AddEvent:function(obj,eventName,func) {
		if(!obj || !eventName || !func) {
			return;
		}
		if(document.attachEvent) {
			obj.attachEvent("on"+eventName,func);
		} else if(document.addEventListener) {
			obj.addEventListener(eventName,func,false);
		}
	},
	RemoveEvent:function(obj,eventName,func) {
		if(!obj || !eventName || !func) {
			return;
		}
		if(document.detachEvent) {
			obj.detachEvent("on"+eventName,func);
		} else if(document.removeEventListener) {
			obj.removeEventListener(eventName,func,false);
		}
	},
	
	New:function(object) {
		function F(){};
		F.prototype = object;
		return new F();
	},
	Throw:function(msg) {
		throw new Error(msg);
	}
};
var SPT_ISNOTIE = !(SPT.IsBrowerType("IE") || SPT.IsBrowerType("OPERA"));