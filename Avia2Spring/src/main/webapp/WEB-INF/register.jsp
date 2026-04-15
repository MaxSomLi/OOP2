<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <title>Register</title>
    </head>
    <body>
        <form method="post" action="${pageContext.request.contextPath}/register/addUser">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <p>Name = <input type="text" name="username" required></p>
            <p>Password = <input type="password" name="password" required></p>

            <button type="submit">Add</button>
        </form>
    </body>
</html>