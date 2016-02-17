<%--
  Created by IntelliJ IDEA.
  User: wolf
  Date: 16/2/16
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
  <form action="/user/saveUser" method="post" >
    <label for="name" >Name:</label> <input id="name" name="userName" />
    <br/>
    <label for="userId" >UserId:</label> <input id="userId" name="userId" />
    <br/>
    <label for="age" >age:</label> <input id="age" name="age" />
    <br/>
    <label for="note" >note:</label> <input id="note" name="note" />
    <br/>
    <label for="id" >id:</label> <input id="id" name="id" />
    <br/>
    <input type="submit" value="submit">
  </form>
</body>
</html>
