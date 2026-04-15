<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <title>Assign</title>
    </head>

    <body>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Flight Number</th>
                <th>Origin</th>
                <th>Destination</th>
            </tr>

            <c:forEach var="f" items="${flights}">
                <tr>
                    <td>${f.id}</td>
                    <td>${f.flightNumber}</td>
                    <td>${f.origin}</td>
                    <td>${f.destination}</td>
                </tr>
            </c:forEach>
        </table>

        <sec:authorize access="hasAnyRole('1', '2')">
            <form method="post" action="${pageContext.request.contextPath}/assign/deleteFlight">
                <p>ID = <input type="number" name="id" required></p>
                <button type="submit">Remove</button>
            </form>

            <form method="post" action="${pageContext.request.contextPath}/assign/addFlight">
                <p># = <input type="text" name="flightNumber" required></p>
                <p>Origin = <input type="text" name="origin" required></p>
                <p>Destination = <input type="text" name="destination" required></p>
                <button type="submit">Add</button>
            </form>
        </sec:authorize>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Admin</th>
            </tr>

            <c:forEach var="f" items="${crewMembers}">
                <tr>
                    <td>${f.id}</td>
                    <td>${f.name}</td>
                    <td>${f.admin}</td>
                </tr>
            </c:forEach>
        </table>

        <sec:authorize access="hasAnyRole('2')">
            <form method="post" action="${pageContext.request.contextPath}/assign/deleteCrew">
                <p>ID = <input type="number" name="id" required></p>
                <button type="submit">Remove</button>
            </form>
        </sec:authorize>

        <sec:authorize access="hasAnyRole('1', '2')">
            <form method="post" action="${pageContext.request.contextPath}/assign/addCrew">
                <p>Name = <input type="text" name="name" required></p>
                <p>Admin<input type="checkbox" name="is_admin"></p>
                <p>Password = <input type="text" name="password" required></p>
                <button type="submit">Add</button>
            </form>
        </sec:authorize>

        <table border="1">
            <tr>
                <th>Flight ID</th>
                <th>Crew ID</th>
            </tr>

            <c:forEach var="f" items="${flights}">
                <c:if test="${fn:length(f.crewMembers) > 0}">
                    <tr>
                        <td>${f.id}</td>
                        <td>
                            <c:forEach var="crew" items="${f.crewMembers}">
                                ${crew.id}
                            </c:forEach>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>

        <sec:authorize access="hasAnyRole('1', '2')">
            <form method="post" action="${pageContext.request.contextPath}/assign/deleteJoin">
                <p>Flight ID = <input type="number" name="id1" required></p>
                <p>Crew ID = <input type="number" name="id2" required></p>
                <button type="submit">Remove</button>
            </form>

            <form method="post" action="${pageContext.request.contextPath}/assign/addJoin">
                <p>Flight ID = <input type="number" name="id1" required></p>
                <p>Crew ID = <input type="number" name="id2" required></p>
                <button type="submit">Add</button>
            </form>
        </sec:authorize>

        <sec:authorize access="hasAnyRole('2')">
            <jsp:useBean id="users" scope="request" type="java.util.List"/>
            <c:forEach var="f" items="${users}">
                <form method="post" action="${pageContext.request.contextPath}/assign/verify">
                    <h6 name="id">${f.id}</h6>
                    <input type="hidden" name="id" value="${f.id}">
                    <p>${f.name}</p>
                    <p>Admin<input type="checkbox" name="is_admin"></p>
                    <p>Verify<input type="checkbox" name="verify"></p>
                    <button type="submit">*</button>
                </form>
            </c:forEach>
        </sec:authorize>

        <sec:authorize access="hasAnyRole('1', '0')">
            <button onclick="window.location.href='${pageContext.request.contextPath}/flights'">My flights</button>
            <button onclick="window.location.href='${pageContext.request.contextPath}/pass'">Change password</button>
        </sec:authorize>

        <button onclick="window.location.href='${pageContext.request.contextPath}/login'">Log Out</button>

    </body>
</html>