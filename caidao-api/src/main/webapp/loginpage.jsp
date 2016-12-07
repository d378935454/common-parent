<%--
  Created by IntelliJ IDEA.
  User: Mr.bean
  Date: 2016/4/1
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Login</h1>

<div id="login-error">${error}</div>

<form action="../j_spring_security_check" method="post">

  <p>
    <label for="j_username">Username</label> <input id="j_username"
                                                    name="j_username" type="text" />
  </p>

  <p>
    <label for="j_password">Password</label> <input id="j_password"
                                                    name="j_password" type="password" />
  </p>

  <input type="submit" value="Login" />

</form>
</body>
</html>
