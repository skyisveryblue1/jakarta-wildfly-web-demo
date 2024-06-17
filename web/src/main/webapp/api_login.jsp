<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>API Login</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <%--    <script src="https://apis.google.com/js/platform.js" async defer></script>--%>
    <style>
        .form-signin {
            max-width: 330px;
            padding: 1rem;
        }
    </style>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

    <main class="form-signin w-100 m-auto">
        <form action="api-key-auth" method="post">
            <h1 class="h3 mb-3 fw-normal">API Login</h1>
            <div class="alert alert-info">
                Please input username <strong>exampleUser</strong> and api key <strong>key-b151ba2b1acc</strong>.
            </div>
            <div class="form-floating">
                <input type="text" name="username" class="form-control" id="floatingInput" placeholder="username" required autocomplete="off">
                <label for="floatingInput">username</label>
            </div>
            <div class="form-floating">
                <%--<input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">--%>
                <input type="text" name="api_key" class="form-control" id="floatingPassword" placeholder="your_api_key" required autocomplete="off">
                <label for="floatingPassword">API key</label>
            </div>


            <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
            <p class="mt-5 mb-3 text-body-secondary">© 2017–2023</p>
        </form>
    </main>
</body>
</html>
