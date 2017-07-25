//----------------------------------------------------------------------
//@files:	首都之窗（2014年改造）-政民互动栏目头部输出js文件
//@brief: 	政务信息栏目公用头部输出
//@author:	Hank
//@date:	2014/10/1
//----------------------------------------------------------------------

document.writeln('<style>body, div, ul, dl, ol, li, dt, dd, h1, h2, h3, h4, h5, h6, span, form, fieldset, table, td, img, input { margin: 0; padding: 0; border: 0; }ul, ol { list-style-type: none; text-transform: capitalize; }img { border: 0; vertical-align: top; }a { color: #333333; text-decoration: none; }a:hover { color: #e71a21; text-decoration: underline; }.clearit { clear: both; height: 0px; *display:inline;}body { background-color: #f0f0f0; }.gotop { position: fixed; width: 58px; cursor: pointer; top: 300px; z-index: 10; display: none; }#header_bg { width: 100%; height: 207px; background: url(/images/top_bg.gif) repeat-x center top;}#header { width: 1002px; height: 165px; margin: 0 auto; background: url(/images/top_bg.gif) repeat-x center top; }#header a { color: #333333; font-size: 12px; }#header a:hover { color: #e71a21; font-size: 12px; }#top { width: 1002px; height: 34px; margin: 0 auto; color: #333333; font-size: 12px; }#top a { color: #333333; font-size: 12px; line-stacking: 18px; cursor: pointer; }#zfjglink { float: left; width: 190px; padding-left: 10px; margin-top:10px; color: #333333; font-size: 12px; }#weatherbox { float: left; width: 640px; padding-left:24px;padding-top:5px;}#Landbox { float: right; width: 110px;padding-top:10px; }#Landbox li { float: left; width: 34px; background: url(/images/arr001.gif) no-repeat 0 center; padding-left: 16px; cursor: pointer; }#header_center { width: 1002px; height: 131px; margin: 0 auto; position: relative; }#logo { width: 217px; height: 67px; position: absolute; left: 14px; top: 33px; }#search { width: 390px; height: 35px; position: absolute; left: 276px; top: 50px; }#search select { width: 74px; height: 35px; font-family: "宋体", "SimSun", Verdana, Geneva, sans-serif; font-size: 14px; line-height:33px; color: #666666; border: 1px #E9E9E9 solid; border-right: none; float: left; }#search .input { float: left; width: 228px; height: 33px; border: 1px #E9E9E9 solid; padding-top: 0; padding-bottom: 0; font-family: "宋体", "SimSun", Verdana, Geneva, sans-serif; font-size: 14px; line-height: 33px; color: #666666; padding:0px 5px; }#version { position: absolute; left: 676px; top: 48px; width: 210px; }#version li { float: left; padding: 0px 11px 0px 10px; background: url(/images/vertical_666.gif) no-repeat right center; }#version .last { background: none; }#version li a { color: #666666; line-height: 18px; }#disabled { position: absolute; left: 897px; top: 48px; width: 100px; }#disabled li { float: left; width: 42px; background: url(/images/dot001.gif) no-repeat left 9px; padding-left: 8px; }#disabled li a { color: #666666; line-height: 18px; }#menu { display: block; width: 100%; background: url(/images/nav_bg.gif) repeat-x center top; }#nav { display: block; width: 1002px; height: 41px; margin: 0 auto; background: url(/images/nav_bg.gif) repeat-x center top; }#nav li { float: left; width: 118px; height: 41px; background: url(/images/nav_vertical.gif) no-repeat right top; padding-right: 2px; }#nav a.mainnav { text-align: center; font-size: 18px; font-family: "微软雅黑", "Microsoft YaHei"; color: #FFFFFF; text-decoration: none; line-height: 41px; padding: 0 20px; display: block; _width: 48px; }#nav a.mainnavcur { text-align: center; font-size: 18px; font-family: "微软雅黑", "Microsoft YaHei"; color: #d02022; text-decoration: none; line-height: 41px; padding: 0 20px; display: block; _width: 48px; background: url(/images/nav_002.gif) repeat-x; }#nav .sdzcsy { width: 150px; }#nav .bjfwn { width: 130px; background: none; padding-right: 0px; }#nav .subnav { background: url(/images/nav_001.gif) repeat-x center -26px; height: 35px; width: 100%; position: absolute; left: 0; display: none; }#nav .subnav p { background: url(/images/nav_001.gif) repeat-x center -26px; width: 1002px; margin: 0 auto; height: 35px; position: relative; }#nav .subnav p a { color: #333333; font-family: "微软雅黑", "SimSun"; font-size: 14px; line-height: 33px;padding:0 2px; }#nav .subnav p a:hover { color: #e71a21; text-decoration: none; }#nav .zmhdsubnav { background: url(/images/nav_001.gif) repeat-x center top; height: 61px; width: 100%; position: absolute; left: 0; }#nav .zmhdsubnav ul { background: url(/images/nav_001.gif) repeat-x center top; width: 1002px; margin: 0 auto; height: 61px; position: relative; }#nav .zmhdsubnav li { float: left; width: auto; padding: 0px 10px; background: none; }#nav .zmhdsubnav li a { color: #000; font-family: "微软雅黑", "Microsoft YaHei"; font-size: 14px; line-height: 61px; padding: 0px; }#nav .zmhdsubnav li a:hover { color: #e71a21; text-decoration: none; }#nav a.wyss_but { position: absolute; right: 0px; top: 4px; width: 188px; height: 52px; display: block; padding: 0px; }</style>');

