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
    <title>Register Auto</title>
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
    <h3>Register Auto</h3>
    <form action="AutoControllerServlet" method="get">
        <input type="hidden" name="command" value="ADD"/>
        <table>
            <tbody>
            <tr>
                <td><label> Casa di costruzione:</label></td>
                <td><input type="text" name="casaCostruttrice"/></td>
            </tr>
            <tr>
                <td><label> Modello:</label></td>
                <td><input type="text" name="modello"/></td>
            </tr>
            <tr>
                <td><label> Anno di Immatricolazione:</label></td>
                <td><input type="text" name="annoImmatricolazione"/></td>
            </tr>
            <tr>
                <td><label> Targa:</label></td>
                <td><input type="text" name="targa"/></td>
            </tr>
            <tr>
                <td><label>Tipologia:</label></td>
                <td><input type="text" name="tipologia"/></td>
            </tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" value="save" class="save"/></td>
            </tr>
            </tbody>

        </table>
    </form>
    <div style="clear: both"></div>
    <p>
        <a href="AutoControllerServlet">Back to List</a>
    </p>
</div>


</body>
</html>