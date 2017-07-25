var regexEnum = 
{
	intege:"^-?[1-9]\\d*$",					//整数
	intege1:"^[1-9]\\d*$",					//正整数
	intege2:"^-[1-9]\\d*$",					//负整数
	num:"^([+-]?)\\d*\\.?\\d+$",			//数字
	num1:"^[1-9]\\d*|0$",					//正数（正整数 + 0）
	num2:"^-[1-9]\\d*|0$",					//负数（负整数 + 0）
	decmal:"^([+-]?)\\d*\\.\\d+$",			//浮点数
	decmal1:"^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$",　　	//正浮点数
	decmal2:"^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$",　 //负浮点数
	decmal3:"^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$",　 //浮点数
	decmal4:"^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$",　　 //非负浮点数（正浮点数 + 0）
	decmal5:"^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$",　　//非正浮点数（负浮点数 + 0）

	email:"^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", //邮件
	color:"^[a-fA-F0-9]{6}$",				//颜色
	url:"^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$",	//url
	chinese:"^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$",					//仅中文
	ascii:"^[\\x00-\\xFF]+$",				//仅ACSII字符
	zipcode:"^\\d{6}$",						//邮编
	mobile:"^(13|14|15|17|18)[0-9]{9}$",				//手机
	ip4:"^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$",	//ip地址
	notempty:"^\\S+$",						//非空
	picture:"(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$",	//图片
	rar:"(.*)\\.(rar|zip|7zip|tgz)$",								//压缩文件
	date:"^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$",					//日期
	qq:"^[1-9]*[1-9][0-9]*$",				//QQ号码
	tel:"^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$",	//电话号码的函数(包括验证国内区号,国际区号,分机号)
	username:"^\\w+$",						//用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
	letter:"^[A-Za-z]+$",					//字母
	letter_u:"^[A-Z]+$",					//大写字母
	letter_l:"^[a-z]+$",					//小写字母
	idcard:"^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$"	//身份证
}

var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}; 
function cidInfo(sId){
	
	var iSum=0;
	var info="";
	
	if(!checkProvince(sId)){
		return "你的身份证地区非法";
	}
	
	if(!checkBirthday(sId)){
		return "身份证上的出生日期非法";
	}
	
	if(!checkParity(sId)){
		return "你输入的身份证号非法";
	}
		
	return "";
}

checkProvince = function(obj) {  
    var province = obj.substr(0,2);
    if(aCity[province] == undefined) {  
        return false;
    }  
    return true;  
};  

checkBirthday = function(obj) {  
    var len = obj.length;
    //身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X  
    if(len == '18') {  
        var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;  
        var arr_data = obj.match(re_eighteen);
        var year = arr_data[2];  
        var month = arr_data[3];  
        var day = arr_data[4];  
        var birthday = new Date(year+'/'+month+'/'+day);  
        return verifyBirthday(year,month,day,birthday);  
    }  
    return false;  
};
verifyBirthday = function(year,month,day,birthday) {  
    var now = new Date();  
    var now_year = now.getFullYear();  
    //年月日是否合理  
    if(birthday.getFullYear() == year && (birthday.getMonth() + 1) == month && birthday.getDate() == day) {  
        //判断年份的范围（3岁到100岁之间)  
        var time = now_year - year;
        if(time >= 0 && time <= 130) {  
            return true;  
        }  
        return false;  
    }  
    return false;  
};

checkParity = function(obj) { 
    var len = obj.length;  
    if(len == '18') {  
        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);  
        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');  
        var cardTemp = 0, i, valnum;   
       for(i = 0; i < 17; i ++) {   
           cardTemp += obj.substr(i, 1) * arrInt[i];   
       }   
       valnum = arrCh[cardTemp % 11];
       if (valnum == obj.substr(17, 1)) {  
            return true;  
        }  
        return false;  
    }  
    return false;  
};

function isSelect(sele){
	if(sele=='请选择')
		return false;
	else
		return true;
	
}

function checkDomainName(value)
{
	 //只能输入汉字 大小写英文 数字 and _
	 var a = value.match("[`~!@#$%^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）&mdash;—|{}【】‘；：”“'。，、？]");
	 if(a != null)
	{
	   return false;
	}
	else
	{
	  return true;
	}
}

function isFetionNO(){
	
}

//短时间，形如 (13:04:06)
function isTime(str)
{
	var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
	if (a == null) {return false}
	if (a[1]>24 || a[3]>60 || a[4]>60)
	{
		return false;
	}
	return true;
}

//短日期，形如 (2003-12-05)
function isDate(str)
{
	var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/); 
	if(r==null)return false; 
	var d= new Date(r[1], r[3]-1, r[4]); 
	return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
}

//长时间，形如 (2003-12-05 13:04:06)
function isDateTime(str)
{
	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/; 
	var r = str.match(reg); 
	if(r==null) return false; 
	var d= new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]); 
	return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()==r[7]);
}
/**
校验金额

*/
function doJiaoyanJE(value) //单位是(亿元)时的金额验证:(整数:12-8=4位,小数:6+4=10位)
{

if(value == ''){
	return false;
}
if(isNaN(value))
{
	return false;
}

var intCount = 12;
var floatCount = 2;


var num = value.indexOf(".");
if(num > -1)
{
	var zje = value.substring(0, num);
	var xje = value.substring(num+1);
	
	if(zje.length > intCount)
	{
		return false;
	}
	else if(xje.length > floatCount)
	{
		return false;
	}
}
else
{
	if(value.length > intCount)
	{
		return false;
	}
}

return true;
}

//比较开始时间是否大于当前时间
/**
 *    	format: 格式化 yyyy-MM-dd 也可是 yyyy-MM-dd hh:mm:ss 等
 *   	beginid: 开始时间录入框ID
 *   	endid: 结束时间录入框ID
 *   	beginOrg:  原有开始时间ID
 *  	 endOrg: 原有结束时间ID
 *   	 id ： 验证结果显示标签
 * 
 */
function compareTodayData(format, beginid, endid, beginOrg, endOrg, id){

	//Date.parse(startTime.replace("-","/"))>Date.parse(endTime.replace("-","/"));
	
    var today = new Date().format(format);           
    var start = $("#"+beginid).val(); 
    var end = $("#"+endid).val(); 
    var origstart = $("#"+beginOrg).val(); 
    var origend = $("#"+endOrg).val();

    if(start == "")
    {
    	$("#"+id).attr('className','onError');
     	$("#"+id).html("请输入开始日期！");  
        return false;  
    }
    
    else if(end == "")
    {
    	$("#"+id).attr('className','onError');
     	$("#"+id).html("请输入结束日期！");  
        return false;  
    }
    
    else if(start == origstart && end == origend){
        	 $("#"+id).attr('className','onCorrect');
      	     $("#"+id).html('日期正确')
      	     return true;
        }
        else if(start != "" && start < today) {  

     	    $("#"+id).attr('className','onError');
            $("#"+id).html("开始日期不能小于当期日期！");  
            return false;  
        } else if(end != "" && end < today) {  

         	$("#"+id).attr('className','onError');
         	$("#"+id).html("结束日期不能小于当期日期！");  
            return false;  
        } else if(start != "" && end != "" && start > end) {

      	    $("#"+id).attr('className','onError');  
     	    $("#"+id).html("结束日期必须大于开始日期！");  
     	    return false;
        } else { 

    	    $("#"+id).attr('className','onCorrect');
     	    $("#"+id).html('日期正确') ;
     	    return true;
        }  
}