document.writeln('<script language=\"javascript\" type=\"text/javascript\" src=\"/js/jquery-1.9.1.min.js\"></script>');
document.writeln('<script type=\"text/javascript\" src=\"/js/jquery.scrollTo.min.js\"></script>');
document.writeln('<script type=\"text/javascript\" src=\"/js/jquery.flexslider-min.js\"></script>');
document.writeln('<script language=\"javascript\" type=\"text/javascript\" src=\"/js/zmhdmenu.js\"></script>');
document.writeln('<script type=\"text/javascript\" src=\"/js/koala.min.1.5.js\"></script>');

document.writeln('<div id=\"header_bg\"><div id=\"header\"><div id=\"top\">');
document.writeln('<div id=\"zfjglink\"><a href=\"http://zhengwu.beijing.gov.cn/sld/swld/swsj/t1232150.htm\" title=\"市委\" target=\"_blank\">市委</a> | <a href=\"http://www.bjrd.gov.cn/\" title=\"市人大\" target=\"_blank\">市人大</a> | <a href=\"http://www.beijing.gov.cn/\" title=\"市政府\" target=\"_blank\">市政府</a> | <a href=\"http://www.bjzx.gov.cn/\" title=\"市政协\" target=\"_blank\">市政协</a></div>');

document.writeln('<div id=\"weatherbox\"><iframe width="280" scrolling="no" height="24" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=34&icon=1&num=3"></iframe></div>');

document.writeln('<ul id=\"Landbox\"><li><a href=\"#\" target=\"_blank\" title=\"登录\">登录</a></li><li><a href=\"#\" target=\"_blank\" title=\"注册\">注册</a></li></ul></div>');

document.writeln('<div id=\"header_center\"><h1 id=\"logo\"><img src=\"/images/logo_zmhd.jpg\" width=\"217\" height=\"67\"alt=\"政民互动\" title=\"政民互动\"/></h1>');

document.writeln('<form id=\"search\"><select><option>信息1</option><option>信息2</option><option>信息3</option></select><input type=\"text\" class=\"input\" value=\"大家正在搜索:冬季供暖\" ><input type=\"image\" src=\"/images/but001.gif\" title=\"点击搜索\" alt=\"点击搜索\" class=\"input_img\"></form>');

