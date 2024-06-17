<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Project Dashboard</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f4f4;
    }

    header {
      background-color: #333;
      color: #fff;
      padding: 10px;
      text-align: center;
    }

    section {
      margin: 20px;
      padding: 20px;
      background-color: #fff;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h2 {
      color: #333;
    }

    ul {
      list-style: none;
      padding: 0;
    }

    li {
      margin-bottom: 10px;
    }

    a {
      text-decoration: none;
      color: #007BFF;
    }

    button, a {
      background-color: #007BFF !important;
      color: #fff;
      padding: 8px 12px;
      border: none;
      border-radius: 3px;
      cursor: pointer;
    }

    button:hover, a:hover {
      background-color: #0056b3 !important;
    }
  </style>
</head>
<body>
<header>
  <h1>Project Dashboard</h1>
</header>

<section>
  <h2>1. EJB Section</h2>
  <p>Logging in as a user in a batch job</p>
  <button>Run EJB Batch Job</button>
</section>

<section>
  <h2>Custom Tag Library</h2>

  <a href="custom-tag">-Tag Functionality</a>
</section>

<section>
  <h2>3. Security Samples Section</h2>
  <ul>
    <li>2-Step Authentication</li>
    <li>API Key Authentication</li>
    <li>JWT Token Authentication</li>
    <li>User/Pass Authentication</li>
    <li>Google Login Authentication</li>
    <li>Google Login using Authenticator</li>
    <!-- Add other security samples here -->
  </ul>
  <a href="two-step-auth">Test 2-Step Auth</a>
  <a href="api-key-auth">Test API Key</a>
  <a href="jwt-result">Test JWT</a>
  <a href="plain-user-pass-auth">Test User/Pass</a>
  <a href="google-login">Test Google Login</a>
  <a href="google-login-with-authenticator">Test Google Login using Authenticator</a>
  <!-- Add buttons for other security samples -->
</section>

<section>
  <h2>4. Maven Setup and Configuration</h2>
  <button>Maven Setup Instructions</button>
  <button>Configuration XML</button>
</section>

<section>
  <h2>5. Database Setup</h2>
  <p>- PostgreSQL Database Script</p>
  <button>Run Database Script</button>
</section>

<section>
  <button>Featured Documentation</button>
  <button>Tutorials</button>
  <button>Feedback</button>
</section>
</body>
</html>