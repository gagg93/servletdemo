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
    <title>Update Prenotazione</title>
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
    <h3>Update Prenotazione</h3>
    <form action="PrenotazioneControllerServlet" method="get">
        <input type="hidden" name="command" value="UPDATE"/>
        <input type="hidden" name="prenotazioneId" value="${THE_PRENOTAZIONE.id}"/>
        <table>
            <tbody>

                <tr>
                    <td><label> Username:</label></td>
                    <td><input type="text" name="username" value="${THE_PRENOTAZIONE.username}" readonly/> </td>
                </tr>
                <tr>
                    <td><label> Targa:</label></td>
                    <td><input type="text" name="targa" value="${THE_PRENOTAZIONE.targa}" readonly/></td>
                </tr>
                <tr>
                    <td><label> Data di inizio:</label></td>
                    <td><input type="text" name="data_di_inizio" value="${THE_PRENOTAZIONE.data_di_inizio}"/></td>
                </tr>
                <tr>
                    <td><label> Data di fine:</label></td>
                    <td><input type="text" name="data_di_fine" value="${THE_PRENOTAZIONE.data_di_fine}"/></td>
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
<a href="PrenotazioneControllerServlet">Back to List</a>
</p>
</div>


</body>
</html>