document.writeln('<div id=\"version\"><ul>');
document.writeln('<li><a id=\"yx_style_nav\" target=\"_blank\" href=\"http://wza.beijing.gov.cn/\" title=\"无障碍\">无障碍</a></li>');
document.writeln('<li><a href=\"http://210.75.193.158/gate/big5/www.beijing.gov.cn\" title=\"繁体版\" target=\"_blank\">繁体</a></li>');
document.writeln('<li><a href=\"http://www.beijing.gov.cn\" title=\"简体版\" target=\"_blank\">简体</a></li>');
document.writeln('<li class=\"last\"><a href=\"http://www.ebeijing.gov.cn\" title=\"English Version\" target=\"_blank\">English</a></li></ul><ul>');
document.writeln('<li><a href=\"javascript:setHomePage()\" title=\"设为首页\">设为首页</a></li>');
document.writeln('<li><a href=\"javascript:bookmarkit()\" title=\"加入收藏\">加入收藏</a></li>');
document.writeln('<li class=\"last\"><a href=\"http://m.beijing.gov.cn/\" title=\"移动门户\" target=\"_blank\">移动门户</a></li></ul></div>');

document.writeln('<ul id=\"disabled\">');
document.writeln('<li><a href=\"#\" title=\"婴幼儿\" target=\"_blank\">婴幼儿</a></li>');
document.writeln('<li><a href=\"#\" title=\"老年人\" target=\"_blank\">老年人</a></li>');
document.writeln('<li><a href=\"#\" title=\"旅游者\" target=\"_blank\">旅游者</a></li>');
document.writeln('<li><a href=\"#\" title=\"残疾人\" target=\"_blank\">残疾人</a></li></ul></div></div>');

document.writeln('<div id=\"menu\"><ul id=\"nav\"><li class=\"sdzcsy\"><a href=\"http://www.beijing.gov.cn/\" title=\"首都之窗首页\" target=\"_blank\" class=\"mainnav\">首都之窗首页</a></li>');

document.writeln('<li><a href=\"http://zwxx.beijing.gov.cn\" title=\"政务信息\" target=\"_blank\" class=\"mainnav\">政务信息</a>');
document.writeln('<div class=\"subnav\"><p>');
document.writeln('<a href=\"http://210.73.66.216/library/5/417274/index.html\" title=\"市领导\" target=\"_blank\">市领导</a> | ');
document.writeln('<a href=\"http://zfxxgk.beijing.gov.cn/fgdyna.prhome.prCategpry.do?cate=24\" title=\"机构\" target=\"_blank\">机构</a> | ');
document.writeln('<a href=\"http://210.73.66.216/library/1/412936/index.html\" title=\"职权\" target=\"_blank\">职权</a> | ');
document.writeln('<a href=\"http://210.73.66.216/library/24/419478/index.html\" title=\"会议\" target=\"_blank\">会议</a> | ');
document.writeln('<a href=\"http://210.73.66.216/library/33/436054/index.html\" title=\"政策\" target=\"_blank\">政策</a> | ');
document.writeln('<a href=\"http://zwxx.beijing.gov.cn/rs/\" title=\"人事\" target=\"_blank\">人事</a> | ');
document.writeln('<a href=\"http://zwxx.beijing.gov.cn/cz/czys/2014n/\" title=\"财政\" target=\"_blank\">财政</a> | ');
document.writeln('<a href=\"#\" title=\"计划规划\" target=\"_blank\">计划规划</a> | ');
document.writeln('<a href=\"#\" title=\"统计\" target=\"_blank\">统计</a> | ');
document.writeln('<a href=\"http://zwxx.beijing.gov.cn/tzts/\" title=\"通知提示\" target=\"_blank\">通知提示</a></p></div></li>');

