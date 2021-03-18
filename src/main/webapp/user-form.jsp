<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 11/03/2021
  Time: 09:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register User</title>
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Parco Auto</h2>
    </div>
</div>

<div id="container">
    <h3>Register User</h3>
    <form action="UserControllerServlet" method="get">
        <input type="hidden" name="command" value="ADD"/>
        <table>
            <tbody>
            <tr>
                <td><label> Username:</label></td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td><label> Password:</label></td>
                <td><input type="text" name="password"/></td>
            </tr>
            <tr>
                <td><label> Nome:</label></td>
                <td><input type="text" name="nome"/></td>
            </tr>
            <tr>
                <td><label> Cognome:</label></td>
                <td><input type="text" name="cognome"/></td>
            </tr>
            <tr>
                <td><label> Data di nascita:</label></td>
                <td><input type="text" name="data"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="save" class="save"/></td>
            </tr>
            </tbody>

        </table>
    </form>
    <div style="clear: both"></div>
    <p>
        <a href="UserControllerServlet">Back to List</a>
    </p>
</div>


</body>
</html>