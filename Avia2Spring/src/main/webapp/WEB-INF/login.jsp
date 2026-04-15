<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
    <head>
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <title>Enter</title>
    </head>
    <body>
        <h2>Auth</h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="text" name="login">
            <input type="password" name="password">
            <button type="submit">Login</button>
        </form>

        <% if(request.getParameter("error") != null) { %>
            <p style="color:red;">Wrong login info</p>
        <% } %>

        <button onclick="window.location.href='${pageContext.request.contextPath}/register'">Register</button>

    </body>
</html>