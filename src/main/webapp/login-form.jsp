<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 11/03/2021
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form action="LogInServlet" method="post">
    Name:<input type="text" name="userName"><br>
    Password:<input type="password" name="password"><br>
    <input type="submit" value="login">
</form>
</body>
</html>
