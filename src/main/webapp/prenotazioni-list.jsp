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
        <a href="UserControllerServlet">User</a>
        <a href="AutoControllerServlet">Auto</a>
        <a href="PrenotazioneControllerServlet">Prenotazioni</a>
    </div>
</div>



<div id="container">
    <div id="content">
        <form action="PrenotazioneControllerServlet"method="get">
            <input type="hidden" name="command" value="RESEARCH"/>
            <select name="researchField" id="researchField">
                <option>Username</option>
                <option>Targa</option>
                <option>Data di inizio</option>
                <option>Data di fine</option>

            </select>
            <input type="text" name="key">
            <td><input type="submit" value="Ricerca" class="save"/></td>

        </form>
        <br><br>

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
                <c:if test="${!admin}">
                        <c:url var="tempLink" value="PrenotazioneControllerServlet">
                            <c:param name="command" value="LOAD"/>
                            <c:param name="prenotazioneId" value="${tempPrenotazione.id}"/>
                        </c:url>
                    <c:url var="deleteLink" value="PrenotazioneControllerServlet">
                        <c:param name="command" value="DELETE"/>
                        <c:param name="prenotazioneId" value="${tempPrenotazione.id}"/>
                    </c:url>
                </c:if>
                <c:if test="${admin}">
                    <c:url var="approveLink" value="PrenotazioneControllerServlet">
                        <c:param name="command" value="APPROVE"/>
                        <c:param name="prenotazioneId" value="${tempPrenotazione.id}"/>
                    </c:url>
                    <c:url var="disapproveLink" value="PrenotazioneControllerServlet">
                        <c:param name="command" value="DISAPPROVE"/>
                        <c:param name="prenotazioneId" value="${tempPrenotazione.id}"/>
                    </c:url>
                </c:if>
                <tr>
                    <td>${tempPrenotazione.username}</td>
                    <td>${tempPrenotazione.targa}</td>
                    <td>${tempPrenotazione.dataDiInizio}</td>
                    <td>${tempPrenotazione.dataDiFine}</td>
                    <td>${tempPrenotazione.approvata}</td>
                    <c:if test="${!admin}">
                    <td>
                        <a href="${tempLink}" onclick="">Update</a>
                        |
                        <a href="${deleteLink}" onclick="if(!(confirm('sei sicuro di cancellare?'))) return false">Delete</a>
                    </td>
                    </c:if>
                    <c:if test="${admin}">
                        <td>
                            <a href="${approveLink}" onclick="if(!(confirm('sei sicuro di approvare?'))) return false">Approva</a>
                            |
                            <a href="${disapproveLink}" onclick="if(!(confirm('sei sicuro di disapprovare?'))) return false">Disapprova</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
