<%@page import="sample.org.test.googleauth.GoogleAuthHelper"%>
<%@ page import="jakarta.ejb.EJB" %>
<%@ page import="sample.org.test.BatchJobEJB" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.Instant" %>
<%@ page import="jakarta.annotation.Resource" %>
<%@ page import="javax.sql.DataSource" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Google Login</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        body {
            font-family: Sans-Serif;
            margin: 1em;
            padding: 3em;
        }

        .oauthDemo a {
            display: block;
            border-style: solid;
            border-color: #bbb #888 #666 #aaa;
            border-width: 1px 2px 2px 1px;
            background: #ccc;
            color: #333;
            line-height: 2;
            text-align: center;
            text-decoration: none;
            font-weight: 900;
            width: 13em;
        }

        .oauthDemo pre {
            background: #ccc;
        }

        .oauthDemo a:active {
            border-color: #666 #aaa #bbb #888;
            border-width: 2px 1px 1px 2px;
            color: #000;
        }

        .readme {
            padding: .5em;
            background-color: #F9AD81;
            color: #333;
        }
    </style>
</head>
<body>
<div class="oauthDemo">
    <%
        /*
         * The GoogleAuthHelper handles all the heavy lifting, and contains all "secrets"
         * required for constructing a google login url.
         */
        final GoogleAuthHelper helper = new GoogleAuthHelper();

        if (request.getParameter("code") == null
                || request.getParameter("state") == null) {

            /*
             * initial visit to the page
             */
            out.println("<a href='" + helper.buildLoginUrl()
                    + "'>log in with google</a>");

            /*
             * set the secure state token in session to be able to track what we sent to google
             */
            session.setAttribute("state", helper.getStateToken());

        } else if (request.getParameter("code") != null && request.getParameter("state") != null
                && request.getParameter("state").equals(session.getAttribute("state"))) {

            session.removeAttribute("state");

            out.println("<pre>");

            // Store data in the session
            String json = helper.getUserInfoJson(request.getParameter("code"));

            out.println(json);

            out.println("</pre>");
            out.println("<script>" +
                    "$.post('google-login', " + json + ", function(res) {" +
                    "location.replace('home');" +
                    "})" +
                    "</script>");

        }
    %>
</div>
<br />
<div class="readme">
    <h1>Read Me First</h1>

    <h3>Assumptions</h3>

    <ul>
        <li>familiarity with OOP, java, maven, and jee</li>
        <li>java application server listening on localhost:8080</li>
    </ul>

    <h3>Prerequisites</h3>

    <ul>
        <li>Google API access credentials (Client ID, Client Secret).
            Set it up here <a href='https://code.google.com/apis/console/'>https://code.google.com/apis/console/</a>
        </li>
        <li>Set up allowed Redirect URIs at Google API &rarr; API
            Access. Input: http://localhost:8080/test-web/google-login</li>
        <li>a positive outlook on life</li>
    </ul>

    <h3>Usage</h3>

    <ol>
        <li>Add Client ID, and Client Secret parameters to <b>GoogleAuthHelper.java</b></li>
        <li>Compile the project (<b>$ mvn clean install</b>)</li>
        <li>Deploy war to application server</li>
        <li>Browse to: <a href="http://localhost:8080/test-web/google-login">http://localhost:8080/test-web/google-login</a></li>
        <li>Click <b>&quot;log in with google&quot;</b> on top of this page</li>
    </ol>

</div>
</body>
</html>