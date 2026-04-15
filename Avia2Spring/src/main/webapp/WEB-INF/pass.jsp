<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <title>Change password</title>
    </head>

    <body>

        <form method="post" action="${pageContext.request.contextPath}/pass/changePass">
            <p>Old password: <input type="password" name="old"></p>
            <p>New password: <input type="password" name="new"></p>
            <p>Confirm password: <input type="password" name="con"></p>
            <button type="submit">CHANGE</button>
        </form>

        <% if(request.getParameter("error") != null) { %>
            <p style="color:red;">Wrong password</p>
        <% } %>

        <button onclick="window.location.href='${pageContext.request.contextPath}/assign'">Assign</button>

        <button onclick="window.location.href='${pageContext.request.contextPath}/login'">Log Out</button>

    </body>
</html>