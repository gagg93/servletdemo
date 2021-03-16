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

        <input type="button" value="Add Auto" onclick="window.location.href='auto-form.jsp'; return false"><br>

        <table>
            <tr>
                <th>Casa Costruttrice</th>
                <th>Modello</th>
                <th>Anno Immatricolazione</th>
                <th>Targa</th>
                <th>Tipologia</th>
                <th>Action</th>
            </tr>
            <c:if test="${admin}">
            <c:forEach var="tempAuto" items="${AUTO_LIST}">
                <c:url var="tempLink" value="AutoControllerServlet">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="autoId" value="${tempAuto.id}"/>
                </c:url>
                <c:url var="deleteLink" value="AutoControllerServlet">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="autoId" value="${tempAuto.id}"/>
                </c:url>

                <tr>
                    <td>${tempAuto.casaCostruttrice}</td>
                    <td>${tempAuto.modello}</td>
                    <td>${tempAuto.annoImmatricolazione}</td>
                    <td>${tempAuto.targa}</td>
                    <td>${tempAuto.tipologia}</td>
                    <td>
                        <a href="${tempLink}">Update</a>
                        |
                        <a href="${deleteLink}" onclick="if(!(confirm('sei sicuro di cancellare?'))) return false">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </c:if>
            <c:if test="${!admin}">
                <c:forEach var="tempAuto" items="${AUTO_LIST}">
                    <c:url var="tempLink" value="PrenotazioneControllerServlet">
                        <c:param name="command" value="LOADADD"/>
                        <c:param name="autoId" value="${tempAuto.id}"/>
                    </c:url>

                    <tr>
                        <td>${tempAuto.casaCostruttrice}</td>
                        <td>${tempAuto.modello}</td>
                        <td>${tempAuto.annoImmatricolazione}</td>
                        <td>${tempAuto.targa}</td>
                        <td>${tempAuto.tipologia}</td>
                        <td>
                            <a href="${tempLink}">prenota</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
</div>
</body>
</html>
