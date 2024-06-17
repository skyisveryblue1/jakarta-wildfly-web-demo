<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Security Information</title>
</head>
<body>
<h1>Security Information</h1>
<p>Welcome, ${userName}!</p>
<c:if test="${isAdmin}">
    <p>You have ADMIN privileges.</p>
</c:if>
</body>
</html>