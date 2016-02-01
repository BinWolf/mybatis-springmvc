<%--
  Created by IntelliJ IDEA.
  User: wolf
  Date: 15/11/23
  Time: 18:01
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>login</title>
    <script src="/static/js/jquery-1.8.2.max.js"></script>
    <script src="/static/js/sha256.js"></script>
    <script src="/static/js/login.js"></script>
</head>
<body>
    <form action="/doLogin" id="loginForm" method="post">
        <label for="loginName">LoginName</label><input id="loginName" name="loginName" />
        <label for="passwd">Password</label><input id="passwd" name="passwd" />
        <button id="okBtn">登录</button>
    </form>
</body>
</html>
