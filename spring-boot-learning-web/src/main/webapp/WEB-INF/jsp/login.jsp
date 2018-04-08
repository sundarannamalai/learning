<html>
<head>
    <title>Spring boot web application</title>
</head>
<body>
<h2>This is login page</h2>
<h3>Welcome to Spring boot application!</h3>
<form method="post">
    Name    : <input type="text" id="login-form-username" name="name"/>
   Password : <input type="password" id="login-form-password" name="password" />
    <input type="submit" id="login-form-submit" />
    <label style="color: #ff0000;font-size: 18px;">${error}</label>
</form>
</body>
</html>