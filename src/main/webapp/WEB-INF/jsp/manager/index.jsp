<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page session="false"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pf" uri="/localTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理系统</title>

<link href="/static/JUI/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/static/JUI/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/static/JUI/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="/static/JUI/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="/static/JUI/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="/static/JUI/js/speedup.js" type="text/javascript"></script>
<![endif]-->
<script src="/static/JUI/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="/static/JUI/js/jquery.cookie.js" type="text/javascript"></script>
<script src="/static/JUI/js/jquery.validate.js" type="text/javascript"></script>
<script src="/static/JUI/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="/static/JUI/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="/static/JUI/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="/static/JUI/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<link href="/static/JUI/js/scombobox/jquery.scombobox.css" rel="stylesheet" />
<script src="/static/JUI/js/scombobox/missed.js"></script><!-- add this script for IE8 compatibility -->
<script src="/static/JUI/js/scombobox/jquery.scombobox.js"></script>
<script src="/static/JUI/js/scombobox/jquery.easing.min.js"></script>


<!-- 可以用dwz.min.js替换前面全部dwz.*.js (注意：替换是下面dwz.regional.zh.js还需要引入)-->
<script src="/static/JUI/bin/dwz.min.js" type="text/javascript"></script>

<script src="/static/JUI/js/dwz.regional.zh.js" type="text/javascript"></script>

<script src="<pf:resources url="/static/js/common.js"/>" type="text/javascript"></script>

<script src="/static/js/clipboard.min.js"></script>

<link type="text/css" href="/static/css/jquery.toastmessage.css" rel="stylesheet"/>

<script type="text/javascript" src="/static/js/jquery.toastmessage.js"></script>


<script type="text/javascript">
$(function(){
	DWZ.init("/static/JUI/dwz.frag.xml", {
		loginUrl:"${home}/forLogin", //loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		keys: {statusCode:"statusCode", message:"message"}, //【可选】
		ui:{hideMode:'offsets'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"/static/JUI/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

function loadurlBycall(t){
	navTab.openTab("main32","${home}/call/bussiness",{title:"客服业务受理"});
	
}

function ajax_frash_close(json) {
	if (json.statusCode == DWZ.statusCode.ok) {
		DWZ.ajaxDone(json);
		if (json.navTabId) {
			//把指定navTab页面标记为需要“重新载入”。注意navTabId不能是当前navTab页面的
			navTab.reloadFlag(json.navTabId);
		} else {
			//重新载入当前navTab页面
			navTabPageBreak();
		}
		if ("closeCurrent" == json.callbackType) {
			setTimeout(function() {
				navTab.closeCurrentTab();
			}, 100);
		} else if ("forward" == json.callbackType) {
			navTab.reload(json.forwardUrl);
		}
	}
}
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
			<table>
			<tr>
			<td><img src="/static/images/wolfbin.png" alt="" width="100" height="50"/></td>
			<td><img src="/static/images/line_top.jpg" style="margin-left:150px;"/></td>
			<td>&nbsp;&nbsp;&nbsp;当前身份</td>
			</tr>
			</table>
				<ul class="nav">
					<%--<li><a href="https://me.alipay.com/dwzteam" target="_blank">捐赠</a></li>--%>
					<%--<li><a href="changepwd.html" target="dialog" width="600">设置</a></li>--%>
					<%--<li><a href="http://www.cnblogs.com/dwzjs" target="_blank">博客</a></li>--%>
					<%--<li><a href="http://weibo.com/dwzui" target=-0="_blank">微博</a></li>--%>
					<li><span><a href="/forlogin"><img src="/static/images/tuichu.gif" /></a></span></li>
				</ul>
				<%--<!-- <ul class="themeList" id="themeList">--%>
					<%--<li theme="default"><div class="selected">蓝色</div></li>--%>
					<%--<li theme="green"><div>绿色</div></li>--%>
					<%--<li theme="red"><div>红色</div></li>--%>
					<%--<li theme="purple"><div>紫色</div></li>--%>
					<%--<li theme="silver"><div>银色</div></li>--%>
					<%--<li theme="azure"><div>天蓝</div></li>--%>
				<%--</ul> -->--%>
			</div>
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
				<pf:menuTree />
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"> <!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div> <!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div> <!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox" style="overflow-y:auto">
					<div class="page unitBox">
						
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">
							
						</div>
						
					<div style="width:100%;position: absolute;top:0px;right:0" layoutH="0">
							<iframe width="100%" height="100%" class="share_self"  frameborder="0" scrolling="no" src="about:blank"></iframe>
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>
	<div id="footer">Copyright &copy; 2014 <a href="#" target="dialog">后台管理系统</a></div>
</body>
</html>