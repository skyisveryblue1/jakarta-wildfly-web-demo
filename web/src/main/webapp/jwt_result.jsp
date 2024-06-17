<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="sample.org.test.utils.JWTUtil" %>
<html>
<head>
    <title>Secured Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Welcome to the Secured Page</h1>
    <%-- Check if the user is authenticated using JWT token --%>
    <% String jwtToken = (String) session.getAttribute("jwtToken"); %>
    <h4>JWT Token:  <%= jwtToken %></h4>
    <% if (jwtToken != null && JWTUtil.validateToken(jwtToken)) { %>
        <% String username = JWTUtil.getUsernameFromToken(jwtToken); %>
    <% } else { %>
        <p>Unauthorized access. Please login.</p>
    <% } %>
</div>
</body>
</html>