document.writeln('<li id=\"zmhd\"><a href=\"http://hudong.beijing.gov.cn\" title=\"政民互动\" target=\"_blank\" class=\"mainnavcur\">政民互动</a>');
document.writeln('<div class=\"subnav\" style=\"display:block;\"><p>');
document.writeln('<a href=\"http://szxx.beijing.gov.cn/webmayorbox/\" target=\"_blank\">市长信箱</a> | ');
document.writeln('<a href=\"http://jianyi.beijing.gov.cn\" target=\"_blank\">人民建议征集</a> | ');
document.writeln('<a href=\"http://211.147.145.77/\" target=\"_blank\">法规文件意见征集</a> | ');
document.writeln('<a href=\"http://rexian.beijing.gov.cn/\" target=\"_blank\" title=\"政风行风热线\">政风行风热线</a> | ');
document.writeln('<a href=\"http://jy.beijing.gov.cn/\" target="_blank\" title=\"京友网\">京友网</a>');
document.writeln('<!--<a href=\"#\" target=\"_blank\" class=\"wyss_but\"><img src=\"/images/but-wyss.gif\" width=\"188\" height=\"52\" alt=\"我要说说\" title=\"我要说说\"/></a>-->');
document.writeln('</p></div></li>');

document.writeln('<li><a href=\"http://eservice.beijing.gov.cn\" title=\"办事服务\" target=\"_blank\" class=\"mainnav\">办事服务</a>');
document.writeln('<div class=\"subnav\"><p>');
document.writeln('<a href=\"http://eservice.beijing.gov.cn/\" target=\"_blank\" title=\"个人服务\">个人服务</a> | ');
document.writeln('<a href=\"http://eservice.beijing.gov.cn/index_88.htm\" target=\"_blank\" title=\"企业服务\">企业服务</a></p></div></li>');

document.writeln('<li><a href=\"http://www.beijing.cn/\" title=\"便民服务\" target=\"_blank\" class=\"mainnav\">便民服务</a>');
document.writeln('<div class=\"subnav\"><p>');
document.writeln('<a href=\"http://bjjs.beijing.cn/\" target=\"_blank\" title=\"北京介绍\">北京介绍</a> | ');
document.writeln('<a href=\"http://ly.beijing.cn/\" target=\"_blank\"  title=\"旅游住宿\">旅游住宿</a> | ');
document.writeln('<a href=\"http://sqjt.beijing.cn/\" target=\"_blank\"  title=\"社区家庭\">社区家庭</a> | ');
document.writeln('<a href=\"http://yljk.beijing.cn/\" target=\"_blank\"  title=\"医疗健康\">医疗健康</a> | ');
document.writeln('<a href=\"http://cyxf.beijing.cn/\" target=\"_blank\"  title=\"餐饮消费\">餐饮消费</a> | ');
document.writeln('<a href=\"http://ggaq.beijing.cn/\" target=\"_blank\"  title=\"公共安全\">公共安全</a> | ');
document.writeln('<a href=\"http://wtyl.beijing.cn/\" target=\"_blank\"  title=\"文体娱乐\">文体娱乐</a> | ');
document.writeln('<a href=\"http://jypx.beijing.cn/\" target=\"_blank\"  title=\"教育培训\">教育培训</a> | ');
document.writeln('<a href=\"http://ldjy.beijing.cn/\" target=\"_blank\"  title=\"劳动就业\">劳动就业</a> | ');
document.writeln('<a href=\"http://shbz.beijing.cn/\" target=\"_blank\"  title=\"社会保障\">社会保障</a> | ');
document.writeln('<a href=\"http://jtcx.beijing.cn/\" target=\"_blank\"  title=\"交通出行\">交通出行</a> | ');
document.writeln('<a href=\"http://ditu.beijing.cn/\" target=\"_blank\"  title=\"电子地图\">电子地图</a></p></div></li>');

