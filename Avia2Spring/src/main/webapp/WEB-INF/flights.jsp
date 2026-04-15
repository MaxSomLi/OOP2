<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <title>My flights</title>
    </head>

    <body>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Flight Number</th>
                <th>Origin</th>
                <th>Destination</th>
            </tr>

            <c:forEach var="f" items="${myFlights}">
                <tr>
                    <td>${f.id}</td>
                    <td>${f.flightNumber}</td>
                    <td>${f.origin}</td>
                    <td>${f.destination}</td>
                </tr>
            </c:forEach>
        </table>

        <button onclick="window.location.href='${pageContext.request.contextPath}/assign'">Assign</button>

        <button onclick="window.location.href='${pageContext.request.contextPath}/login'">Log Out</button>

    </body>
</html>