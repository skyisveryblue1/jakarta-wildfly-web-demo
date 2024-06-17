<!-- JSP code to generate secret key and display QR code -->
<%@ page import="com.warrenstrange.googleauth.GoogleAuthenticator" %>
<%@ page import="com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.io.UnsupportedEncodingException" %>

<%
    // Generate a new secret key for the user
    GoogleAuthenticator gAuth = new GoogleAuthenticator();
    String secretKey = gAuth.createCredentials().getKey();

    // Generate the QR code URL for the secret key
    String qrCodeURL = GoogleAuthenticatorQRGenerator.getOtpAuthURL("MyApp", "${username}", gAuth.createCredentials());

    // Encode the URL (required for some QR code generators)
    String encodedURL = URLEncoder.encode(qrCodeURL, "UTF-8");
%>

<!-- Display the QR code for the user to scan -->
<html>
<head>
    <title>Google Authenticator 2FA</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Set up Two-Factor Authentication</h1>
    <p>Scan the QR code below or Enter a setup key with Google Authenticator:</p>
    <img src="https://api.qrserver.com/v1/create-qr-code/?data=<%= encodedURL %>&amp;size=300x200" alt="QR Code" style="margin-left: 100px"/>
    <h5>setup key: <%= secretKey %></h5>
    <hr>
    <!-- Form to verify the code -->
    <form action="google-login-with-authenticator/verify" method="post">
        <label for="code">Enter verification code:</label>
        <input type="text" id="code" name="code">
        <input type="submit" value="Verify Code">
        <input type="hidden" name="secretKey" value="<%= secretKey %>">
    </form>
</div>
</body>
</html>