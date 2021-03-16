<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>AppParco Auto</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>
<div id="wrapper">
    <div id="header">
        <h2>Parco Auto</h2>
    </div>
</div>



<div id="container">
    <div id="content">

        <table>
            <tr>
                <th>Username</th>
                <th>Targa</th>
                <th>Data inizio</th>
                <th>Data fine</th>
                <th>Approvata</th>
                <th>Action</th>
            </tr>

            <c:forEach var="tempPrenotazione" items="${PRENOTAZIONI_LIST}">
                <c:url var="tempLink" value="PrenotazioneControllerServlet">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="prenotazioneId" value="${tempPrenotazione.id}"/>
                </c:url>
                <c:url var="deleteLink" value="PrenotazioneControllerServlet">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="prenotazioneId" value="${tempPrenotazione.id}"/>
                </c:url>
                <tr>
                    <td>${tempPrenotazione.username}</td>
                    <td>${tempPrenotazione.targa}</td>
                    <td>${tempPrenotazione.data_di_inizio}</td>
                    <td>${tempPrenotazione.data_di_fine}</td>
                    <td>${tempPrenotazione.approvata}</td>
                    <td>
                        <a href="${tempLink}">Update</a>
                        |
                        <a href="${deleteLink}" onclick="if(!(confirm('sei sicuro di cancellare?'))) return false">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
