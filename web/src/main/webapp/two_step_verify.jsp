<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Verification Code</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        .form-signin {
            max-width: 330px;
            padding: 1rem;
        }
    </style>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">
    <main class="form-signin w-100 m-auto">
        <form action="two-step-auth-verify" method="get">
            <h2>Verification Code:</h2>
            <img src="${pageContext.request.contextPath}/qrcode" alt="">
            <p>Scan the image with the two-factor authenticator app on your phone.</p>
            <div class="form-floating">
                <input type="hidden" name="username" value="${ username }">
                <input type="text" name="verificationCode" class="form-control" id="floatingInput" placeholder="123456" required>
                <label for="floatingInput">Enter the code</label>
            </div>

            <button class="btn btn-primary w-100 py-2" type="submit">Verify</button>
            <p class="mt-5 mb-3 text-body-secondary">© 2017–2023</p>
        </form>
    </main>
</body>
</html>
