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
        <form action="UserControllerServlet"method="get">
            <input type="hidden" name="command" value="LOAD"/>
            <input type="submit" value="Add Customer" class="save"><br><br>
        </form>
        <form action="UserControllerServlet"method="get">
            <input type="hidden" name="command" value="RESEARCH"/>
            <select name="researchField" id="researchField">
                <option>Username</option>
                <option>Nome</option>
                <option>Cognome</option>
                <option>Data di nascita</option>

            </select>
            <input type="text" name="key">
            <td><input type="submit" value="Ricerca" class="save"/></td>

        </form>
        <br><br>
        <table>
            <tr>
                <th>Username</th>
                <th>Nome</th>
                <th>Cognome</th>
                <th>Data di nascita</th>
                <th>Action</th>
            </tr>

            <c:forEach var="tempUser" items="${USER_LIST}">
                <c:url var="tempLink" value="UserControllerServlet">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="userId" value="${tempUser.id}"/>
                </c:url>
                <c:url var="deleteLink" value="UserControllerServlet">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="userId" value="${tempUser.id}"/>
                </c:url>
                <c:url var="prenotazioniLink" value="PrenotazioneControllerServlet">
                    <c:param name="command" value="USERLIST"/>
                    <c:param name="userId" value="${tempUser.id}"/>
                </c:url>
                <tr>
                    <td>${tempUser.username}</td>
                    <td>${tempUser.getNome()}</td>
                    <td>${tempUser.getCognome()}</td>
                    <td>${tempUser.getDataDiNascita()}</td>
                    <td>
                        <a href="${tempLink}">Update</a>
                        |
                        <a href="${deleteLink}" onclick="if(!(confirm('sei sicuro di cancellare?'))) return false">Delete</a>
                        |
                        <a href="${prenotazioniLink}">prenotazioni</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
