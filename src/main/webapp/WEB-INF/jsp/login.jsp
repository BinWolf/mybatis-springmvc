<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%--
 --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>系统登录</title>
<link href="/static/DWZ/themes/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
<!-- uploadify -->
<link href="/static/DWZ/plugins/uploadify/css/uploadify.css" rel="stylesheet" media="screen"/>
<link href="/static/DWZ/plugins/Huploadify/Huploadify.css" rel="stylesheet" media="screen"/>
<!-- core - css -->
<link href="/static/DWZ/themes/css/style.css" rel="stylesheet" media="screen"/>
<link href="/static/DWZ/themes/blue/core.css" rel="stylesheet" media="screen"/>
<!-- plug - css -->
<link href="/static/DWZ/plugins/kindeditor_4.1.10/themes/default/default.css" rel="stylesheet" media="screen" />
<link href="/static/DWZ/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet" media="screen" />
<link href="/static/DWZ/plugins/validationEngine/validationEngine.jquery.css" rel="stylesheet" media="screen" />
<link href="/static/DWZ/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet" media="screen" />
<link href="/static/DWZ/themes/css/FA/css/font-awesome.min.css" rel="stylesheet" media="screen" />

<script src="/static/DWZ/js/jquery-1.7.2.min.js"></script>
<script src="/static/DWZ/js/jquery.cookie.js"></script>
<script src="/static/DWZ/js/dwz.core.js"></script>
<script src="/static/DWZ/js/dwz.ui.js"></script>
<script src="/static/DWZ/js/dwz.alertMsg.js"></script>
<script src="/static/DWZ/plugins/icheck/icheck.min.js"></script>

<script src="/static/js/sha256.js"></script>

