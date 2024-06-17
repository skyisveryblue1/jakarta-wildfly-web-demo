<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1>Welcome </h1>
        <h2>${message}</h2>
        <h3>Session timeout set: <%= session.getMaxInactiveInterval() %> seconds</h3>
        <a href="logout" class="btn btn-primary">Logout</a>
    </div>

</body>
</html>
