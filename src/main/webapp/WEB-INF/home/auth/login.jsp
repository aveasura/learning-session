<%--
  Created by IntelliJ IDEA.
  User: aveasura
  Date: 27.12.2024
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>Вход в аккаунт</h1>

<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="username">Введите ваш логин</label>
    <input type="text" id="username" name="username" placeholder="username" required><br/><br/>

    <label for="password">Введите ваш пароль</label>
    <input type="text" id="password" name="password" placeholder="password" required><br/><br/>

    <hr/>
    <button type="submit">Войти</button>
</form>

</body>
</html>