<style type="text/css">
* {font-family: "Verdana", "Tahoma", "Lucida Grande", "Microsoft YaHei", "Hiragino Sans GB", sans-serif;}
body {
    background: url(/static/images/loginbg_01.jpg) no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
}
a:link {color: #285e8e;}
.main_box {
    position: absolute; top:50%; left:50%; margin-top:-260px; margin-left: -300px; padding: 30px; width:600px; height:460px;
    background: #FAFAFA; background: rgba(255,255,255,0.5); border: 1px #DDD solid;
    border-radius: 5px;
    -webkit-box-shadow: 1px 5px 8px #888888; -moz-box-shadow: 1px 5px 8px #888888; box-shadow: 1px 5px 8px #888888;
}
.main_box .setting {position: absolute; top: 5px; right: 10px; width: 10px; height: 10px;}
.main_box .setting a {color: #FF6600;}
.main_box .setting a:hover {color: #555;}
.login_logo {margin-bottom: 20px;text-align: center;}
.login_logo img {height: 45px;}
.login_msg {text-align: center; font-size: 16px;}
.login_form {padding-top: 20px; font-size: 16px;}
.login_box .form-control {display: inline-block; *display: inline; zoom: 1; width: auto; font-size: 18px;}
.login_box .form-control.x319 {width: 319px;}
.login_box .form-control.x164 {width: 164px;}
.login_box .form-group {margin-bottom: 20px;}
.login_box .form-group label.t {width: 120px; text-align: right; cursor: pointer;}
.login_box .form-group.space {padding-top: 15px; border-top: 1px #FFF dotted;}
.login_box .form-group img {margin-top: 1px; height: 32px; vertical-align: top;}
.login_box .m {cursor: pointer;}
.bottom {text-align: center; font-size: 12px;}
</style>
<script type="text/javascript">
var COOKIE_NAME = 'sys__username';
$(function(){
	DWZ.init("/static/DWZ/loginPage.dwz.frag.xml", {
        loginUrl:"${home}/fologin", //loginTitle:"登录",    // 带loginTitle为弹出登录对话框
        statusCode:{ok:200, error:300, timeout:301}, //【可选】
        pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
        keys: {statusCode:"statusCode", message:"message"}, //【可选】
        ui:{hideMode:'display'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
        debug:false,    // 调试模式 【true|false】
        callback:function(){
            initEnv();
            $("#topTheme").theme({themeBase:"/static/DWZ/themes"}); // themeBase 相对于index页面的主题base路径
        }
    });
	choose_bg();
	if ($.cookie(COOKIE_NAME)){
	    $("#loginName").val($.cookie(COOKIE_NAME));
	    $("#passWord").focus();
	    $("#j_remember").attr('checked', true);
	} else {
		$("#loginName").focus();
	}
	/*$("#captcha_img").click(function(){
		changeCode();
	});*/
	$("#passWord").bind('keyup',function(event){
        if(event.keyCode==13){ 
        	$("#login_ok").trigger("click");
        } 
    });
	$("#login_ok").click(function(){

		if($("#loginName").val()==""){
			alertMsg.error('用户名不能为空!'); 
			return;
		}
		if($("#passWord").val()==""){
			alertMsg.error('密码不能为空!'); 
			return;
		}
		var issubmit = true;
		var i_index  = 0;
		$(this).find('.in').each(function(i){
			if ($.trim($(this).val()).length == 0) {
				$(this).css('border', '1px #ff0000 solid');
				issubmit = false;
				if (i_index == 0)
					i_index  = i;
			}
		});
		if (!issubmit) {
			$(this).find('.in').eq(i_index).focus();
			return false;
		}
		var $remember = $("#j_remember");
		if ($remember.attr('checked')) {
			$.cookie(COOKIE_NAME, $("#loginName").val(), { path: '/', expires: 15 });
		} else {
			$.cookie(COOKIE_NAME, null, { path: '/' });  //删除cookie
		}
		$("#login_ok").attr("disabled", true).val('登陆中..');
		$("#passWord").val(HMAC_SHA256_MAC( $("#loginName").val(), $("#passWord").val()));
		$.ajax({ url: "/doLogin",contentType:"application/x-www-form-urlencoded",type:"post",data: "loginName="+$("#loginName").val()+"&passWord="+$("#passWord").val(), success: function(data){
			   if(data){
				   if(data=="0") {
					   window.location.href = '/page/manager/index';
					   return;
				   }
				   else if(data=="1")  alertMsg.error('密码错误!');
				   else if(data=="2")  alertMsg.error('该用户不存在!');
				   else if(data=="3")  alertMsg.error('用户名、密码填写不完整!');
				   $("#login_ok").attr("disabled", false).val('登陆');
				   $("#passWord").val("");
			   }
	      }}); 
		
        return false;
	});
});
function genTimestamp(){
	var time = new Date();
	return time.getTime();
}
function changeCode(){
	//$("#captcha_img").attr("src", "/captcha.jpeg?t="+genTimestamp());
}
function choose_bg() {
	var bg = Math.floor(Math.random() * 9 + 1);
	$('body').css('background-image', 'url(/static/images/loginbg_0'+ bg +'.jpg)');
}
</script>
</head>
<body>
<!--[if lte IE 7]>
<style type="text/css">
#errorie {position: fixed; top: 0; z-index: 100000; height: 30px; background: #FCF8E3;}
#errorie div {width: 900px; margin: 0 auto; line-height: 30px; color: orange; font-size: 14px; text-align: center;}
#errorie div a {color: #459f79;font-size: 14px;}
#errorie div a:hover {text-decoration: underline;}
</style>
<div id="errorie"><div>您还在使用老掉牙的IE，请升级您的浏览器到 IE8以上版本 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div></div>
<![endif]-->
<div class="main_box">
    <div class="setting"><a href="javascript:;" onclick="choose_bg();" title="更换背景"><span class="glyphicon glyphicon-th-large"></span></a></div>
	<div class="login_box">
        <div class="login_logo">
            <%--<img src="/static/images/wolfbin.png" style="width:175px;height:94px;"/>--%>
			<span style="font-size: xx-large; width: 175px; height: 94px;" >放公司logo</span>
        </div>
        <!--
		<c:if test="${!empty message}">
			<div class="login_msg">
	      		<font color="red">${message }</font>
	    	</div>
	    </c:if>
        -->
        <div class="login_form">
            <input type="hidden" value="${randomKey }" id="j_randomKey" />
    		<form action="index.html" id="login_form" method="post">
                <input type="hidden" name="jfinal_token" value="${jfinal_token }" />
    			<div class="form-group">
    				<label for="loginName" class="t">用户名：</label> <input id="loginName" value="" name="loginName" type="text" class="form-control x319 in" style="height:40px;" autocomplete="off">
    			</div>
    			<div class="form-group">
    				<label for="passWord" class="t">密　码：</label> <input id="passWord" value="" name="passWord" type="password" class="form-control x319 in">
    			</div>
    			<div class="form-group">
                    <label class="t"></label>
                    <label for="j_remember" class="m"><input id="j_remember" type="checkbox"  class="j-icheck" value="true">&nbsp;记住登陆账号!</label>
    			</div>
    			<div class="form-group space">
                    <label class="t"></label>　　　
    				<input type="button" id="login_ok" value="&nbsp;登&nbsp;录&nbsp;" class="btn btn-primary btn-lg">&nbsp;&nbsp;&nbsp;&nbsp;
    				<input type="reset" value="&nbsp;重&nbsp;置&nbsp;" class="btn btn-default btn-lg">
    			</div>
    		</form>
        </div>
	</div>
	<div class="bottom">Copyright &copy; 2016 - 2017 <a href="#">wolf后台管理系统</a></div>
</div>
</body>
</html>