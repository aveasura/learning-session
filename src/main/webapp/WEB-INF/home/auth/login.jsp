<%--
  Created by IntelliJ IDEA.
  User: aveasura
  Date: 25.12.2024
  Time: 1:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>Login page!</h1>

<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="username">enter username:</label>
    <input type="text" id="username" name="name" placeholder="your name here">
    <button type="submit">send</button>
</form>

</body>
</html>