document.writeln('<li><a href=\"http://www.beijing.gov.cn/rwbj/\" title=\"人文北京\" target=\"_blank\" class=\"mainnav\">人文北京</a>');
document.writeln('<div class=\"subnav\"><p>');
document.writeln('<a title=\"北京概览\" href=\"http://www.beijing.gov.cn/rwbj/bjgm/default.htm\" target=\"_blank\">北京概览</a> | ');
document.writeln('<a title=\"文化快报\" href=\"http://www.beijing.gov.cn/rwbj/rwbjsy/whkb/default.htm\" target=\"_blank\">文化快报</a> | ');
document.writeln('<a title=\"文明城市\" href=\"http://www.beijing.gov.cn/rwbj/rwbjsy/wm/default.htm\" target=\"_blank\">文明城市</a> | ');
document.writeln('<a title=\"历史名城\" href=\"http://www.beijing.gov.cn/rwbj/lsmc/default.htm\" target=\"_blank\">历史名城</a> | ');
document.writeln('<a title=\"数字图书生活\" href=\"http://www.beijing.gov.cn/zhuanti/rwbj/sztssh/default.htm\" target=\"_blank\">数字图书生活</a> | ');
document.writeln('<a title=\"北京年鉴\" href=\"http://www.bjdfz.gov.cn/search/searchIndex2.jsp\" target=\"_blank\">北京年鉴</a> | ');
document.writeln('<a title=\"图说北京\" href=\"http://photo.beijing.gov.cn/\" target=\"_blank\">图说北京</a> | ');
document.writeln('<a title=\"电子杂志\" href=\"http://www.beijing.gov.cn/rwbj/rwbjsy/fybj/default.htm\" target=\"_blank\">电子杂志</a> | ');
document.writeln('<a title=\"北京数字博物馆\" href=\"http://www.beijingmuseum.gov.cn/\" target=\"_blank\">北京数字博物馆</a> | ');
document.writeln('<a title=\"专题\" href=\"http://www.beijing.gov.cn/rwbj/rwbjsy/zt/default.htm\" target=\"_blank\">专题</a></p></div></li>');

document.writeln('<li><a href=\"http://www.mybj.gov.cn\" title=\"市民主页\" target=\"_blank\" class=\"mainnav\">市民主页</a>');
document.writeln('<div class=\"subnav\"><p>');
document.writeln('<a href=\"http://www.mybj.gov.cn/bj/jt/index.html\" title=\"交通\" target=\"_blank\">交通</a> | ');
document.writeln('<a href=\"http://www.mybj.gov.cn/bj/jiaoyu/index.html\" title=\"教育\" target=\"_blank\">教育</a> | ');
document.writeln('<a href=\"http://www.mybj.gov.cn/bj/yl/index.html\" title=\"医疗\" target=\"_blank\">医疗</a> | ');
document.writeln('<a href=\"http://www.mybj.gov.cn/bj/sh/index.html\" title=\"生活\" target=\"_blank\">生活</a> | ');
document.writeln('<a href=\"http://www.mybj.gov.cn/bj/zf/index.html\" title=\"住房\" target=\"_blank\">住房</a> | ');
document.writeln('<a href=\"http://www.mybj.gov.cn/bj/sb/index.html\" title=\"社保\" target=\"_blank\">社保</a> | ');
document.writeln('<a href=\"http://www.mybj.gov.cn/bj/wt/index.html\" title=\"娱乐\" target=\"_blank\">娱乐</a> | ');
document.writeln('<a href=\"http://www.mybj.gov.cn/bj/ly/index.html\" title=\"旅游\" target=\"_blank\">旅游</a> | ');
document.writeln('<a href=\"http://www.mybj.gov.cn/bj/jy/index.html\" title=\"就业\" target=\"_blank\">就业</a> | ');
document.writeln('<a href=\"http://www.mybj.gov.cn/bj/sw/index.html\" title=\"税务\" target=\"_blank\">税务</a> | ');
document.writeln('<a href=\"http://www.mybj.gov.cn/bj/ga/index.html\" title=\"政府办事\" target=\"_blank\">政府办事</a></p></div></li>');

document.writeln('<li class=\"bjfwn\"><a href=\"http://www.beijing.gov.cn/bjfwn/\" title=\"北京服务您\" target=\"_blank\" class=\"mainnav\">北京服务您</a></li></ul></div><!--菜单结束 --></div>');