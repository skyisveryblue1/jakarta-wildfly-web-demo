<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        .form-signin {
            max-width: 330px;
            padding: 1rem;
        }
    </style>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="form-signin w-100 m-auto">
    <form action="plain-user-pass-auth" method="post">
        <h1>User/Pass Login</h1>
        <div class="alert alert-info">
            Please input username <strong>exampleUser</strong> and password <strong>examplePassword</strong>.
        </div>
        <div class="form-floating">
            <input type="text" name="username" class="form-control" id="floatingInput" placeholder="name@example.com" autocomplete="off" required>
            <label for="floatingInput">Username</label>
        </div>
        <div class="form-floating">
            <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password" required>
            <label for="floatingPassword">Password</label>
        </div>

        <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-body-secondary">© 2017–2023</p>
    </form>
</main>

</body>
</html>
